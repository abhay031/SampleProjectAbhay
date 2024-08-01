package  com.android.abhay.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.PopupWindow
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.android.abhay.R
import com.android.abhay.jetpack.entities.RecipeEntity
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader

class Utils(private val activity: Activity) {
    private var userName: String? = null


    private fun loadRecipeDataArray(context: Context): JSONArray {
        val inputStream = context.resources.openRawResource(R.raw.recipe_data)
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }

    public fun getRecipeData(context: Context): List<RecipeEntity> {
        val recipeDataArray = loadRecipeDataArray(context)
        val recipeModels = arrayListOf<RecipeEntity>()
        recipeModels.addAll(RecipeEntity.fromJsonArray(recipeDataArray.toString()))
        return recipeModels
    }

    fun popupWindowOverflow(
        layout: View, activity: Context, viewPoint: View
    ): PopupWindow {
        val location = IntArray(2)
        viewPoint.getLocationOnScreen(location)
        val point = Point()
        point.x = location[0]
        point.y = location[1]
        val popupWindow = PopupWindow(activity)
        popupWindow.contentView = layout
        popupWindow.width = 600
        popupWindow.height = LinearLayout.LayoutParams.WRAP_CONTENT
        popupWindow.isFocusable = true
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popupWindow.showAtLocation(layout, Gravity.NO_GRAVITY, point.x, point.y)
        return popupWindow
    }

    fun doStatusBarColorWhite() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            activity.window.statusBarColor = Color.WHITE
        }
    }

    fun doStatusBarColorPrimary() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.window.statusBarColor = ContextCompat.getColor(activity, R.color.colorPrimary)
        }
    }

    fun hideSoftKeyboard() {
        try {
            val inputMethodManager =
                activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)

        } catch (e: Exception) {

        }
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context == null) return false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val capabilities: NetworkCapabilities? =
                connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if (capabilities != null) {
                when {
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> {
                        return true
                    }

                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                        return true
                    }

                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> {
                        return true
                    }
                }
            }
        } else {
            try {
                val activeNetworkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                if (activeNetworkInfo != null && activeNetworkInfo.isConnected) {
                    return true
                }
            } catch (e: Exception) {
            }
        }
        return false
    }

    private var toast: Toast? = null

    fun checkFieldIsEmpty(editText: EditText, message: String? = "Field can't be empty"): Boolean {
        if (TextUtils.isEmpty(editText.text.toString().trim())) {
            editText.requestFocus()
            editText.isSelected = true
            showToast(activity, message)
            return true
        }
        return false
    }

    fun showToast(
        activity: Activity,
        message: String?,
    ) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun checkMobileFieldIsEmpty(
        editText: EditText, message: String? = "Field can't be empty"
    ): Boolean {
        if (TextUtils.isEmpty(
                editText.text.toString().trim()
            ) || editText.text.startsWith("0") || editText.text.startsWith("1") || editText.text.startsWith(
                "2"
            ) || editText.text.startsWith("3") || editText.text.startsWith("4") || editText.text.startsWith(
                "5"
            )
        ) {
            editText.requestFocus()
            editText.isActivated = false
            showToast(
                activity, message,
            )
            return true
        }
        return false
    }

    fun clearEditTexts(arrayListOf: ArrayList<EditText>) {
        for (data in arrayListOf) {
            data.text.clear()
            data.isActivated = false
            data.isSelected = true
        }
    }

    fun disableEditTexts(arrayListOf: ArrayList<EditText>) {
        for (data in arrayListOf) {
            data.isActivated = true
            data.isEnabled = false
        }
    }

    fun enableEditTexts(arrayListOf: ArrayList<EditText>) {
        for (data in arrayListOf) {
            data.isActivated = true
            data.isEnabled = true
        }
    }
}