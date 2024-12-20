package com.driveawayz.dashboard.setiingFrag.adatper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.driveawayz.R
import com.driveawayz.dashboard.setiingFrag.SettingFragment
import com.driveawayz.dashboard.setiingFrag.response.MyVehiclesResponse
import retrofit2.Response

class MyVehicelAdapter(var context: Context, var list: Response<List<MyVehiclesResponse>>) :
    RecyclerView.Adapter<MyVehicelAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyVehicelAdapter.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.myvehicles, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyVehicelAdapter.ViewHolder, position: Int) {
        var listData = list.body()?.get(position)
        holder.bindItems(listData,position)
        if (position == (itemCount-1))
        {
            holder.view.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return list.body()?.size!!
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var make_year_tv: TextView
        lateinit var year_tv : TextView
        lateinit var type: TextView
        lateinit var transsmisuion_tv : TextView
        lateinit var licenceplate: TextView
        lateinit var state : TextView
        lateinit var delete_vehicle : ImageButton
        lateinit var view : View

        fun bindItems(list: MyVehiclesResponse?, position: Int) {
            type = itemView.findViewById(R.id.type)
            year_tv = itemView.findViewById(R.id.year_tv)
            make_year_tv = itemView.findViewById(R.id.make_year_tv)
            licenceplate = itemView.findViewById(R.id.licenceplate)
            transsmisuion_tv = itemView.findViewById(R.id.transsmisuion_tv)
            state = itemView.findViewById(R.id.state)
            delete_vehicle = itemView.findViewById(R.id.delete_vehicle)
            view = itemView.findViewById(R.id.view)

            make_year_tv.setText(list!!.getMake().toString())
            type.setText(list.getType().toString())
            licenceplate.setText(list.getLicensePlate().toString())
            year_tv.setText(list.getYear().toString())
            transsmisuion_tv.setText(list.getTransmission().toString())
            state.setText(list.getState().toString())

            delete_vehicle.setOnClickListener {
                SettingFragment.deleteVehicleIF?.getID(position.toString())
            }
        }
    }
}