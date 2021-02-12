package com.driveawayz.dashboard.setiingFrag.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DeleteVehicleReponse(
     @SerializedName("deleted")
     @Expose
     var deleted: Boolean? = null
 )
