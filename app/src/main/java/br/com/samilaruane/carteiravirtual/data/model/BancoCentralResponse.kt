package br.com.samilaruane.carteiravirtual.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created by samila on 18/03/18.
 */
data class BancoCentralResponse (@SerializedName("@odata.context")val context : String,
                                 val value : List<DollarExchangeRate>)