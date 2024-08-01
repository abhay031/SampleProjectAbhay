package com.android.abhay.ui

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.util.Pair
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.abhay.databinding.ActivityHomeBinding
import com.android.abhay.jetpack.bases.BaseActivity
import com.android.abhay.jetpack.bases.OnEventTriggerListener
import com.android.abhay.jetpack.entities.RecipeEntity
import com.android.abhay.utils.HelperActionBar
import com.android.abhay.utils.IntentKeys
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initializeRecyclerView()
        setToolbar()
    }

    private fun setToolbar() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
        HelperActionBar.setAppBarLayout(this, 0.02, object : HelperActionBar.ScrollingListener {
            override fun up() {
                binding.idFrameCollapsingBig.visibility = View.GONE
                binding.idFrame.visibility = View.GONE
                binding.idFrameCollapsingSmall.visibility = View.VISIBLE
            }

            override fun down() {
                binding.idFrame.visibility = View.VISIBLE
                binding.idFrameCollapsingBig.visibility = View.VISIBLE
                binding.idFrameCollapsingSmall.visibility = View.GONE
            }
        })
    }


    override fun initializeRecyclerView() {
        super.initializeRecyclerView()
        val models = arrayListOf<RecipeEntity>()
        models.addAll(utils.getRecipeData(this))
        binding.idRecyclerViewHorizontal.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val horizontalAdapter =
            RecipeAdapter(RecipeAdapter.ITEM_HORIZONTAL, listener)
        binding.idRecyclerViewHorizontal.adapter = horizontalAdapter
        horizontalAdapter.notifyAdapterDataSetChanged(models)

        binding.idRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RecipeAdapter(RecipeAdapter.ITEM_VERTICAL, listener)
        binding.idRecyclerView.adapter = adapter
        adapter.notifyAdapterDataSetChanged(models)
    }


    private var listener = object : OnEventTriggerListener() {
        override fun getEvent(any: Any, actionType: Any, variable: Any, view: Any) {
            super.getEvent(any, actionType, variable, view)
            startActivity(
                Intent(
                    this@HomeActivity, DetailsActivity::class.java
                ).putExtra(IntentKeys.INTENT_FOR_DATA, any as RecipeEntity),
                ActivityOptions.makeSceneTransitionAnimation(
                    this@HomeActivity, Pair(variable as View, "unique_text"),
                    Pair(actionType as View, "unique_word"),
                    Pair(view as View, "unique_icon"),
                ).toBundle()
            )
        }
    }

}