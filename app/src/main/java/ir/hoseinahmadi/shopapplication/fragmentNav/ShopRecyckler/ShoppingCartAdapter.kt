package ir.hoseinahmadi.shopapplication.fragmentNav.ShopRecyckler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ir.hoseinahmadi.shopapplication.databinding.ItemListShopBinding

class ShoppingCartAdapter(private val items: List<ShoppingCartItem>) :
    RecyclerView.Adapter<ShoppingCartAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemListShopBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(item: ShoppingCartItem) {
            binding.nameTextView.text = item.name
            binding.priceTextView.text = "$ ${item.price}"
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
