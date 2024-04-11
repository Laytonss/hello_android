package com.thoughtworks.helloworld_view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thoughtworks.helloworld_view.R
import com.thoughtworks.helloworld_view.activity.ui.theme.HelloWorld_viewTheme
import com.thoughtworks.helloworld_view.dataSource.TweetDataSource
import com.thoughtworks.helloworld_view.room.entity.Image
import com.thoughtworks.helloworld_view.room.entity.Tweet
import com.thoughtworks.helloworld_view.viewModel.TweetsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import javax.inject.Inject

@AndroidEntryPoint
class ComposeActivity : ComponentActivity() {

    @Inject
    lateinit var dataSource: TweetDataSource

    private val tweetsViewModel: TweetsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareTweetData()
        setContent {
            HelloWorld_viewTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Tweets(tweetsViewModel)
                }
            }
        }
    }

    private fun prepareTweetData() {
        try {
            dataSource.getAndInsertDataToDB()
        } catch (e: IOException) {
            Toast.makeText(applicationContext, "network request fail", Toast.LENGTH_LONG).show()
        }
    }
}

@Composable
private fun Tweets(
    tweetsViewModel: TweetsViewModel,
    modifier: Modifier = Modifier,
) {
    val tweets by tweetsViewModel.tweetsLiveData.observeAsState(initial = emptyList())
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = tweets) { tweet ->
            TweetItem(tweet = tweet)
        }
        item {
            Text(
                text = "到底部了",
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun TweetItem(modifier: Modifier = Modifier, tweet: Tweet) {
    Row(
        modifier = modifier.padding(start = 30.dp, top = 20.dp)
    ) {
        AvatarImage(tweet.sender?.avatar)
        Column(
            modifier = modifier
                .weight(1f)
                .padding(start = 20.dp)
        ) {
            Text(
                text = "${tweet.sender?.userName ?: tweet.sender?.nick}",
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "${tweet.content}",
            )
        }
    }
}

@Composable
fun AvatarImage(url: String?, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(url)
            .crossfade(true)
            .build(),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier.clip(CircleShape).width(50.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun TweetItemPreview() {
    val tweet = Tweet(1, "content", arrayListOf(Image("url")))
    HelloWorld_viewTheme {
        TweetItem(tweet = tweet)
    }
}