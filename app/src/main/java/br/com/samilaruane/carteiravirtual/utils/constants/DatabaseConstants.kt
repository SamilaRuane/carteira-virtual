package br.com.samilaruane.carteiravirtual.utils.constants

/**
 * Created by samila on 24/12/17.
 */
class DatabaseConstants {

    object USER {
        const val TABLE_NAME = "user"

        object COLUMNS {
            const val ID = "id"
            const val NAME = "name"
            const val PHONE = "phone"
            const val PASSWORD = "password"
            const val EMAIL = "email"

        }
    }

    object ACCOUNT {
        const val TABLE_NAME = "account"

        object COLUMNS {
            const val ID = "id"
            const val BALANCE = "balance"
            const val USER_ID = "user_id"
            const val COIN_INITIALS = "coin_initials"
        }
    }

    object TRANSACTION {
        const val TABLE_NAME = "transaction_"

        object COLUMNS {
            const val TYPE = "type"
            const val ID = "id"
            const val SOURCE_ACCOUNT = "source_account"
            const val DESTINATION_ACCOUNT = "destination_account"
            const val DATE = "date"
            const val AMOUNT = "amount"
            const val USER_ID = "user_id"
        }
    }

}