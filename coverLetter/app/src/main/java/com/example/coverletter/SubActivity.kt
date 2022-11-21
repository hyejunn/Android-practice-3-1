package com.example.coverletter
import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toBitmap

class SubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)
        val korName:TextView = findViewById(R.id.korNameSub)
        val engName:TextView = findViewById(R.id.engNameSub)
        val contact:TextView = findViewById(R.id.contactSub)
        val email:TextView = findViewById(R.id.emailSub)
        val address:TextView = findViewById(R.id.addressSub)
        val btn:Button = findViewById(R.id.btnSub)
        val photo: ImageView = findViewById(R.id.imgSub)

        val bitmap = intent.getParcelableExtra<Bitmap>("image")
        photo.setImageBitmap(bitmap)
        korName.text =intent.getStringExtra("name1").toString()
        engName.text = intent.getStringExtra("name2").toString()
        contact.text = intent.getStringExtra("contact").toString()
        email.text = intent.getStringExtra("email").toString()
        address.text = intent.getStringExtra("address").toString()

        btn.setOnClickListener{
            setResult(RESULT_OK, intent)
            finish()
        }
    }
}