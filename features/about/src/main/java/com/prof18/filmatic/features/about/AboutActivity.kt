package com.prof18.filmatic.features.about

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.LibsBuilder
import com.prof18.filmatic.features.about.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = getString(R.string.about)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.contentAbout.aboutScreenShowLicensesBtn.setOnClickListener {
            LibsBuilder()
                .withLicenseShown(true)
                .withAboutAppName(getString(R.string.app_name))
                .withActivityTitle(getString(R.string.open_source_licenses))
                .withLibraryModification(
                    "lottie",
                    Libs.LibraryFields.LIBRARY_DESCRIPTION,
                    LOTTIE_FILES_LICENSE
                )
                .withEdgeToEdge(true)
                .start(this)
        }

        binding.contentAbout.aboutScreenShowGithubBtn.setOnClickListener {
            val browserIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://github.com/prof18/Filmatic")
            )
            startActivity(browserIntent)
        }

        // Author label and link
        val authorTextView = binding.contentAbout.aboutScreenAuthor

        // Set link
        val completeText = getString(R.string.author_label)
        val link = "Marco Gomiero"
        val spannableStringBuilder = SpannableStringBuilder(completeText)
        spannableStringBuilder.setSpan(
            object : ClickableSpan() {
                override fun onClick(view: View) {
                    val browserIntent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("https://www.marcogomiero.com")
                    )
                    startActivity(browserIntent)
                }
            },
            completeText.indexOf(link),
            completeText.indexOf(link) + link.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        authorTextView.movementMethod = LinkMovementMethod.getInstance()
        authorTextView.text = spannableStringBuilder
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val LOTTIE_FILES_LICENSE = """
             Lottie is a mobile library for Android and iOS that parses Adobe After Effects 
             animations exported as json with Bodymovin and renders them natively on mobile.
             
             The app uses animations from:
             
             Bryan Vogel @LottieFiles
             Sin Xiang Yi @LottieFiles
        """
    }
}
