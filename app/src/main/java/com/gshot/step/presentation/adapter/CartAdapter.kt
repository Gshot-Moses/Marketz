package com.gshot.step.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gshot.step.R
import com.gshot.step.presentation.model.Product

class CartAdapter: ListAdapter<Product, CartAdapter.ViewHolder>(DIFFPRODUCT) {

    private var productList = emptyList<Product>().toMutableList()
    lateinit var updateListener: (qty: Int) -> Unit
    lateinit var removeProductFromCartListener: (product: Product) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(productList[position])
        holder.add.setOnClickListener {
            val qty = holder.qtyTv.text.toString().toInt() + 1
            updateListener.invoke(qty)
            holder.qtyTv.text = qty.toString()
        }
        holder.reduce.setOnClickListener {
            var qty = holder.qtyTv.text.toString().toInt()
            if (qty == 1) {
                removeProductFromCartListener.invoke(productList[position])
                productList.removeAt(position)
                notifyItemRemoved(position)
            } else {
                qty -= 1
                updateListener.invoke(qty)
                holder.qtyTv.text = qty.toString()
            }
        }
    }

    fun setQtyUpdateListener(listener: (qty: Int) -> Unit) {
        updateListener = listener
    }

    fun setOnRemoveProductCartFromCartListener(listener: (product: Product) -> Unit) {
        removeProductFromCartListener = listener
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    fun setData(products: List<Product>) {
        this.productList = products as MutableList<Product>
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        val nameTv: TextView
        val imageView: ImageView
        val add: ImageView
        val reduce: ImageView
        val qtyTv: TextView

        init {
            nameTv = itemView.findViewById(R.id.product_name)
            imageView = itemView.findViewById(R.id.product_image)
            add = itemView.findViewById(R.id.add_product)
            reduce = itemView.findViewById(R.id.reduce_product)
            qtyTv = itemView.findViewById(R.id.product_qty)
        }

        fun bind(product: Product) {
            nameTv.text = product.name
        }
    }
}