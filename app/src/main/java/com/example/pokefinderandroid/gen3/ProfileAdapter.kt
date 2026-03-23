package com.example.pokefinderandroid.gen3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokefinderandroid.R

class ProfileAdapter(
    private val profiles: MutableList<Profile3>,
    private val onEdit: (Int) -> Unit,
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {

    class ProfileViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.textViewName)
        val textViewVersion: TextView = view.findViewById(R.id.textViewVersion)
        val textViewTid: TextView = view.findViewById(R.id.textViewTid)
        val textViewSid: TextView = view.findViewById(R.id.textViewSid)
        val textViewDeadBattery: TextView = view.findViewById(R.id.textViewDeadBattery)
        val buttonEdit: Button = view.findViewById(R.id.buttonEdit)
        val buttonDelete: Button = view.findViewById(R.id.buttonDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile, parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profile = profiles[position]
        holder.textViewName.text = profile.name
        holder.textViewVersion.text = "Version: ${profile.version.name}"
        holder.textViewTid.text = "TID: ${profile.tid}"
        holder.textViewSid.text = "SID: ${profile.sid}"
        holder.textViewDeadBattery.text = "Dead Battery: ${if (profile.deadBattery) "Yes" else "No"}"

        holder.buttonEdit.setOnClickListener { onEdit(position) }
        holder.buttonDelete.setOnClickListener { onDelete(position) }
    }

    override fun getItemCount() = profiles.size
}
