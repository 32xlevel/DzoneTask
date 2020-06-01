package me.i32xlevel.dzonetask.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.circleCropTransform
import me.i32xlevel.dzonetask.R

fun ImageView.bindAvatar(url: String?) {
    if (!url.isNullOrBlank()) {
        Glide.with(this)
            .load(url)
            .apply(circleCropTransform())
            .placeholder(R.drawable.ic_dz)
            .error(R.drawable.ic_dz)
            .into(this)
    } else {
        Glide.with(this)
            .load(R.drawable.ic_dz)
            .apply(circleCropTransform())
            .into(this)
    }
}