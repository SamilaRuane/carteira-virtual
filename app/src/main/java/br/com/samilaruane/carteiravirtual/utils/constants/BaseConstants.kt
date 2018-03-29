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
        val GENERIC_ERROR = "Caro cliente, no momento não foi possível recuperar as informações. Tente Novamente em instantes"
        val INVALID_PASSWORD = "Usuário ou senha inválida"
        val USER_NOT_FOUND = "Usuário não cadastrado no sistema"
        val SUCCESS_ON_TASK = "Operação realizada com Sucesso"
        val PASSWORD_NOT_MATCH = "Senhas não conferem"
        val SUCCESS_ON_CREATE_USER = "Usuário cadastrado com Sucesso"
    }
}