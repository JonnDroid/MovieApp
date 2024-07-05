package com.example.movieapplication.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.movieapplication.R
import com.example.movieapplication.constant.AppConstant.IMG_BASE_URL

object GlideUtil {

    fun loadImage(context: Context, url: String, imageView: ImageView){
        Glide.with(context)
            .load(IMG_BASE_URL + url)
            .fitCenter()
            .placeholder(R.drawable.img_placeholder)
            .into(imageView)
    }
}