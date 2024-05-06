package ru.itech.lectotium.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.itech.lectotium.data.SignUpRequest
import ru.itech.lectotium.repository.Repository


class RegisterViewModel(
    private val repository: Repository = Repository()
) : ViewModel() {

    private val _registerState = MutableStateFlow(RegisterState())
    val registerState = _registerState.asStateFlow()

    fun updateLogin(login: String) {
        _registerState.value = _registerState.value.copy(login = login)
    }

    fun updateEmail(email: String) {
        _registerState.value = _registerState.value.copy(email = email)
    }

    fun updatePassword(password: String) {
        _registerState.value = _registerState.value.copy(password = password)
    }

    fun registerUser() {
        viewModelScope.launch {
            val signUpRequest = SignUpRequest(
                login = _registerState.value.login,
                email = _registerState.value.email,
                password = _registerState.value.password
            )

            val response = repository.registerUser(signUpRequest)
            if (response.isSuccessful) {
                // Handle successful registration
            } else {
                // Handle registration error
            }
        }
    }
}

data class RegisterState(
    val login: String = "",
    val email: String = "",
    val password: String = ""
)