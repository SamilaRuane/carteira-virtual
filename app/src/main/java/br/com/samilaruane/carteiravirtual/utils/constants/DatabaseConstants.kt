package br.com.samilaruane.carteiravirtual.utils.constants

/**
 * Created by samila on 24/12/17.
 */
class DatabaseConstants {

    object USER {
        val TABLE_NAME = "user"
        object COLUMNS {
            val ID = "id"
            val NAME = "name"
            val PHONE = "phone"
            val PASSWORD = "password"
            val EMAIL = "email"

        }
    }


    object ACCOUNT {
        val TABLE_NAME = "account"
        object COLUMNS {
            val ID = "id"
            val BALANCE = "balance"
            val USER_ID = "user_id"
            val COIN_INITIALS = "coin_initials"
        }
    }

    object TRANSACTION {
        val TABLE_NAME = "transaction_"
        object COLUMNS {
            val TYPE = "type"
            val ID = "id"
            val SOURCE_ACCOUNT = "source_account_id"
            val DESTINATION_ACCOUNT = "destination_account_id"
            val DATE = "date"
            val AMOUNT = "amount"
        }
    }

}