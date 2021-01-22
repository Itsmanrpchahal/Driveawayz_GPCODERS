package com.driveawayz.dashboard.setiingFrag.adatper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.driveawayz.R
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
        holder.bindItems(listData)
    }

    override fun getItemCount(): Int {
        return list.body()?.size!!
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        lateinit var make_tv: TextView
        lateinit var type_tv: TextView
        lateinit var licenceplate_tv: TextView

        fun bindItems(list: MyVehiclesResponse?) {
            type_tv = itemView.findViewById(R.id.type_tv)
            make_tv = itemView.findViewById(R.id.make_tv)
            licenceplate_tv = itemView.findViewById(R.id.licenceplate_tv)
            make_tv.setText(list!!.make)
            type_tv.setText(list.type)
            licenceplate_tv.setText(list.licensePlate)


        }
    }
}