package avd.fsa.demoviewmodeldinsdag

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import avd.fsa.demoviewmodeldinsdag.ui.theme.DemoViewModelDinsdagTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoViewModelDinsdagTheme {
                // A surface container using the 'background' color from the theme
                val viewModel: ColorViewModel2 by viewModels()
                val flowColor by viewModel.color.collectAsStateWithLifecycle()

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(flowColor))
                        .clickable { viewModel.generateNewColor() }
                )
            }
        }
    }
}


class ColorViewModel1() : ViewModel() {
    private val _color = MutableStateFlow(0xFFFFFFFF)

    // public UI state which is exposed by the ColorViewModel
    val color = _color.asStateFlow()

    // public event handler which is exposed by the ColorViewModel
    fun generateNewColor() {
        val color = Random.nextLong(0xFFFFFFFF)
        _color.value = color
    }
}

class ColorViewModel2(
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    // public UI state which is exposed by the ColorViewModel
    val color = savedStateHandle.getStateFlow("color",0xFFFFFFFF)

    // public event handler which is exposed by the ColorViewModel
    fun generateNewColor() {
        val color = Random.nextLong(0xFFFFFFFF)

        savedStateHandle["color"] = color
    }
}
