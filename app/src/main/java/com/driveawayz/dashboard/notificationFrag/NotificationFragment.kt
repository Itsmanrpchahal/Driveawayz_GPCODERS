package com.driveawayz.dashboard.notificationFrag

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.driveawayz.Constant.BaseFrag
import com.driveawayz.Controller.Controller
import com.driveawayz.R
import com.driveawayz.Utilities.Constants
import com.driveawayz.Utilities.Utility
import com.driveawayz.dashboard.mydriveFrag.adater.MyDrivesAdapter
import kotlinx.android.synthetic.main.fragment_my_drives.*
import retrofit2.Response

class NotificationFragment : BaseFrag() ,Controller.NotificationsAPI,Controller.ClearNotificationsAPI{

    lateinit var notifications_recycler:RecyclerView
    private lateinit var utility: Utility
    private lateinit var controller : Controller
    private lateinit var pd: ProgressDialog
    private lateinit var notificationsList : ArrayList<NotificationsResponse>
    private lateinit var notificationAdapter: NotificationAdapter
    private lateinit var clear_notifications: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var  view:View
        view = inflater.inflate(R.layout.fragment_notification, container, false)

        findIds(view)
        lisenters()
        return view
    }

    private fun lisenters() {
        clear_notifications.setOnClickListener {
        controller.ClearNotifications("Bearer "+getStringVal(Constants.TOKEN))
            pd.show()
            pd.setContentView(R.layout.loading)
        }
    }

    private fun findIds(view: View?) {

        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)

        utility = Utility()
        controller = Controller()
        controller.Controller(this  ,this)
        notifications_recycler = view!!.findViewById(R.id.notifications_recycler)
        clear_notifications = view!!.findViewById(R.id.clear_notifications)

        controller.Notifications("Bearer "+getStringVal(Constants.TOKEN))
        pd.show()
        pd.setContentView(R.layout.loading)
    }

    override fun onNotifications(notifications: Response<List<NotificationsResponse>>) {
       pd.dismiss()
        if (notifications.isSuccessful)
        {
            notificationsList = ArrayList()
            for (i in notifications.body()?.indices!!) {
                notificationsList.addAll(listOf(notifications.body()!!.get(i)))
            }

            notifications_recycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            notificationAdapter = NotificationAdapter(context!!,notificationsList
            )
            notifications_recycler.adapter = notificationAdapter

            if(notificationsList.size>=1)
            {
                clear_notifications.visibility = View.VISIBLE
            }

        } else {
            utility!!.relative_snackbar(
                requireActivity().window.decorView,
                notifications.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onClearNotifications(clearNotifications: Response<ClearNotificationsResponse>) {
        pd.dismiss()
        if (clearNotifications.isSuccessful)
        {
            if (clearNotifications.body()?.delete==false)
            {
                controller.Notifications("Bearer "+getStringVal(Constants.TOKEN))
                pd.show()
                pd.setContentView(R.layout.loading)
            } else {
                utility!!.relative_snackbar(
                    requireActivity().window.decorView,
                    clearNotifications.body()?.message,
                    getString(R.string.close_up)
                )
            }

        } else {
            utility!!.relative_snackbar(
                requireActivity().window.decorView,
                clearNotifications.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onError(error: String) {
        pd.dismiss()
        utility!!.relative_snackbar(
            requireActivity().window.decorView,
            error,
            getString(R.string.close_up)
        )
    }

}