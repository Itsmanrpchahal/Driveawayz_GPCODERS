package com.driveawayz.dashboard.supportFrag

import android.app.ProgressDialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.driveawayz.Constant.BaseFrag
import com.driveawayz.Controller.Controller
import com.driveawayz.R
import com.driveawayz.Utilities.Constants
import com.driveawayz.Utilities.Utility
import retrofit2.Response

class SupportFragment : BaseFrag() ,Controller.SupportAPI{

    lateinit var question_et : EditText
    lateinit var answer_et : EditText
    lateinit var submit_bt : Button
    private lateinit var utility: Utility
    private lateinit var pd: ProgressDialog
    private lateinit var controller: Controller
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var  view =inflater.inflate(R.layout.fragment_support, container, false)

        findIds(view)
        lisenters()

        return view;
    }

    private fun lisenters() {
        submit_bt.setOnClickListener {
            when {
                question_et.text.isEmpty() -> {
                    question_et.requestFocus()
                    question_et.error ="Enter question"
                }

                answer_et.text.isEmpty() -> {
                    answer_et.requestFocus()
                    answer_et.error ="Enter description"
                }
                else -> {
                    pd.show()
                    pd.setContentView(R.layout.loading)
                    controller.Support("Bearer "+getStringVal(Constants.TOKEN),question_et.text.toString(),answer_et.text.toString())
                }
            }
        }
    }

    private fun findIds(view: View) {
        question_et = view.findViewById(R.id.question_et)
        answer_et = view.findViewById(R.id.answer_et)
        submit_bt = view.findViewById(R.id.submit_bt)

        pd = ProgressDialog(context)
        pd!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        pd!!.window!!.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND)
        pd!!.isIndeterminate = true
        pd!!.setCancelable(false)
        controller = Controller()
        controller.Controller(this)

    }

    override fun onSupportSuccess(support: Response<SupportResponse>) {
        pd.dismiss()
        if (support.isSuccessful)
        {
            question_et.setText("")
            answer_et.setText("")
        }else {
            Toast.makeText(context,""+support.message(),Toast.LENGTH_SHORT).show()
        }
    }

    override fun onError(error: String) {
       pd.dismiss()
        Toast.makeText(context,""+error,Toast.LENGTH_SHORT).show()
    }
}