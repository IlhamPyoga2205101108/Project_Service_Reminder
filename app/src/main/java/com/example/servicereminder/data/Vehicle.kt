package com.example.servicereminder.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "vehicle_table")
data class Vehicle(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val merk: String,
    val tipe: String,
    val tahun: String,
    val odometer: Int
)
