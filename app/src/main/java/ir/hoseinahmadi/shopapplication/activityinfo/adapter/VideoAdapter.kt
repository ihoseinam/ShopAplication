package ir.hoseinahmadi.shopapplication.activityinfo.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import ir.hoseinahmadi.shopapplication.databinding.ItemvideobindingBinding

class VideoAdapter(private val videoList: ArrayList<String>) : RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    class VideoViewHolder(val binding: ItemvideobindingBinding) : RecyclerView.ViewHolder(binding.root) {
        val player: SimpleExoPlayer = SimpleExoPlayer.Builder(binding.root.context).build()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemvideobindingBinding.inflate(inflater, parent, false)
        return VideoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoUrl = videoList[position]
        holder.binding.playerView.player = holder.player

        val mediaItem = MediaItem.fromUri(videoUrl)
        holder.player.setMediaItem(mediaItem)


        holder.player.playWhenReady = false
        holder.player.prepare()

        holder.binding.playerView.useController = true

        holder.itemView.setOnClickListener {
            holder.player.playWhenReady = true
        }
        holder.binding.playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT

    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onViewDetachedFromWindow(holder: VideoViewHolder) {
        holder.player.stop()
        super.onViewDetachedFromWindow(holder)
    }
}
