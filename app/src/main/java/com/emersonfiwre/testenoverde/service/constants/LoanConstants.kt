package com.emersonfiwre.testenoverde.service.constants

class LoanConstants private constructor() {

    companion object {
        const val APPROVED = "approved"
    }

    object HTTP {
        const val SUCCESS = 200
    }

    object HEADER {
        const val AUTHORIZATION = "Authorization"
    }

    object ARGUMENTS {
        const val CPF = "cpf"
        const val USER = "user"
    }

    object TAG {
        const val FRAG_AMOUNT = "amountNeedFragment"
    }

}