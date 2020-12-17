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
import com.emersonfiwre.testenoverde.service.utils.Mask
import com.emersonfiwre.testenoverde.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.fragment_amount_need.*
import kotlinx.android.synthetic.main.fragment_amount_need.view.*
import kotlinx.android.synthetic.main.fragment_cnpj.*
import kotlinx.android.synthetic.main.fragment_cnpj.view.*
import kotlinx.android.synthetic.main.fragment_welcome.view.*

class AmountNeedFragment private constructor() : Fragment(), View.OnClickListener {
    private lateinit var mViewRoot: View
    private lateinit var mCpf: String

    companion object {
        fun newInstance(cpf: String): AmountNeedFragment {
            val args = Bundle()
            args.putString(LoanConstants.ARGUMENTS.CPF, cpf);
            val fragment = AmountNeedFragment()
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
        mViewRoot = inflater.inflate(R.layout.fragment_amount_need, container, false)
        mCpf = arguments?.getString(LoanConstants.ARGUMENTS.CPF).toString()
        mViewRoot.edit_amount.addTextChangedListener(Mask.mask("######.##", mViewRoot.edit_amount))

        setListener()

        return mViewRoot
    }

    private fun setListener() {
        mViewRoot.button_request.setOnClickListener(this)
    }

    //VALIDATION AMOUNT LOAN
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_request -> {
                //set trasaction
                if (edit_amount != null || edit_amount.text.isNotEmpty()) {
                    val editAmount = edit_amount.text.toString().toDouble()
                    val user: UserModel = UserModel().apply {
                        this.cpf = mCpf
                        this.amount = editAmount
                    }
                    nextFragment(ResultFragment.newInstance(user))

                } else {
                    Toast.makeText(
                        context,
                        context!!.getString(R.string.amount_empty),
                        Toast.LENGTH_SHORT
                    ).show()

                }

            }
        }

    }

    private fun nextFragment(fragment: ResultFragment) {
        activity!!.supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            .replace(R.id.container_root, fragment, "TAG")
            .addToBackStack(null)
            .commit()
    }
}