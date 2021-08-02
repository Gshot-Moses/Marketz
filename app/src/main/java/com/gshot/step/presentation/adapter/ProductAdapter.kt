package com.gshot.step.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gshot.step.R
import com.gshot.step.model.Product

class ProductAdapter: ListAdapter<Product, ProductAdapter.ViewHolder>(DIFFPRODUCT) {

    var productList = emptyList<Product>()
    private lateinit var listener: (product: Product) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
        holder.itemView.setOnClickListener {
            listener.invoke(productList[position])
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setItemClickListener(listener: (product: Product)-> Unit) {
        this.listener = listener
    }

    fun setData(products: List<Product>) {
        productList = products
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val productTitle: TextView
        val productPrice: TextView

        init {
            productTitle = itemView.findViewById(R.id.product_title)
            productPrice = itemView.findViewById(R.id.product_price)
        }

        fun bind(product: Product) {
            productTitle.text = product.productName
            productPrice.text = product.productPrice.toString()
        }
    }
}

val DIFFPRODUCT = object: DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
        return oldItem.id == newItem.id
    }
}