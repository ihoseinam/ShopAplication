package ir.hoseinahmadi.shopapplication.recycler

import android.annotation.SuppressLint
import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import android.graphics.Color
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.snackbar.Snackbar
import ir.hoseinahmadi.shopapplication.Rom.db.DBHandler
import ir.hoseinahmadi.shopapplication.Rom.db.model.UserEntity
import ir.hoseinahmadi.shopapplication.activityinfo.ActivityInfo
import ir.hoseinahmadi.shopapplication.databinding.ActivityPassBinding
import ir.hoseinahmadi.shopapplication.databinding.ListItemAllBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AllRecyclerAdapter(
    private val context: Activity,
    private val product: Array<DataProduct>,
    private val coroutineScope: LifecycleCoroutineScope,
    private val snackbarDuration: Int = 1500,
) : RecyclerView.Adapter<AllRecyclerAdapter.ProductViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ListItemAllBinding.inflate(context.layoutInflater, parent, false)
        return ProductViewHolder(binding)


    }

    override fun getItemCount(): Int = product.size


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.setDataProduct(product[position])
    }

    inner class ProductViewHolder(private val binding: ListItemAllBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setDataProduct(product: DataProduct) {
            val db = DBHandler.getDatabase(context)
            binding.txtName.text = product.name
            binding.txtPrice.text = "$ ${product.price}"
            binding.imageView.setImageResource(product.imgAddresss)

            binding.root.setOnClickListener {
                if (product.id == 2) {
                    val dialog = BottomSheetDialog(context)
                    val view = ActivityPassBinding.inflate(context.layoutInflater)
                    view.btnConfirm.setOnClickListener {
                        if (view.textInputPass.editText?.text.toString() == "psamoE8585E") {
                            val intent = Intent(context, ActivityInfo::class.java)
                            intent.putExtra("name", product.name)
                            intent.putExtra("price", product.price)
                            intent.putExtra("img", product.imgAddresss)
                            intent.putExtra("rating", product.rating)
                            intent.putExtra("info", product.info)
                            intent.putStringArrayListExtra("video", product.video)
                            intent.putExtra("vip", product.vip)
                            context.startActivity(intent)
                            dialog.dismiss()
                        }
                        if (view.textInputPass.editText?.text.toString() != "psamoE8585E") {
                            view.textInputPass.error = "رمز اشتباه است "
                        }

                    }
                    dialog.setContentView(view.root)
                    dialog.show()
                } else if (product.id == 6) {
                    val dialog = BottomSheetDialog(context)
                    val view = ActivityPassBinding.inflate(context.layoutInflater)
                    view.btnConfirm.setOnClickListener {
                        if (view.textInputPass.editText?.text.toString() == "hazratE33B33") {
                            val intent = Intent(context, ActivityInfo::class.java)
                            intent.putExtra("name", product.name)
                            intent.putExtra("price", product.price)
                            intent.putExtra("img", product.imgAddresss)
                            intent.putExtra("rating", product.rating)
                            intent.putExtra("info", product.info)
                            intent.putStringArrayListExtra("video", product.video)
                            intent.putExtra("vip", product.vip)
                            context.startActivity(intent)
                            dialog.dismiss()
                        }
                        if (view.textInputPass.editText?.text.toString() != "hazratE33B33") {
                            view.textInputPass.error = "رمز اشتباه است "
                        }

                    }
                    dialog.setContentView(view.root)
                    dialog.show()
                } else {
                    val intent = Intent(context, ActivityInfo::class.java)
                    intent.putExtra("name", product.name)
                    intent.putExtra("price", product.price)
                    intent.putExtra("img", product.imgAddresss)
                    intent.putExtra("rating", product.rating)
                    intent.putExtra("info", product.info)
                    intent.putStringArrayListExtra("video", product.video)
                    intent.putExtra("vip", product.vip)
                    context.startActivity(intent)
                }
            }
            binding.fabKharid.setOnClickListener {
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        try {
                            db.userDao().insertUser(
                                UserEntity(
                                    id = product.id,
                                    name = product.name,
                                    price = product.price
                                )
                            )
                            withContext(Dispatchers.Main) {
                                showCustomSnackbar(
                                    binding.root,
                                    "محصول با موفقیت به سبد خرید اضافه شد",
                                    Color.BLACK
                                )
                            }
                        } catch (e: SQLiteConstraintException) {
                            withContext(Dispatchers.Main) {
                                showCustomSnackbar(
                                    binding.root,
                                    "محصول تکراری! این محصول در سبد خرید وجود دارد ",
                                    Color.RED
                                )
                            }
                        }
                    }
                }
            }

        }


    }

    private fun showCustomSnackbar(view: View, message: String, backgroundColor: Int) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
        snackbar.view.setBackgroundColor(backgroundColor)
        snackbar.setTextColor(Color.WHITE)
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({ snackbar.dismiss() }, snackbarDuration.toLong())
        snackbar.show()
    }

}

