package com.emersonfiwre.testenoverde.service

import android.content.Context
import com.emersonfiwre.testenoverde.R
import com.emersonfiwre.testenoverde.service.listener.APIListener
import com.emersonfiwre.testenoverde.service.model.LoanModel
import com.emersonfiwre.testenoverde.service.model.UserModel
import com.emersonfiwre.testenoverde.service.repository.remote.APIRequest
import com.emersonfiwre.testenoverde.service.repository.remote.RetrofitClient
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoanRepository(val context: Context) : ValidationRepository() {
    private val mRemote = RetrofitClient.createService(APIRequest::class.java)

    fun load(user: UserModel, listener: APIListener<LoanModel>) {
        if (!isConnectionAvailable(context)) {
            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
            return
        }

        val call: Call<LoanModel> = mRemote.loan()
        call.enqueue(object : Callback<LoanModel> {
            override fun onResponse(call: Call<LoanModel>, response: Response<LoanModel>) {
                if (response.code() != 200) {//CREATE CONSTANTS FOR SUCCESS RESPONSE API
                    val validation = Gson().fromJson(
                        response.errorBody()!!.string(),
                        String::class.java
                    )//Converte o json em uma string
                    listener.onFailure(validation)
                }
                response.body()?.let { listener.onSuccess(it) }
            }

            override fun onFailure(call: Call<LoanModel>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))

            }
        })
    }

}