package com.android.abhay.utils

import android.app.Activity
import androidx.appcompat.app.ActionBar
import com.google.android.material.appbar.AppBarLayout
import com.android.abhay.R
import kotlin.math.abs

object HelperActionBar {
    private var appBarExtraVisibility: Boolean = false

    fun setSupportActionBar(actionBar: ActionBar, activity: Activity, title: Int): ActionBar? {
        return setSupportActionBar(actionBar, activity.getString(title))
    }

    fun setSupportActionBar(actionBar: ActionBar?, title: String = ""): ActionBar? {
        if (actionBar != null) {
            actionBar!!.setDisplayHomeAsUpEnabled(true)
            actionBar.title = title
            actionBar.setDisplayShowTitleEnabled(true)
        }
        return actionBar
    }

    fun setSupportActionBar(actionBar: ActionBar?, title: Int): ActionBar? {
        if (actionBar != null) {
            actionBar!!.setDisplayHomeAsUpEnabled(true)
            actionBar.setTitle(title)
            actionBar.setDisplayShowTitleEnabled(true)
        }
        return actionBar
    }

    fun setAppBarLayout(
        activity: Activity,
        value: Double,
        scrollingListener: ScrollingListener
    ): AppBarLayout {
        val appBarLayout = activity.findViewById<AppBarLayout>(R.id.id_parent_app_bar)
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout1: AppBarLayout, offset: Int ->
            val maxScroll = appBarLayout1.totalScrollRange
            val percentage = abs(offset).toFloat() / maxScroll.toFloat()
            if (percentage >= value) {
                if (!appBarExtraVisibility) {
                    appBarExtraVisibility = true
                    scrollingListener.up()
                }
            } else if (appBarExtraVisibility) {
                appBarExtraVisibility = false
                scrollingListener.down()
            }
        })
        return appBarLayout
    }

    interface ScrollingListener {
        fun up()

        fun down()
    }

    fun showActionBar(actionBar: ActionBar?) {
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(true)
    }

    fun hideActionBar(actionBar: ActionBar?) {
        actionBar?.setDisplayShowTitleEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(false)
    }
}
