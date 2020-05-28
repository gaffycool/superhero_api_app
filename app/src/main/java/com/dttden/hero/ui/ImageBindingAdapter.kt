package com.dttden.hero.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.dttden.hero.R
import com.squareup.picasso.Picasso


class ImageBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("bind:heroPicture")
        fun loadBadgeIcon(imageView: ImageView, heroPicture: String) {
            Picasso.with(imageView.context)
                .load(imageView.context.getString(R.string.badge_url, heroPicture.split(".jpeg")[0]))
                .error(R.drawable.ic_launcher_background)
                .into(imageView)
        }
    }
}
