package com.example.servicereminder.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VehicleDao {

    @Insert
    suspend fun insertVehicle(vehicle: Vehicle)

    @Query("SELECT * FROM vehicle_table")
    suspend fun getAllVehicles(): List<Vehicle>

    @Query("DELETE FROM vehicle_table WHERE id = :id")
    suspend fun deleteVehicle(id: Int)

    @Query("UPDATE vehicle_table SET merk = :merk, tipe = :tipe, tahun = :tahun, odometer = :odometer WHERE id = :id")
    suspend fun updateVehicle(id: Int, merk: String, tipe: String, tahun: String, odometer: Int)
}