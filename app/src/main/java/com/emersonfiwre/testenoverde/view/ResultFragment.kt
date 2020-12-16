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
import com.emersonfiwre.testenoverde.viewmodel.LoanViewModel
import kotlinx.android.synthetic.main.fragment_amount_need.view.*
import kotlinx.android.synthetic.main.fragment_cnpj.view.*
import kotlinx.android.synthetic.main.fragment_welcome.view.*

class ResultFragment : Fragment(){
    private lateinit var mViewRoot: View
    private lateinit var mViewModel: LoanViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mViewRoot = inflater.inflate(R.layout.fragment_result, container, false)
        mViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)

        //Criando observadores
        observe()
        //Pedir emprestimo
        //mViewModel.list()

        return mViewRoot
    }

    private fun observe() {
        mViewModel.validation.observe(viewLifecycleOwner, Observer {
            if (!it.success()) {
                Toast.makeText(context, it.failure(), Toast.LENGTH_LONG).show()
            }
        })
    }

}