package  com.android.abhay.jetpack.retrofit

import com.android.abhay.jetpack.bases.Urls
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit

/**
 * Retrofit Util for Retrofit Initialization and Instance signup
 *
 * @author Abhay Gupta
 */
class RetrofitUtil {
    var retrofitCalls: RetrofitCalls? = null
    val gson: Gson = GsonBuilder().setLenient().create()

    fun retrofitApis(): RetrofitCalls {
        if (retrofitCalls == null) {
            val interceptor = Interceptor { chain: Interceptor.Chain ->
                val request = chain.request()
                val newRequest = request.newBuilder().addHeader("Content-Type", "application/json")
                    .addHeader("x-rapidapi-host", "chinese-food-db.p.rapidapi.com")
                    .addHeader(
                        "x-rapidapi-key",
                        "31df622b76msh558fc35d59eb95fp1e2009jsn88a161327e43"
                    )
                    .build()
                chain.proceed(newRequest)
            }
            val retrofit = Retrofit.Builder().baseUrl(Urls.API_URL)
                .addConverterFactory(GsonConverterFactory.create(gson)).client(
                    OkHttpClient.Builder().addInterceptor(interceptor)
                        .connectTimeout(1, TimeUnit.MINUTES).readTimeout(1, TimeUnit.MINUTES)
                        .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                        .build()
                ).build()
            retrofitCalls = retrofit.create(RetrofitCalls::class.java)
        }
        return retrofitCalls!!
    }

    /**
     * @author Abhay Gupta (01-12-2021)
    </Model> */
    fun getTypeToken(typeToken: TypeToken<*>): Type {
        return typeToken.type
    }

    /**
     * Method used to create JSON From Model or Collection Variable
     *
     * @param object Your Collection or model Object
     * @param type   You need to type VolleyGSON.get().getTypeToken(new TypeToken<Model.Class or List></Model.Class><Model>>(){});
     * @return String of json
    </Model> */
    fun toJson(`object`: Any?, type: Type?): String {
        return gson.toJson(`object`, type)
    }

    /**
     * Method used to create JSON From Model or Collection Variable
     *
     * @param object Your Collection or model Object
     * @param type   You need to type VolleyGSON.get().getTypeToken(new TypeToken<Model.Class or List></Model.Class><Model>>(){});
     * @return String of json
    </Model> */
    fun <T> toJson(data: String, classOfT: Class<T>): T {
        return gson.fromJson(data, classOfT)
    }

    /**
     * Method used to parse your data into Your Model Object
     *
     * @param type You need to type VolleyGSON.get().getTypeToken(new TypeToken<Model.Class or List></Model.Class><Model>>(){});
     * @param data String of your JSON response
     * @return Return you Object of your Model Class
    </Model> */
    fun fromJson(type: Type?, data: String) {
        return gson.fromJson(data, type)
    }

    /**
     * Method used to parse your data into Your Model Object
     *
     * @param type You need to type VolleyGSON.get().getTypeToken(new TypeToken<Model.Class or List></Model.Class><Model>>(){});
     * @param data String of your JSON response
     * @return Return you Object of your Model Class
    </Model> */
    fun <T> fromJson(data: String, classOfT: Class<T>): T {
        return gson.fromJson(data, classOfT)
    }

    /**
     * Method used to parse your data into Your Model Object
     *
     * @param type You need to type VolleyGSON.get().getTypeToken(new TypeToken<Model.Class or List></Model.Class><Model>>(){});
     * @param data String of your JSON response
     * @return Return you Object of your Model Class
    </Model> */
    fun toMap(data: String): HashMap<String, Any> {
        return gson.fromJson(data, HashMap::class.java) as HashMap<String, Any>
    }

    fun toMap(jsonObject: JSONObject): HashMap<String, Any> {
        val map: HashMap<String, Any> = HashMap()
        val keys: Iterator<String> = jsonObject.keys()
        while (keys.hasNext()) {
            val key = keys.next()
            var value: Any = jsonObject.get(key)
            if (value is JSONArray) {
                value = toList(value)
            } else if (value is JSONObject) {
                value = toMap(value)
            }
            map[key] = value
        }
        return map
    }

    private fun toList(jsonArray: JSONArray): List<Any> {
        val list: MutableList<Any> = ArrayList()
        for (i in 0 until jsonArray.length()) {
            var value = jsonArray[i]
            if (value is JSONArray) {
                value = toList(value)
            } else if (value is JSONObject) {
                value = toMap(value)
            }
            list.add(value)
        }
        return list
    }

    fun getRequestBody(data: String): RequestBody {
        return RequestBody.create("text/plain".toMediaTypeOrNull(), data)
    }

    fun getResponse(response: Response<Any?>): JSONObject {
        if (response.body() != null) {
            return JSONObject(gson.toJson(response.body()))
        } else if (response.errorBody() != null) {
            return JSONObject(gson.toJson(response.errorBody()))
        } else {
            return JSONObject()
        }
    }

    fun getErrorResponse(response: Response<Any?>): JSONObject {
        return JSONObject(gson.toJson(response.errorBody()))
    }


    fun getResponseArray(response: Response<Any?>): JSONArray {
        return JSONArray(gson.toJson(response.body()))
    }

    companion object {
        /**
         * Get singleton implementation of Retrofit Util to access its Data Members [RetrofitInterface] and [Gson]
         *
         * @author Abhay Gupta
         */
        @get:Synchronized
        val instance: RetrofitUtil = RetrofitUtil()

    }
}