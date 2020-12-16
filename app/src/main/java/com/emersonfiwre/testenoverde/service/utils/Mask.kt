package com.emersonfiwre.testenoverde.service.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

/*
* Codigo fonte feito por: Paulo Linhares no medium
* https://medium.com/@paulo_linhares/cpf-m%C3%A1scara-e-valida%C3%A7%C3%A3o-kotlin-975f1e394ecb
*
* */
class Mask {
    companion object {
        private fun replaceChars(cpfFull: String): String {
            return cpfFull.replace(".", "").replace("-", "")
                .replace("(", "").replace(")", "")
                .replace("/", "").replace(" ", "")
                .replace("*", "")
        }


        fun mask(mask: String, etCpf: EditText): TextWatcher {

            val textWatcher: TextWatcher = object : TextWatcher {
                var isUpdating: Boolean = false
                var oldString: String = ""
                override fun beforeTextChanged(
                    charSequence: CharSequence,
                    i: Int,
                    i1: Int,
                    i2: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                    val str = replaceChars(s.toString())
                    var cpfWithMask = ""

                    if (count == 0)//is deleting
                        isUpdating = true

                    if (isUpdating) {
                        oldString = str
                        isUpdating = false
                        return
                    }

                    var i = 0
                    for (m: Char in mask.toCharArray()) {
                        if (m != '#' && str.length > oldString.length) {
                            cpfWithMask += m
                            continue
                        }
                        try {
                            cpfWithMask += str.get(i)
                        } catch (e: Exception) {
                            break
                        }
                        i++
                    }

                    isUpdating = true
                    etCpf.setText(cpfWithMask)
                    etCpf.setSelection(cpfWithMask.length)

                }

                override fun afterTextChanged(editable: Editable) {

                }
            }

            return textWatcher
        }
    }
}