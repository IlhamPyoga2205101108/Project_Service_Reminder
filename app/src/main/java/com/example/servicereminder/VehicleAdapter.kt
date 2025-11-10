package com.example.servicereminder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.servicereminder.data.Vehicle

class VehicleAdapter(
    private val vehicles: List<Vehicle>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<VehicleAdapter.VehicleViewHolder>() {

    interface OnItemClickListener {
        fun onEditClick(vehicle: Vehicle)
        fun onDeleteClick(vehicle: Vehicle)
    }

    class VehicleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvMerk: TextView = itemView.findViewById(R.id.tvMerk)
        val tvTipe: TextView = itemView.findViewById(R.id.tvTipe)
        val tvTahun: TextView = itemView.findViewById(R.id.tvTahun)
        val tvOdometer: TextView = itemView.findViewById(R.id.tvOdometer)
        val btnEdit: Button = itemView.findViewById(R.id.btnEdit)
        val btnDelete: Button = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vehicle, parent, false)
        return VehicleViewHolder(view)
    }

    override fun onBindViewHolder(holder: VehicleViewHolder, position: Int) {
        val vehicle = vehicles[position]
        holder.tvMerk.text = "Merk: ${vehicle.merk}"
        holder.tvTipe.text = "Tipe: ${vehicle.tipe}"
        holder.tvTahun.text = "Tahun: ${vehicle.tahun}"
        holder.tvOdometer.text = "Odometer: ${vehicle.odometer} km"

        holder.btnEdit.setOnClickListener {
            listener.onEditClick(vehicle)
        }
        holder.btnDelete.setOnClickListener {
            listener.onDeleteClick(vehicle)
        }
    }

    override fun getItemCount(): Int = vehicles.size
}
