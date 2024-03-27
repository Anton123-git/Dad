package com.example.dad


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class VerificationActivity : AppCompatActivity() {

    private lateinit var resendText: TextView
    private lateinit var timerTextView: TextView
    private lateinit var editTextList: List<EditText>
    private lateinit var nextButtonVer: Button
    private lateinit var countDownTimer: CountDownTimer

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification)

        resendText = findViewById(R.id.txtResend)
        timerTextView = findViewById(R.id.timerTextView)
        editTextList = listOf(
            findViewById(R.id.etCode1),
            findViewById(R.id.etCode2),
            findViewById(R.id.etCode3),
            findViewById(R.id.etCode4),
            findViewById(R.id.etCode5),
            findViewById(R.id.etCode6)
        )
        nextButtonVer = findViewById(R.id.next_buttonVer)
        nextButtonVer.setOnClickListener {
            // Создаем Intent для перехода на LoginActivity
            val intent = Intent(this, PasswordActivity::class.java)
            // Запускаем LoginActivity
            startActivity(intent)
        }


        editTextList.forEachIndexed { index, editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (s?.length == 1 && index < editTextList.lastIndex) {
                        editTextList[index + 1].requestFocus()
                    }
                    checkFields()
                }
                override fun afterTextChanged(s: Editable?) {}
            })
        }

        startTimer()
        checkFields()
    }

    private fun checkFields() {
        val isAllFieldsFilled = editTextList.all { it.text.toString().isNotBlank() }
        nextButtonVer.isEnabled = isAllFieldsFilled
        if (isAllFieldsFilled) {
            nextButtonVer.setBackgroundResource(R.drawable.rounded_button_blue)
        } else {
            nextButtonVer.setBackgroundResource(R.drawable.rounded_button_gray)
        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 60000
                val seconds = (millisUntilFinished % 60000) / 1000
                val timeLeftFormatted = String.format("%02d:%02d", minutes, seconds)
                timerTextView.text = timeLeftFormatted
            }

            override fun onFinish() {
                timerTextView.text = ""
                resendText.setTextColor(resources.getColor(android.R.color.holo_blue_dark))
                resendText.setOnClickListener {
                    startTimer()
                }
            }
        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}