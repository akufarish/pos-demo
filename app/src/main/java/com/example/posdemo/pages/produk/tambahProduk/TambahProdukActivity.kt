package com.example.posdemo.pages.produk.tambahProduk

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.posdemo.databinding.ActivityTambahProdukBinding
import com.example.posdemo.requests.BarangRequest
import com.example.posdemo.services.barang.BarangServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

class TambahProdukActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTambahProdukBinding
    private lateinit var image: File

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()){uri ->
        if (uri != null) {
            binding.uploadImage.setImageURI(uri)
            CoroutineScope(Dispatchers.IO).launch {
                val file = getFileFromUri(uri)
                CoroutineScope(Dispatchers.IO).launch {
                    val link = file
                    image = file!!
                    Log.d("gambar", link.toString())
                }
            }
        }
    }

    fun getFileFromUri(uri: Uri): File? {
        val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(uri, filePathColumn, null, null, null)
        cursor?.moveToFirst()
        val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
        val filePath = cursor?.getString(columnIndex!!)
        cursor?.close()
        return filePath?.let { File(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTambahProdukBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tambahButton.setOnClickListener {

            val namaEditText = binding.namaBarangEditText.text.toString()
            val hargaProdukEditText = binding.hargaEditText.text.toString()

            val payload = BarangRequest(
                nama_produk = MultipartBody.Part
                    .createFormData(
                        "nama_produk",
                        namaEditText
                    ),
                harga_produk = MultipartBody.Part.createFormData(
                    "harga_produk",
                    hargaProdukEditText
                ),
                image = MultipartBody.Part
                    .createFormData(
                        "image",
                        image.name,
                        image.asRequestBody()
                    ),
                pcs = MultipartBody.Part.createFormData(
                    "pcs",
                    "2"
                )
            )

            BarangServices.storeBarang(payload, applicationContext)

        }

        binding.uploadImage.setOnClickListener {
            pickMedia.launch(
                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo)
            )
        }
    }
}