package br.com.samilaruane.carteiravirtual.utils.constants

/**
 * Created by samila on 07/01/18.
 */
class BaseConstants {
    companion object {
        const val SELL = "VENDA"
        const val BUY = "COMPRA"
        const val TRADE = "TROCA"

        const val BRITA = "BRITA"
        const val BITCOIN = "BITCOIN"
        const val BRL = "BRL"

        const val SALE_PRICE = "salePrice"
        const val PURCHASE_QUOTATION = "purchaseQuotation"
    }

    object MESSAGES {
        const val GENERIC_ERROR = "Caro cliente, no momento não foi possível recuperar as informações. Tente Novamente em instantes"
        const val INVALID_PASSWORD = "Usuário ou senha inválida"
        const val SUCCESS_ON_TASK = "Operação realizada com Sucesso"
        const val PASSWORD_NOT_MATCH = "Senhas não conferem"
        const val EMPTY_FIELDS = "Preencha todos os campos"
        const val USER_ALREADY_EXISTS = "O usuário informado já está cadastrado no sistema"
        const val INSUFFICIENT_BALANCE = "Saldo Insuficiente"
    }
}