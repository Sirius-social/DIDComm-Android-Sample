package com.sirius.travelpass.ui.activities.splash


import android.os.Bundle
import android.os.Handler
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.AppPref
import com.sirius.travelpass.base.ui.BaseActivity
import com.sirius.travelpass.databinding.ActivitySplashBinding
import com.sirius.travelpass.ui.activities.auth.AuthActivity
import com.sirius.travelpass.ui.activities.loader.LoaderActivity
import com.sirius.travelpass.ui.activities.main.MainActivity
import com.sirius.travelpass.ui.activities.tutorial.TutorialActivity


class SplashActivity : BaseActivity<ActivitySplashBinding, SplashActivityModel>() {
    override fun getLayoutRes(): Int {
        return R.layout.activity_splash
    }

    override fun isBottomNavigationEnabled(): Boolean {
        return false
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (AppPref.getInstance().isLoggedIn()) {
            LoaderActivity.newInstance(this)
        } else {
            if(AppPref.getInstance().isTutorialDone()){
                AuthActivity.newInstance(this)
            }else{
                TutorialActivity.newInstance(this)
            }
        }
        finish()
    }

}