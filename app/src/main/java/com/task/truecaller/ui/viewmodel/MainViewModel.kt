package com.task.truecaller.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.truecaller.network.NetworkResult
import com.task.truecaller.repository.ContentRepository
import com.task.truecaller.task.TaskState
import com.task.truecaller.utils.find15thCharacter
import com.task.truecaller.utils.findEvery15thCharacter
import com.task.truecaller.utils.requestWordCounter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel (private val contentRepository: ContentRepository) : ViewModel() {

    val TAG = "MainViewModel"
    private val _task1State = mutableStateOf<TaskState>(TaskState.Idle)
    val task1State: MutableState<TaskState> = _task1State

    private val _task2State = mutableStateOf<TaskState>(TaskState.Idle)
    val task2State: MutableState<TaskState> = _task2State

    private val _task3State = mutableStateOf<TaskState>(TaskState.Idle)
    val task3State: MutableState<TaskState> = _task3State

    fun loadContentAndProcessTasks() {
        val exceptionHandler = CoroutineExceptionHandler { _, exception ->
            setState(TaskState.Error("Error loading content"))
            Log.e(TAG, "CoroutineExceptionHandler got $exception")
        }
        viewModelScope.launch(Dispatchers.Default + exceptionHandler) {
            when(fetchContent()){
                is NetworkResult.Success -> {
                    val content = (contentRepository.getContent() as NetworkResult.Success).data
                    val task1 = async {content.find15thCharacter() }
                    val task2 = async {content.findEvery15thCharacter() }
                    val task3 = async {content.requestWordCounter() }
                    _task1State.value = TaskState.Success(task1.await().toString())
                    _task2State.value = TaskState.Success(task2.await())
                    _task3State.value = TaskState.Success(task3.await())
                }
                is NetworkResult.Error -> {
                    setState(TaskState.Error("Error loading content"))
                }
            }
        }
    }

    private suspend fun fetchContent(): NetworkResult<String> {
        setState(TaskState.Loading)
        return contentRepository.getContent()
    }

    private fun setState(state: TaskState) {
        _task1State.value = state
        _task2State.value = state
        _task3State.value = state
    }
}
