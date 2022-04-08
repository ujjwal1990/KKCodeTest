package com.kk.codetest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kk.codetest.R
import com.kk.codetest.ui.main.MenuFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MenuFragment.newInstance())
                .commitNow()
        }
    }
}