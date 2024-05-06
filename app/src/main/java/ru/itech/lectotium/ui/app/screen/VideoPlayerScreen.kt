package ru.itech.lectotium.ui.app.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.itech.lectotium.models.VideoPlayerViewModel


@Composable
fun VideoPlayerScreen(
    viewModel: VideoPlayerViewModel = VideoPlayerViewModel()
) {
    val videoState = viewModel.videoState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = videoState.value.fileId,
            onValueChange = { viewModel.updateFileId(it) },
            label = { Text("File ID") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { viewModel.playVideo() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Play Video")
        }

        if (videoState.value.isPlaying) {
            videoState.value.videoUri?.let {
                VideoPlayer(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    videoUri = it
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VideoScreenPreview() {
    VideoPlayerScreen()

}