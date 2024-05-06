package ru.itech.lectotium.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import ru.itech.lectotium.data.AuthTokenResponse
import ru.itech.lectotium.data.SignInRequest
import ru.itech.lectotium.data.SignUpRequest

interface ApiInterface {
    @POST("api/auth/login")
    suspend fun authenticate(@Body signInRequest: SignInRequest): Response<AuthTokenResponse>

    @POST("api/auth/refresh")
    suspend fun refreshToken(@Query("refresh_token") refreshToken: String): Response<AuthTokenResponse>

    @POST("api/auth/register")
    suspend fun registerUser(@Body signUpRequest: SignUpRequest): Response<AuthTokenResponse>

    @GET("api/v1/stream/stream/{fileId}")
    suspend fun streamVideo(@Path("fileId") fileId: Long): Response<ResponseBody>
}
