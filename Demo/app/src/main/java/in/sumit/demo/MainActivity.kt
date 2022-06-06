package `in`.sumit.demo

import `in`.sumit.demo.fragment.Home
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadFragment(Home())

        var bottomNavigationView=findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.background = null
        bottomNavigationView.selectedItemId=R.id.home
/*sumit*/

        bottomNavigationView.setOnNavigationItemReselectedListener {
            when (it.itemId) {
                R.id.home -> {
                   loadFragment(Home())
                    return@setOnNavigationItemReselectedListener
                }
//                R.id.message -> {
//                    loadFragment(ChatFragment())
//                    return@setOnNavigationItemReselectedListener
//                }
//                R.id.settings -> {
//                    loadFragment(SettingFragment())
//                    return@setOnNavigationItemReselectedListener
//                }
            }
        }
    }

    private  fun loadFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.flFragment,fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }


}