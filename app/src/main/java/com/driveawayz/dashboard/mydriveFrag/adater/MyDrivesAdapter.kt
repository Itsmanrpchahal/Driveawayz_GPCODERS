package com.driveawayz.dashboard.mydriveFrag.adater

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.driveawayz.R
import com.driveawayz.dashboard.mydriveFrag.MyDrivesFragment
import com.driveawayz.dashboard.mydriveFrag.MyDrivesResponse
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.mydriveslayout.view.*
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

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

    @RequiresApi(Build.VERSION_CODES.O)
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
                var spf = SimpleDateFormat("yyyy-MM-dd")
                val newDate = spf.parse(date)
                spf = SimpleDateFormat("MMM-dd-yyyy")
                date = spf.format(newDate)
                println(date)
                val date2 = date+" "+futureRide.get(position).pickDate?.substring(
                    11,
                    16
                )
                val formatter: DateTimeFormatter =
                    DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm", Locale.ENGLISH)
                val localDate: LocalDateTime = LocalDateTime.parse(date2, formatter)
                val timeInMilliseconds: Long =
                    localDate.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()




                val sdf = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
                val currentDate = sdf.format(Date())
                System.out.println(" C DATE is  "+currentDate)


//                val formatter1: DateTimeFormatter =
//                    DateTimeFormatter.ofPattern("dd/M/yyyy hh:mm:ss", Locale.ENGLISH)
//                val localDate1: LocalDateTime = LocalDateTime.parse(currentDate, formatter1)
//                val timeInMilliseconds1: Long =
//                    localDate1.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()
                val calender = Calendar.getInstance();
                val currentMilles = calender.timeInMillis
//                Log.d("newTime", timeInMilliseconds.toString()+"     "+currentDate+"     "+timeInMilliseconds1)


                Log.d(
                    "datetime",
                    "Date in milli :: FOR API >= 26 >>> $timeInMilliseconds" + "       " + currentMilles + "    "
                )

                holder.itemView.drive_date.text = date+" at "+futureRide.get(position).pickDate?.substring(
                    11,
                    16
                )
                holder.itemView.drive_price.text = "$ " + futureRide.get(position).rideCharge
                if (futureRide.get(position).numberOfHours?.equals("1")!!)
                {
                    holder.itemView.drive_time.text =  futureRide.get(position).numberOfHours.toString()+" Hour"
                } else {
                    holder.itemView.drive_time.text =  futureRide.get(position).numberOfHours.toString()+" Hours"
                }

                holder.itemView.drive_loc.text = futureRide.get(position).pickLocation
                holder.itemView.drive_drop_loc.text = futureRide.get(position).destination
                holder.itemView.driver_name.text = name

                if (!type.equals("past"))
                {
                    if (currentMilles<=timeInMilliseconds)
                    {
                        holder.itemView.cancelride_bt.visibility = View.VISIBLE
                    } else {
                        holder.itemView.startride_bt.visibility = View.VISIBLE
                        holder.itemView.startride_bt.text = "Complete Ride"
                    }
                    holder.itemView.cancelride_bt.setOnClickListener {
                        MyDrivesFragment.deleterideidIf?.getID(futureRide.get(position).id.toString())
                    }
                }

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
        lateinit var cancelride_bt : Button
        lateinit var startride_bt : Button

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
            cancelride_bt = itemView.findViewById(R.id.cancelride_bt)
            startride_bt = itemView.findViewById(R.id.startride_bt)
        }
    }
}