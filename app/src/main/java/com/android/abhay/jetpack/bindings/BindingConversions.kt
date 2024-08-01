//package com.android.abhay.jetpack.bindings
//
//import android.view.View
//import androidx.appcompat.widget.AppCompatImageView
//import androidx.databinding.BindingConversion
//import androidx.databinding.BindingMethod
//import androidx.databinding.BindingMethods
//
//object BindingConversions {
//
//    @BindingConversion
//    @JvmStatic
//    fun booleanToVisibility(isNotVisible: Boolean): Int {
//        return if (isNotVisible) View.GONE else View.VISIBLE
//    }
//
//    @BindingMethods(
//        BindingMethod(
//            type = AppCompatImageView::class,
//            attribute = "app:srcCompat",
//            method = "setImageDrawable"
//        )
//    )
//    class BindingClass {
//    }
//}