package com.sirius.travelpass.ui.activities.tutorial


import android.view.View
import androidx.lifecycle.MutableLiveData
import com.sirius.travelpass.R
import com.sirius.travelpass.base.providers.ResourcesProvider
import com.sirius.travelpass.base.ui.BaseActivityModel
import com.sirius.travelpass.models.ui.ItemTutorial
import javax.inject.Inject

class TutorialActivityModel @Inject constructor(
    resourceProvider: ResourcesProvider
) :
    BaseActivityModel(resourceProvider) {


    val itemListLiveData: MutableLiveData<List<ItemTutorial>> = MutableLiveData()
    val nextClickLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val startClickLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val skipVisibilityLiveData: MutableLiveData<Int> = MutableLiveData(View.VISIBLE)
    val nextTextLiveData: MutableLiveData<String> = MutableLiveData(resourceProvider.getString(R.string.auth_next))

    fun onSkipBtnClick(v: View) {
        startClickLiveData.postValue(true)
    }

    fun onNextBtnClick(v: View) {
        if(skipVisibilityLiveData.value == View.INVISIBLE){
            startClickLiveData.postValue(true)
        }else{
            nextClickLiveData.postValue(true)
        }

    }

    fun setupBottomBtn(page:Int){
        if(page == itemListLiveData.value?.size){
            skipVisibilityLiveData.postValue(View.INVISIBLE)
            nextTextLiveData.postValue(resourceProvider.getString(R.string.auth_start))
        }else{
            skipVisibilityLiveData.postValue(View.VISIBLE)
            nextTextLiveData.postValue(resourceProvider.getString(R.string.auth_next))
        }
    }

    fun createList() {
        val list : MutableList<ItemTutorial> = mutableListOf()
        val item1= ItemTutorial(1,resourceProvider.getString(R.string.tutorial_title_1),
            resourceProvider.getString(R.string.tutorial_text_1), R.drawable.tutorial_1)
        val item2= ItemTutorial(2,resourceProvider.getString(R.string.tutorial_title_2),resourceProvider.getString(R.string.tutorial_text_2), R.drawable.tutorial_2)
       // val item3= ItemTutorial(3,resourceProvider.getString(R.string.tutorial_title_3),resourceProvider.getString(R.string.tutorial_text_3), R.drawable.tutorial_1)
        list.add(item1)
        list.add(item2)
       //list.add(item3)
        itemListLiveData.postValue(list)
    }
}