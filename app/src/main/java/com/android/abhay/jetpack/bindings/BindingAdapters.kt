//package com.android.abhay.jetpack.bindings
//
//import android.view.View
//import android.widget.Button
//import android.widget.TextView
//import androidx.appcompat.widget.AppCompatImageView
//import androidx.appcompat.widget.AppCompatRadioButton
//import androidx.databinding.BindingAdapter
//import com.android.abhay.R
//
///**
// * @author Abhay Kumar Gupta
// * binding adapters for binding data and branding purpose
// */
//
//object BindingAdapters {
//
//    @BindingAdapter("text")
//    @JvmStatic
//    fun text(view: TextView, any: Any? = null) {
//        when (any) {
//            is String -> {
//                if (any == "null" || any == "") view.visibility = View.GONE
//                else view.text = any
//            }
//
//            is Int -> {
//                view.text = any.toString()
//            }
//
//            else -> view.text = ""
//        }
//    }
//
//    @BindingAdapter("tint")
//    @JvmStatic
//    fun tint(view: AppCompatImageView, branding: Int) {
//        when (branding) {
//            1 -> view.setColorFilter(R.color.primary)
//            else -> view.setColorFilter(R.color.primaryHdfc)
//        }
//    }
//
//    @BindingAdapter("buttonTint")
//    @JvmStatic
//    fun buttonTint(view: AppCompatRadioButton, branding: Int) {
//        when (branding) {
//            1 -> view.highlightColor =
//                MyApplication.instance.applicationContext.resources.getColor(R.color.primary)
//
//            else -> view.highlightColor =
//                MyApplication.instance.applicationContext.resources.getColor(R.color.primaryHdfc)
//        }
//    }
//
//    @BindingAdapter("background")
//    @JvmStatic
//    fun background(view: Button, branding: Int) {
//        when (branding) {
//            1 -> view.setBackgroundResource(R.drawable.button_style_primary)
//            else -> view.setBackgroundResource(
//                R.drawable.button_style_primary_hdfc
//            )
//        }
//    }
//
//
//    @BindingAdapter("isEnable")
//    @JvmStatic
//    fun isEnable(view: View, isEnable: Boolean) {
//        view.isEnabled = isEnable
//    }
//
//    @BindingAdapter("visibility")
//    @JvmStatic
//    fun visibility(view: View, any: Any? = null) {
//        when (any) {
//            is Boolean -> {
//                view.visibility = if (any) View.VISIBLE else View.GONE
//            }
//
//            is Int -> {
//                view.visibility = if (any == 1) View.VISIBLE else View.GONE
//            }
//
//            else -> {
//                view.visibility = if (any != null) View.VISIBLE else View.GONE
//            }
//        }
//    }
//
//
//}