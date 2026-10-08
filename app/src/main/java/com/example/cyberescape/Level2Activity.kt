package com.example.cyberescape

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Level2Activity : AppCompatActivity() {

    private lateinit var txtQuestion: TextView
    private lateinit var txtTimer: TextView
    private lateinit var radioGroup: RadioGroup
    private lateinit var option1: RadioButton
    private lateinit var option2: RadioButton
    private lateinit var option3: RadioButton
    private lateinit var option4: RadioButton
    private lateinit var btnNext: Button

    private var currentQuestion = 0
    private var score = 0
    private var countDownTimer: CountDownTimer? = null

    private val questions = arrayOf(
        arrayOf(
            "Which attack injects malicious SQL commands?",
            "Phishing",
            "SQL Injection",
            "DDoS",
            "Brute Force"
        ),
        arrayOf(
            "Which is commonly used for credential theft?",
            "Phishing",
            "Encryption",
            "Hashing",
            "Firewall"
        ),
        arrayOf(
            "Which protects data during transmission?",
            "HTTP",
            "HTTPS",
            "FTP",
            "Telnet"
        )
    )

    private val correctAnswers = arrayOf(
        "SQL Injection",
        "Phishing",
        "HTTPS"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_level2)

        txtQuestion = findViewById(R.id.txtQuestion)
        txtTimer = findViewById(R.id.txtTimer)

        radioGroup = findViewById(R.id.radioGroup)

        option1 = findViewById(R.id.option1)
        option2 = findViewById(R.id.option2)
        option3 = findViewById(R.id.option3)
        option4 = findViewById(R.id.option4)

        btnNext = findViewById(R.id.btnNext)

        loadQuestion()

        countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                txtTimer.text = "TIME: ${millisUntilFinished / 1000}"
            }

            override fun onFinish() {
                startActivity(
                    Intent(
                        this@Level2Activity,
                        GameOverActivity::class.java
                    )
                )
                finish()
            }
        }.start()

        btnNext.setOnClickListener {

            val selectedId = radioGroup.checkedRadioButtonId

            if (selectedId == -1) {
                Toast.makeText(
                    this,
                    "Please select an option",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            val selectedButton =
                findViewById<RadioButton>(selectedId)

            val selectedAnswer =
                selectedButton.text.toString()

            if (selectedAnswer == correctAnswers[currentQuestion]) {

                score++

                Toast.makeText(
                    this,
                    "Correct!",
                    Toast.LENGTH_SHORT
                ).show()

                currentQuestion++

                if (currentQuestion < questions.size) {
                    loadQuestion()
                } else {

                    countDownTimer?.cancel()

                    if (score >= 2) {
                        startActivity(
                            Intent(
                                this,
                                Level3Activity::class.java
                            )
                        )
                    } else {
                        startActivity(
                            Intent(
                                this,
                                GameOverActivity::class.java
                            )
                        )
                    }

                    finish()
                }

            } else {

                Toast.makeText(
                    this,
                    "Wrong Answer! Try Again.",
                    Toast.LENGTH_SHORT
                ).show()

                radioGroup.clearCheck()
            }
        }
    }

    private fun loadQuestion() {

        radioGroup.clearCheck()

        txtQuestion.text = questions[currentQuestion][0]

        option1.text = questions[currentQuestion][1]
        option2.text = questions[currentQuestion][2]
        option3.text = questions[currentQuestion][3]
        option4.text = questions[currentQuestion][4]
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }
}