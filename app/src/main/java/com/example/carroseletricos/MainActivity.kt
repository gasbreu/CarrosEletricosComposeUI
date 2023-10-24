package com.example.carroseletricos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.carroseletricos.data.TabRowItem
import com.example.carroseletricos.ui.theme.ComposeTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TabScreen(text = "mopa")
                }
            }
        }
    }
}

val tabRowItems = listOf(
    TabRowItem(
        title = "Carros",
        screen = { TabScreen(text = "Favoritos") },
    ),
    TabRowItem(
        title = "Favoritos",
        screen = { TabScreen(text = "Favoritos") },
    )
)

@OptIn(ExperimentalPagerApi::class,)
@Composable
fun TabScreen(text: String) {

    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall
        )

    Column(
        modifier = Modifier
            .padding(16.dp)
    ) {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator()
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            }
        ) {
            tabRowItems.forEachIndexed { index, tabRowItem ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.lauch { pagerState.animateScrollToPage(index) }
                    },
                    text = {
                        Text(
                        text = tabRowItem.title,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        )

                    }
                )
            }
        }
        HorizontalPager(
            count = tabRowItems.size,
            state = pagerState,
        ) {
            tabRowItems[pagerState.currentPage].screen()
        }
    }

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeTheme {
        TabScreen(text = "mopa")
    }
}