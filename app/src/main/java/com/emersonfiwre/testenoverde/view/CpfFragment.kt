package com.emersonfiwre.testenoverde.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.emersonfiwre.testenoverde.R
import com.emersonfiwre.testenoverde.service.constants.LoanConstants
import com.emersonfiwre.testenoverde.service.utils.Mask
import kotlinx.android.synthetic.main.fragment_cnpj.*
import kotlinx.android.synthetic.main.fragment_cnpj.view.*

class CpfFragment : Fragment(), View.OnClickListener {
    private lateinit var mViewRoot: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        mViewRoot = inflater.inflate(R.layout.fragment_cnpj, container, false)

        mViewRoot.edit_cpf.addTextChangedListener(Mask.mask("###.###.###-##", mViewRoot.edit_cpf))

        setListener()

        return mViewRoot
    }

    private fun setListener() {
        mViewRoot.button_next.setOnClickListener(this)
    }

    //VALIDATION CPF
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.button_next -> {
                //set trasaction
                nextFragment(AmountNeedFragment.newInstance(edit_cpf.text.toString()))
            }
        }
    }

    private fun nextFragment(fragment: AmountNeedFragment) {
        activity!!.supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                android.R.anim.slide_in_left,
                android.R.anim.slide_out_right
            )
            .replace(R.id.container_root, fragment, LoanConstants.TAG.FRAG_AMOUNT)
            .addToBackStack(null)
            .commit()
    }
}