package ru.itech.lectotium.data


data class SignInRequest(
    val login: String,
    val password: String
)

data class SignUpRequest(
    val login: String,
    val email: String,
    val password: String
)

data class AuthTokenResponse(
    val accessToken: String,
    val refreshToken: String
)