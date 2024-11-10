package com.task.truecaller.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.task.truecaller.repository.ContentRepository
import com.task.truecaller.ui.viewmodel.MainViewModel

class MainViewModelFactory(
    private val contentRepository: ContentRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(contentRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
