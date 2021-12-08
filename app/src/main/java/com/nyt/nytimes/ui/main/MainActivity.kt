package com.nyt.nytimes.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.nyt.nytimes.R
import com.nyt.nytimes.data.model.ResultsItem
import com.nyt.nytimes.databinding.ActivityMainBinding
import com.nyt.nytimes.ui.home.HomeFragmentDirections

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.mainAppbar.toolbar)

        val drawerLayout: DrawerLayout = binding.mainDrawer
        val navView: NavigationView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_about) {
            showAboutPopup()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    fun newsDetailedPage(item: ResultsItem) {
        var url = ""
        if (item.media?.isNotEmpty()!!) {
            item.media[0]?.mediaMetadata?.forEach { mediaItem ->
                if (mediaItem?.format?.contains("440")!!)
                    url = mediaItem.url?:""
            }
        }
        val action = HomeFragmentDirections.actionNavHomeToNavSlideshow(
            title = item.title!!, image = url, author = item.byline!!,
            date = item.publishedDate!!, content = item.jsonMemberAbstract!!
        )
        findNavController(R.id.nav_host_fragment_content_main).navigate(action)
    }

    private fun showAboutPopup() {
        AlertDialog.Builder(this)
            .setTitle(R.string.app_name)
            .setMessage(getString(R.string.about_message))
            .setPositiveButton(R.string.ok) { dialog, _ -> dialog.dismiss() }
            .show()
    }
}