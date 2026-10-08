package com.example.cyberescape

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class GameOverActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_over)

        val btnRetry = findViewById<Button>(R.id.btnRetry)
        val btnMainMenu = findViewById<Button>(R.id.btnMainMenu)

        btnRetry.setOnClickListener {
            startActivity(Intent(this, LevelSelectActivity::class.java))
            finish()
        }

        btnMainMenu.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}