package com.example.cyberescape

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LevelSelectActivity : AppCompatActivity() {

    private lateinit var btnLevel1: Button
    private lateinit var btnLevel2: Button
    private lateinit var btnLevel3: Button

    private lateinit var txtLevel1: TextView
    private lateinit var txtLevel2: TextView
    private lateinit var txtLevel3: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level_select)

        btnLevel1 = findViewById(R.id.btnLevel1)
        btnLevel2 = findViewById(R.id.btnLevel2)
        btnLevel3 = findViewById(R.id.btnLevel3)

        txtLevel1 = findViewById(R.id.txtLevel1)
        txtLevel2 = findViewById(R.id.txtLevel2)
        txtLevel3 = findViewById(R.id.txtLevel3)

        updateLevelStatus()

        btnLevel1.setOnClickListener {
            startActivity(Intent(this, Level1Activity::class.java))
        }

        btnLevel2.setOnClickListener {
            Toast.makeText(
                this,
                "Complete Level 1 first!",
                Toast.LENGTH_SHORT
            ).show()
        }

        btnLevel3.setOnClickListener {
            Toast.makeText(
                this,
                "Complete Level 2 first!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onResume() {
        super.onResume()
        updateLevelStatus()
    }

    private fun updateLevelStatus() {
        txtLevel1.text = "🔓 UNLOCKED"

        txtLevel2.text = "🔒 LOCKED (Complete Level 1)"
        btnLevel2.alpha = 0.5f

        txtLevel3.text = "🔒 LOCKED (Complete Level 2)"
        btnLevel3.alpha = 0.5f
    }
}