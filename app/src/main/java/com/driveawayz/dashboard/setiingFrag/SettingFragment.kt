package com.driveawayz.dashboard.setiingFrag

import android.app.Activity
import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.provider.MediaStore
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.driveawayz.Constant.BaseFrag
import com.driveawayz.Controller.Controller
import com.driveawayz.R
import com.driveawayz.SignUp.signupphone.response.AddNewAddressResponse
import com.driveawayz.SignUp.signupphone.response.AddVehiclesResponse
import com.driveawayz.Utilities.Constants
import com.driveawayz.Utilities.Utility
import com.driveawayz.dashboard.setiingFrag.IF.UpdateAddress_IF
import com.driveawayz.dashboard.setiingFrag.adatper.MyAddress_Adapter
import com.driveawayz.dashboard.setiingFrag.adatper.MyVehicelAdapter
import com.driveawayz.dashboard.setiingFrag.response.MyAddessesResponse
import com.driveawayz.dashboard.setiingFrag.response.MyVehiclesResponse
import com.driveawayz.dashboard.setiingFrag.response.UpdateAddressResponse
import com.driveawayz.splashScreen.MeResponse
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.bottomnavigation.BottomNavigationView
import okhttp3.MultipartBody
import retrofit2.Response
import java.io.File
import kotlin.collections.ArrayList


