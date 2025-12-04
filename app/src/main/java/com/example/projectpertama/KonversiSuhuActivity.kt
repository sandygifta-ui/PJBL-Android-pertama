package com.example.projectpertama

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton


class KonversiSuhuActivity : AppCompatActivity() {

    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var spinner1: Spinner
    private lateinit var spinner2: Spinner
    private lateinit var btnCount: AppCompatButton
    private lateinit var layoutFormula: LinearLayout
    private lateinit var textFormula: TextView

    private val temperatureUnits = arrayOf("Celsius", "Fahrenheit", "Kelvin")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_konversi_suhu)

        editText1 = findViewById(R.id.editText1)
        editText2 = findViewById(R.id.editText2)
        spinner1 = findViewById(R.id.spinner1)
        spinner2 = findViewById(R.id.spinner2)
        btnCount = findViewById(R.id.count)
        layoutFormula = findViewById(R.id.layout_formula)
        textFormula = findViewById(R.id.text_formula)

        setupSpinners()

        btnCount.setOnClickListener {
            convertTemperature()
        }

        editText1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
            }
        })
    }

    private fun setupSpinners() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, temperatureUnits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner1.adapter = adapter
        spinner2.adapter = adapter

        spinner1.setSelection(0)
        spinner2.setSelection(1)

        spinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (editText1.text.isNotEmpty()) {
                    convertTemperature()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (editText1.text.isNotEmpty()) {
                    convertTemperature()
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun convertTemperature() {
        val inputText = editText1.text.toString()

        if (inputText.isEmpty()) {
            editText2.setText("")
            layoutFormula.visibility = View.GONE
            return
        }

        try {
            val inputValue = inputText.toDouble()
            val fromUnit = spinner1.selectedItem.toString()
            val toUnit = spinner2.selectedItem.toString()

            val celsius = when (fromUnit) {
                "Celsius" -> inputValue
                "Fahrenheit" -> (inputValue - 32) * 5 / 9
                "Kelvin" -> inputValue - 273.15
                else -> inputValue
            }

            val result = when (toUnit) {
                "Celsius" -> celsius
                "Fahrenheit" -> (celsius * 9 / 5) + 32
                "Kelvin" -> celsius + 273.15
                else -> celsius
            }

            editText2.setText(String.format("%.2f", result))

            // Show formula
            layoutFormula.visibility = View.VISIBLE
            textFormula.text = getFormula(fromUnit, toUnit, inputValue, result)

        } catch (e: NumberFormatException) {
            editText2.setText("")
            layoutFormula.visibility = View.GONE
        }
    }

    private fun getFormula(from: String, to: String, input: Double, result: Double): String {
        return when {
            from == "Celsius" && to == "Fahrenheit" ->
                "°F = (°C × 9/5) + 32\n°F = ($input × 9/5) + 32\n°F = ${String.format("%.2f", result)}"

            from == "Celsius" && to == "Kelvin" ->
                "K = °C + 273.15\nK = $input + 273.15\nK = ${String.format("%.2f", result)}"

            from == "Fahrenheit" && to == "Celsius" ->
                "°C = (°F - 32) × 5/9\n°C = ($input - 32) × 5/9\n°C = ${String.format("%.2f", result)}"

            from == "Fahrenheit" && to == "Kelvin" ->
                "K = (°F - 32) × 5/9 + 273.15\nK = ($input - 32) × 5/9 + 273.15\nK = ${String.format("%.2f", result)}"

            from == "Kelvin" && to == "Celsius" ->
                "°C = K - 273.15\n°C = $input - 273.15\n°C = ${String.format("%.2f", result)}"

            from == "Kelvin" && to == "Fahrenheit" ->
                "°F = (K - 273.15) × 9/5 + 32\n°F = ($input - 273.15) × 9/5 + 32\n°F = ${String.format("%.2f", result)}"

            from == to ->
                "Satuan sama: $input $from = $result $to"

            else ->
                "Formula tidak tersedia"
        }
    }
}