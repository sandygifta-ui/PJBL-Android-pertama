package com.example.projectpertama

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

class NotaActivity : AppCompatActivity() {

    private lateinit var tvNamaPelanggan: TextView
    private lateinit var tvTanggal: TextView
    private lateinit var layoutDaftarItem: LinearLayout
    private lateinit var tvTotalBayar: TextView
    private lateinit var btnCetak: Button
    private lateinit var btnKembali: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota)

        // Initialize views
        tvNamaPelanggan = findViewById(R.id.tvNamaPelanggan)
        tvTanggal = findViewById(R.id.tvTanggal)
        layoutDaftarItem = findViewById(R.id.layoutDaftarItem)
        tvTotalBayar = findViewById(R.id.tvTotalBayar)
        btnCetak = findViewById(R.id.btnCetak)
        btnKembali = findViewById(R.id.btnKembali)

        // Set tanggal
        val tanggal = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()).format(Date())
        tvTanggal.text = "Tanggal: $tanggal"

        // Contoh data (nanti ganti dengan data dari intent)
        tvNamaPelanggan.text = "Nama Pelanggan: Budi Santoso"

        // Clear template
        layoutDaftarItem.removeAllViews()

        // Tambah item contoh
        tambahItem(1, "Soto Ayam", 2, 8000.0)
        tambahItem(2, "Es Teh Manis", 2, 3000.0)
        tambahItem(3, "Nasi Goreng", 1, 12000.0)

        // Hitung total
        hitungTotal()

        // Button listeners
        btnCetak.setOnClickListener {
            Toast.makeText(this, "Fitur cetak nota", Toast.LENGTH_SHORT).show()
        }

        btnKembali.setOnClickListener {
            finish()
        }
    }

    private fun tambahItem(no: Int, nama: String, jumlah: Int, harga: Double) {
        val itemView = LayoutInflater.from(this).inflate(
            android.R.layout.simple_list_item_1,
            layoutDaftarItem,
            false
        ) as LinearLayout

        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            setPadding(0, 8, 0, 8)
        }

        val formatRupiah = NumberFormat.getNumberInstance(Locale("id", "ID"))
        val subtotal = jumlah * harga

        // No
        row.addView(TextView(this).apply {
            text = no.toString()
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.1f)
        })

        // Nama
        row.addView(TextView(this).apply {
            text = nama
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.4f)
        })

        // Jumlah
        row.addView(TextView(this).apply {
            text = jumlah.toString()
            gravity = android.view.Gravity.CENTER
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.2f)
        })

        // Harga
        row.addView(TextView(this).apply {
            text = formatRupiah.format(harga)
            gravity = android.view.Gravity.END
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.3f)
        })

        // Subtotal
        row.addView(TextView(this).apply {
            text = formatRupiah.format(subtotal)
            gravity = android.view.Gravity.END
            layoutParams = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 0.3f)
        })

        layoutDaftarItem.addView(row)
    }

    private fun hitungTotal() {
        var total = 0.0
        for (i in 0 until layoutDaftarItem.childCount) {
            val row = layoutDaftarItem.getChildAt(i) as? LinearLayout
            row?.let {
                val subtotalText = (it.getChildAt(4) as? TextView)?.text.toString()
                total += subtotalText.replace(".", "").replace(",", ".").toDoubleOrNull() ?: 0.0
            }
        }
        val formatRupiah = NumberFormat.getCurrencyInstance(Locale("id", "ID"))
        tvTotalBayar.text = "Total Bayar: ${formatRupiah.format(total)}"
    }
}