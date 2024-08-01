package  com.android.abhay.jetpack.repo

import  com.android.abhay.jetpack.bases.OnEventTriggerListener
import  com.android.abhay.jetpack.remote.RecipeRemote
import com.android.abhay.jetpack.remote.RecipeRemoteImpl
import javax.inject.Inject


class RecipeRepo @Inject constructor(private val remote: RecipeRemote) {

    fun apiGetData(
        listener: OnEventTriggerListener
    ) {
        remote.apiGetData(listener)
    }

    fun closeEverything() {
        remote.closeCall()
    }
}