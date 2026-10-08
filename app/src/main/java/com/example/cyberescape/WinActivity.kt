package com.example.cyberescape

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_win)

        val btnPlayAgain = findViewById<Button>(R.id.btnPlayAgain)
        val btnMainMenuWin = findViewById<Button>(R.id.btnMainMenuWin)

        btnPlayAgain.setOnClickListener {
            startActivity(Intent(this, LevelSelectActivity::class.java))
            finish()
        }

        btnMainMenuWin.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
