package com.task.truecaller.repository

import com.task.truecaller.network.ContentService
import com.task.truecaller.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException

class ContentRepositoryImpl (private val service: ContentService) : ContentRepository {
    override suspend fun getContent(): NetworkResult<String> {
        return withContext(Dispatchers.IO) {
            try {
                val response = service.fetchContent()
                NetworkResult.Success(response.string())
            } catch (e: IOException) {
                NetworkResult.Error(IOException("Error fetching content", e))
            }
        }
    }
}
