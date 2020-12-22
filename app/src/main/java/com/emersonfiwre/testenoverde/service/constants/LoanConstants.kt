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
        private const val BEARER = "Bearer "
        private const val TOKEN = "e6f710ecca5b476189c7ecdde4392cc8"
        const val VALUE = BEARER + TOKEN

    }

    object ARGUMENTS {
        const val CPF = "cpf"
        const val USER = "user"
    }

    object TAG {
        const val FRAG_AMOUNT = "amountNeedFragment"
    }

}