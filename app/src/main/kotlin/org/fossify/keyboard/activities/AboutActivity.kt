package org.fossify.keyboard.activities

import android.content.Intent
import android.os.Bundle
import org.fossify.commons.activities.LicenseActivity
import org.fossify.commons.extensions.getProperPrimaryColor
import org.fossify.commons.extensions.launchViewIntent
import org.fossify.commons.extensions.updateTextColors
import org.fossify.commons.extensions.viewBinding
import org.fossify.commons.helpers.APP_ICON_IDS
import org.fossify.commons.helpers.APP_LAUNCHER_NAME
import org.fossify.commons.helpers.APP_LICENSES
import org.fossify.commons.helpers.LICENSE_GSON
import org.fossify.commons.helpers.NavigationIcon
import org.fossify.keyboard.BuildConfig
import org.fossify.keyboard.R
import org.fossify.keyboard.databinding.ActivityAboutBinding

/** Links to the issues and the source code of the app, to Fossify Keyboard it's based on and to the licences. */
class AboutActivity : SimpleActivity() {
    private val binding by viewBinding(ActivityAboutBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            setupEdgeToEdge(padBottomSystem = listOf(aboutNestedScrollview))
            setupMaterialScrollListener(aboutNestedScrollview, aboutAppbar)

            aboutIssuesHolder.setOnClickListener { launchViewIntent(ISSUES_URL) }
            aboutSourceCodeHolder.setOnClickListener { launchViewIntent(SOURCE_CODE_URL) }
            aboutBasedOnHolder.setOnClickListener { launchViewIntent(FOSSIFY_KEYBOARD_URL) }
            aboutLicencesHolder.setOnClickListener { launchLicences() }
            aboutVersion.text = getString(R.string.app_version, BuildConfig.VERSION_NAME)
        }
    }

    override fun onResume() {
        super.onResume()
        setupTopAppBar(binding.aboutAppbar, NavigationIcon.Arrow)

        binding.apply {
            updateTextColors(aboutNestedScrollview)
            arrayOf(aboutSupportLabel, aboutOtherLabel).forEach {
                it.setTextColor(getProperPrimaryColor())
            }
        }
    }

    private fun launchLicences() {
        Intent(applicationContext, LicenseActivity::class.java).apply {
            putExtra(APP_ICON_IDS, getAppIconIDs())
            putExtra(APP_LAUNCHER_NAME, getAppLauncherName())
            putExtra(APP_LICENSES, LICENSE_GSON)
            startActivity(this)
        }
    }

    private companion object {
        const val SOURCE_CODE_URL = "https://github.com/blckassassin/Keyboard"
        const val ISSUES_URL = "$SOURCE_CODE_URL/issues"
        const val FOSSIFY_KEYBOARD_URL = "https://github.com/FossifyOrg/Keyboard"
    }
}
