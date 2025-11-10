package com.example.servicereminder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.servicereminder.data.AppDatabase
import com.example.servicereminder.data.Vehicle
import kotlinx.coroutines.launch

class AddVehicleActivity : AppCompatActivity() {

    private lateinit var etMerk: EditText
    private lateinit var etTipe: EditText
    private lateinit var etTahun: EditText
    private lateinit var etOdometer: EditText
    private lateinit var btnSimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_vehicle)

        etMerk = findViewById(R.id.etMerk)
        etTipe = findViewById(R.id.etTipe)
        etTahun = findViewById(R.id.etTahun)
        etOdometer = findViewById(R.id.etOdometer)
        btnSimpan = findViewById(R.id.btnSimpan)

        val db = AppDatabase.getDatabase(this)
        val vehicleDao = db.vehicleDao()

        btnSimpan.setOnClickListener {
            val merk = etMerk.text.toString()
            val tipe = etTipe.text.toString()
            val tahun = etTahun.text.toString()
            val odometer = etOdometer.text.toString()

            if (merk.isEmpty() || tipe.isEmpty() || tahun.isEmpty() || odometer.isEmpty()) {
                Toast.makeText(this, "Semua kolom harus diisi!", Toast.LENGTH_SHORT).show()
            } else {
                val vehicle = Vehicle(
                    merk = merk,
                    tipe = tipe,
                    tahun = tahun,
                    odometer = odometer.toInt()
                )

                lifecycleScope.launch {
                    vehicleDao.insertVehicle(vehicle)
                    runOnUiThread {
                        Toast.makeText(
                            this@AddVehicleActivity,
                            "Data kendaraan berhasil disimpan!",
                            Toast.LENGTH_SHORT
                        ).show()
                        finish()
                    }
                }
            }
        }
    }
}
