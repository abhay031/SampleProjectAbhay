package  com.newsbytes.abhay.jetpack.retrofit

import com.google.gson.JsonObject
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*
import java.util.*

/**
 * @author Abhay Gupta by 09-Oct-2020 16:07
 */
interface RetrofitCalls {


    /** Common API Calls */
    /**
     * Common [GET] call of [retrofit2.Retrofit]
     *
     * @param url Link to load information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     * @author Abhay Gupta
     */
    @GET
    fun commonGet(@Url url: String): Call<Any>

    /**
     * Common [GET] call of [retrofit2.Retrofit]
     *
     * @param url Link to load information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     * @author Abhay Gupta
     */
    @GET
    fun commonGet(@Header("access-token") auth: String, @Url url: String): Call<Any>

    /**
     * Common [DELETE] call of [retrofit2.Retrofit]
     *
     * @param url Link to load information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     * @author Abhay Gupta
     */
    @DELETE
    fun commonDelete(@Url url: String): Call<Any>

    /**
     * Common [PUT] call of [retrofit2.Retrofit]
     *
     * @param url Link to upload information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     * @author Abhay Gupta
     */
    @PUT
    fun commonPut(@Url url: String, @Body jsonObject: JsonObject?): Call<Any>

    /**
     * Common [PUT] call of [retrofit2.Retrofit]
     *
     * @param url Link to upload information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     * @author Abhay Gupta
     */
    @PUT
    fun commonPutRequest(
        @Header("access-token") auth: String,
        @Url url: String,
        @Body map: HashMap<String, Any?>
    ): Call<Any>

    /**
     * Common [PUT] call of [retrofit2.Retrofit]
     *
     * @param url Link to upload information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     * @author Abhay Gupta
     */
    @PUT
    fun commonPut(
        @Header("access-token") auth: String, @Url url: String,
        @Body jsonObject: JsonObject?
    ): Call<Any>

    /**
     * Common [POST] call of [retrofit2.Retrofit]
     *
     * @param url Link to upload information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     * @author Abhay Gupta
     */
    @POST
    fun commonPost(
        @Url url: String,
        @Body jsonObject: JsonObject?
    ): Call<Any>

    /**
     * Common [POST] call of [retrofit2.Retrofit]
     *
     * @param url Link to load information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     * @author Abhay Gupta
     */
    @POST
    fun commonPost(@Url url: String, @Body map: HashMap<String, Any?>): Call<Any>

    /**
     * Common [POST] call of [retrofit2.Retrofit]
     *
     * @param url Link to load information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     * @author Abhay Gupta
     */
    @POST
    fun commonPost(
        @Header("access-token") auth: String,
        @Url url: String,
        @Body map: HashMap<String, Any?>
    ): Call<Any>

    /**
     * Common [POST] call of [retrofit2.Retrofit]
     *
     * @param url Link to load information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     * @author Abhay Gupta
     */
    @POST
    fun commonPost(
        @Header("access-token") auth: String, @Url url: String,
        @Body jsonObject: JsonObject?
    ): Call<Any>

    /**
     * Common [POST] call of [retrofit2.Retrofit] to upload single [MultipartBody]
     *
     * @param url Link to load information
     * @return Object is a kind of Generic implementation to return anything which will handle manually
     * @author Abhay Gupta
     */
    @Multipart
    @POST
    fun commonUpload(
        @Header("access-token") auth: String?,
        @Url url: String,
        @PartMap map: HashMap<String, RequestBody>, @Part image: MultipartBody.Part?
    ): Call<Any>

    /**
     * [POST] call of [retrofit2.Retrofit] to upload records by [MultipartBody]
     *
     * @param url Link to load information
     * @return [RecordModel]
     * @author Abhay Gupta
     */
    @POST
    fun uploadRecord(
        @Header("access-token") auth: String?,
        @Url url: String,
        @PartMap map: RequestBody
    ): Call<Any?>

    @Multipart
    @PUT
    fun commonPutUpload(
        @Header("access-token") auth: String?,
        @Url url: String?,
        @PartMap map: HashMap<String, RequestBody>
    ): Call<Any>

    @Multipart
    @POST
    fun commonPostUpload(
        @Url url: String?,
        @Header("access-token") auth: String?,
        @Part map: MultipartBody.Part
    ): Call<Any>

    @PUT
    fun uploadBinaryFile(@Url url: String?, @Body photo: RequestBody?): Call<Void>
}
