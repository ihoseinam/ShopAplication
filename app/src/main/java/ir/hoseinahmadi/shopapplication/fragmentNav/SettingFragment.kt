package ir.hoseinahmadi.shopapplication.fragmentNav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.hoseinahmadi.shopapplication.databinding.HomeFragmentNavBinding
import ir.hoseinahmadi.shopapplication.databinding.SettingFragmentNavBinding

class SettingFragment:Fragment() {
    private lateinit var binding:SettingFragmentNavBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingFragmentNavBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }
}