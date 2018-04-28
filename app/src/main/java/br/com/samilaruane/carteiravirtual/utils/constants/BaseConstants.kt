package br.com.samilaruane.carteiravirtual.utils.constants

/**
 * Created by samila on 07/01/18.
 */
class BaseConstants {
    companion object {
        val SELL = "VENDA"
        val BUY = "COMPRA"
        val TRADE = "TROCA"

        val BRITA_ACCOUNT = "BRITA"
        val BITCOIN_ACCOUNT = "BITCOIN"
        val BRL_ACCOUNT = "BRL"

        val SALE_PRICE = "salePrice"
        val PURCHASE_QUOTATION = "purchaseQuotation"
    }

    object MESSAGES {
        const val GENERIC_ERROR = "Caro cliente, no momento não foi possível recuperar as informações. Tente Novamente em instantes"
        const val INVALID_PASSWORD = "Usuário ou senha inválida"
        const val USER_NOT_FOUND = "Usuário não cadastrado no sistema"
        const val SUCCESS_ON_TASK = "Operação realizada com Sucesso"
        const val PASSWORD_NOT_MATCH = "Senhas não conferem"
        const val SUCCESS_ON_CREATE_USER = "Usuário cadastrado com Sucesso"
        const val EMPTY_FIELDS = "Preencha todos os campos"
        const val USER_ALREADY_EXISTS = "O usuário informado já está cadastrado no sistema"
        const val FAIL_ON_TASK = "Ocorreu um erro. Tente novamente mais tarde"
        const val INSUFFICIENT_BALANCE = "Saldo Insuficiente"
    }
}