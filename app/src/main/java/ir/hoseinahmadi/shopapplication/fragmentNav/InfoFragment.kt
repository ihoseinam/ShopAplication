package ir.hoseinahmadi.shopapplication.fragmentNav

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import ir.hoseinahmadi.shopapplication.databinding.ProfileFragmentNavBinding

class InfoFragment:Fragment() {
    private lateinit var binding:ProfileFragmentNavBinding
    private lateinit var preferences: SharedPreferences
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
// دسترسی به context
        val context: Context = requireContext()

        preferences = context.getSharedPreferences("profile", Context.MODE_PRIVATE)

        //کدنویسی بدنه

        val editable = Editable.Factory()

        if (preferences.getBoolean("resultSaveName", false)){
            binding.edtName.text = editable.newEditable(preferences.getString("name", ""))
        } else {
            binding.edtName.hint = "نام......"
        }

        if (preferences.getBoolean("resultSaveBio" , false)){
            binding.edtBio.text = editable.newEditable(preferences.getString("bio", ""))
        } else {
            binding.edtBio.hint = "بیوگرافی...."
        }

        binding.save.setOnClickListener {
            val editor = preferences.edit()
            editor.putString("name", binding.edtName.text.toString())
            editor.putString("bio", binding.edtBio.text.toString())
            if (binding.edtName.text!!.isNotEmpty()){
                editor.putBoolean("resultSaveName", true)
            } else {
                editor.putBoolean("resultSaveName", false)
            }
            if (binding.edtBio.text!!.isNotEmpty()){
                editor.putBoolean("resultSaveBio", true)
            } else {
                editor.putBoolean("resultSaveBio", false)
            }
            editor.apply()
        }

    }

}