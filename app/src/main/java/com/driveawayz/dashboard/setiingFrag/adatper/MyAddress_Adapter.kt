package com.driveawayz.dashboard.setiingFrag.adatper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.driveawayz.R
import com.driveawayz.dashboard.setiingFrag.response.MyAddessesResponse
import retrofit2.Response

class MyAddress_Adapter (var context: Context,var list: Response<List<MyAddessesResponse>>) :
    RecyclerView.Adapter<MyAddress_Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.myaddress_view, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var listData = list.body()?.get(position)
        holder.bindItems(listData)
    }

    override fun getItemCount(): Int {
        return list.body()?.size!!
    }

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

        lateinit var street_tv : TextView
        lateinit var address_tv : TextView
        fun bindItems(list: MyAddessesResponse?)
        {
            street_tv = itemView.findViewById(R.id.street_tv)
            address_tv = itemView.findViewById(R.id.address_tv)
            street_tv.setText(list?.street)
            address_tv.setText(list?.address)
        }

    }
}