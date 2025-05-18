package com.example.p9_androidbeginer_crud.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.p9_androidbeginer_crud.Models.ProductsItem
import com.example.p9_androidbeginer_crud.R
import com.example.p9_androidbeginer_crud.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private var product : ProductsItem? = null

    companion object {
        private const val TAG = "DetailActivity"
        const val EXTRA_PRODUCT = "extra_product"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
    }

    private fun initViews() {
        with(binding) {
            toolbar.setNavigationOnClickListener {
                finish()
            }

            product = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(EXTRA_PRODUCT, ProductsItem::class.java)
            } else {
                intent.getParcelableExtra(EXTRA_PRODUCT)
            }

            Log.d(TAG, "Produk: $product")

            if (product != null) {
                tvProductName.text = product?.title
                tvProductPrice.text = "$${product?.price}"
                tvProductDesc.text = product?.description
                Glide.with(this@DetailActivity).load(product?.thumbnail).into(ivProduct)
            }
        }
    }
}