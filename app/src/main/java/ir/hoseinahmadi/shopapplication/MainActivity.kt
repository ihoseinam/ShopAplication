package ir.hoseinahmadi.shopapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ir.hoseinahmadi.shopapplication.databinding.ActivityMainBinding
import ir.hoseinahmadi.shopapplication.fragmentNav.HomeFragment
import ir.hoseinahmadi.shopapplication.fragmentNav.InfoFragment
import ir.hoseinahmadi.shopapplication.fragmentNav.SettingFragment
import ir.hoseinahmadi.shopapplication.fragmentNav.ShopFragment

class MainActivity : AppCompatActivity(),ActiveItem {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout,HomeFragment())
            .commit()

        binding.bottomNav.onClickHelper(this)
    }

    override fun setFragment(type: FragmentType) {
        val fragment =when(type){
            FragmentType.HOME ->HomeFragment()
            FragmentType.SHOP ->ShopFragment()
            FragmentType.PROFILE ->InfoFragment()
            FragmentType.SETTING ->SettingFragment()
        }

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frameLayout,fragment)
            .commit()
    }
}