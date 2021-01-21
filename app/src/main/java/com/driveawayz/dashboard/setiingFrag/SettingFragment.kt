package com.driveawayz.dashboard.setiingFrag

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.driveawayz.Constant.BaseFrag
import com.driveawayz.Controller.Controller
import com.driveawayz.R
import com.driveawayz.SignUp.signupphone.response.AddVehiclesResponse
import com.driveawayz.Utilities.Constants
import com.driveawayz.Utilities.Utility
import com.driveawayz.dashboard.setiingFrag.adatper.MyVehicelAdapter
import com.driveawayz.dashboard.setiingFrag.response.MyVehiclesResponse
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.button.MaterialButton
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File

class SettingFragment : BaseFrag(), View.OnClickListener, Controller.MyVehiclesAPI,
    Controller.AddVehiclesAPI {


    private lateinit var part: MultipartBody.Part
    private lateinit var bitMap: Bitmap
    private var utility = Utility()
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private var path: String = ""
    private lateinit var addvehiclepopup: Dialog
    private var myVehicles = ArrayList<List<MyVehiclesResponse>>()
    private lateinit var myVehicelAdapter: MyVehicelAdapter
    private lateinit var bottomnavigaton: BottomNavigationView
    private lateinit var profileview: RelativeLayout
    private lateinit var myaddressview: RelativeLayout
    private lateinit var myvehicles_view: RelativeLayout
    private lateinit var uploadImage: ImageButton
    private lateinit var myvehicles_recycler: RecyclerView
    private lateinit var addnewvehicle_bt: Button
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View
        view = inflater.inflate(R.layout.fragment_setting, container, false)

        findIds(view)
        listeners()
        return view
    }

    private fun listeners() {
        bottomnavigaton.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                when {
                    item.itemId == R.id.nav_profile -> {
                        profileview.visibility = View.VISIBLE
                        myaddressview.visibility = View.GONE
                        myvehicles_view.visibility = View.GONE
                    }

                    item.itemId == R.id.nav_address -> {
                        profileview.visibility = View.GONE
                        myaddressview.visibility = View.VISIBLE
                        myvehicles_view.visibility = View.GONE
                    }

                    item.itemId == R.id.nav_MyVehicles -> {
                        profileview.visibility = View.GONE
                        myaddressview.visibility = View.GONE
                        myvehicles_view.visibility = View.VISIBLE
                        pd.show()
                        pd.setContentView(R.layout.loading)
                        controller.MyVehicles("Bearer " + getStringVal(Constants.TOKEN))

                    }
                }
                return true
            }

        })

        uploadImage.setOnClickListener(this)
        addnewvehicle_bt.setOnClickListener(this)

    }

    private fun findIds(view: View?) {
        utility = Utility()
        controller = Controller()
        controller.Controller(this, this)
        bottomnavigaton = view!!.findViewById(R.id.bottomnavigaton)
        profileview = view.findViewById(R.id.profileview)
        myaddressview = view.findViewById(R.id.myaddressview)
        myvehicles_view = view.findViewById(R.id.myvehicles_view)
        uploadImage = view.findViewById(R.id.uploadImage)
        myvehicles_recycler = view.findViewById(R.id.myvehicles_recycler)
        addnewvehicle_bt = view.findViewById(R.id.addnewvehicle_bt)
        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
    }

    private fun pictureSelectionDialog() {

        val camera: LinearLayout
        val gallery: LinearLayout
        val dialog = Dialog(context!!)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.imagepicker)
        camera = dialog.findViewById(R.id.linear_camera) as LinearLayout
        gallery = dialog.findViewById(R.id.linear_gallery) as LinearLayout

        camera.setOnClickListener {
            ImagePicker.with(this)
                .cameraOnly()
                .crop()         //User can only capture image using Camera
                .start()
            dialog.dismiss()
        }

        gallery.setOnClickListener {
            ImagePicker.with(this)
                .galleryOnly()
                .crop()     //User can only select image from Gallery
                .start()    //Default Request Code is ImagePicker.REQUEST_CODE
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val fileUri = data?.data

            //You can get File object from intent
            var file: File? = ImagePicker.getFile(data)
            //You can also get File Path from intent
            val filePath: String? = ImagePicker.getFilePath(data)

            path = filePath!!
            bitMap = MediaStore.Images.Media.getBitmap(context?.contentResolver, fileUri)
            part = Utility.sendImageFileToserver(context?.filesDir, bitMap, "image")

            //ToDo: Hit upload image api here
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            utility!!.relative_snackbar(
                requireActivity().window.currentFocus,
                ImagePicker.getError(data),
                getString(R.string.close_up)
            )
        } else {
            utility!!.relative_snackbar(
                requireActivity().window.currentFocus,
                "Task Cancelled",
                getString(R.string.close_up)
            )
        }
    }

    override fun onClick(v: View?) {
        if (v == uploadImage) {
            pictureSelectionDialog()
        }

        if (v == addnewvehicle_bt) {
            dialogOK()
        }
    }

    private fun dialogOK() {
        addvehiclepopup = Dialog(context!!)
        val window: Window = addvehiclepopup.getWindow()!!
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        addvehiclepopup.setContentView(R.layout.addvehiclelayout)
        addvehiclepopup.setCancelable(true)
        addvehiclepopup.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        var nextbt: Button
        var make_et: EditText
        var type_et: EditText
        var year_et: EditText
        var transsmisuion_et: EditText
        var state_et: EditText
        var licenceplate_et: EditText

        nextbt = addvehiclepopup.findViewById(R.id.nextbt)
        make_et = addvehiclepopup.findViewById(R.id.make_et)
        type_et = addvehiclepopup.findViewById(R.id.type_et)
        year_et = addvehiclepopup.findViewById(R.id.year_et)
        transsmisuion_et = addvehiclepopup.findViewById(R.id.year_et)
        state_et = addvehiclepopup.findViewById(R.id.state_et)
        licenceplate_et = addvehiclepopup.findViewById(R.id.licenceplate_et)
        nextbt.setOnClickListener {
            when {
                make_et.text.isEmpty() -> {
                    make_et.requestFocus()
                    make_et.error = "Enter make"
                }

                type_et.text.isEmpty() -> {
                    type_et.requestFocus()
                    type_et.error = "Enter type"
                }

                year_et.text.isEmpty() -> {
                    year_et.requestFocus()
                    year_et.error = "Enter year"
                }

                transsmisuion_et.text.isEmpty() -> {
                    transsmisuion_et.requestFocus()
                    transsmisuion_et.error = "Enter transmission"
                }

                licenceplate_et.text.isEmpty() -> {
                    licenceplate_et.requestFocus()
                    licenceplate_et.error = "Enter licence plate"
                }

                state_et.text.isEmpty() -> {
                    state_et.requestFocus()
                    state_et.error = "Enter state"
                }
                else -> {
                    pd.show()
                    pd.setContentView(R.layout.loading)
                    controller.AddVehicle("Bearer "+getStringVal(Constants.TOKEN)
                        ,make_et.text.toString()
                        ,type_et.text.toString()
                        ,year_et.text.toString()
                        ,transsmisuion_et.text.toString()
                        ,licenceplate_et.text.toString()
                        ,state_et.text.toString())

                }
            }
        }

        addvehiclepopup.show()
    }


    override fun onMyVehiclesSuccess(success: Response<List<MyVehiclesResponse>>) {
        pd.dismiss()
        if (success.isSuccessful) {
            myVehicles = ArrayList()
            if (success.code() == 200) {

                myvehicles_recycler.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                myVehicelAdapter = MyVehicelAdapter(context!!, success)
                myvehicles_recycler.adapter = myVehicelAdapter

            } else {
                utility!!.relative_snackbar(
                    requireActivity().window.currentFocus,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            utility!!.relative_snackbar(
                requireActivity().window.currentFocus,
                success.message(),
                getString(R.string.close_up)
            )
        }


    }

    override fun onAddVehicleSuccess(success: Response<AddVehiclesResponse>) {
        addvehiclepopup.dismiss()
        controller.MyVehicles("Bearer " + getStringVal(Constants.TOKEN))
    }

    override fun onError(error: String) {
        pd.dismiss()
        utility!!.relative_snackbar(
            requireActivity().window.currentFocus,
            error,
            getString(R.string.close_up)
        )
    }

}