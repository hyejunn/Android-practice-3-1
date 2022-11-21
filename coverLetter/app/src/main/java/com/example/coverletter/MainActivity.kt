package com.example.coverletter

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap


class MainActivity : AppCompatActivity() {
    val GALLERY = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.btnMain)
        val korName : EditText = findViewById<EditText>(R.id.korNameMain)
        val engName : EditText = findViewById<EditText>(R.id.engNameMain)
        val contact : EditText = findViewById<EditText>(R.id.contactMain)
        val email : EditText = findViewById<EditText>(R.id.emailMain)
        val address : EditText = findViewById<EditText>(R.id.addressMain)
        val photo: ImageView = findViewById(R.id.imgMain)

        photo.setOnClickListener{
            openGallery()

        }

        btn.setOnClickListener{
            val name1: String = korName.text.toString(); val name2: String = engName.text.toString()
            val contactStr: String = contact.text.toString();   val emailStr: String = email.text.toString()
            val addressStr: String = address.text.toString()

            val intent = Intent(this,SubActivity::class.java)
            intent.putExtra("name1",name1)
            intent.putExtra("name2",name2)
            intent.putExtra("contact",contactStr)
            intent.putExtra("email",emailStr)
            intent.putExtra("address",addressStr)
            val bitmap = photo.drawable.toBitmap()
            intent.putExtra("image",bitmap)
            startActivityForResult(intent,100)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK)
        {
            when(requestCode) {
                GALLERY -> {
                    var ImageData: Uri? = data?.data
                    try {

                        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, ImageData)
                        val pt: ImageView = findViewById(R.id.imgMain)
                        val resize = resizeBitmap(bitmap, 300, 300)
                        pt.setImageBitmap(resize)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }



    private fun openGallery() {
        val intent: Intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.setType("image/*")
        startActivityForResult(intent, GALLERY)
    }

    private fun resizeBitmap(bitmap: Bitmap, width:Int, height:Int):Bitmap{
        return Bitmap.createScaledBitmap(
            bitmap,
            300,
            300,
            false
        )
    }
}