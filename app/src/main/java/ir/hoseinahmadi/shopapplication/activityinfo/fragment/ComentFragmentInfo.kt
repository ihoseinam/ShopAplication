package ir.hoseinahmadi.shopapplication.activityinfo.fragment

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ir.hoseinahmadi.shopapplication.Api.ApiRepository
import ir.hoseinahmadi.shopapplication.Api.MainModel
import ir.hoseinahmadi.shopapplication.Api.Request
import ir.hoseinahmadi.shopapplication.databinding.ComentInfoBinding

class ComentFragmentInfo:Fragment(),Request {
    private lateinit var binding:ComentInfoBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= ComentInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val name = activity?.intent?.getStringExtra("name")
        val price = activity?.intent?.getStringExtra("price")
        val rating = activity?.intent?.getDoubleExtra("rating", 0.0)

        binding.btnSend.setOnClickListener {
            val a =binding.txtName.text.toString()
            val b = binding.txtEmail.text.toString()
            val c = binding.txtText.text.toString()
            if (a.isNotEmpty() && b.isNotEmpty() && c.isNotEmpty()) {
                val text =
                    " Message or\n name : $name\n price : $price\n rating : $rating\n  Sender Name:  ${a}\n Sender Phone:  ${b}\n Sender Text:  ${c}\n "
                ApiRepository.instance.sendText(
                    "ExWIdNbkufZaoeBdCjaAZhU190Y2ti9SrqtZuEwr",
                    text,
                  this,
                )
            } else snack("لطفا همه فیلد هارو وارد کنید")

        }
    }
    override fun onSuccess(data: MainModel) {
        binding.txtName.text?.clear()
        binding.txtEmail.text?.clear()
        binding.txtText.text?.clear()
        snack("پیام با موفقیت ارسال شد")
    }

    override fun onNotSuccess(message: String) {
        snack("ارسال پیام با خطا مواجه شد")

    }

    override fun onError(error: String) {
        snack("erorrrrr")
    }

    fun snack(text: String) {
        val snake = Snackbar.make(binding.root, "no internet ", Snackbar.LENGTH_SHORT)
        snake.animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
        snake.setText(text)
        snake.setBackgroundTint(Color.GRAY)
        snake.setTextColor(Color.WHITE)
        ViewCompat.setLayoutDirection(snake.view, ViewCompat.LAYOUT_DIRECTION_RTL)
        snake.show()
    }

}