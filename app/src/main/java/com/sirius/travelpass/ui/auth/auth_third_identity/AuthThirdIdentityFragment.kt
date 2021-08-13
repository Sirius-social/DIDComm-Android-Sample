package com.sirius.travelpass.ui.auth.auth_third_identity

import android.content.Intent
import android.os.Bundle
import androidx.annotation.NonNull
import com.sirius.travelpass.R
import com.sirius.travelpass.base.App
import com.sirius.travelpass.base.ui.BaseFragment
import com.sirius.travelpass.databinding.FragmentAuthThirdIdentityBinding
import vcm.github.webkit.proview.ProWebView


class AuthThirdIdentityFragment : BaseFragment<FragmentAuthThirdIdentityBinding, AuthThirdIdentityViewModel>() {

    override fun setupViews() {
        super.setupViews()
       /* dataBinding.webview.webChromeClient = MyWebChromeClient(baseActivity)
        dataBinding.webview.webViewClient = MyWebViewClient()
        dataBinding.webview.settings.allowFileAccess = true
        dataBinding.webview.settings.allowContentAccess = true
        dataBinding.webview.settings.javaScriptCanOpenWindowsAutomatically = true
        dataBinding.webview.settings.javaScriptEnabled = true
        dataBinding.webview.settings.databaseEnabled = true
        dataBinding.webview.loadUrl("https://kyc.golem-art.ru/test")*/
        dataBinding.webview.setFragment(this) // Also works with fragments!
        dataBinding.webview.loadUrl("https://kyc.golem-art.ru/test")

    }

    override fun getLayoutRes(): Int {
        return R.layout.fragment_auth_third_identity
    }

    override fun initDagger() {
        App.getInstance().appComponent.inject(this)
    }


    override fun isBottomNavigationEnabled(): Boolean {
        return false
    }

    override fun subscribe() {

    }



    override fun setModel() {
        dataBinding.viewModel = model
    }


     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
         dataBinding.webview.onActivityResult(requestCode, resultCode, data)
    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        dataBinding.webview.onRestoreInstanceState(savedInstanceState)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        dataBinding.webview.onRequestPermissionResult(requestCode, permissions, grantResults)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        dataBinding.webview.onSavedInstanceState(outState)
    }


     override fun onDestroy() {
        super.onDestroy()
         dataBinding.webview.onDestroy()
    }

}