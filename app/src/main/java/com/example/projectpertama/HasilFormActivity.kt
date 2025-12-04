package com.example.projectpertama

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HasilFormActivity : AppCompatActivity() {
    lateinit var tvNama: TextView
    lateinit var tvAlamat: TextView
    lateinit var tvNomorHP: TextView
    lateinit var tvAgama: TextView
    lateinit var tvJenisKelamin: TextView
    lateinit var tvHobi: TextView
    lateinit var btnKembali: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil_form)
        init()

        val nama = intent.getStringExtra("nama") ?: "-"
        val alamat = intent.getStringExtra("alamat") ?: "-"
        val nomerhp = intent.getStringExtra("nomorhp") ?: "-"
        val agama = intent.getStringExtra("agama") ?: "-"
        val jeniskelamin = intent.getStringExtra("jeniskelamin") ?: "-"
        val hobi = intent.getStringExtra("hobi") ?: "-"

        tvNama.text = "Nama: $nama"
        tvAlamat.text = "Alamat: $alamat"
        tvNomorHP.text = "No HP: $nomerhp"
        tvAgama.text = "Agama: $agama"
        tvJenisKelamin.text = "Jenis Kelamin: $jeniskelamin"
        tvHobi.text = "Hobi: $hobi"

        btnKembali.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun init() {
        tvNama = findViewById(R.id.tvNama)
        tvAlamat = findViewById(R.id.tvAlamat)
        tvNomorHP = findViewById(R.id.tvNoHP)
        tvAgama = findViewById(R.id.tvAgama)
        tvJenisKelamin = findViewById(R.id.tvJenisKelamin)
        tvHobi = findViewById(R.id.tvHobi)
        btnKembali = findViewById(R.id.btnKembali)
    }
}