package com.example.projectpertama

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class TransaksiActivity : AppCompatActivity() {

    // Deklarasi EditText untuk makanan
    private lateinit var etSoto: EditText
    private lateinit var etTimlo: EditText
    private lateinit var etAyam: EditText
    private lateinit var etIndomie: EditText

    // Deklarasi EditText untuk minuman
    private lateinit var etEsTeh: EditText
    private lateinit var etEsJeruk: EditText
    private lateinit var etNutrisari: EditText
    private lateinit var etKopi: EditText

    // Deklarasi EditText untuk nama pelanggan
    private lateinit var etNamaPelanggan: EditText

    // Deklarasi Button
    private lateinit var btnHitung: Button

    // Harga item
    private val hargaSoto = 8000
    private val hargaTimlo = 10000
    private val hargaAyam = 12000
    private val hargaIndomie = 10000
    private val hargaEsTeh = 3000
    private val hargaEsJeruk = 4000
    private val hargaNutrisari = 3000
    private val hargaKopi = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Cara alternatif jika R.layout error
        val layoutId = resources.getIdentifier("activity_transaksi", "layout", packageName)
        setContentView(layoutId)

        // Inisialisasi views
        initViews()

        // Set listener untuk button hitung
        btnHitung.setOnClickListener {
            hitungTotal()
        }
    }

    private fun initViews() {
        // Inisialisasi EditText makanan
        etSoto = findViewById(R.id.etSoto)
        etTimlo = findViewById(R.id.etTimlo)
        etAyam = findViewById(R.id.etAyam)
        etIndomie = findViewById(R.id.etIndomie)

        // Inisialisasi EditText minuman
        etEsTeh = findViewById(R.id.etEsTeh)
        etEsJeruk = findViewById(R.id.etEsJeruk)
        etNutrisari = findViewById(R.id.etNutrisari)
        etKopi = findViewById(R.id.etKopi)

        // Inisialisasi EditText nama pelanggan
        etNamaPelanggan = findViewById(R.id.etNamaPelanggan)

        // Inisialisasi Button
        btnHitung = findViewById(R.id.btnHitung)
    }

    private fun hitungTotal() {
        // Ambil nama pelanggan
        val namaPelanggan = etNamaPelanggan.text.toString().trim()

        if (namaPelanggan.isEmpty()) {
            Toast.makeText(this, "Nama pelanggan harus diisi!", Toast.LENGTH_SHORT).show()
            return
        }

        // Ambil jumlah pesanan (default 0 jika kosong)
        val jmlSoto = etSoto.text.toString().toIntOrNull() ?: 0
        val jmlTimlo = etTimlo.text.toString().toIntOrNull() ?: 0
        val jmlAyam = etAyam.text.toString().toIntOrNull() ?: 0
        val jmlIndomie = etIndomie.text.toString().toIntOrNull() ?: 0
        val jmlEsTeh = etEsTeh.text.toString().toIntOrNull() ?: 0
        val jmlEsJeruk = etEsJeruk.text.toString().toIntOrNull() ?: 0
        val jmlNutrisari = etNutrisari.text.toString().toIntOrNull() ?: 0
        val jmlKopi = etKopi.text.toString().toIntOrNull() ?: 0

        // Hitung subtotal per item
        val subtotalSoto = jmlSoto * hargaSoto
        val subtotalTimlo = jmlTimlo * hargaTimlo
        val subtotalAyam = jmlAyam * hargaAyam
        val subtotalIndomie = jmlIndomie * hargaIndomie
        val subtotalEsTeh = jmlEsTeh * hargaEsTeh
        val subtotalEsJeruk = jmlEsJeruk * hargaEsJeruk
        val subtotalNutrisari = jmlNutrisari * hargaNutrisari
        val subtotalKopi = jmlKopi * hargaKopi

        // Hitung total keseluruhan
        val totalBelanja = subtotalSoto + subtotalTimlo + subtotalAyam + subtotalIndomie +
                subtotalEsTeh + subtotalEsJeruk + subtotalNutrisari + subtotalKopi

        // Cek apakah ada pesanan
        if (totalBelanja == 0) {
            Toast.makeText(this, "Tidak ada pesanan yang dipilih!", Toast.LENGTH_SHORT).show()
            return
        }

        // Buat detail pesanan
        val detailPesanan = buildString {
            append("Nama Pelanggan: $namaPelanggan\n\n")
            append("DETAIL PESANAN:\n")
            append("─────────────────────\n")

            if (jmlSoto > 0) append("Soto: $jmlSoto x Rp ${formatRupiah(hargaSoto)} = Rp ${formatRupiah(subtotalSoto)}\n")
            if (jmlTimlo > 0) append("Timlo: $jmlTimlo x Rp ${formatRupiah(hargaTimlo)} = Rp ${formatRupiah(subtotalTimlo)}\n")
            if (jmlAyam > 0) append("Nasi Ayam: $jmlAyam x Rp ${formatRupiah(hargaAyam)} = Rp ${formatRupiah(subtotalAyam)}\n")
            if (jmlIndomie > 0) append("Indomie: $jmlIndomie x Rp ${formatRupiah(hargaIndomie)} = Rp ${formatRupiah(subtotalIndomie)}\n")
            if (jmlEsTeh > 0) append("Es Teh: $jmlEsTeh x Rp ${formatRupiah(hargaEsTeh)} = Rp ${formatRupiah(subtotalEsTeh)}\n")
            if (jmlEsJeruk > 0) append("Es Jeruk: $jmlEsJeruk x Rp ${formatRupiah(hargaEsJeruk)} = Rp ${formatRupiah(subtotalEsJeruk)}\n")
            if (jmlNutrisari > 0) append("Nutrisari: $jmlNutrisari x Rp ${formatRupiah(hargaNutrisari)} = Rp ${formatRupiah(subtotalNutrisari)}\n")
            if (jmlKopi > 0) append("Kopi: $jmlKopi x Rp ${formatRupiah(hargaKopi)} = Rp ${formatRupiah(subtotalKopi)}\n")

            append("─────────────────────\n")
            append("TOTAL: Rp ${formatRupiah(totalBelanja)}")
        }

        // Tampilkan dialog dengan detail pesanan
        AlertDialog.Builder(this)
            .setTitle("Detail Transaksi")
            .setMessage(detailPesanan)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
                resetForm()
            }
            .setNegativeButton("Batal") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun formatRupiah(angka: Int): String {
        return String.format("%,d", angka).replace(',', '.')
    }

    private fun resetForm() {
        // Reset semua input
        etSoto.text.clear()
        etTimlo.text.clear()
        etAyam.text.clear()
        etIndomie.text.clear()
        etEsTeh.text.clear()
        etEsJeruk.text.clear()
        etNutrisari.text.clear()
        etKopi.text.clear()
        etNamaPelanggan.text.clear()

        Toast.makeText(this, "Form berhasil direset", Toast.LENGTH_SHORT).show()
    }
}