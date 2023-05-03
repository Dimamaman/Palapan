package uz.gita.dima.palapan.utils

import android.view.View
import android.widget.ImageView
import uz.gita.dima.palapan.R
import uz.gita.dima.palapan.data.CardData

fun ImageView.openAnimation(block: () -> Unit) {
    this.animate().setDuration(250).rotationY(89f).withEndAction {
        rotationY = -91f
//        this.scaleType = ImageView.ScaleType.CENTER_CROP
        this.setImageResource((this.tag as CardData).imgRes)
        this.setPadding(2,5,2,5)
        this.animate().setDuration(250).rotationY(0f).withEndAction {
            block()
        }.start()
    }.start()
}

fun ImageView.closeAnimation(block: () -> Unit) {
    this.animate().setDuration(250).rotationY(-89f).withEndAction {
        rotationY = 91f
//        this.scaleType = ImageView.ScaleType.CENTER_CROP
        this.setImageResource(R.drawable.nagis1icon)
        this.animate().setDuration(250).rotationY(0f).withEndAction {
            block()
        }.start()
    }.start()
}

fun ImageView.closeAnimation() {
    this.animate().setDuration(250).rotationY(-89f).withEndAction {
        rotationY = 91f
//        this.scaleType = ImageView.ScaleType.CENTER_CROP
        setImageResource(R.drawable.nagis1icon)
        this.animate().setDuration(250).rotationY(0f).start()
    }.start()
}

fun ImageView.remove(block: ImageView.() -> Unit) {
    this.animate().setDuration(500).rotation(-360f)
        .scaleX(0f)
        .scaleY(0f).withEndAction {
            this.gone()
            block(this)
        }.start()
}

fun View.gone() {
    this.visibility = View.INVISIBLE
}

fun View.visible() {
    this.visibility = View.VISIBLE
}