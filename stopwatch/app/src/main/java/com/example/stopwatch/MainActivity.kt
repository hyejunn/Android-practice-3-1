package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.stopwatch.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        var binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.activityMain)

        val startButton : Button = findViewById(R.id.Start)
        val stopButton : Button = findViewById(R.id.Stop)
        val pauseButton : Button = findViewById(R.id.Pause)
        val resumeButton : Button = findViewById(R.id.Resume)
        var status = 0

        var time = 0
        val channel = Channel<Int>()
        val backgroundScope = CoroutineScope(Dispatchers.Default + Job())
        backgroundScope.launch {
            while (true) {
                if (status == 0) {
                    time = 0
                    channel.send(time.toInt())
                }
                if (status == 1) {
                    time += 1
                    delay(10)
                    channel.send(time.toInt())
                }
            }
        }
        var mainScope = GlobalScope.launch(Dispatchers.Main) {
            channel.consumeEach {
                var s = it / 60
                var min = s / 60
                var ms = time % 60
                s = s % 60
                binding.resultView.text = String.format("%02d:%02d:%02d", min, s, ms)
            }
        }

        startButton.setOnClickListener {
            if (status == 0) status = 1
        }
        stopButton.setOnClickListener {
            status = 0
        }
        pauseButton.setOnClickListener {
            if (status == 1) status = 2
        }
        resumeButton.setOnClickListener {
            if (status == 2) status = 1
        }
    }
}