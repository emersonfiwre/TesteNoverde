package com.emersonfiwre.testenoverde.service

import retrofit2.Call
import retrofit2.http.POST

interface APIRequest {
    @POST("fakes")
    fun loan(): Call<User>//Create class user for request api
}