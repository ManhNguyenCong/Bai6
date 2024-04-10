package com.example.bai6.util

import android.net.Uri
import android.widget.ImageView
import coil.load

fun ImageView.loadImage(url: String) {
    val uri = Uri.parse(url)
    this.load(uri)
}