package  com.android.abhay.jetpack.remote

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


class RecipeRemoteImpl : RecipeRemote {

    private var apiCall: Call<Any>? = null

    override fun apiGetData(
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


    override fun closeCall() {
        apiCall?.cancel()
    }

}