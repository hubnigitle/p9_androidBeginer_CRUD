package com.example.p9_androidbeginer_crud.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.p9_androidbeginer_crud.Models.ProductsItem
import com.example.p9_androidbeginer_crud.databinding.ItemProductsBinding

class RvProductAdapter(
    private val listProduct: List<ProductsItem?>?,
    private val onItemClick: (ProductsItem) -> Unit,
    private val onHoldItemClick: (ProductsItem) -> Unit
) : RecyclerView.Adapter<RvProductAdapter.ProductViewHolder>() {

    inner class ProductViewHolder(private val binding: ItemProductsBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(product: ProductsItem) {
            with(binding) {
                tvItemName.text = product.title
                tvItemPrice.text = "$${product.price}"
                Glide.with(itemView).load(product.thumbnail).into(ivItemProduct)

                itemView.setOnClickListener {
                    onItemClick(product)
                }
                itemView.setOnLongClickListener {
                    onHoldItemClick(product)
                    true
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = listProduct?.get(position)
        if (product != null) {
            holder.bind(product)
        }
    }

    override fun getItemCount(): Int = listProduct?.size ?: 0
}