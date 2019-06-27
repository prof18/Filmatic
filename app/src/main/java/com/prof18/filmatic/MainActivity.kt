package com.prof18.filmatic

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Decide what to open
        startActivity(Actions.openHomeIntent(this))
    }
}

// TODO: use the plaid approach
object Actions {


    fun openHomeIntent(context: Context) = internalIntent(context, "com.prof18.filmatic.features.home")
    private fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)
}
