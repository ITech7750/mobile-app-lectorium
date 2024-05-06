package ru.itech.lectotium.models

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itech.lectotium.repository.Repository

class VideoPlayerViewModel(
    private val repository: Repository = Repository()
) : ViewModel() {

    private val _videoState = MutableStateFlow(VideoState())
    val videoState = _videoState.asStateFlow()

    fun updateFileId(fileId: String) {
        _videoState.value = _videoState.value.copy(fileId = fileId)
    }

    fun playVideo() {
        viewModelScope.launch {
            val fileId = _videoState.value.fileId.toLongOrNull() ?: return@launch

            val response = repository.streamVideo(fileId)
            if (response.isSuccessful) {
                val videoUri = response.body()?.let { responseBody ->
                    Uri.parse(responseBody.byteStream().toString())
                }
                _videoState.value = _videoState.value.copy(
                    isPlaying = true,
                    videoUri = videoUri
                )
            }
        }
    }
}

data class VideoState(
    val fileId: String = "",
    val isPlaying: Boolean = false,
    val videoUri: Uri? = null
)