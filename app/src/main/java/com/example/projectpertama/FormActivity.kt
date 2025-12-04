package com.example.projectpertama

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FormActivity : AppCompatActivity() {
    lateinit var etNama: EditText
    lateinit var etAlamat: EditText
    lateinit var etNoHP: EditText
    lateinit var spinnerAgama: Spinner
    lateinit var rgJenisKelamin: RadioGroup
    lateinit var cbMembaca: CheckBox
    lateinit var cbMakan: CheckBox
    lateinit var cbTidur: CheckBox
    lateinit var cbOlahraga: CheckBox
    lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_from)
        init()

        btnSimpan.setOnClickListener {
            try {
                val nama = etNama.text.toString()
                val alamat = etAlamat.text.toString()
                val nomorhp = etNoHP.text.toString()
                val agama = spinnerAgama.selectedItem.toString()

                val selectedGenderId = rgJenisKelamin.checkedRadioButtonId
                val jeniskelamin = when (selectedGenderId) {
                    R.id.rbLakiLaki -> "Laki-laki"
                    R.id.rbPerempuan -> "Perempuan"
                    else -> "-"
                }

                val hobiList = mutableListOf<String>()
                if (cbMembaca.isChecked) hobiList.add("Membaca")
                if (cbMakan.isChecked) hobiList.add("Makan")
                if (cbTidur.isChecked) hobiList.add("Tidur")
                if (cbOlahraga.isChecked) hobiList.add("Olahraga")
                val hobi = if (hobiList.isNotEmpty()) hobiList.joinToString(", ") else "-"

                // PENTING: Ganti jadi HasilFormActivity (bukan HasilFromActivity)
                val keHasil = Intent(this, HasilFormActivity::class.java)
                keHasil.putExtra("nama", nama)
                keHasil.putExtra("alamat", alamat)
                keHasil.putExtra("nomorhp", nomorhp)
                keHasil.putExtra("agama", agama)
                keHasil.putExtra("jeniskelamin", jeniskelamin)
                keHasil.putExtra("hobi", hobi)

                startActivity(keHasil)

            } catch (e: Exception) {
                Toast.makeText(this, "ERROR: ${e.message}", Toast.LENGTH_LONG).show()
                e.printStackTrace()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun init() {
        etNama = findViewById(R.id.etNama)
        etAlamat = findViewById(R.id.etAlamat)
        etNoHP = findViewById(R.id.etNoHP)
        spinnerAgama = findViewById(R.id.spinnerAgama)
        rgJenisKelamin = findViewById(R.id.rgJenisKelamin)
        cbMembaca = findViewById(R.id.cbMembaca)
        cbMakan = findViewById(R.id.cbMakan)
        cbTidur = findViewById(R.id.cbTidur)
        cbOlahraga = findViewById(R.id.cbOlahraga)
        btnSimpan = findViewById(R.id.btnSimpan)
    }
}