package ir.hoseinahmadi.shopapplication.activityinfo.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.hoseinahmadi.shopapplication.databinding.HomeInfoFragmentBinding

class HomeFragmentInfo:Fragment() {
    private lateinit var binding:HomeInfoFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeInfoFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = activity?.intent?.getStringExtra("name")
        val price = activity?.intent?.getIntExtra("price",0)
        val img = activity?.intent?.getIntExtra("img", 0)
        val rating = activity?.intent?.getDoubleExtra("rating", 0.0)
        val info = activity?.intent?.getStringExtra("info")
        val vip = activity?.intent?.getStringExtra("vip")


        binding.ratingbar.rating = rating!!.toFloat()
        binding.imgItemprodoct.setImageResource(img!!)
        binding.txtNameItemp.text = name
        binding.txtPriceItemprr.text ="$ ${ price.toString()}"
        binding.textView.text = info
        binding.txtVip.text = vip
    }
}