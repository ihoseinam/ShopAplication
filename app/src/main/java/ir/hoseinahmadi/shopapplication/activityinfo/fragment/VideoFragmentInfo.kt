package ir.hoseinahmadi.shopapplication.activityinfo.fragment

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import ir.hoseinahmadi.shopapplication.activityinfo.adapter.VideoAdapter
import ir.hoseinahmadi.shopapplication.databinding.VideoInfoBinding

class VideoFragmentInfo:Fragment() {
    private lateinit var binding:VideoInfoBinding
    private lateinit var videoAdapter: VideoAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VideoInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        val  asa = activity?.intent?.getStringArrayListExtra("video")
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        videoAdapter = VideoAdapter(asa!!)
        recyclerView.adapter = videoAdapter
    }

}