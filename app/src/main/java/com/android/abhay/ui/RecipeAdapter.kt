package com.android.abhay.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.android.abhay.R
import com.android.abhay.databinding.AdapterRecipeBinding
import com.android.abhay.databinding.AdapterRecipeHorizontalBinding
import com.android.abhay.jetpack.bases.OnEventTriggerListener
import com.android.abhay.jetpack.entities.RecipeEntity
import com.android.abhay.utils.RoundedCornersTransformation

class RecipeAdapter(
    private var actionType: Int,
    private var listener: OnEventTriggerListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var modelList: ArrayList<RecipeEntity> = arrayListOf()
    private var size = 0

    fun clearAdapterItem() {
        size = 0
        modelList = arrayListOf()
        notifyDataSetChanged()
    }


    fun notifyAdapterDataSetChanged(modelList: ArrayList<RecipeEntity>) {
        this.modelList = modelList
        size = modelList.size
        notifyDataSetChanged()
    }

    fun notifyAdapterRemove(position: Int) {
        modelList.removeAt(position)
        size -= 1
        notifyItemRemoved(position)
    }

    override fun getItemViewType(position: Int): Int {
        return if (actionType == ITEM_HORIZONTAL) {
            0
        } else 1
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 1)
            ViewHolder(
                AdapterRecipeBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        else
            HorizontalHolder(
                AdapterRecipeHorizontalBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ViewHolder)
            holder.bindTo(modelList[position], position)
        else if (holder is HorizontalHolder)
            holder.bindTo(modelList[position], position)
    }

    override fun getItemCount(): Int {
        return size
    }

    inner class HorizontalHolder(private val binding: AdapterRecipeHorizontalBinding) :
        RecyclerView.ViewHolder(
            binding.root
        ) {

        @SuppressLint("SetTextI18n")
        fun bindTo(model: RecipeEntity, position: Int) {
            binding.apply {
                idImage.setOnClickListener {
                    listener.getEvent(model, idImage, idTitle,idImageIcon)
                }
                Glide.with(itemView.context).load(model.image).centerCrop().transform(
                    RoundedCornersTransformation(
                        itemView.context, idImage, 32, RoundedCornersTransformation.CornerType.ALL
                    )
                ).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.placeholder_round).into(idImage)
                idTitle.text = model.title
            }
        }
    }

    inner class ViewHolder(private val binding: AdapterRecipeBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {

        @SuppressLint("SetTextI18n")
        fun bindTo(model: RecipeEntity, position: Int) {
            binding.apply {
                idImage.setOnClickListener {
                    listener.getEvent(model, idImage, idTitle,idImageIcon)
                }
                Glide.with(itemView.context).load(model.image).centerCrop().transform(
                    RoundedCornersTransformation(
                        itemView.context, idImage, 32, RoundedCornersTransformation.CornerType.ALL
                    )
                ).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .placeholder(R.drawable.placeholder_round).into(idImage)
                idTitle.text = model.title
            }
        }
    }

    companion object {
        const val ITEM_HORIZONTAL = 0
        const val ITEM_VERTICAL = 1
    }
}