package ir.hoseinahmadi.shopapplication.fragmentNav

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.coding.imagesliderwithdotindicatorviewpager2.adapters.ImageAdapter
import com.coding.imagesliderwithdotindicatorviewpager2.models.ImageItem
import ir.hoseinahmadi.shopapplication.R
import ir.hoseinahmadi.shopapplication.databinding.HomeFragmentNavBinding
import ir.hoseinahmadi.shopapplication.recycler.AllRecyclerAdapter
import ir.hoseinahmadi.shopapplication.recycler.DataProduct
import java.util.UUID

class HomeFragment : Fragment() {
    private lateinit var binding: HomeFragmentNavBinding
    private lateinit var dataAll: Array<DataProduct>
    private lateinit var dataLove: Array<DataProduct>
    private lateinit var dataTakh: Array<DataProduct>

    private lateinit var viewpager2: ViewPager2
    private lateinit var pageChangeListener: ViewPager2.OnPageChangeCallback

    private val params = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.WRAP_CONTENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        setMargins(8, 0, 8, 0)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = HomeFragmentNavBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewpager2 = binding.viewpager2

        val imageList = arrayListOf(
            ImageItem(
                UUID.randomUUID().toString(),
                R.drawable.slide1
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                R.drawable.slide2
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                R.drawable.hojat
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                R.drawable.hosey
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                R.drawable.jasem
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                R.drawable.psamo
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                R.drawable.psamo
            ),
            ImageItem(
                UUID.randomUUID().toString(),
                R.drawable.psamo
            )
        )


        val imageAdapter = ImageAdapter()
        viewpager2.adapter = imageAdapter
        imageAdapter.submitList(imageList)

        val slideDotLL = binding.slideDotLL
        val dotsImage = Array(imageList.size) { ImageView(context) }

        dotsImage.forEach {
            it.setImageResource(
                R.drawable.non_active_dot
            )
            slideDotLL.addView(it, params)
        }

        // default first dot selected
        dotsImage[0].setImageResource(R.drawable.active_dot)

