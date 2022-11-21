package com.example.project4_5

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {
    lateinit var linLayer : LinearLayout; lateinit var change : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "직접해보기1번 문제"
        linLayer = findViewById<LinearLayout>(R.id.LinLay)
        change = findViewById(R.id.Change)
        var i :Int = 0
        change.setOnClickListener {
            if(i==0) {
                linLayer.setBackgroundColor(Color.parseColor("#00FF00"))
            }
            if(i==1) {
                linLayer.setBackgroundColor(Color.parseColor("#0000FF"))
            }
            if(i==2) {
                linLayer.setBackgroundColor(Color.parseColor("#FF0000"))
            }
            if(i!=2) i=i+1
            else i=0
        }
    }
}