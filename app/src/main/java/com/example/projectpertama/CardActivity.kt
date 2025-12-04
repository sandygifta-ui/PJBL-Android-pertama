package com.example.projectpertama

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView

class CardActivity : AppCompatActivity() {

    private lateinit var cardProfile: CardView
    private lateinit var cardForm: CardView
    private lateinit var cardCalculator: CardView
    private lateinit var cardFormTransaksi: CardView
    private lateinit var cardKonversiSuhu: CardView
    private lateinit var cardExit: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        initViews()
        setupClickListeners()
        setupBackPressHandler()
    }

    private fun initViews() {
        cardProfile = findViewById(R.id.cardProfile)
        cardForm = findViewById(R.id.cardForm)
        cardCalculator = findViewById(R.id.cardCalculator)
        cardFormTransaksi = findViewById(R.id.cardFormTransaksi)
        cardKonversiSuhu = findViewById(R.id.cardKonversiSuhu)
        cardExit = findViewById(R.id.cardExit)
    }

    private fun setupClickListeners() {
        // Card Profile
        cardProfile.setOnClickListener {
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
        }

        // Card Form - Ke FormActivity
        cardForm.setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        // Card Calculator
        cardCalculator.setOnClickListener {
            val intent = Intent(this, CalculatorActivity::class.java)
            startActivity(intent)
        }

        // Card Form Transaksi
        cardFormTransaksi.setOnClickListener {
            val intent = Intent(this, TransaksiActivity::class.java)
            startActivity(intent)
        }

        // Card Konversi Suhu
        cardKonversiSuhu.setOnClickListener {
            val intent = Intent(this,KonversiSuhuActivity::class.java)
            startActivity(intent)
        }

        // Card Exit - Keluar Aplikasi
        cardExit.setOnClickListener {
            showExitDialog()
        }
    }

    private fun setupBackPressHandler() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showExitDialog()
            }
        })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showExitDialog() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Keluar")
            .setMessage("Apakah Anda yakin ingin keluar dari aplikasi?")
            .setPositiveButton("Ya") { _, _ ->
                finishAffinity() // Keluar dari semua activity
            }
            .setNegativeButton("Tidak", null)
            .show()
    }
}