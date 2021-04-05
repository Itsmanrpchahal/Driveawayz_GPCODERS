package com.driveawayz.dashboard.notificationFrag

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.driveawayz.R
import com.driveawayz.dashboard.mydriveFrag.adater.MyDrivesAdapter
import kotlinx.android.synthetic.main.custom_notifications.view.*
import java.text.SimpleDateFormat

class NotificationAdapter (var context: Context,var notifications:MutableList<NotificationsResponse>):RecyclerView.Adapter<NotificationAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.custom_notifications, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.notifcation_title.text = notifications.get(position).message

        var date1 = notifications.get(position).createdAt?.substring(0, 10)
        var date = date1
        var spf = SimpleDateFormat("yyyy-MM-dd")
        val newDate = spf.parse(date)
        spf = SimpleDateFormat("MMM-dd-yyyy")
        date = spf.format(newDate)
        println(date)
        var date2 = date + " " + notifications.get(position).createdAt?.substring(
            11,
            16
        )

        holder.itemView.notifcation_date.text = date2
    }

    override fun getItemCount(): Int {
        return notifications.size
    }

    class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {


        fun bindItems()
        {
            lateinit var notifcation_title : TextView
            lateinit var notifcation_date: TextView
            notifcation_title = itemView.findViewById(R.id.notifcation_title)
            notifcation_date = itemView.findViewById(R.id.notifcation_date)
        }
    }
}