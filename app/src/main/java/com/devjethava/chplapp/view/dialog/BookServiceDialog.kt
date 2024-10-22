package com.devjethava.chplapp.view.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.devjethava.chplapp.databinding.DialogBookServiceBinding
import com.devjethava.chplapp.helper.toast
import com.devjethava.chplapp.model.data.response.ServicesData
import com.devjethava.chplapp.view.activity.dashboard.DashboardActivity
import com.devjethava.chplapp.view.adapter.ServiceAdapter

class BookServiceDialog(
    val serviceList: ArrayList<ServicesData>,
    private val listener: (ArrayList<ServicesData>) -> Unit
) : DialogFragment() {

    private val TAG = BookServiceDialog::class.java.simpleName
    private lateinit var context: DashboardActivity
    private var _binding: DialogBookServiceBinding? = null
    private val binding get() = _binding!!

    private val lstService = ArrayList<ServicesData>()

    private var adapter: ServiceAdapter? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        try {
//            dialog.window?.setBackgroundDrawableResource(android.R.color.darker_gray)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = DialogBookServiceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context = activity as DashboardActivity
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ServiceAdapter(fun(service: ServicesData) {
            context.toast(service.serviceName.toString())
        })
        binding.rvService.layoutManager = GridLayoutManager(context, 2)
        binding.rvService.adapter = adapter

        adapter?.setServiceData(serviceList)


        binding.mbBack.setOnClickListener {
            dismiss()
        }

        binding.mbSubmit.setOnClickListener {
            adapter?.getData()?.let { it1 -> listener.invoke(it1) }
            dismiss()
        }


    }
}