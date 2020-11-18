package com.example.compose_view_uitest

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.ui.tooling.preview.Preview
import com.example.compose_view_uitest.databinding.ActivityMainBinding
import com.example.compose_view_uitest.ui.Composeview_uiTestTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val liveData = MutableLiveData<String>()
    val ld: LiveData<String> = liveData
    val viewModel: HelloViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        CoroutineScope(Dispatchers.IO).launch {
            delay(2000)
        }
            binding.compose.setContent {
                Composeview_uiTestTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(color = MaterialTheme.colors.background, modifier = Modifier.padding(40.dp
                    )) {
                        Greeting(viewModel) {
                            viewModel.onNameChanged(System.currentTimeMillis().toString())
                        }
                    }
                }
            }

    }
}

@Composable
fun Greeting(
    viewModel: HelloViewModel = viewModel(),
    onClick: () -> Unit
) {
    val nameState = viewModel.name.observeAsState("")
    Button(onClick = onClick) {
        Text(text = "Hey, ${nameState.value}")
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Composeview_uiTestTheme {
        Greeting(onClick = {})
    }
}