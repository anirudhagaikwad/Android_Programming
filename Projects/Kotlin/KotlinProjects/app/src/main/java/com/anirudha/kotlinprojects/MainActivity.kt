package com.anirudha.kotlinprojects

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
//import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.anirudha.kotlinprojects.ui.theme.JetComposeCardsTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetComposeCardsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CardGridScreen()
                }
            }
        }
    }
}//class end

@Composable
fun CardGridScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(1.dp), // Reduced padding
        verticalArrangement = Arrangement.spacedBy(1.dp), // Minimal spacing between rows
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        repeat(3) { // 3 rows
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f), // Make sure each row takes an equal part of the screen height
                horizontalArrangement = Arrangement.spacedBy(1.dp) // Minimal spacing between cards
            ) {
                repeat(3) { // 3 columns per row
                    GradientMaterialCard(
                        modifier = Modifier
                            .weight(1f) // Each card takes equal width
                            .fillMaxHeight() // Cards fill the height of the row
                    )
                }
            }
        }
    }
}

@Composable
fun GradientMaterialCard(modifier: Modifier = Modifier) {
    val gradientColors = listOf(
        Color(0xFF42A5F5), // Blue
        Color(0xFFAB47BC)  // Purple
    )

    Card(
        modifier = modifier.padding(1.dp), // Minimal padding inside the card
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = gradientColors
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Card",
                color = Color.White,
                style = MaterialTheme.typography.titleMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetComposeCardsTheme {
        CardGridScreen()
    }
}



//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            JetComposeCardsTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    CardGridScreen()
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun CardGridScreen() {
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        verticalArrangement = Arrangement.spacedBy(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        repeat(3) { // 3 rows
//            Row(
//                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(16.dp)
//            ) {
//                repeat(3) { // 3 columns per row
//                    GradientMaterialCard()
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun GradientMaterialCard() {
//    val gradientColors = listOf(
//        Color(0xFF42A5F5), // Blue
//        Color(0xFFAB47BC)  // Purple
//    )
//
//    Card(
//        modifier = Modifier
//            .size(100.dp),
//        shape = RoundedCornerShape(12.dp),
//        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
//        colors = CardDefaults.cardColors(
//            containerColor = Color.Transparent
//        )
//    ) {
//        Box(
//            modifier = Modifier
//                .fillMaxSize()
//                .background(
//                    brush = Brush.linearGradient(
//                        colors = gradientColors
//                    )
//                ),
//            contentAlignment = Alignment.Center
//        ) {
//            // Add any content to the card (if needed), like text
//            Text(
//                text = "Card",
//                color = Color.White,
//                style = MaterialTheme.typography.titleMedium
//            )
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    JetComposeCardsTheme {
//        CardGridScreen()
//    }
//}
//
