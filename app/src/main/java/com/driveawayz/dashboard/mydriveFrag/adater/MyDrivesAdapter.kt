package com.driveawayz.dashboard.mydriveFrag.adater

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.driveawayz.R
import com.driveawayz.dashboard.mydriveFrag.MyDrivesFragment
import com.driveawayz.dashboard.mydriveFrag.MyDrivesResponse
import com.makeramen.roundedimageview.RoundedImageView
import kotlinx.android.synthetic.main.mydriveslayout.view.*
import java.text.ParseException
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
            if (pastRide.size >= 1) {
                val date1 = pastRide.get(position).pickDate?.substring(0, 10)
                var date = date1
                var spf = SimpleDateFormat("yyyy-MM-dd")
                val newDate = spf.parse(date)
                spf = SimpleDateFormat("MMM-dd-yyyy")
                date = spf.format(newDate)
                println(date)

                holder.itemView.drive_date.text =
                    date + " at " + pastRide.get(position).pickDate?.substring(
                        11,
                        16
                    )
                holder.itemView.drive_price.text = "$ " + pastRide.get(position).rideCharge
                holder.itemView.drive_time.text = pastRide.get(position).pickTime
                holder.itemView.drive_loc.text = pastRide.get(position).pickLocation
                holder.itemView.drive_drop_loc.text = pastRide.get(position).destination
                holder.itemView.driver_name.text = name
                if (pastRide.get(position).numberOfHours == 1!!) {
                    holder.itemView.drive_time.text =
                        pastRide.get(position).numberOfHours.toString() + " Hour"
                } else {
                    holder.itemView.drive_time.text =
                        pastRide.get(position).numberOfHours.toString() + " Hours"
                }
            }


        } else {
            if (futureRide.size >= 1) {
                var date1 = futureRide.get(position).pickDate?.substring(0, 10)
                var date = date1
                var spf = SimpleDateFormat("yyyy-MM-dd")
                val newDate = spf.parse(date)
                spf = SimpleDateFormat("MMM-dd-yyyy")
                date = spf.format(newDate)
                println(date)
                var date2 = date + " " + futureRide.get(position).pickDate?.substring(
                    11,
                    16
                )
                val formatter: DateTimeFormatter =
                    DateTimeFormatter.ofPattern("MMM-dd-yyyy HH:mm", Locale.ENGLISH)
                val localDate: LocalDateTime = LocalDateTime.parse(date2, formatter)
                val timeInMilliseconds: Long =
                    localDate.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()


                val sdf = SimpleDateFormat("MMM-dd-yyyy HH:mm", Locale.getDefault())
                val sdf1 = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
                val currentDateandTime = sdf.format(Date())
                val currentDateandTime1 = sdf1.format(Date())
                val localDate1: LocalDateTime = LocalDateTime.parse(currentDateandTime, formatter)
                val timeInMilliseconds1: Long =
                    localDate1.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()


                val calender = Calendar.getInstance();
                val currentMilles = calender.timeInMillis

                Log.d(
                    "datetime",
                    "Date in milli :: FOR API >= 26 >>> $timeInMilliseconds" + "       " + timeInMilliseconds1 + "    " + currentDateandTime
                )

                holder.itemView.drive_date.text =
                    date + " at " + futureRide.get(position).pickDate?.substring(
                        11,
                        16
                    )
                holder.itemView.drive_price.text = "$ " + futureRide.get(position).rideCharge
                if (futureRide.get(position).numberOfHours?.equals("1")!!) {
                    holder.itemView.drive_time.text =
                        futureRide.get(position).numberOfHours.toString() + " Hour"
                } else {
                    holder.itemView.drive_time.text =
                        futureRide.get(position).numberOfHours.toString() + " Hours"
                }

                holder.itemView.drive_loc.text = futureRide.get(position).pickLocation
                holder.itemView.drive_drop_loc.text = futureRide.get(position).destination
                holder.itemView.driver_name.text = name

                if (!type.equals("past")) {
                    if (timeInMilliseconds1 <= timeInMilliseconds) {
                        holder.itemView.cancelride_bt.visibility = View.VISIBLE
                    } else {
                        holder.itemView.completeRide_bt.visibility = View.VISIBLE
                        holder.itemView.completeRide_bt.text = "Complete Ride"
                    }
                    holder.itemView.cancelride_bt.setOnClickListener {
                        MyDrivesFragment.deleterideidIf?.getID(futureRide.get(position).id.toString())
                    }
                }

                val startDate =
                    futureRide.get(position).pickDate?.substring(0, 10) + " " + futureRide.get(
                        position
                    ).pickDate?.substring(
                        11,
                        16
                    )
                val stopDate = currentDateandTime1

// Custom date format

// Custom date format
                val format = SimpleDateFormat("yyyy-MM-dd HH:mm")

                var d1: Date? = null
                var d2: Date? = null
                try {
                    d1 = format.parse(startDate)
                    d2 = format.parse(stopDate)
                } catch (e: ParseException) {
                    e.printStackTrace()
                }

// Get msec from each, and subtract.

// Get msec from each, and subtract.
                val diff = d2!!.time - d1!!.time
                val diffSeconds = diff / 1000
                val diffMinutes = diff / (60 * 1000)
                val diffHours = diff / (60 * 60 * 1000)
                println("Time in seconds: $diffSeconds seconds.")
                println("Time in minutes: $diffMinutes minutes.")
                println("Time in hours: $diffHours hours.")

                Log.d("checkDate", startDate + "    " + stopDate)

                val rideCharge =
                    (futureRide.get(position).rideCharge!!)?.div(futureRide.get(position).numberOfHours!!)
                val pricepermintue = rideCharge?.div(60)
                val totalprice = rideCharge?.times(pricepermintue!!)

                holder.itemView.completeRide_bt.setOnClickListener {
                    if (diffHours.equals("0")) {
                        MyDrivesFragment.completedriveIf?.getID(
                            futureRide.get(position).id.toString(),
                            futureRide.get(position).rideCharge!!,
                            diffMinutes.toString()
                        )
                    } else {
                        MyDrivesFragment.completedriveIf?.getID(
                            futureRide.get(position).id.toString(),
                            totalprice?.toDouble(),
                            diffMinutes.toString()
                        )
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
        lateinit var cancelride_bt: Button
        lateinit var completeRide_bt: Button

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
            completeRide_bt = itemView.findViewById(R.id.completeRide_bt)
        }
    }
}