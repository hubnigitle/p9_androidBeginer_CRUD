package com.example.p9_androidbeginer_crud.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.example.p9_androidbeginer_crud.Adapter.RvProductAdapter
import com.example.p9_androidbeginer_crud.Api.RetrofitClient
import com.example.p9_androidbeginer_crud.Models.ProductResponse
import com.example.p9_androidbeginer_crud.Models.ProductsItem
import com.example.p9_androidbeginer_crud.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var productAdapter: RvProductAdapter
    private var listProduct: List<ProductsItem?>? = emptyList()

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition { listProduct.isNullOrEmpty() }

        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        loadProduct()
        initViews()
    }

    private fun initViews() {
        with(binding) {
            btnAddProduct.setOnClickListener {
                intentToModify(product = null, sourceIntent = false)
            }
        }
    }

    private fun intentToModify(product: ProductsItem?, sourceIntent: Boolean) {
        val intent = Intent(this@MainActivity, ModifyProductActivity::class.java).apply {
            putExtra(ModifyProductActivity.EXTRA_PRODUCT, product)
            putExtra(ModifyProductActivity.SOURCE_INTENT, sourceIntent)
        }
        startActivity(intent)
    }

    private fun loadProduct() {
        RetrofitClient.instance.getAllProducts().enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        Log.d(TAG, "onResponse: ${responseBody.products}")
                        listProduct = responseBody.products

                        with(binding) {
                            productAdapter = RvProductAdapter(
                                listProduct = listProduct,
                                onItemClick = { intentToDetail(product = it) },
                                onHoldItemClick = { intentToModify(product = it, sourceIntent = true) }
                            )

                            rvProduct.apply {
                                adapter = productAdapter
                                layoutManager = GridLayoutManager(this@MainActivity, 2)
                            }
                        }
                    }
                } else {
                    Log.d(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }
        })
    }

    private fun intentToDetail(product: ProductsItem) {
        val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
            putExtra(DetailActivity.EXTRA_PRODUCT, product)
        }
        startActivity(intent)
    }

}