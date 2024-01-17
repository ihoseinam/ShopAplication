package ir.hoseinahmadi.shopapplication.bottomNav

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import ir.hoseinahmadi.shopapplication.ActiveItem
import ir.hoseinahmadi.shopapplication.FragmentType
import ir.hoseinahmadi.shopapplication.R
import ir.hoseinahmadi.shopapplication.databinding.CustomButtomNavBinding

class CustomNav(
    context: Context,
    attrs: AttributeSet,
) : FrameLayout(context, attrs) {
    private val binding =
        CustomButtomNavBinding.inflate(LayoutInflater.from(context))

    init {
        addView(binding.root)
    }


    fun onClickHelper(activeItem: ActiveItem) {
        binding.viewHome.setOnClickListener {
            activeHome()
            activeItem.setFragment(FragmentType.HOME)
        }
        binding.viewProfile.setOnClickListener {
            activeProfile()
            activeItem.setFragment(FragmentType.PROFILE)

        }

        binding.viewShop.setOnClickListener {
            activeShop()
            activeItem.setFragment(FragmentType.SHOP)
        }
    }

    private fun activeHome() {
        binding.imgHome.setImageResource(R.drawable.ic_home_zori)
        binding.imgProfile.setImageResource(R.drawable.ic_info_e)
        binding.imgShop.setImageResource(R.drawable.ic_shop_zori_e)
    }


    private fun activeProfile() {
        binding.imgHome.setImageResource(R.drawable.ic_home_zori_e)
        binding.imgProfile.setImageResource(R.drawable.ic_info)
        binding.imgShop.setImageResource(R.drawable.ic_shop_zori_e)
    }

    private fun activeShop() {
        binding.imgHome.setImageResource(R.drawable.ic_home_zori_e)
        binding.imgProfile.setImageResource(R.drawable.ic_info_e)
        binding.imgShop.setImageResource(R.drawable.ic_shop_zori)
    }

}