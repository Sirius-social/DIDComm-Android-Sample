package com.sirius.travelpass.utils.webview

import android.util.Log
import android.view.View
import android.webkit.ConsoleMessage
import android.webkit.PermissionRequest
import android.webkit.PermissionRequest.RESOURCE_VIDEO_CAPTURE
import android.webkit.WebChromeClient
import androidx.appcompat.app.AppCompatActivity
import com.sirius.travelpass.utils.PermissionHelper

class MyWebChromeClient(val activity: AppCompatActivity) : WebChromeClient() {

    override fun onPermissionRequest(request: PermissionRequest?) {
        super.onPermissionRequest(request)
        Log.d("mylog200","onPermissionRequest="+request)
        Log.d("mylog200","onPermissionRequest="+request?.origin)
        request?.resources?.forEach {
            if(RESOURCE_VIDEO_CAPTURE == it){
                PermissionHelper.checkPermissionsOnlyForCamera(activity,1009)
            }
            Log.d("mylog200","onPermissionRequest res="+it)
        }

        //Log.d("mylog200","onPermissionRequest="+request?.grant(request?.resources))
    }

    override fun onShowCustomView(view: View?, callback: CustomViewCallback?) {
        Log.d("mylog200","onShowCustomView=")
        super.onShowCustomView(view, callback)
    }
    override fun onConsoleMessage(consoleMessage: ConsoleMessage?): Boolean {
        Log.d("mylog200","consoleMessage="+consoleMessage)
        return super.onConsoleMessage(consoleMessage)
    }
}