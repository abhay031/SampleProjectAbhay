package  com.newsbytes.abhay.jetpack.entities

import android.os.Parcelable
import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.newsbytes.abhay.jetpack.retrofit.RetrofitUtil
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable
import org.json.JSONObject

@Keep
@Parcelize
data class RecipeEntity(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("image")
    var image: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("time")
    var time: String? = null,

    @SerializedName("portion")
    var portion: String? = null,

    @SerializedName("ingredients")
    var ingredients: ArrayList<String> = arrayListOf(),

    ) : Parcelable {

    companion object {
        /**
         * [fromJson] parse your [JSONObject] into [Array<ClassModel>] Object
         * @author Abhay Gupta
         */
        fun fromJsonArray(data: String): Array<RecipeEntity> {
            return RetrofitUtil.instance.fromJson(
                data, Array<RecipeEntity>::class.java
            )
        }

        fun fromJson(data: String): RecipeEntity {
            return RetrofitUtil.instance.fromJson(
                data, RecipeEntity::class.java
            )
        }

        /**
         * [fromJson] parse your [JSONObject] into [Array<RecipeEntity>] Object
         * @author Abhay Gupta
         */
        fun fromJsonArrayString(data: String): Array<String> {
            return RetrofitUtil.instance.fromJson(
                data, Array<String>::class.java
            )
        }

        /**
         * [toJson] parse your [Array<RecipeEntity>] into JSON Object
         * @author Abhay Gupta
         */
        fun toJsonArrayString(models: Array<String>): String {
            return RetrofitUtil.instance.toJson(models, Array<String>::class.java)
        }

        /**
         * [toJson] parse your [Array<RecipeEntity>] into JSON Object
         * @author Abhay Gupta
         */
        fun toJsonArray(models: Array<RecipeEntity>): String {
            return RetrofitUtil.instance.toJson(models, Array<RecipeEntity>::class.java)
        }

    }
}