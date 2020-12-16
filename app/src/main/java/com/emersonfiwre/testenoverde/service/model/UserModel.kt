package com.emersonfiwre.testenoverde.service.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class UserModel: Serializable {
    @SerializedName("cpf")
    var cpf: String = ""

    @SerializedName("amount")
    var amount: Double = 0.0
}