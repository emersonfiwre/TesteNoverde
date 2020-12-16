package com.emersonfiwre.testenoverde.service.model

import com.google.gson.annotations.SerializedName

class LoanModel {
    @SerializedName("status")
    lateinit var status: String

    @SerializedName("amount")
    var amount: Double = 0.0

    @SerializedName("period")
    var period: Int = 0

    @SerializedName("installment")
    var installments: Double = 0.0

    @SerializedName("first_due_date")
    lateinit var dueDate: String
}