package com.task.truecaller.repository

import com.task.truecaller.network.NetworkResult


interface ContentRepository {
    suspend fun getContent(): NetworkResult<String>
}
