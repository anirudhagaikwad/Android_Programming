package com.anirudha.projectsjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Camera
//import androidx.compose.material.icons.filled.Message
import androidx.compose.material.icons.filled.Settings
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.core.view.WindowCompat
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.filled.AddCard


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Disable default system window insets handling
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavigationHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf("camera", "message", "setting")
    var selectedTab by remember { mutableStateOf("camera") }

    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    when (screen) {
                        "camera" -> Icon(Icons.Filled.Camera, contentDescription = "Camera")
                        "message" -> Icon(Icons.Filled.AddCard, contentDescription = "Message")
                        "setting" -> Icon(Icons.Filled.Settings, contentDescription = "Setting")
                    }
                },
                label = { Text(screen.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }) },
                selected = selectedTab == screen,
                onClick = {
                    if (selectedTab != screen) {
                        selectedTab = screen
                        navController.navigate(screen) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) { saveState = true }
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun NavigationHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = "camera",
        modifier = modifier.windowInsetsPadding(WindowInsets.systemBars) // Handling insets
    ) {
        composable("camera") { CameraScreen() }
        composable("message") { MessageScreen() }
        composable("setting") { SettingScreen() }
    }
}

@Composable
fun CameraScreen() {
    Text("Camera Screen", modifier = Modifier.padding(16.dp))
}

@Composable
fun MessageScreen() {
    Text("Message Screen", modifier = Modifier.padding(16.dp))
}

@Composable
fun SettingScreen() {
    Text("Setting Screen", modifier = Modifier.padding(16.dp))
}

//@Composable
//fun CameraScreen() {
//    val context = LocalContext.current
//    val cameraPermissionLauncher = rememberLauncherForActivityResult(
//        contract = ActivityResultContracts.RequestPermission(),
//        onResult = { isGranted: Boolean ->
//            if (isGranted) {
//                launchCamera(context)
//            } else {
//                Toast.makeText(context, "Camera permission denied", Toast.LENGTH_SHORT).show()
//            }
//        }
//    )
//
//    // Function to launch camera
//    fun launchCamera(context: Context) {
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//        if (intent.resolveActivity(context.packageManager) != null) {
//            context.startActivity(intent)
//        }
//    }
//
//    // UI for CameraScreen
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text("Camera Screen")
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        Button(onClick = {
//            // Check permission before launching the camera
//            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
//        }) {
//            Text("Launch Camera")
//        }
//    }
//}


