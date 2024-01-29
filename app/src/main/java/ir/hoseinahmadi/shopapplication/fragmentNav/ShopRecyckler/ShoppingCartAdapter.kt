package ir.hoseinahmadi.shopapplication.fragmentNav.ShopRecyckler

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.manager.Lifecycle
import ir.hoseinahmadi.shopapplication.Rom.db.DBHandler
import ir.hoseinahmadi.shopapplication.Rom.db.model.UserEntity
import ir.hoseinahmadi.shopapplication.databinding.ItemListShopBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ShoppingCartAdapter(
    private val items: List<ShoppingCartItem>,
    private val lifecycle: LifecycleCoroutineScope,
    private val context:Activity
) :
    RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemListShopBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ShoppingCartItem) {
            binding.nameTextView.text = item.name
            val zori = String.format("%,d", item.price)
            binding.priceTextView.text = zori
            binding.deleteOrder.setOnClickListener{
                val db =DBHandler.getDatabase(context)
                lifecycle.launch {
                    withContext(Dispatchers.IO){
                        db.userDao().deleteUsers(
                            UserEntity(item.id,"",0,)
                        )
                    }
                }
            }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemListShopBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }
}
