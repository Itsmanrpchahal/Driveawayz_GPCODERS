package com.driveawayz.dashboard.homeFrag

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import com.driveawayz.R


class BookDriver : Fragment() {

    private lateinit var bookdriver_bt : Button
    private lateinit var popup : Dialog
    private lateinit var okpopup : Dialog
    private lateinit var cancel_bt: Button
    private lateinit var accept_bt: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View

        view = inflater.inflate(R.layout.fragment_book_driver, container, false)

        findIds(view)
        listeners()
        return view
    }

    private fun listeners() {
        bookdriver_bt.setOnClickListener {  Dialog() }
    }

    private fun Dialog() {
        popup = Dialog(context!!)
        val window: Window? = popup.window
        window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        popup.setContentView(R.layout.bookdriverdialog)
        popup.setCancelable(true)
        popup.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        popup.show()

        accept_bt = popup.findViewById(R.id.accept_bt)
        cancel_bt = popup.findViewById(R.id.accept_bt)

        cancel_bt.setOnClickListener(View.OnClickListener { popup.dismiss() })

        accept_bt.setOnClickListener(View.OnClickListener {
            popup.dismiss()
            dialogOK()
        })
    }

    private fun dialogOK() {
        okpopup = Dialog(context!!)
        val window: Window = okpopup.getWindow()!!
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        okpopup.setContentView(R.layout.thankspopup)
        okpopup.setCancelable(true)
        okpopup.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val ok_bt : Button
        ok_bt = okpopup.findViewById(R.id.ok_bt)
        ok_bt.setOnClickListener { okpopup.dismiss() }
        okpopup.show()
    }

    private fun findIds(view: View) {
        bookdriver_bt = view.findViewById(R.id.bookdriver_bt)
    }

}