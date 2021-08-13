package com.sirius.travelpass.ui.activities.auth

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.AppPref
import com.sirius.travelpass.base.ui.BaseActivity
import com.sirius.travelpass.databinding.ActivityAuthBinding
import com.sirius.travelpass.ui.auth.auth_first.AuthFirstFragment
import com.sirius.travelpass.ui.auth.auth_second.AuthSecondFragment
import com.sirius.travelpass.ui.auth.auth_second_second.AuthSecondSecondFragment
import com.sirius.travelpass.ui.auth.auth_zero.AuthZeroFragment


class AuthActivity : BaseActivity<ActivityAuthBinding, AuthActivityModel>() {

    companion object {
        @JvmStatic
        fun newInstance(context: Context) {
            val intent = Intent(context, AuthActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }

    override fun isBottomNavigationEnabled(): Boolean {
        return false
    }
    override fun getLayoutRes(): Int {
        return R.layout.activity_auth
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    override fun getRootFragmentId(): Int {
        return R.id.mainFrameAuth
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(AppPref.getInstance().getUser()?.name.isNullOrEmpty()){
            showPage(AuthFirstFragment())
        }else{
            showPage(AuthSecondSecondFragment())
        }
    }

    override fun subscribe() {
        super.subscribe()

    }


}