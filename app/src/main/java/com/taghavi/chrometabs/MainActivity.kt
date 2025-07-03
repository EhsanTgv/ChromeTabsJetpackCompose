package com.taghavi.chrometabs

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.ShoppingCart
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.taghavi.chrometabs.ui.theme.ChromeTabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChromeTabsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    TabView(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun TabView(modifier: Modifier = Modifier) {
    val tabs = remember {
        mutableStateListOf(
            TabItem(0, "Home", Icons.Rounded.Home) { Text("Home Content") },
            TabItem(1, "Mainbox", Icons.Rounded.Email) { Text("Mailbox Content") },
            TabItem(2, "Shop", Icons.Rounded.ShoppingCart) { Text("Shop Content") },
            TabItem(3, "Settings", Icons.Rounded.Settings) { Text("Settings Content") },
        )
    }

    var selectedTab by remember { mutableStateOf<TabItem?>(tabs.first()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondaryContainer),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LazyRow(
            modifier = modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            items(tabs, key = { it.id }) { tab ->
                val isActive = (tab.id == selectedTab?.id)

                Tab(
                    icon = tab.icon,
                    title = tab.title,
                    containerColor = if (isActive) {
                        MaterialTheme.colorScheme.surface
                    } else {
                        Color.Transparent
                    },
                    contentColor = if (isActive) {
                        MaterialTheme.colorScheme.onSurface
                    } else {
                        MaterialTheme.colorScheme.onSecondaryContainer
                    },
                    onClose = {
                        val closedIndex = tabs.indexOf(tab)
                        if (tabs.remove(tab)) {
                            if (isActive) {
                                selectedTab =
                                    tabs.getOrNull(closedIndex.coerceAtMost(tabs.lastIndex))
                            }
                        }
                    },
                    onClick = {
                        selectedTab = tab
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ChromeTabsTheme {
        TabView()
    }
}