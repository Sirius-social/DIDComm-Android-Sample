package com.sirius.travelpass.ui.activities.tutorial


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.AppPref
import com.sirius.travelpass.base.ui.BaseActivity
import com.sirius.travelpass.databinding.ActivityTutorialBinding
import com.sirius.travelpass.ui.activities.auth.AuthActivity
import com.sirius.travelpass.ui.activities.loader.LoaderActivity


class TutorialActivity : BaseActivity<ActivityTutorialBinding, TutorialActivityModel>() {


    companion object {
        @JvmStatic
        fun newInstance(context: Context) {
            val intent = Intent(context, TutorialActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        }
    }


    override fun getLayoutRes(): Int {
        return R.layout.activity_tutorial
    }

    override fun isBottomNavigationEnabled(): Boolean {
        return false
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }

    val adapter = TutorialAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding.viewModel = model
        dataBinding.tutorialPager.adapter = adapter
        dataBinding.tutorialPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                dataBinding.indicator.selectPage(position+1)
                model.setupBottomBtn(position+1)
            }
        })
        model.createList()


    }



    override fun subscribe() {
        super.subscribe()
        model.itemListLiveData.observe(this, Observer {
            adapter.setDataList(it)
            adapter.notifyDataSetChanged()
            dataBinding.indicator.setPages(it.size)
        })

        model.nextClickLiveData.observe(this, Observer {
            if(it){
                model.nextClickLiveData.value = false
                dataBinding.tutorialPager.setCurrentItem( dataBinding.tutorialPager.currentItem+1, true)
            }

        })

        model.startClickLiveData.observe(this, Observer {
            if(it){
                finish()
                AppPref.getInstance().setTutorialDone(true)
                AuthActivity.newInstance(this)
            }

        })

    }
}