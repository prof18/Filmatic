package com.prof18.filmatic

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prof18.filmatic.core.utils.Actions

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Decide what to open
        startActivity(Actions.openHomeIntent(this))
    }
}
