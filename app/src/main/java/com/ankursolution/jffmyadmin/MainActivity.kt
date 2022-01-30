package com.ankursolution.jffmyadmin

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupWithNavController
import androidx.viewbinding.BuildConfig
import com.ankursolution.jffmyadmin.data.model.session.SessionManager
import com.ankursolution.jffmyadmin.utils.ext.Common
import com.ankursolution.jffmyadmin.utils.ext.Constants
import com.ankursolution.jffmyadmin.utils.ext.PageConfiguration
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var bottomView:BottomNavigationView

    lateinit var appBarConfiguration: AppBarConfiguration

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomView = findViewById(R.id.bottomNav)
        setSupportActionBar(toolbar)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment

        navController = navHost.navController
        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment,R.id.takeOrdersFragment,R.id.showCategoriesFragment))
        toolbar.setupWithNavController(navController,appBarConfiguration)

        //setupActionBarWithNavController(navController,appBarConfiguration)
        bottomView.setupWithNavController(navController)

        lifecycleScope.launchWhenResumed {
            navController.addOnDestinationChangedListener{_,destination,_->
                onDestinationChange(destination)
            }
        }




    }



    private fun onDestinationChange(destination: NavDestination) {

        val config = PageConfiguration.getConfiguration(destination.id)
        toolbar.isVisible = config.toolbarVisible
        if (config.bottomNavigationBarVisible)
            bottomView.visibility = View.VISIBLE
        else
            bottomView.visibility = View.GONE
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId==R.id.logout)
        {
            FirebaseMessaging.getInstance().unsubscribeFromTopic(Constants.NOTIFICATION_CHANNEL).addOnSuccessListener { }
            Toast.makeText(this, "Successfully Logout", Toast.LENGTH_SHORT).show()
            sessionManager.deleteAuth()

            finish()
        }
        return item.onNavDestinationSelected(navController)||super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }
}