class SettingFragment : BaseFrag(), View.OnClickListener, Controller.MyVehiclesAPI,
    Controller.AddVehiclesAPI, Controller.MeAPI, Controller.MyAdderessAPI,
    Controller.AddNewAddress, UpdateAddress_IF ,Controller.UpdateAddressAPI{


    private lateinit var part: MultipartBody.Part
    private lateinit var bitMap: Bitmap
    private var utility = Utility()
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    private var path: String = ""
    private lateinit var addvehiclepopup: Dialog
    private lateinit var addnewAddressPopup: Dialog
    private var myVehicles = ArrayList<List<MyVehiclesResponse>>()
    private lateinit var myVehicelAdapter: MyVehicelAdapter
    private lateinit var myAddressAdapter: MyAddress_Adapter
    private lateinit var bottomnavigaton: BottomNavigationView
    private lateinit var profileview: RelativeLayout
    private lateinit var myaddressview: RelativeLayout
    private lateinit var myvehicles_view: RelativeLayout
    private lateinit var uploadImage: ImageButton
    private lateinit var myvehicles_recycler: RecyclerView
    private lateinit var myaddress_recycler: RecyclerView
    private lateinit var addnewvehicle_bt: Button
    private lateinit var addnewaddress_bt: Button
    private lateinit var user_phn_number_et: EditText
    private lateinit var user_birthdate_et: EditText
    private lateinit var street: EditText
    private lateinit var city: EditText
    private var addressID: String = ""
    private lateinit var addressesList: ArrayList<MyAddessesResponse>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View
        view = inflater.inflate(R.layout.fragment_setting, container, false)
        addressesList = ArrayList()
        findIds(view)
        pd.show()
        pd.setContentView(R.layout.loading)
        controller.Me("Bearer " + getStringVal(Constants.TOKEN))
        updateaddressIf = this
        listeners()
        return view
    }

    companion object {
        var updateaddressIf: UpdateAddress_IF? = null
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
                        pd.show()
                        pd.setContentView(R.layout.loading)
                        controller.Me("Bearer " + getStringVal(Constants.TOKEN))
                    }

                    item.itemId == R.id.nav_address -> {
                        profileview.visibility = View.GONE
                        myaddressview.visibility = View.VISIBLE
                        myvehicles_view.visibility = View.GONE
                        pd.show()
                        pd.setContentView(R.layout.loading)
                        controller.MyAddresss("Bearer " + getStringVal(Constants.TOKEN))
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
        addnewaddress_bt.setOnClickListener(this)

    }

    private fun findIds(view: View?) {
        utility = Utility()
        controller = Controller()
        controller.Controller(this, this, this, this, this,this)
        bottomnavigaton = view!!.findViewById(R.id.bottomnavigaton)
        profileview = view.findViewById(R.id.profileview)
        myaddressview = view.findViewById(R.id.myaddressview)
        myvehicles_view = view.findViewById(R.id.myvehicles_view)
        uploadImage = view.findViewById(R.id.uploadImage)
        myvehicles_recycler = view.findViewById(R.id.myvehicles_recycler)
        myaddress_recycler = view.findViewById(R.id.myaddress_recycler)
        addnewvehicle_bt = view.findViewById(R.id.addnewvehicle_bt)
        addnewaddress_bt = view.findViewById(R.id.addnewaddress_bt)
        user_phn_number_et = view.findViewById(R.id.user_phn_number_et)
        user_birthdate_et = view.findViewById(R.id.user_birthdate_et)
        city = view.findViewById(R.id.city)
        street = view.findViewById(R.id.street)
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

        if (v == addnewaddress_bt) {
            addnewAddressDialog("1")
        }
    }

    private fun addnewAddressDialog(type: String) {
        addnewAddressPopup = Dialog(context!!)
        val window: Window = addnewAddressPopup.getWindow()!!
        window.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
        addnewAddressPopup.setContentView(R.layout.addnew_address_dialog)
        addnewAddressPopup.setCancelable(true)
        addnewAddressPopup.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        var street_et: EditText
        var address_et: EditText
        var done_bt: Button

        street_et = addnewAddressPopup.findViewById(R.id.street_et)
        address_et = addnewAddressPopup.findViewById(R.id.address_et)
        done_bt = addnewAddressPopup.findViewById(R.id.done_bt)
        if (!type.equals("1")) {


            street_et.setText(addressesList.get(addressID.toInt()).street)
            address_et.setText(addressesList.get(addressID.toInt()).address)
            done_bt.setText("Update")
        }

        done_bt.setOnClickListener {
            when {
                street_et.text.isEmpty() -> {
                    street_et.requestFocus()
                    street_et.error = "Enter street"
                }

                address_et.text.isEmpty() -> {
                    address_et.requestFocus()
                    address_et.error = "Enter address"
                }
                else -> {
                    if (type.equals("1"))
                    {
                        addnewAddressPopup.dismiss()
                        pd.show()
                        pd.setContentView(R.layout.loading)
                        hideKeyboard()
                        if (utility.isConnectingToInternet(context))
                        {
                            controller.AddNewAddress(
                                "Bearer " + getStringVal(Constants.TOKEN),
                                street_et.text.toString(),
                                address_et.text.toString()
                            )
                        } else {
                            utility!!.relative_snackbar(
                                requireActivity().window.currentFocus,
                                getString(R.string.nointernet),
                                getString(R.string.close_up)
                            )
                        }

                    } else {
                        addnewAddressPopup.dismiss()
                        pd.show()
                        pd.setContentView(R.layout.loading)
                        hideKeyboard()
                        if (utility.isConnectingToInternet(context))
                        {
                            controller.UpdateAddress(
                                "Bearer " + getStringVal(Constants.TOKEN),
                                addressesList.get(addressID.toInt()).id.toString(),
                                street_et.text.toString(),
                                address_et.text.toString()
                            )
                        } else {
                            utility!!.relative_snackbar(
                                requireActivity().window.currentFocus,
                                getString(R.string.nointernet),
                                getString(R.string.close_up)
                            )
                        }

                    }

                }
            }
        }

        addnewAddressPopup.show()
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
                    hideKeyboard()
                    controller.AddVehicle(
                        "Bearer " + getStringVal(Constants.TOKEN),
                        make_et.text.toString(),
                        type_et.text.toString(),
                        year_et.text.toString(),
                        transsmisuion_et.text.toString(),
                        licenceplate_et.text.toString(),
                        state_et.text.toString()
                    )

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

    override fun onMeSuccess(success: Response<MeResponse>) {
        pd.dismiss()
        if (success.isSuccessful) {
            if (success.code() == 200) {
                user_phn_number_et.isEnabled = false
                user_phn_number_et.setText(success.body()?.getPhoneNumber().toString())
                user_birthdate_et.isEnabled = false
                //changeDateTimeToDateTime(success.body()?.dateOfBirth.toString())
                street.isEnabled = false
                street.setText(success.body()?.getAddress()?.get(0)?.street)
                city.isEnabled = false
                city.setText(success.body()?.getAddress()?.get(0)?.address)
            } else {
                utility!!.relative_snackbar(
                    requireActivity().window.decorView,
                    success.message(),
                    getString(R.string.close_up)
                )
            }
        } else {
            utility!!.relative_snackbar(
                requireActivity().window.decorView,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onMyAddressSuccess(success: Response<List<MyAddessesResponse>>) {
        pd.dismiss()
        if (success.isSuccessful) {
            addressesList = ArrayList()
            for (i in success.body()?.indices!!) {
                addressesList.addAll(listOf(success.body()!!.get(i)))
            }
            myaddress_recycler.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            myAddressAdapter = MyAddress_Adapter(context!!, success)
            myaddress_recycler.adapter = myAddressAdapter
        } else {
            utility!!.relative_snackbar(
                requireActivity().window.currentFocus,
                success.message(),
                getString(R.string.close_up)
            )
        }
    }

    override fun onUpdateAddress(success: Response<AddNewAddressResponse>) {
       pd.dismiss()
        addnewAddressPopup.dismiss()


    }

    override fun onUpdateAddressSuccess(success: Response<UpdateAddressResponse>) {

        if (success.isSuccessful){
            addnewAddressPopup.dismiss()
            controller.MyAddresss("Bearer " + getStringVal(Constants.TOKEN))
        }else {
            utility!!.relative_snackbar(
                requireActivity().window.currentFocus,
                success.message(),
                getString(R.string.close_up)
            )
        }

    }

    override fun onError(error: String) {
        pd.dismiss()
        utility!!.relative_snackbar(
            requireActivity().window.currentFocus,
            error,
            getString(R.string.close_up)
        )
    }

    override fun getID(id: String?) {

        addressID = id.toString()
        addnewAddressDialog("2")
    }

    private fun hideKeyboard() {
        try {
            val imm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(activity?.currentFocus!!.windowToken, 0)
        } catch (e: Exception) {

        }
    }

//    fun changeDateTimeToDateTime(time: String): String? {
//        val ymdFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
//
//        val EEEddMMMyyyy = SimpleDateFormat("EEE dd-MMM-yyyy", Locale.US)
//        var outputDateStr = ""
//        outputDateStr = DatatypeConverter.parseDate("2018-08-31", ymdFormat, EEEddMMMyyyy)
//        Log.i("output_string", outputDateStr)
//    }


}