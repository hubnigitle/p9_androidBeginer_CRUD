package com.example.p9_androidbeginer_crud.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.p9_androidbeginer_crud.Api.RetrofitClient
import com.example.p9_androidbeginer_crud.Models.CreateUpdateProductRequest
import com.example.p9_androidbeginer_crud.Models.CreateUpdateProductResponse
import com.example.p9_androidbeginer_crud.R
import com.example.p9_androidbeginer_crud.databinding.ActivityModifyProductBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModifyProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModifyProductBinding

    companion object {
        private const val TAG = "ModifyProductActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityModifyProductBinding.inflate(layoutInflater)
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

            toolbar.setTitle(getString(R.string.add_product))
            btnSave.text = getString(R.string.add_product)
            btnDelete.visibility = View.GONE

            btnSave.setOnClickListener {
                addProduct()
            }
        }
    }

    private fun addProduct() {
        val productName = binding.tietProductName.text.toString().trim()
        val productPrice = binding.tietProductPrice.text.toString().trim()
        val productDesc = binding.tietProductDesc.text.toString().trim()

        if (productName.isEmpty() || productPrice.isEmpty() || productDesc.isEmpty()) {
            Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            return
        }

        RetrofitClient.instance.addProduct(
            product = CreateUpdateProductRequest(
                title = productName,
                price = productPrice.toDouble(),
                description = productDesc
            )
        ).enqueue(object: Callback<CreateUpdateProductResponse>{
            override fun onResponse(
                call: Call<CreateUpdateProductResponse>,
                response: Response<CreateUpdateProductResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d(TAG, "onResponse: ${response.body()}")
                    Toast.makeText(this@ModifyProductActivity, "Product berhasil ditambahkan", Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    Log.d(TAG, "onResponse: ${response.body()}")
                    Toast.makeText(this@ModifyProductActivity, "Product gagal ditambahkan", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<CreateUpdateProductResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
                Toast.makeText(this@ModifyProductActivity, "Product gagal ditambahkan", Toast.LENGTH_SHORT).show()
            }
        })
    }
}