package com.emersonfiwre.testenoverde.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.emersonfiwre.testenoverde.service.LoanRepository
import com.emersonfiwre.testenoverde.service.listener.APIListener
import com.emersonfiwre.testenoverde.service.listener.ValidationListener
import com.emersonfiwre.testenoverde.service.model.LoanModel
import com.emersonfiwre.testenoverde.service.model.UserModel

class LoanViewModel(application: Application) : AndroidViewModel(application) {
    private val mContext = application.applicationContext
    private val mRepository = LoanRepository(mContext)

    private var mValidation = MutableLiveData<ValidationListener>()
    var validation: LiveData<ValidationListener> = mValidation

    private var mLoan = MutableLiveData<LoanModel>()
    var loan: LiveData<LoanModel> = mLoan

    fun applyLoan(user: UserModel) {
        if (user.cpf.isNotEmpty() && user.amount > 0.00) {
            mRepository.load(user, object : APIListener<LoanModel> {
                override fun onSuccess(result: LoanModel) {
                    mLoan.value = result
                }
                override fun onFailure(message: String) {
                    mValidation.value = ValidationListener(message)
                }
            })

        } else {
            mValidation.value = ValidationListener("Cpf ou valor vázios")
        }

    }
}