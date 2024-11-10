package com.task.truecaller.task

sealed class TaskState {
    data object Idle: TaskState()
    data object Loading : TaskState()
    data class Error(val message: String) : TaskState()
    data class Success(val data: String) : TaskState()
}