        pageChangeListener = object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                dotsImage.mapIndexed { index, imageView ->
                    if (position == index) {
                        imageView.setImageResource(
                            R.drawable.active_dot
                        )
                    } else {
                        imageView.setImageResource(R.drawable.non_active_dot)
                    }
                }
                super.onPageSelected(position)
            }
        }
        viewpager2.registerOnPageChangeCallback(pageChangeListener)



        dataAll = arrayOf(
            DataProduct(
                3, "یونس", 1500, R.drawable.sondi, 0.5, getString(R.string.sondi),
                arrayListOf(
                    "https://github.com/ihoseinam/video-shop/raw/main/younes/1.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/younes/2.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/younes/3.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/younes/4.mp4"
                ),
                "آپلودر ",
            ),
            DataProduct(
                4, " حجت", 5000, R.drawable.hojat, 2.5, getString(R.string.hojat),
                arrayListOf(
                    "https://github.com/ihoseinam/video-shop/raw/main/hojat/1.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/hojat/2.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/hojat/3.mp4",
                ),
                "  دست کردن تو کون تاآرنج و لس لس",
            ),
            DataProduct(
                1, "اویس اصلی", 8000, R.drawable.oves, 4.0, "ورژن اصلی اویس ",
                arrayListOf(
                    "https://github.com/ihoseinam/video-shop/raw/main/oveis/1.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/oveis/2.mp4",
                ), "کصلک شدید"

            ),
            DataProduct(
                2, "معید", 5000, R.drawable.psamo, 4.5, getString(R.string.psamo),
                arrayListOf(
                    "https://github.com/ihoseinam/video-shop/raw/main/moeid/1.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/moeid/2.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/moeid/3.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/moeid/4.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/moeid/5.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/moeid/6.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/moeid/7.mp4"
                ),
                "کارشناسی رنگ و یافتن کد رنگی هر فرد از روی عکس",
            ),
            DataProduct(
                5, " سجاد", 8000, R.drawable.sardar, 4.0, getString(R.string.sardar),
                arrayListOf(
                    "https://github.com/ihoseinam/video-shop/raw/main/sadjat/1.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/sadjat/2.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/sadjat/3.mp4",
                ),
                "گذاشتن کون سید محمت",
            ),
            DataProduct(
                6, "محسن", 10000, R.drawable.hazrat, 3.0, getString(R.string.mohsen),
                arrayListOf(
                    "https://github.com/ihoseinam/video-shop/raw/main/mohsen/1.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/mohsen/2.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/mohsen/3.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/mohsen/4.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/mohsen/5.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/mohsen/6.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/mohsen/8.mp4",
                ), "دهن گاییده بازی"
            ),
            DataProduct(
                7, "ممد ", 3000, R.drawable.mmd, 1.5, getString(R.string.mmd),
                arrayListOf(
                    "https://github.com/ihoseinam/video-shop/raw/main/mmd/1.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/mmd/2.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/mmd/3.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/mmd/4.mp4",
                ), "تریسام با سردار و بروبچ"
            ),
            DataProduct(
                8, "جاسم ", 10, R.drawable.jasem, 0.5, getString(R.string.jasem),
                arrayListOf(
                    "https://github.com/ihoseinam/video-shop/raw/main/jasem/1.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/jasem/2.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/jasem/3.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/jasem/4.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/jasem/5.mp4",
                ), "بالا رفتن از در و دیوار"
            ),
            DataProduct(
                10, "امـیر", 7000, R.drawable.amir, 3.5, getString(R.string.amir),
                arrayListOf(
                    "https://github.com/ihoseinam/video-shop/raw/main/amir/1.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/amir/2.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/amir/3.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/amir/4.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/amir/5.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/amir/6.mp4",
                ),
                "کصکلک و لس لس"
            ),
        )

        dataLove = arrayOf(
            DataProduct(
                1, "اویس اصلی", 8000, R.drawable.oves, 4.0, "ورژن اصلی اویس ",
                arrayListOf(
                    "https://github.com/ihoseinam/video-shop/raw/main/oveis/1.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/oveis/2.mp4",
                ), "کصلک شدید"

            ),
            DataProduct(
                2, "معید", 5000, R.drawable.psamo, 4.5, getString(R.string.psamo),
                arrayListOf(
                    "https://github.com/ihoseinam/video-shop/raw/main/moeid/1.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/moeid/2.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/moeid/3.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/moeid/4.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/moeid/5.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/moeid/6.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/moeid/7.mp4"
                ),
                "کارشناسی رنگ و یافتن کد رنگی هر فرد از روی عکس",
            ),
            DataProduct(
                6, "محسن", 10000, R.drawable.hazrat, 3.0, getString(R.string.mohsen),
                arrayListOf(
                    "https://github.com/ihoseinam/video-shop/raw/main/mohsen/1.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/mohsen/2.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/mohsen/3.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/mohsen/4.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/mohsen/5.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/mohsen/6.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/mohsen/8.mp4",
                ), "دهن گاییده بازی"
            ),
            DataProduct(
                10, "امـیر", 7000, R.drawable.amir, 3.5, getString(R.string.amir),
                arrayListOf(
                    "https://github.com/ihoseinam/video-shop/raw/main/amir/1.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/amir/2.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/amir/3.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/amir/4.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/amir/5.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/amir/6.mp4",
                ),
                "کصکلک و لس لس"
            ),
        )

        dataTakh = arrayOf(
            DataProduct(
                3, "یونس", 1500, R.drawable.sondi, 0.5, getString(R.string.sondi),
                arrayListOf(
                    "https://github.com/ihoseinam/video-shop/raw/main/younes/1.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/younes/2.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/younes/3.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/younes/4.mp4"
                ),
                "آپلودر ",
            ),
            DataProduct(
                8, "جاسم ", 10, R.drawable.jasem, 0.5, getString(R.string.jasem),
                arrayListOf(
                    "https://github.com/ihoseinam/video-shop/raw/main/jasem/1.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/jasem/2.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/jasem/3.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/jasem/4.mp4",
                    "https://github.com/ihoseinam/video-shop/raw/main/jasem/5.mp4",
                ), "بالا رفتن از در و دیوار"
            ),
        )

        val adapter1 = AllRecyclerAdapter(context as Activity, dataTakh, lifecycleScope)
        binding.recyclerView1.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.HORIZONTAL,
            true
        )
        binding.recyclerView1.adapter = adapter1

        val adapter2 = AllRecyclerAdapter(context as Activity, dataLove, lifecycleScope)
        binding.recyclerView2.layoutManager = LinearLayoutManager(
            context,
            RecyclerView.HORIZONTAL,
            true
        )
        binding.recyclerView2.adapter = adapter2

        val adapter3 = AllRecyclerAdapter(context as Activity, dataAll, lifecycleScope)
        binding.recyclerView3.layoutManager = GridLayoutManager(
            context,
            2,
            RecyclerView.VERTICAL,
            false
        )
        binding.recyclerView3.adapter = adapter3


    }


}