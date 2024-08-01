package  com.android.abhay.jetpack.remote

import com.google.gson.JsonObject
import com.android.abhay.jetpack.bases.OnEventTriggerListener
import com.android.abhay.jetpack.bases.ParseJson
import com.android.abhay.jetpack.bases.ResponseType
import com.android.abhay.jetpack.bases.Urls
import com.android.abhay.jetpack.entities.RecipeEntity
import com.android.abhay.jetpack.retrofit.RetrofitUtil
import com.android.abhay.utils.Constants.KEY_UNAUTHORISED_CODE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


interface RecipeRemote {

    fun apiGetData(listener: OnEventTriggerListener)

    fun closeCall()

}