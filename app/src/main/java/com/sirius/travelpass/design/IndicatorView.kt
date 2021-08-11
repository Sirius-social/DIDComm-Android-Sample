package com.sirius.travelpass.design


import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.sirius.travelpass.R
import kotlinx.android.synthetic.main.view_navigation_bottom.view.*


class IndicatorView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {


    public var selectedPage: Int = 0
    public var pagesCount: Int = 0


    fun selectPage(page : Int){
        selectedPage = page
        setImagesForTabs()
    }

    fun setPages(pages : Int){
        this.pagesCount = pages
        setPagesVisibility()
    }


    val indicator1: ImageView
    val indicator2: ImageView
    val indicator3: ImageView
    val indicator4: ImageView

    init {
        val view = View.inflate(context, R.layout.view_indicator, this)
        indicator1 = view.findViewById<ImageView>(R.id.firstStepIndicator)
        indicator2 = view.findViewById<ImageView>(R.id.secondStepIndicator)
        indicator3 = view.findViewById<ImageView>(R.id.thirdStepIndicator)
        indicator4 = view.findViewById<ImageView>(R.id.fourthStepIndicator)
        setImagesForTabs()
    }

    private fun setPagesVisibility() {
        if(pagesCount<4){
            indicator4.visibility = GONE
        }
        if(pagesCount<3){
            indicator3.visibility = GONE
        }
        if(pagesCount<2){
            indicator2.visibility = GONE
        }
        if(pagesCount<1){
            indicator1.visibility = GONE
        }
    }

    private fun setAllButtonUnselected() {
        indicator1.setImageResource(R.drawable.circle_yellow)
        indicator2.setImageResource(R.drawable.circle_yellow)
        indicator3.setImageResource(R.drawable.circle_yellow)
        indicator4.setImageResource(R.drawable.circle_yellow)
    }

    private fun setImagesForTabs() {
        setAllButtonUnselected()
        if(selectedPage > 0){
            indicator1.setImageResource(R.drawable.circle_blue)
        }
        if(selectedPage > 1){
            indicator2.setImageResource(R.drawable.circle_blue)
        }
        if(selectedPage > 2){
            indicator3.setImageResource(R.drawable.circle_blue)
        }
        if(selectedPage > 3){
            indicator4.setImageResource(R.drawable.circle_blue)
        }
    }
}