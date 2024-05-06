package ru.itech.lectotium.repository

import okhttp3.ResponseBody
import retrofit2.Response
import ru.itech.lectotium.data.AuthTokenResponse
import ru.itech.lectotium.data.SignInRequest
import ru.itech.lectotium.data.SignUpRequest
import ru.itech.lectotium.network.ApiClient

class Repository {
    suspend fun authenticate(signInRequest: SignInRequest): Response<AuthTokenResponse> {
        return ApiClient.apiInterface.authenticate(signInRequest)
    }

    suspend fun refreshToken(refreshToken: String): Response<AuthTokenResponse> {
        return ApiClient.apiInterface.refreshToken(refreshToken)
    }

    suspend fun registerUser(signUpRequest: SignUpRequest): Response<AuthTokenResponse> {
        return ApiClient.apiInterface.registerUser(signUpRequest)
    }

    suspend fun streamVideo(fileId: Long): Response<ResponseBody> {
        return ApiClient.apiInterface.streamVideo(fileId)
    }
}