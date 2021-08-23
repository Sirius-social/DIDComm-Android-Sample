package com.sirius.travelpass.ui.activities.loader


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.AppPref
import com.sirius.travelpass.base.ui.BaseActivity
import com.sirius.travelpass.databinding.ActivityLoaderBinding
import com.sirius.travelpass.databinding.ActivitySplashBinding
import com.sirius.travelpass.ui.activities.auth.AuthActivity
import com.sirius.travelpass.ui.activities.main.MainActivity


class LoaderActivity : BaseActivity<ActivityLoaderBinding, LoaderActivityModel>() {


    companion object {
        @JvmStatic
        fun newInstance(context: Context) {
            val intent = Intent(context, LoaderActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }


    override fun getLayoutRes(): Int {
        return R.layout.activity_loader
    }

    override fun isBottomNavigationEnabled(): Boolean {
        return false
    }
    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }


    override fun subscribe() {
        super.subscribe()
        model.initEndLiveData.observe(this, Observer {
            finishAffinity()
            MainActivity.newInstance(this)
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model.initSdk(this)
    }


}