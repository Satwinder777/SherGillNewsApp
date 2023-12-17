package com.shergill.sgpgym.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.RenderProcessGoneDetail
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import com.shergill.sgpgym.R
import com.shergill.sgpgym.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity() {
    private lateinit var binding:ActivityWebViewBinding
    lateinit var progress:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        progress = binding.progress
        var url = intent.getStringExtra("newsUrl")
        binding.webview.loadUrl(url?:"www.google.com")

        binding.webview.settings.javaScriptEnabled = true
        binding.webview.webViewClient = WebViewClient()
        progress.visibility = View.GONE


    }

}