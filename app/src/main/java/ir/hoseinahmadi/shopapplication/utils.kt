package ir.hoseinahmadi.shopapplication

enum class FragmentType{
    HOME, SHOP,SETTING,PROFILE
}

interface ActiveItem{
    fun setFragment(type: FragmentType)
}