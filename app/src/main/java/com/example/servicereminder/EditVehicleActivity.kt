package com.example.servicereminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.servicereminder.data.AppDatabase
import kotlinx.coroutines.launch

class EditVehicleActivity : AppCompatActivity() {

    private lateinit var etMerk: EditText
    private lateinit var etTipe: EditText
    private lateinit var etTahun: EditText
    private lateinit var etOdometer: EditText
    private lateinit var btnUpdate: Button
    private var vehicleId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_vehicle)

        etMerk = findViewById(R.id.etMerk)
        etTipe = findViewById(R.id.etTipe)
        etTahun = findViewById(R.id.etTahun)
        etOdometer = findViewById(R.id.etOdometer)
        btnUpdate = findViewById(R.id.btnSimpan)
        btnUpdate.text = "Update Data"

        vehicleId = intent.getIntExtra("id", 0)
        etMerk.setText(intent.getStringExtra("merk"))
        etTipe.setText(intent.getStringExtra("tipe"))
        etTahun.setText(intent.getStringExtra("tahun"))
        etOdometer.setText(intent.getIntExtra("odometer", 0).toString())

        val db = AppDatabase.getDatabase(this)
        val dao = db.vehicleDao()

        btnUpdate.setOnClickListener {
            val merk = etMerk.text.toString()
            val tipe = etTipe.text.toString()
            val tahun = etTahun.text.toString()
            val odometer = etOdometer.text.toString()

            if (merk.isEmpty() || tipe.isEmpty() || tahun.isEmpty() || odometer.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                dao.updateVehicle(vehicleId, merk, tipe, tahun, odometer.toInt())
                runOnUiThread {
                    Toast.makeText(this@EditVehicleActivity, "Data diperbarui!", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
