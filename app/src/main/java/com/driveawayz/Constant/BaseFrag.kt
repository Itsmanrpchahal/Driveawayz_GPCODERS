package com.driveawayz.Constant

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment


open class BaseFrag : Fragment() {
    var alertDialog: AlertDialog? = null
    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences = getContext()?.getSharedPreferences("UserToken", Context.MODE_PRIVATE)
        editor = sharedPreferences!!.edit()
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun setStringVal(key: String?, `val`: String?) {
        editor!!.putString(key, `val`)
        editor!!.apply()
    }

    fun getStringVal(key: String?): String? {
        return sharedPreferences!!.getString(key, "")
    }

    fun clearStringVal(key: String?) {
        editor!!.clear()
        editor!!.apply()
    }
}