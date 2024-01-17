package ir.hoseinahmadi.shopapplication.fragmentNav

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import ir.hoseinahmadi.shopapplication.Api.ApiRepository
import ir.hoseinahmadi.shopapplication.Api.MainModel
import ir.hoseinahmadi.shopapplication.Api.Request
import ir.hoseinahmadi.shopapplication.Rom.db.DBHandler
import ir.hoseinahmadi.shopapplication.Rom.db.dao.UserDAO
import ir.hoseinahmadi.shopapplication.databinding.AddOrderBinding
import ir.hoseinahmadi.shopapplication.databinding.ShopFragmentNavBinding
import ir.hoseinahmadi.shopapplication.fragmentNav.ShopRecyckler.ShoppingCartAdapter
import ir.hoseinahmadi.shopapplication.fragmentNav.ShopRecyckler.ShoppingCartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShopFragment : Fragment(), Request {
    private lateinit var binding: ShopFragmentNavBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = ShopFragmentNavBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (!isInternetAvailable(context as Activity)) {
            Snackbar.make(binding.root, "no internet ", Snackbar.LENGTH_SHORT)
                .setText("اتصال اینترنت وجود ندارد!")
                .setBackgroundTint(Color.RED)
                .setTextColor(Color.WHITE)
                .show()
        }

        addOrder()
        update()

        binding.btndelet.setOnClickListener { deleteOrder() }

        binding.btnAddOrder.setOnClickListener {
            val dialog = BottomSheetDialog(context as Activity)
            val view = AddOrderBinding.inflate((context as Activity).layoutInflater)
            view.fabcancel.setOnClickListener { dialog.dismiss() }
            val a = view.edtName.text
            val b = view.edtAddres.text
            val c = view.edtCall.text
            val d = view.edtEmail.text
            val e = view.notesComment.text

            view.btnSendOrder.setOnClickListener {
                if (a.toString().isNotEmpty() && b.toString().isNotEmpty() && c.toString()
                        .isNotEmpty() && d.toString().isNotEmpty()
                ) {
                    val text =
                        " New Order \n  Sender Name:  ${a.toString()}\n Sender Phone:  ${c.toString()}\n sender email :${d.toString()}\n sender addres:${b.toString()}\n Sender note:  ${e.toString()}\n "
                    ApiRepository.instance.sendText(
                        "ExWIdNbkufZaoeBdCjaAZhU190Y2ti9SrqtZuEwr",
                        text,
                        this,
                    )
                    deleteOrder()

                } else {
                    snack("لطفا فیلد های ضروری رو وارد کنید")
                }


                dialog.dismiss()
            }
            dialog.setContentView(view.root)
            dialog.show()

        }

    }

    @SuppressLint("SetTextI18n")
    private fun update() {
        val db = DBHandler.getDatabase(context as Activity)
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                db.userDao().getzori.collect { agesList ->
                    val totalAge = agesList.sum()

                    withContext(Dispatchers.Main) {
                        binding.textView4.text = " مجموع سبد خرید\n $ ${totalAge} "
                    }
                }
            }
        }
    }

    private fun readItemsFromDatabase(): Flow<List<ShoppingCartItem>> {
        val db = DBHandler.getDatabase(requireContext() as Activity)
        return db.userDao().getShoppingCartItems()
            .map { userList ->
                userList.map { userEntity ->
                    ShoppingCartItem(
                        id = userEntity.id,
                        name = userEntity.name,
                        price = userEntity.price
                    )
                }
            }
    }

    private fun deleteOrder() {
        val db = DBHandler.getDatabase(requireContext() as Activity)

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                db.userDao().deleteAllUser()
            }
        }
    }

    private fun addOrder() {
        lifecycleScope.launch {
            readItemsFromDatabase().collect { shoppingItems ->
                val adapter = ShoppingCartAdapter(shoppingItems)
                binding.recyclerView.layoutManager = LinearLayoutManager(context)
                binding.recyclerView.adapter = adapter
            }
        }

    }


    override fun onSuccess(data: MainModel) {
        snack("سفارش دریافت شد. به زودی با شما تماس خواهند گرفت")
        deleteOrder()
    }

    override fun onNotSuccess(message: String) {
        snack("خطا")
    }

    override fun onError(error: String) {
        snack("ارور")
    }

   private fun snack(text: String) {
        val snake = Snackbar.make(binding.root, "no internet ", Snackbar.LENGTH_SHORT)
        snake.animationMode = BaseTransientBottomBar.ANIMATION_MODE_SLIDE
        snake.setText(text)
        snake.setBackgroundTint(Color.GRAY)
        snake.setTextColor(Color.WHITE)
        ViewCompat.setLayoutDirection(snake.view, ViewCompat.LAYOUT_DIRECTION_RTL)
        snake.show()
    }

    private fun isInternetAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected == true
    }

}
