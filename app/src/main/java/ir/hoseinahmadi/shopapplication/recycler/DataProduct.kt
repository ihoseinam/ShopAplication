package ir.hoseinahmadi.shopapplication.recycler

data class DataProduct(

    val id: Int,
    val name: String,
    val price: Int,
    val imgAddresss: Int,
    val rating: Double= 3.5,
    val info: String="",
    val video: ArrayList<String> ,
    val vip :String = "",
)
