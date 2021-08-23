package com.sirius.travelpass.ui.activities.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.ui.BaseActivity
import com.sirius.travelpass.databinding.ActivityMainBinding
import com.sirius.travelpass.design.BottomNavView
import com.sirius.travelpass.ui.auth.auth_third_identity.AuthThirdIdentityFragment
import com.sirius.travelpass.ui.auth.auth_third_third.AuthThirdThirdFragment
import com.sirius.travelpass.ui.chats.ChatsFragment
import com.sirius.travelpass.ui.menu.MenuFragment
import com.sirius.travelpass.ui.validating.ErrorFragment
import com.sirius.travelpass.ui.validating.ValidatingFragment


class MainActivity : BaseActivity<ActivityMainBinding, MainActivityModel>() {

    companion object {
        @JvmStatic
        fun newInstance(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun getLayoutRes(): Int {
        return R.layout.activity_main
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun getRootFragmentId(): Int {
        return R.id.mainFrame
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.navigationBottom.onBottomNavClickListener = model.getOnBottomNavClickListner()
    }

    override fun subscribe() {
        super.subscribe()

        model.selectedTab.observe(this, Observer {
            dataBinding.navigationBottom.selectedTabLiveData.value = it
        })

        model.invitationStartLiveData.observe(this, Observer {
            // pushPage(ValidatingFragment())
            val item = model.getMessage(it)
            pushPage(ChatsFragment.newInstance(item))
        })

        model.invitationErrorLiveData.observe(this, Observer {
            pushPage(ErrorFragment.newInstance(it.second))
        })

        model.invitationSuccessLiveData.observe(this, Observer {
            val item = model.getMessage(it)
            showPage(MenuFragment())
            pushPage(ChatsFragment.newInstance(item))
        })
    }


}