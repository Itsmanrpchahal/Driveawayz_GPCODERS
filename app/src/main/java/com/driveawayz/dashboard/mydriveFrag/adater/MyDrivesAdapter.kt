package com.driveawayz.dashboard.mydriveFrag.adater

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.driveawayz.R
import com.driveawayz.dashboard.mydriveFrag.MyDrivesResponse
import com.driveawayz.dashboard.setiingFrag.adatper.MyAddress_Adapter
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.mydriveslayout.view.*
import java.text.SimpleDateFormat

class MyDrivesAdapter(
    var context: Context,
    var type: String,
    var futureRide: MutableList<MyDrivesResponse.FutureRide>,
    var pastRide: MutableList<MyDrivesResponse.CompletedRide>,
    var name: String
) :
    RecyclerView.Adapter<MyDrivesAdapter.Viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyDrivesAdapter.Viewholder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.mydriveslayout, parent, false)
        return Viewholder(v)
    }

    override fun onBindViewHolder(holder: MyDrivesAdapter.Viewholder, position: Int) {
        //var futureRides = futureRide.g

        if (type.equals("past")) {
            val date1 = pastRide.get(position).pickDate?.substring(0, 10)
            var date = date1
            var spf = SimpleDateFormat("yyyy-mm-dd")
            val newDate = spf.parse(date)
            spf = SimpleDateFormat("dd/MM/yyyy")
            date = spf.format(newDate)
            println(date)

            holder.itemView.drive_date.text = date
            holder.itemView.drive_price.text = "$ " + pastRide.get(position).rideCharge
            holder.itemView.drive_time.text = pastRide.get(position).pickTime
            holder.itemView.drive_loc.text = pastRide.get(position).pickLocation
            holder.itemView.drive_drop_loc.text = pastRide.get(position).destination
            holder.itemView.driver_name.text = name

        } else {
            if (futureRide.size >= 1) {
                val date1 = futureRide.get(position).pickDate?.substring(0, 10)
                var date = date1
                var spf = SimpleDateFormat("yyyy-mm-dd")
                val newDate = spf.parse(date)
                spf = SimpleDateFormat("dd/MM/yyyy")
                date = spf.format(newDate)
                println(date)


                holder.itemView.drive_date.text = date
                holder.itemView.drive_price.text = "$ " + futureRide.get(position).rideCharge
                holder.itemView.drive_time.text =  futureRide.get(position).pickTime
                holder.itemView.drive_loc.text = futureRide.get(position).pickLocation
                holder.itemView.drive_drop_loc.text = futureRide.get(position).destination
                holder.itemView.driver_name.text = name

            }
        }
    }

    override fun getItemCount(): Int {

        if (type.equals("past")) {
            return pastRide.size
        } else {
            return futureRide.size
        }


    }

    class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var driver_image: RoundedImageView
        lateinit var driver_name: TextView
        lateinit var drive_date: TextView
        lateinit var drive_price: TextView
        lateinit var drive_time: TextView
        lateinit var drive_loc: TextView
        lateinit var drive_drop_loc: TextView

        fun binditems(
            futureRide: MutableList<MyDrivesResponse.FutureRide>,
            pastRide: MutableList<MyDrivesResponse.CompletedRide>
        ) {

            driver_image = itemView.findViewById(R.id.driver_image)
            driver_name = itemView.findViewById(R.id.driver_name)
            drive_date = itemView.findViewById(R.id.drive_date)
            drive_price = itemView.findViewById(R.id.drive_price)
            drive_time = itemView.findViewById(R.id.drive_time)
            drive_loc = itemView.findViewById(R.id.drive_loc)
            drive_drop_loc = itemView.findViewById(R.id.drive_drop_loc)
        }
    }
}