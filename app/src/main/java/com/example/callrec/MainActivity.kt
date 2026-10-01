package com.example.callrec

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.LinearLayout
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val permLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(48, 96, 48, 48)
        }

        val btnPerm = Button(this).apply {
            text = "1. دادن مجوزها"
            setOnClickListener {
                permLauncher.launch(
                    arrayOf(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.READ_PHONE_STATE
                    )
                )
            }
        }

        val btnAcc = Button(this).apply {
            text = "2. تنظیمات Accessibility"
            setOnClickListener {
                startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
            }
        }

        val btnStart = Button(this).apply {
            text = "3. شروع سرویس ضبط"
            setOnClickListener {
                ContextCompat.startForegroundService(
                    this@MainActivity,
                    Intent(this@MainActivity, CallRecorderService::class.java)
                )
            }
        }

        val btnStop = Button(this).apply {
            text = "توقف سرویس"
            setOnClickListener {
                stopService(Intent(this@MainActivity, CallRecorderService::class.java))
            }
        }

        layout.addView(btnPerm)
        layout.addView(btnAcc)
        layout.addView(btnStart)
        layout.addView(btnStop)
        setContentView(layout)
    }
}
