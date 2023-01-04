package com.example.eventhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.eventhub.addEvent.AddEventView
import com.example.eventhub.list.EventsList
import com.example.eventhub.ui.theme.EventHubTheme
import com.example.eventhub.R
import com.example.eventhub.details.DetailsActivity
import com.example.eventhub.saved.SavedActivty


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {


            EventHubTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                   // Navigation
                    val navController = rememberNavController()
                    Scaffold(bottomBar = {
                        BottomNavigationBar(
                            items = listOf(
                                BottomNavItem(
                                    name = stringResource(id = R.string.nav_menu_explore),
                                    route = "eventsList",
                                    drawable = R.drawable.baseline_explore_24
                                ),
                                BottomNavItem(
                                    name = stringResource(id = R.string.nav_menu_add),
                                    route = "add",
                                    drawable = R.drawable.baseline_add_circle_24
                                ),
                                BottomNavItem(
                                    name = stringResource(id = R.string.nav_menu_saved),
                                    route = "saved",
                                    drawable = R.drawable.round_bookmark_24
                                )

                            ),
                            navController = navController,
                            onItemClick = {
                                navController.navigate(it.route)
                            }
                        )
                    }) {
                        innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            Navigation(navController = navController)
                        }

                    }

                }
            }
        }
    }
}



@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "eventsList") {
        composable("eventsList") {
            EventsList(
                onEventClick = { eventId ->
                    navController.navigate("details/$eventId")
                }
            )
        }
        composable("add") {
            AddEventView()
        }

        composable("saved") {
            SavedActivty()
        }

        composable(
            "details/{eventId}"
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("eventId")?.let { DetailsActivity(it) }
        }

    }
}



@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier
            .shadow(
                elevation = 30.dp,
                shape = RectangleShape,
                clip = true
            ),
        backgroundColor = Color.White,

    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemClick(item) },
                selectedContentColor = colorResource(R.color.primary_blue),
                unselectedContentColor = colorResource(R.color.light_blue),
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(imageVector = ImageVector.vectorResource(item.drawable), contentDescription = item.name)
                        Text(
                            text = item.name,
                            textAlign = TextAlign.Center,
                            fontSize = 15.sp
                        )

                    }
                }
            )
        }
    }
}