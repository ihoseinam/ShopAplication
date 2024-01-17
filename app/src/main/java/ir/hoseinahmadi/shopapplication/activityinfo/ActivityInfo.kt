package ir.hoseinahmadi.shopapplication.activityinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import ir.hoseinahmadi.shopapplication.activityinfo.adapter.MyAdapterTab
import ir.hoseinahmadi.shopapplication.databinding.ActivityInfoBinding

class ActivityInfo : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityInfoBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val e = intent.getStringExtra("name")
        binding.txtTitle.text = e
        binding.imgBack.setOnClickListener { finish() }
        val tabTitle = arrayOf("مشخصات محصول", "نمونه ویدیو", "ارسال نظر")
        binding.viewPager.adapter = MyAdapterTab(supportFragmentManager, lifecycle)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitle[position]

        }.attach()
    }
}