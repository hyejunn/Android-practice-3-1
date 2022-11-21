package com.example.project4_6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var btn1 : Button;   lateinit var btn2 : Button
    lateinit var btn3 : Button;   lateinit var btn4 : Button
    lateinit var btn5 : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "직접해보기 6번 문제"

        btn1 = findViewById<Button>(R.id.Btn1)
        btn2 = findViewById<Button>(R.id.Btn2)
        btn3 = findViewById<Button>(R.id.Btn3)
        btn4 = findViewById<Button>(R.id.Btn4)
        btn5 = findViewById<Button>(R.id.Btn5)

        btn1.setOnTouchListener { view, motionEvent ->
            btn1.visibility = View.INVISIBLE
            btn2.visibility = View.VISIBLE
            false
        }
        btn2.setOnTouchListener { view, motionEvent ->
            btn2.visibility = View.INVISIBLE
            btn3.visibility = View.VISIBLE
            false
        }
        btn3.setOnTouchListener { view, motionEvent ->
            btn3.visibility = View.INVISIBLE
            btn4.visibility = View.VISIBLE
            false
        }
        btn4.setOnTouchListener { view, motionEvent ->
            btn4.visibility = View.INVISIBLE
            btn5.visibility = View.VISIBLE
            false
        }
        btn5.setOnTouchListener { view, motionEvent ->
            btn5.visibility = View.INVISIBLE
            btn1.visibility = View.VISIBLE
            false
        }
    }
}