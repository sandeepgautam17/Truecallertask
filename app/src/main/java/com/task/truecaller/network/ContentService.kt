package com.task.truecaller.network

import okhttp3.ResponseBody
import retrofit2.http.GET

const val BASE_URL = "https://www.truecaller.com/"
private const val BLOG = "blog/"
private const val LIFE_AS_ENGINEER = "life-at-truecaller/life-as-an-android-engineer"

interface ContentService {
    @GET("$BLOG$LIFE_AS_ENGINEER")
    suspend fun fetchContent(): ResponseBody
}
