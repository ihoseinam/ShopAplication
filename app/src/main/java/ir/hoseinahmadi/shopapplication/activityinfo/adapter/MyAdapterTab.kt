package ir.hoseinahmadi.shopapplication.activityinfo.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.hoseinahmadi.shopapplication.activityinfo.fragment.ComentFragmentInfo
import ir.hoseinahmadi.shopapplication.activityinfo.fragment.HomeFragmentInfo
import ir.hoseinahmadi.shopapplication.activityinfo.fragment.VideoFragmentInfo

class MyAdapterTab(
    fragment: FragmentManager,
    lifecycle: Lifecycle,
) : FragmentStateAdapter(fragment, lifecycle) {
    override fun getItemCount(): Int = 3


    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> HomeFragmentInfo()
            1 -> VideoFragmentInfo()
            2 -> ComentFragmentInfo()
            else -> HomeFragmentInfo()

        }

    }

}

