package com.thoughtworks.helloworld_view.activity


import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.thoughtworks.helloworld_view.activity.ui.theme.HelloWorld_viewTheme
import com.thoughtworks.helloworld_view.dataSource.TweetDataSource
import com.thoughtworks.helloworld_view.room.entity.Image
import com.thoughtworks.helloworld_view.room.entity.Tweet
import com.thoughtworks.helloworld_view.viewModel.TweetsViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import javax.inject.Inject
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextField

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
        AvatarImageWithDialog(tweet, modifier)
        Column(
            modifier = modifier
                .weight(1f)
                .padding(start = 20.dp)
        ) {
            Text(
                text = "${tweet.sender?.userName ?: tweet.sender?.nick}",
                fontWeight = FontWeight.Bold
            )
            ContentWithEdit(tweet)
        }
    }
}

@Composable
fun ContentWithEdit(tweet: Tweet, modifier: Modifier = Modifier) {
    var contentText by remember { mutableStateOf(tweet.content!!) }
    var editingText by remember { mutableStateOf(tweet.content!!) }
    var isEdit by remember { mutableStateOf(false) }
    Text(
        text = contentText,
        modifier = modifier.clickable { isEdit = true }
    )
    if (isEdit) {
        TextField(
            value = editingText,
            onValueChange = { editingText = it }
        )
        Row {
            Button(onClick = { contentText = editingText; isEdit = false }) {
                Text("Save")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = { editingText = contentText; isEdit = false }) {
                Text("Cancel")
            }
        }
    }
}

@Composable
fun AvatarImageWithDialog(tweet: Tweet, modifier: Modifier = Modifier) {
    var showDialog by remember { mutableStateOf(false) }
    val imageModel = ImageRequest.Builder(LocalContext.current)
        .data(tweet.sender?.avatar)
        .crossfade(true)
        .build()
    Box(
        modifier = modifier.clickable { showDialog = true }
    ) {
        AvatarImage(imageModel, 50)
    }
    if (showDialog) {
        ImageDialog(imageModel) { showDialog = false }
    }
}

@Composable
fun AvatarImage(imageModel: ImageRequest, width: Int, modifier: Modifier = Modifier) {
    AsyncImage(
        model = imageModel,
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .clip(CircleShape)
            .width(width.dp)
    )
}

@Composable
fun ImageDialog(imageModel: ImageRequest, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        text = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(300.dp)
            ) {
                AvatarImage(imageModel, 300)
            }
        },
        confirmButton = { }
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