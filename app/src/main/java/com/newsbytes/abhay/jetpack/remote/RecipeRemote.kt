package  com.newsbytes.abhay.jetpack.remote

import com.google.gson.JsonObject
import com.newsbytes.abhay.jetpack.bases.OnEventTriggerListener
import com.newsbytes.abhay.jetpack.bases.ParseJson
import com.newsbytes.abhay.jetpack.bases.ResponseType
import com.newsbytes.abhay.jetpack.bases.Urls
import com.newsbytes.abhay.jetpack.entities.RecipeEntity
import com.newsbytes.abhay.jetpack.retrofit.RetrofitUtil
import com.newsbytes.abhay.utils.Constants.KEY_UNAUTHORISED_CODE
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RecipeRemote {

    private var apiCall: Call<Any>? = null

    fun apiGetData(
        listener: OnEventTriggerListener
    ) {
        apiCall?.cancel()
        apiCall = RetrofitUtil.instance.retrofitApis().commonGet(
            auth = "", Urls.apiFetchData()
        )
        apiCall?.enqueue(object : Callback<Any?> {
            override fun onResponse(call: Call<Any?>, response: Response<Any?>) {
                if (ParseJson.isApiRunSuccessful(response)) {
                    val jsonObjects = RetrofitUtil.instance.getResponseArray(response)
                    listener.onApiSuccess(RecipeEntity.fromJsonArray(jsonObjects.toString()))
                    return
                } else if (response.code().toString() == KEY_UNAUTHORISED_CODE) {
                    listener.onErrorMessage(
                        KEY_UNAUTHORISED_CODE,
                        "Un-Authorised User!!"
                    )
                } else {
                    ParseJson.parseErrorResponse(
                        RetrofitUtil.instance.getResponse(response), listener
                    )
                }
            }

            override fun onFailure(call: Call<Any?>, t: Throwable) {
                listener.onErrorMessage(ResponseType.RESPONSE_FAILED, t.message)
            }
        })
    }


    fun closeCall() {
        apiCall?.cancel()
    }

}