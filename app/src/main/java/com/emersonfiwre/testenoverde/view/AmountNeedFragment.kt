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

class AmountNeedFragment : Fragment(), View.OnClickListener {
    private lateinit var mViewRoot: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mViewRoot = inflater.inflate(R.layout.fragment_amount_need, container, false)
        setListener()

        return mViewRoot
    }

    private fun setListener() {
        mViewRoot.button_request.setOnClickListener(this)
    }

    //VALIDATION CPF
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_request -> {
                //set trasaction
                activity!!.supportFragmentManager
                    .beginTransaction()
                    .setCustomAnimations(
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right
                    )
                    .replace(R.id.container_root, ResultFragment(), "TAG")
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}