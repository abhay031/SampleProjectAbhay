package com.android.abhay.jetpack.bases

import com.android.abhay.utils.Constants
import org.json.JSONObject
import retrofit2.Response

/**
 * @author : Abhay Kumar Gupta
 * @date : 26 Feb 2022
 **/

object ParseJson {
    private fun isSuccessTrue(result: JSONObject): Boolean {
        return (result.has("success") && result.getBoolean("success"))
    }

    fun isApiRunSuccessful(response: Response<Any?>): Boolean {
        return response.body() != null && response.isSuccessful && (response.code() == 200 || response.code() == 201)
    }


    private fun hasAndNotNull(key: String, results: JSONObject): Boolean {
        return results.has(key) && results.getString(key).isNotEmpty()
    }

    fun parseErrorResponse(results: JSONObject, listener: OnEventTriggerListener) {
        var errorCode = ""
        var errorMessage = ""
        if (hasAndNotNull("errorMessage", results)) {
            errorMessage = results.getString("errorMessage")
        }
        if (hasAndNotNull("errorCode", results)) {
            errorCode = results.getString("errorCode")
        } else if (hasAndNotNull("realCode", results)) {
            errorCode = results.getString("realCode")
        }
        listener.onErrorMessage(errorCode, errorMessage)
    }

    fun parseRecipeData(jsonObjects: JSONObject, listener: OnEventTriggerListener) {

    }
}