package  com.newsbytes.abhay.jetpack.repo

import  com.newsbytes.abhay.jetpack.bases.OnEventTriggerListener
import  com.newsbytes.abhay.jetpack.remote.RecipeRemote


class RecipeRepo {

    private val remote: RecipeRemote = RecipeRemote()

    fun apiGetData(
        listener: OnEventTriggerListener
    ) {
        remote.apiGetData(listener)
    }

    fun apiTestTxnApi(
        deviceSerial: String, amount: String, listener: OnEventTriggerListener
    ) {
    }

    fun closeEverything() {
        remote.closeCall()
    }
}