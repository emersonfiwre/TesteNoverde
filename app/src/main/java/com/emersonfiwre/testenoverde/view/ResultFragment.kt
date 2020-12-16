package com.emersonfiwre.testenoverde.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.emersonfiwre.testenoverde.R
import com.emersonfiwre.testenoverde.service.constants.LoanConstants
import com.emersonfiwre.testenoverde.service.model.UserModel
import com.emersonfiwre.testenoverde.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.fragment_result.view.*

class ResultFragment private constructor() : Fragment() {
    private lateinit var mViewRoot: View
    private lateinit var mViewModel: LoanViewModel

    companion object {
        fun newInstance(user: UserModel): ResultFragment {
            val args = Bundle()
            args.putSerializable(LoanConstants.ARGUMENTS.USER, user);
            val fragment = ResultFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mViewRoot = inflater.inflate(R.layout.fragment_result, container, false)
        mViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)

        val mUser = arguments?.getSerializable(LoanConstants.ARGUMENTS.CPF) as UserModel

        //Criando observadores
        observe()
        //Pedir emprestimo
        mViewModel.applyLoan(mUser)

        return mViewRoot
    }

    private fun observe() {
        mViewModel.validation.observe(viewLifecycleOwner, Observer {
            if (!it.success()) {
                Toast.makeText(context, it.failure(), Toast.LENGTH_LONG).show()
            }
        })
        mViewModel.loan.observe(viewLifecycleOwner, Observer {
            if (it.status == LoanConstants.APPROVED) {
                mViewRoot.txt_result.text = context!!.getString(R.string.you_approved)

                mViewRoot.txt_amount_released.text = it.amount.toString()
                mViewRoot.txt_installment.text = it.period.toString()
                mViewRoot.txt_first_duedate.text = it.dueDate

            } else {
                mViewRoot.txt_result.text = context!!.getString(R.string.you_reproved)

                mViewRoot.txt_amount_released.visibility = View.GONE
                mViewRoot.txt_installment.visibility = View.GONE
                mViewRoot.txt_first_duedate.visibility = View.GONE
            }
        })
    }

}