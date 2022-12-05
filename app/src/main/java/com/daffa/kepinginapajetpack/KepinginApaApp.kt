package com.daffa.kepinginapajetpack

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.daffa.kepinginapajetpack.ui.navigation.NavigationItem
import com.daffa.kepinginapajetpack.ui.navigation.Screen
import com.daffa.kepinginapajetpack.ui.screen.home.HomeScreen
import com.daffa.kepinginapajetpack.ui.theme.KepinginApaJetpackTheme
import com.daffa.kepinginapajetpack.ui.screen.detail.DetailScreen

@Composable
fun KepinginApaApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != Screen.DetailWish.route) {
                BottomBar(navController = navController)
            }
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    navigateToDetail = { wishId ->
                        navController.navigate(Screen.DetailWish.createRoute(wishId))
                    }
                )
            }

//            composable(Screen.Wishlist.route) {
//                val context = LocalContext.current
//                CartScreen(
//                    onOrderButtonClicked = { message ->
//                        shareOrder(context, message)
//                    }
//                )
//            }
            composable(Screen.About.route) {
//                ProfileScreen()
            }
            composable(
                route = Screen.DetailWish.route,
                arguments = listOf(navArgument("wishId") { type = NavType.IntType }),
            ) {
                val id = it.arguments?.getInt("wishId") ?: 0
                DetailScreen(
                    wishId = id,
                    navigateBack = {
                        navController.navigateUp()
                    },
                    navigateToWishlist = {
                        navController.popBackStack()
//                        navController.navigate(Screen.Wishlist.route) {
//                            popUpTo(navController.graph.findStartDestination().id) {
//                                saveState = true
//                            }
//                            launchSingleTop = true
//                            restoreState = true
//                        }
                    }
                )
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    BottomNavigation(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.wishlist),
                icon = Icons.Default.List,
                screen = Screen.Wishlist
            ),
            NavigationItem(
                title = stringResource(R.string.about),
                icon = Icons.Default.AccountCircle,
                screen = Screen.About
            ),
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun KepinginAppPreview() {
    KepinginApaJetpackTheme() {
        KepinginApaApp()
    }
}