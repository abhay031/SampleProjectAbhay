package com.newsbytes.abhay.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.newsbytes.abhay.databinding.AdapterStringBinding
import com.newsbytes.abhay.jetpack.bases.OnEventTriggerListener

class StringAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var modelList: ArrayList<String> = arrayListOf()
    private var size = 0


    fun notifyAdapterDataSetChanged(modelList: ArrayList<String>) {
        this.modelList.clear()
        this.modelList.addAll(modelList)
        size = modelList.size
        notifyDataSetChanged()
    }

    fun clearAdapterItem() {
        size = 0
        modelList = arrayListOf()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return VerticalViewHolder(
            AdapterStringBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is VerticalViewHolder -> holder.bindTo(modelList[position], position)
        }

    }

    override fun getItemCount(): Int {
        return size
    }

    inner class VerticalViewHolder(private val binding: AdapterStringBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(model: String, position: Int) {
            binding.idText.text = model
        }
    }

}