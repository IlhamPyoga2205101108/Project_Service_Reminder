package com.example.servicereminder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.servicereminder.data.AppDatabase
import com.example.servicereminder.data.Vehicle
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(), VehicleAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAddVehicle: Button
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewVehicles)
        btnAddVehicle = findViewById(R.id.btnAddVehicle)
        db = AppDatabase.getDatabase(this)

        recyclerView.layoutManager = LinearLayoutManager(this)

        btnAddVehicle.setOnClickListener {
            startActivity(Intent(this, AddVehicleActivity::class.java))
        }
    }

    private fun loadVehicles() {
        lifecycleScope.launch {
            val list = db.vehicleDao().getAllVehicles()
            runOnUiThread {
                recyclerView.adapter = VehicleAdapter(list, this@MainActivity)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadVehicles()
    }

    override fun onEditClick(vehicle: Vehicle) {
        val intent = Intent(this, EditVehicleActivity::class.java)
        intent.putExtra("id", vehicle.id)
        intent.putExtra("merk", vehicle.merk)
        intent.putExtra("tipe", vehicle.tipe)
        intent.putExtra("tahun", vehicle.tahun)
        intent.putExtra("odometer", vehicle.odometer)
        startActivity(intent)
    }

    override fun onDeleteClick(vehicle: Vehicle) {
        lifecycleScope.launch {
            db.vehicleDao().deleteVehicle(vehicle.id)
            loadVehicles()
        }
    }
}
