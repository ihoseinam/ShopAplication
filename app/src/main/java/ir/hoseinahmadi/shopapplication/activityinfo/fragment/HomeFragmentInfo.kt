package ir.hoseinahmadi.shopapplication.activityinfo.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.android.material.snackbar.Snackbar
import ir.hoseinahmadi.shopapplication.Rom.db.DBHandler
import ir.hoseinahmadi.shopapplication.Rom.db.model.UserEntity
import ir.hoseinahmadi.shopapplication.databinding.HomeInfoFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragmentInfo : Fragment(
) {
    private lateinit var binding: HomeInfoFragmentBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = HomeInfoFragmentBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("CommitTransaction")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = activity?.intent?.getIntExtra("id", 0)
        val name = activity?.intent?.getStringExtra("name")
        val price = activity?.intent?.getIntExtra("price", 0)
        val img = activity?.intent?.getIntExtra("img", 0)
        val rating = activity?.intent?.getDoubleExtra("rating", 0.0)
        val info = activity?.intent?.getStringExtra("info")
        val vip = activity?.intent?.getStringExtra("vip")


        binding.ratingbar.rating = rating!!.toFloat()
        binding.imgItemprodoct.setImageResource(img!!)
        binding.txtNameItemp.text = name
        val zori = String.format("%,d", price)
        binding.txtPriceItemprr.text = zori
        binding.textView.text = info
        binding.txtVip.text = vip

        binding.addOrder.setOnClickListener {
            val db = DBHandler.getDatabase(context as Activity)
            lifecycleScope.launch {
                withContext(Dispatchers.IO) {
                    try {
                        db.userDao().insertUser(
                            UserEntity(
                                id = id,
                                name = name!!,
                                price = price!!,
                            )
                        )
                        Snackbar.make(binding.root, "", Snackbar.LENGTH_SHORT)
                            .setText("محصول با موفقیت به سبد خرید اضافه شد")
                            .setTextColor(Color.WHITE)
                            .setBackgroundTint(Color.GREEN)
                            .show()
                    } catch (e: SQLiteConstraintException) {
                        withContext(Dispatchers.Main) {
                            Snackbar.make(binding.root, "", Snackbar.LENGTH_SHORT)
                                .setText("این محصول در سبد خرید وجود دارد !!")
                                .setTextColor(Color.WHITE)
                                .setBackgroundTint(Color.RED)
                                .show()

                        }


                    }
                }
            }

        }
    }
}