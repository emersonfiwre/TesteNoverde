package com.emersonfiwre.testenoverde.service.repository.remote

import com.emersonfiwre.testenoverde.service.model.LoanModel
import retrofit2.Call
import retrofit2.http.POST

interface APIRequest {
    @POST("fakes")
    fun loan(): Call<LoanModel>//Create class user for request api
}