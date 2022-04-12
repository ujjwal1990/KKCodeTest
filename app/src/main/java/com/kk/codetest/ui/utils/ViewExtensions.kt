package com.kk.codetest.ui.utils

import android.view.View

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}

fun View.inVisible() {
    visibility = View.INVISIBLE
}

fun View.setVisibility(show: Boolean) {
    visibility = if (show) View.VISIBLE else View.GONE
}

fun showViews(vararg view: View) {
    view.forEach {
        it.show()
    }
}

fun hideViews(vararg view: View) {
    view.forEach {
        it.hide()
    }
}

fun invisibleViews(vararg view: View) {
    view.forEach {
        it.inVisible()
    }
}