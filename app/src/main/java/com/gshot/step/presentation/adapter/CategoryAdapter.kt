package com.gshot.step.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gshot.step.R
import com.gshot.step.model.Category

class CategoryAdapter: ListAdapter<Category, CategoryAdapter.ViewHolder>(DIFF) {

    private var categoryList = emptyList<Category>()
    private lateinit var listener: (id: Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(categoryList[position])
        holder.itemView.setOnClickListener { listener.invoke(categoryList[position].id.toInt()) }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    fun setItemClickedListener(listener: (id: Int) -> Unit) {
        this.listener = listener
    }

    fun setData(categories: List<Category>) {
        categoryList = categories
        notifyDataSetChanged()
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val categoryTextView: TextView
        val categoryCard: CardView

        init {
            categoryTextView = view.findViewById(R.id.categoryName)
            categoryCard = view.findViewById(R.id.categoryCard)
        }

        fun bindData(category: Category) {
            categoryTextView.text = category.name
        }
    }

    interface OnItemClicked {
        fun onClicked(id: Int)
    }
}

val DIFF = object: DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id && oldItem.name == newItem.name
    }
}