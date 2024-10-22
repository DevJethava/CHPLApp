package com.devjethava.chplapp.view.activity

import android.os.Bundle
import androidx.fragment.app.commit
import com.devjethava.chplapp.R
import com.devjethava.chplapp.databinding.ActivityHolderBinding
import com.devjethava.chplapp.helper.Constants
import com.devjethava.chplapp.view.base.BaseActivity
import com.devjethava.chplapp.view.base.BaseFragment
import com.devjethava.chplapp.view.fragment.BlankFragment
import com.devjethava.chplapp.view.fragment.BlankFragment2

/**
 * HolderActivity
 * contains all fragments
 */
class HolderActivity : BaseActivity<ActivityHolderBinding>(R.layout.activity_holder) {

    private val TAG = HolderActivity::class.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    /**
     * For Initial work
     */
    private fun init() {
        when (intent.getStringExtra(Constants.FRAGMENT)) {

            BlankFragment::class.simpleName -> {
                setFragment(BlankFragment.newInstance())
            }

            BlankFragment2::class.simpleName -> {
                val dataToSend = intent.getStringExtra(Constants.DATA_TO_SEND)
                setFragment(BlankFragment2.newInstance(dataToSend ?: getString(R.string.app_name)))
            }
        }
    }

    private fun setFragment(fragment: BaseFragment<*>) {
        supportFragmentManager.commit {
            add(R.id.holder, fragment)
        }
    }
}