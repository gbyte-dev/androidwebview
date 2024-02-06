package com.webview.android.app.ui

import android.os.Bundle
import android.view.View
import android.webkit.PermissionRequest
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.webview.android.app.R
import com.webview.android.app.databinding.FragmentWebviewBinding

class WebviewFragment : Fragment(R.layout.fragment_webview) {

    private lateinit var binding : FragmentWebviewBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentWebviewBinding.bind(view)
        binding.webView.settings.loadsImagesAutomatically = true
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.settings.mediaPlaybackRequiresUserGesture = false
        binding.webView.loadUrl("https://we1.live/login")
        binding.webView.webViewClient = WebViewClient()
        binding.webView.isFocusable = true
        binding.webView.isFocusableInTouchMode = true
        binding.webView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        binding.webView.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        binding.webView.settings.cacheMode = WebSettings.LOAD_DEFAULT
        binding.webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        binding.webView.settings.setSupportMultipleWindows(false)
        binding.webView.settings.domStorageEnabled = true
        binding.webView.settings.databaseEnabled = true
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onPermissionRequest(request: PermissionRequest) {
                request.grant(request.resources)
            }
        }
        binding.webView.settings.loadsImagesAutomatically = true

        activity?.onBackPressedDispatcher?.addCallback(viewLifecycleOwner,object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                if(binding.webView.canGoBack()){
                    binding.webView.goBack()
                }else{
                    activity?.finish()
                    System.exit(0)
                }
            }
        })

    }
}