package ir.hoseinahmadi.shopapplication

enum class FragmentType{
    HOME, SHOP,PROFILE
}

interface ActiveItem{
    fun setFragment(type: FragmentType)
}