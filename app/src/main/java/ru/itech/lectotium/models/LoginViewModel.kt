package ru.itech.lectotium.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itech.lectotium.data.SignInRequest
import ru.itech.lectotium.repository.Repository


class LoginViewModel(
    private val repository: Repository = Repository()
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun updateLogin(login: String) {
        _loginState.value = _loginState.value.copy(login = login)
    }

    fun updatePassword(password: String) {
        _loginState.value = _loginState.value.copy(password = password)
    }

    fun authenticate() {
        viewModelScope.launch {
            val signInRequest = SignInRequest(
                login = _loginState.value.login,
                password = _loginState.value.password
            )

            val response = repository.authenticate(signInRequest)
            if (response.isSuccessful) {
                _loginState.value = _loginState.value.copy(isAuthenticated = true)
            }
        }
    }
}

data class LoginState(
    val login: String = "",
    val password: String = "",
    val isAuthenticated: Boolean = false
)