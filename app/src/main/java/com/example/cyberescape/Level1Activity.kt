package com.example.cyberescape

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Level1Activity : AppCompatActivity() {

    private var lives = 3
    private var countDownTimer: CountDownTimer? = null

    companion object {
        private const val PASSWORD_HASH =
            "30a65d30d595644c68c26be0c561607a220ff1e314bab00f86da8d477733735a"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level1)

        val timerView = findViewById<TextView>(R.id.txtTimer)
        val livesView = findViewById<TextView>(R.id.txtLives)
        val passwordInput = findViewById<EditText>(R.id.edtPassword)
        val submitBtn = findViewById<Button>(R.id.btnSubmit)

        livesView.text = "Lives: $lives"

        countDownTimer = object : CountDownTimer(60000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                timerView.text = "TIME: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                startActivity(
                    Intent(
                        this@Level1Activity,
                        GameOverActivity::class.java
                    )
                )
                finish()
            }
        }.start()

        submitBtn.setOnClickListener {

            val enteredPassword =
                passwordInput.text.toString().trim()

            val enteredHash =
                SecurityUtils.hashSHA256(enteredPassword)

            if (enteredHash.equals(PASSWORD_HASH, ignoreCase = true)) {

                countDownTimer?.cancel()

                Toast.makeText(
                    this,
                    "Level 1 Cleared!",
                    Toast.LENGTH_SHORT
                ).show()

                startActivity(
                    Intent(
                        this,
                        Level2Activity::class.java
                    )
                )

                finish()

            } else {

                lives--

                livesView.text = "Lives: $lives"

                if (lives <= 0) {

                    countDownTimer?.cancel()

                    startActivity(
                        Intent(
                            this,
                            GameOverActivity::class.java
                        )
                    )

                    finish()

                } else {

                    Toast.makeText(
                        this,
                        "Wrong Password!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}