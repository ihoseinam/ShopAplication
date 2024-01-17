package ir.hoseinahmadi.shopapplication.fragmentNav

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.hoseinahmadi.shopapplication.databinding.ProfileFragmentNavBinding

class InfoFragment:Fragment() {
    private lateinit var binding:ProfileFragmentNavBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileFragmentNavBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnContact.setOnClickListener { openTelegram() }

    }
    private fun openTelegram() {
        val address = "tg://resolve?domain=i_hoseinam"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(address))
        try {
            context?.startActivity(intent)
        } catch (_: ActivityNotFoundException) {
            Toast.makeText(context, "not telegram ", Toast.LENGTH_SHORT).show()
        }
    }

}