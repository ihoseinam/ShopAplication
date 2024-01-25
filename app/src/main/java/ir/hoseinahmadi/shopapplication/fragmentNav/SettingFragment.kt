package ir.hoseinahmadi.shopapplication.fragmentNav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import ir.hoseinahmadi.shopapplication.databinding.HomeFragmentNavBinding
import ir.hoseinahmadi.shopapplication.databinding.SettingFragmentNavBinding

class SettingFragment : Fragment() {
    private lateinit var binding: SettingFragmentNavBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = SettingFragmentNavBinding.inflate(LayoutInflater.from(context))
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            ComposeView.setContent {
                Scaffold(
                    topBar = { MyTopAppBar() },
                    bottomBar = {MyBottomBar()},
                ) {
                    Column(
                        modifier = Modifier.padding(it)
                    ) {
                        hosein()

                    }
                }
            }

        }

    }
    @Composable
    fun hosein() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Heloo from compose",
                fontSize = 30.sp,
                color = Color.Blue
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Heloo from compose",
                fontSize = 30.sp,
                color = Color.Blue
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(text = "Heloo from compose",
                fontSize = 30.sp,
                color = Color.Blue
            )
            Spacer(modifier = Modifier.height(20.dp))

        }
    }
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun MyTopAppBar(){
        TopAppBar(title = { Text(text = "hosein") },
            colors = TopAppBarDefaults.topAppBarColors(
                titleContentColor = Color.White,
                containerColor = Color.Black,
                scrolledContainerColor = Color.Red
            )
            )
    }

    @Composable
    fun MyBottomBar(){
        BottomAppBar(
            contentPadding = BottomAppBarDefaults.ContentPadding,
            containerColor = Color.Gray,
            contentColor = Color.White
        ){
            Text(text = "my bottom appbar",
                fontSize = 20.sp)
        }
    }
}