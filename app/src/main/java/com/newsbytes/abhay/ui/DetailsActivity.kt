package com.newsbytes.abhay.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.newsbytes.abhay.BuildConfig
import com.newsbytes.abhay.R
import com.newsbytes.abhay.databinding.ActivityDetailsBinding
import com.newsbytes.abhay.jetpack.bases.BaseActivity
import com.newsbytes.abhay.jetpack.entities.RecipeEntity
import com.newsbytes.abhay.utils.IntentKeys

class DetailsActivity : BaseActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private val model by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(IntentKeys.INTENT_FOR_DATA, RecipeEntity::class.java)
        } else {
            intent.getParcelableExtra(IntentKeys.INTENT_FOR_DATA)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        initializeData()
        initializeListener()
    }

    override fun initializeRecyclerView() {
        super.initializeRecyclerView()
        binding.idRecyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = StringAdapter()
        binding.idRecyclerView.adapter = adapter
        adapter.notifyAdapterDataSetChanged(modelList = model?.ingredients ?: arrayListOf())
    }


    override fun initializeListener() {
        super.initializeListener()
        binding.idImageArrowBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun initializeData() {
        super.initializeData()
        binding.apply {
            idTitle.text = model?.title
            idTitleDescription.text = model?.description
            Glide.with(this@DetailsActivity).load(model?.image).centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .placeholder(R.drawable.placeholder_round).into(idImage)
        }
        initializeRecyclerView()
    }

}