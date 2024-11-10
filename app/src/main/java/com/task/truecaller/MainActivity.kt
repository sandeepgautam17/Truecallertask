package com.task.truecaller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.task.truecaller.di.RepositoryProvider
import com.task.truecaller.task.TaskState
import com.task.truecaller.ui.theme.TruecallerTaskTheme

class MainActivity : ComponentActivity() {

    val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(RepositoryProvider.provideContentRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TruecallerTaskTheme(
                dynamicColor = false
            ) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TaskScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TaskScreen(modifier: Modifier = Modifier) {
    val activity = LocalContext.current as MainActivity
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(
                top = 16.dp,
                bottom = 16.dp
            )
    ) {
        ListAllTasks()
        ElevatedButton(
            onClick = {
                activity.viewModel.loadContentAndProcessTasks()
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp)
        ) {
            Text(
                text = LocalContext.current.getString(R.string.life_at_truecaller),
                fontSize = 18.sp
            )
        }

    }
}

@Composable
fun ListAllTasks(){
    val activity = LocalContext.current as MainActivity
    var openState by remember { mutableIntStateOf(1) }
    val task1State by activity.viewModel.task1State
    val task2State by activity.viewModel.task2State
    val task3State by activity.viewModel.task3State
    Column{
        TaskView(task1State,1,openState == 1){
            openState = if (it == openState) 0 else it
        }
        Spacer(Modifier.height(16.dp))
        TaskView(task2State,2,openState == 2){
            openState = if (it == openState) 0 else it
        }
        Spacer(Modifier.height(16.dp))
        TaskView(task3State,3,openState == 3){
            openState = if (it == openState) 0 else it
        }
    }
}

@Composable
fun TaskView(
    taskState: TaskState,
    taskNumber: Int,
    openState: Boolean,
    selectedTask: (task: Int) -> Unit
) {
    when (taskState) {
        is TaskState.Idle -> {}
        is TaskState.Loading -> {
            LoadingView(
                taskNumber
            )
        }
        is TaskState.Error -> {
            ContentView(
                taskState.message,
                openState,
                taskNumber
            ) {
                selectedTask(it)
            }
        }
        is TaskState.Success -> {
            ContentView(
                taskState.data,
                openState,
                taskNumber
            ) {
                selectedTask(it)
            }
        }
    }
}

@Composable
fun LoadingView(
    taskNumber: Int
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .border(
                width = 1.dp, // Thicker border if selected
                color = colorScheme.secondary,
            )
    ) {
        Text(
            modifier = Modifier
                .align(alignment = Alignment.CenterStart)
                .padding(start = 16.dp, end = 16.dp),
            text = LocalContext.current.getString(R.string.loading_task, taskNumber),
            fontSize = 18.sp,

            )
    }
}

@Composable
fun ContentView(
    content: String,
    openState: Boolean,
    taskNumber: Int,
    selectedTask: (task: Int) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    // Remember the scroll state
    val scrollState = rememberScrollState()
    Column {
        Row(
            modifier = Modifier
                .clickable { selectedTask(taskNumber) }
                .fillMaxWidth()
                .height(56.dp)
                .border(
                    width = if (openState) 3.dp else 1.dp, // Thicker border if selected
                    color = if (openState) colorScheme.primary else colorScheme.secondary,
                )
        ) {
            Text(
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .padding(start = 16.dp, end = 16.dp)
                    .weight(1f),
                text = LocalContext.current.getString(R.string.task_content, taskNumber),
                fontSize = 18.sp,

            )
            Image(
                painter = painterResource(
                    id = if (openState) R.drawable.ic_up_chevron else R.drawable.ic_down_chevron
                ),
                modifier = Modifier
                    .align(alignment = Alignment.CenterVertically)
                    .size(48.dp)
                    .padding(end = 16.dp),
                contentDescription = null,
            )
        }
        if(openState) {
            Box(
                modifier = Modifier
                    .heightIn(
                        min = 72.dp,
                        max = getScreenHeightFraction(0.6f)
                    ) // Minimum and maximum height
            ) {
                Text(
                    modifier = Modifier
                        .verticalScroll(scrollState)
                        .padding(16.dp),
                    text = content,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun getScreenHeightFraction(fraction: Float): Dp {
    val configuration = LocalContext.current.resources.configuration
    val screenHeightInDp = (configuration.screenHeightDp).dp
    return screenHeightInDp * fraction
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TruecallerTaskTheme {
        TaskScreen()
    }
}