package com.example.disastermanagement.activities

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.disastermanagement.databinding.ActivityVideoPlayerBinding

class VideoPlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVideoPlayerBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = intent.getStringExtra("title") ?: "Video"

        val urlOrId = intent.getStringExtra("video") ?: return
        val videoId = extractYoutubeId(urlOrId)
        val html = """
            <html>
              <head>
                <meta name='viewport' content='width=device-width, initial-scale=1'>
                <style>body,html{margin:0;padding:0;background:#000;height:100%;}</style>
              </head>
              <body>
                <iframe width='100%' height='100%' src='https://www.youtube.com/embed/$videoId?rel=0&modestbranding=1&playsinline=1' frameborder='0' allow='accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share' allowfullscreen></iframe>
              </body>
            </html>
        """.trimIndent()

        val webView: WebView = binding.webView
        webView.visibility = View.INVISIBLE
        with(webView.settings) {
            javaScriptEnabled = true
            domStorageEnabled = true
            mediaPlaybackRequiresUserGesture = false
            cacheMode = WebSettings.LOAD_DEFAULT
        }
        webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                webView.visibility = View.VISIBLE
                super.onPageFinished(view, url)
            }
        }
        webView.webChromeClient = WebChromeClient()
        webView.loadDataWithBaseURL("https://www.youtube.com", html, "text/html", "utf-8", null)
    }

    private fun extractYoutubeId(urlOrId: String): String {
        val u = urlOrId.trim()
        if (!u.contains("/")) return u
        val patterns = listOf("v=", "youtu.be/", "embed/")
        patterns.forEach { p ->
            val idx = u.indexOf(p)
            if (idx >= 0) {
                val start = idx + p.length
                val rest = u.substring(start)
                return rest.takeWhile { it.isLetterOrDigit() || it == '-' || it == '_' }
            }
        }
        return u
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


