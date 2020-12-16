package com.emersonfiwre.testenoverde.service.model

import com.google.gson.annotations.SerializedName

class UserModel {
    @SerializedName("cpf")
    var cpf: String = ""

    @SerializedName("amount")
    var amount: Double = 0.0
}