package com.example.project4_7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    lateinit var img : ImageView
    lateinit var left : Button; lateinit var right : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "바들바들 동물콘"
        
        img = findViewById<ImageView>(R.id.imgView)
        left = findViewById<Button>(R.id.left)
        right = findViewById<Button>(R.id.right)

        var i : Int = 0
        var list = mutableListOf(
            R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d,
            R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h,
            R.drawable.i, R.drawable.j, R.drawable.k, R.drawable.l,
            R.drawable.m, R.drawable.n, R.drawable.o, R.drawable.p,
            R.drawable.q, R.drawable.r, R.drawable.s, R.drawable.t,
            R.drawable.u, R.drawable.v, R.drawable.w, R.drawable.x,
            R.drawable.y, R.drawable.z)
        left.setOnTouchListener { view, motionEvent ->
            i++
            if (i == list.size)
                i = 0
            img.setImageResource(list[i])
            false
        }
        right.setOnTouchListener { view, motionEvent ->
            i--
            if (i == -1)
                i = list.size - 1
            img.setImageResource(list[i])
            false
        }
    }
}