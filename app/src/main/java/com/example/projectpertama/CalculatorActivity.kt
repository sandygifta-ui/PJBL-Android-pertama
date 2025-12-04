package com.example.projectpertama

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculatorActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private var currentInput = ""
    private var operator = ""
    private var firstValue = 0.0
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        tvDisplay = findViewById(R.id.tvDisplay)

        // Number buttons
        val btn0: Button = findViewById(R.id.btn0)
        val btn1: Button = findViewById(R.id.btn1)
        val btn2: Button = findViewById(R.id.btn2)
        val btn3: Button = findViewById(R.id.btn3)
        val btn4: Button = findViewById(R.id.btn4)
        val btn5: Button = findViewById(R.id.btn5)
        val btn6: Button = findViewById(R.id.btn6)
        val btn7: Button = findViewById(R.id.btn7)
        val btn8: Button = findViewById(R.id.btn8)
        val btn9: Button = findViewById(R.id.btn9)

        // Operator buttons
        val btnAdd: Button = findViewById(R.id.btnAdd)
        val btnSubtract: Button = findViewById(R.id.btnClear)
        val btnMultiply: Button = findViewById(R.id.btnMultiply)
        val btnDivide: Button = findViewById(R.id.btnDivide)
        val btnEquals: Button = findViewById(R.id.btnEquals)
        val btnClear: Button = findViewById(R.id.btnClear)
        val btnDot: Button = findViewById(R.id.btnDot)

        // Set click listeners for number buttons
        val numberButtons = listOf(btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9)
        numberButtons.forEach { button ->
            button.setOnClickListener {
                onNumberClick(button.text.toString())
            }
        }

        // Set click listeners for operator buttons
        btnAdd.setOnClickListener { onOperatorClick("+") }
        btnClear.setOnClickListener { onOperatorClick("-") }
        btnMultiply.setOnClickListener { onOperatorClick("×") }
        btnDivide.setOnClickListener { onOperatorClick("÷") }

        btnEquals.setOnClickListener { onEqualsClick() }
        btnClear.setOnClickListener { onClearClick() }
        btnDot.setOnClickListener { onDotClick() }
    }

    private fun onNumberClick(number: String) {
        if (isNewOperation) {
            currentInput = number
            isNewOperation = false
        } else {
            currentInput += number
        }
        updateDisplay()
    }

    private fun onOperatorClick(op: String) {
        if (currentInput.isNotEmpty()) {
            if (operator.isNotEmpty()) {
                onEqualsClick()
            }
            firstValue = currentInput.toDouble()
            operator = op
            isNewOperation = true
        }
    }

    private fun onEqualsClick() {
        if (currentInput.isNotEmpty() && operator.isNotEmpty()) {
            val secondValue = currentInput.toDouble()
            val result = when (operator) {
                "+" -> firstValue + secondValue
                "-" -> firstValue - secondValue
                "×" -> firstValue * secondValue
                "÷" -> {
                    if (secondValue != 0.0) {
                        firstValue / secondValue
                    } else {
                        tvDisplay.text = "Error"
                        resetCalculator()
                        return
                    }
                }
                else -> 0.0
            }

            // Format result to remove unnecessary decimals
            currentInput = if (result % 1.0 == 0.0) {
                result.toInt().toString()
            } else {
                result.toString()
            }

            updateDisplay()
            operator = ""
            isNewOperation = true
        }
    }

    private fun onClearClick() {
        resetCalculator()
        updateDisplay()
    }

    private fun onDotClick() {
        if (isNewOperation) {
            currentInput = "0."
            isNewOperation = false
        } else if (!currentInput.contains(".")) {
            currentInput += "."
        }
        updateDisplay()
    }

    private fun updateDisplay() {
        tvDisplay.text = if (currentInput.isEmpty()) "0" else currentInput
    }

    private fun resetCalculator() {
        currentInput = ""
        operator = ""
        firstValue = 0.0
        isNewOperation = true
    }
}