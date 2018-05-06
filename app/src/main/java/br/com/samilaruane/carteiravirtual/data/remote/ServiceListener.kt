package br.com.samilaruane.carteiravirtual.data.remote

import br.com.samilaruane.carteiravirtual.data.model.BancoCentralResponse
import br.com.samilaruane.carteiravirtual.data.model.MercadoBitcoinResponse

interface ServiceListener {
    interface Brita {
        fun onSuccess (response : BancoCentralResponse)
        fun onError (error : String)
    }

    interface Bitcoin {
        fun onSuccess (response: MercadoBitcoinResponse)
        fun onError (error : String)
    }
}