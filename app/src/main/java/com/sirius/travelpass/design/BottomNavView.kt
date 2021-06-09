package com.sirius.travelpass.design


import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.sirius.travelpass.R
import kotlinx.android.synthetic.main.view_navigation_bottom.view.*


class BottomNavView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    enum class BottomTab {
        Contacts,
        Menu,
        Credentials
    }
   public  val selectedTabLiveData = MutableLiveData<BottomTab>(BottomTab.Contacts)

    var onBottomNavClickListener : OnbottomNavClickListener?  = null
  //  var selectedTab = BottomTab.Home
    interface OnbottomNavClickListener{
        fun onBottomClick(tab : BottomTab)
    }

    fun setShopcartNumber(number : Int){
     /*   if (number>0){
            shopcartBadgeLayout.visibility = VISIBLE
            shopcartBadgeText.setText(number.toString())
        }else{
            shopcartBadgeLayout.visibility = GONE
            shopcartBadgeText.setText("")
        }*/

    }



    init {
       val view =  View.inflate(context, R.layout.view_navigation_bottom, this)
        requestsBtn.isClickable = true
        notificationsBtn.isClickable = true
        cabinetBtn.isClickable = true

        requestsBtn.setOnClickListener { onButtonClick(BottomTab.Contacts) }
        notificationsBtn.setOnClickListener { onButtonClick(BottomTab.Menu) }
        cabinetBtn.setOnClickListener {  onButtonClick(BottomTab.Credentials) }
        setShopcartNumber(0)
        setImagesForTabs()
        selectedTabLiveData.observeForever( Observer {tab->
            setImagesForTabs()
            onBottomNavClickListener?.onBottomClick(tab)
        })
    }

    private fun onButtonClick(tab : BottomTab){
        selectedTabLiveData.postValue(tab)
    }

    private fun setAllButtonUnselected(){
        requestsBtn.alpha = 0.7F
        notificationsBtn.alpha = 0.7F
        cabinetBtn.alpha = 0.7F
        requestsBtnText.setTextSize(TypedValue.COMPLEX_UNIT_SP,12F)
        notificationsBtnText.setTextSize(TypedValue.COMPLEX_UNIT_SP,12F)
        cabinetBtnText.setTextSize(TypedValue.COMPLEX_UNIT_SP,12F)
    }
    private fun setImagesForTabs(){
        when(selectedTabLiveData.value){
            BottomTab.Contacts->{
                setAllButtonUnselected()
                requestsBtn.alpha = 1F
                requestsBtnText.setTextSize(TypedValue.COMPLEX_UNIT_SP,14F)
            }
            BottomTab.Menu -> {
                setAllButtonUnselected()
                notificationsBtn.alpha = 1F
                notificationsBtnText.setTextSize(TypedValue.COMPLEX_UNIT_SP,14F)

            }
            BottomTab.Credentials -> {
                setAllButtonUnselected()
                cabinetBtn.alpha = 1F
                cabinetBtnText.setTextSize(TypedValue.COMPLEX_UNIT_SP,14F)
            }

        }
    }
}