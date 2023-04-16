package com.example.randomguitargenerator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.randomguitargenerator.ui.theme.RandomGuitarGeneratorTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RandomGuitarGeneratorTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    val viewModel: MainViewModel = hiltViewModel()
                    val guitar = viewModel.state.value.guitar
                    val isLoading = viewModel.state.value.isLoading
                    guitar?.let {
                        Image(
                            painter = rememberImagePainter(
                                data = guitar.imageURL,
                                builder = { crossfade(true) }
                            ),
                            contentDescription = "Guitar"
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = guitar.name,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = guitar.description)
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                    Button(
                        onClick = viewModel::getRandomGuitar,
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Next guitar!")
                    }
                    Spacer(Modifier.height(8.dp))
                    if(isLoading) {
                        CircularProgressIndicator()
                    }
                }
            }
        }
    }
}