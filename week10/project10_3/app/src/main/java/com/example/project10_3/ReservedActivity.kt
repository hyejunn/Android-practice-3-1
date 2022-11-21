package com.example.project10_3

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_reserved.*
import java.io.Serializable

data class ReservedMovie(
    val _id:Int?,
    val name:String?,
    val poster_image:String?,
    val director: String?,
    val synopsis: String?,
    val reserved_time: String?
): Serializable

class ReservedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserved)

        processIntent(intent)
        viewbutton.setOnClickListener{
            finish()
        }
    }

    fun processIntent(intent: Intent?){
        val movies = intent?.getSerializableExtra("movies") as ArrayList<ReservedMovie>?
        val movie = movies?.get(0)
        if(movie!=null){
            posterImageView.setImageURI(Uri.parse(movie.poster_image))
            input1.text = movie.name
            input2.text = movie.reserved_time
            input3.text = movie.director
            input4.text = movie.synopsis
        }
    }
}