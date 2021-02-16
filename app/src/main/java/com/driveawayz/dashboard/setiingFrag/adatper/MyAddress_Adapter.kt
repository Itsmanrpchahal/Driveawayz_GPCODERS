package com.driveawayz.dashboard.setiingFrag.adatper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.driveawayz.R
import com.driveawayz.dashboard.setiingFrag.SettingFragment
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
        holder.edit_address.setOnClickListener {
                SettingFragment.updateaddressIf?.getID(position.toString(),"edit")
        }

        holder.delete_address.setOnClickListener {
            SettingFragment.updateaddressIf?.getID(position.toString(),"delete")
        }
        
        if (position == (itemCount-1))
        {
            holder.view.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return list.body()?.size!!
    }

    class ViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {

        lateinit var street_tv : TextView
        lateinit var address_tv : TextView
        lateinit var edit_address : ImageButton
        lateinit var delete_address : ImageButton
        lateinit var view : View
        fun bindItems(list: MyAddessesResponse?)
        {
            street_tv = itemView.findViewById(R.id.street_tv)
            address_tv = itemView.findViewById(R.id.address_tv)
            edit_address = itemView.findViewById(R.id.edit_address)
            delete_address = itemView.findViewById(R.id.delete_address)
            view = itemView.findViewById(R.id.view)
            street_tv.setText(list?.street)
            address_tv.setText(list?.address)
        }

    }
}