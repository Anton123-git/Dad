package com.example.dad
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText

class PasswordActivity : AppCompatActivity() {

    private lateinit var pAddressEmail: EditText
    private lateinit var pPassword: EditText
    private lateinit var nextButtonPass: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password)



        // Инициализируем view-элементы
        pAddressEmail = findViewById(R.id.pAddressEmail)
        pPassword = findViewById(R.id.pPassword)
        nextButtonPass = findViewById(R.id.next_buttonPass)

        // Устанавливаем слушатель изменения текста для полей ввода
        pAddressEmail.addTextChangedListener(textWatcher)
        pPassword.addTextChangedListener(textWatcher)

        // Начальное состояние кнопки - серая и неактивная
        nextButtonPass.isEnabled = false

        // Установка слушателя кликов на кнопку

    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            // Вызываем функцию проверки всех полей на заполненность и устанавливаем состояние кнопки
            checkFieldsForEmpty()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    }

    private fun checkFieldsForEmpty() {
        // Проверяем оба поля на заполненность
        val emailText = pAddressEmail.text.toString().trim()
        val passwordText = pPassword.text.toString().trim()

        // Если оба поля заполнены, делаем кнопку активной и меняем её цвет на синий
        if (emailText.isNotEmpty() && passwordText.isNotEmpty()) {
            nextButtonPass.isEnabled = true
            nextButtonPass.setBackgroundResource(R.drawable.rounded_button_blue)
        } else {
            // Если какое-то поле пустое, делаем кнопку серой и неактивной
            nextButtonPass.isEnabled = false
            nextButtonPass.setBackgroundResource(R.drawable.rounded_button_gray)
        }
    }

}


