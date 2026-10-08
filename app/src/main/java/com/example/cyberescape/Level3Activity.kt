package com.example.cyberescape

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Level3Activity : AppCompatActivity() {

    private lateinit var txtTimer: TextView
    private lateinit var txtQuestion: TextView

    private lateinit var btnA: Button
    private lateinit var btnB: Button
    private lateinit var btnC: Button
    private lateinit var btnD: Button

    private var currentQuestion = 0
    private var timer: CountDownTimer? = null

    private val questions = arrayOf(
        "SECURITY BREACH DETECTED\nChoose Action",
        "SECOND ATTACK DETECTED\nChoose Action",
        "FINAL ATTACK\nChoose Action"
    )

    private val options = arrayOf(
        arrayOf("Disable Firewall", "Block IP", "Ignore Alert", "Disconnect Server"),
        arrayOf("Share Password", "Disable Encryption", "Reset Password", "Ignore"),
        arrayOf("Enable Firewall", "Delete Logs", "Disable Firewall", "Ignore")
    )

    private val answers = arrayOf(
        "Block IP",
        "Reset Password",
        "Enable Firewall"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level3)

        txtTimer = findViewById(R.id.txtTimer3)
        txtQuestion = findViewById(R.id.txtQuestion3)

        btnA = findViewById(R.id.btnA3)
        btnB = findViewById(R.id.btnB3)
        btnC = findViewById(R.id.btnC3)
        btnD = findViewById(R.id.btnD3)

        loadQuestion()
        startTimer()

        btnA.setOnClickListener { checkAnswer(btnA.text.toString()) }
        btnB.setOnClickListener { checkAnswer(btnB.text.toString()) }
        btnC.setOnClickListener { checkAnswer(btnC.text.toString()) }
        btnD.setOnClickListener { checkAnswer(btnD.text.toString()) }
    }

    private fun loadQuestion() {

        txtQuestion.text = questions[currentQuestion]

        btnA.text = options[currentQuestion][0]
        btnB.text = options[currentQuestion][1]
        btnC.text = options[currentQuestion][2]
        btnD.text = options[currentQuestion][3]
    }

    private fun checkAnswer(selected: String) {

        if (selected == answers[currentQuestion]) {

            currentQuestion++

            if (currentQuestion < questions.size) {

                loadQuestion()

            } else {

                timer?.cancel()

                Toast.makeText(
                    this,
                    "You Win!",
                    Toast.LENGTH_LONG
                ).show()

                startActivity(
                    Intent(
                        this,
                        WinActivity::class.java
                    )
                )

                finish()
            }

        } else {

            Toast.makeText(
                this,
                "Wrong Answer!",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun startTimer() {

        timer = object : CountDownTimer(60000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                txtTimer.text =
                    "TIME: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {

                startActivity(
                    Intent(
                        this@Level3Activity,
                        GameOverActivity::class.java
                    )
                )

                finish()
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer?.cancel()
    }
}