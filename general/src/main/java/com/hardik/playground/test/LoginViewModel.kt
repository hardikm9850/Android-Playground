package com.hardik.playground.test

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hardik.playground.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val emailValidator: EmailValidator,
    private val passwordValidator: PasswordValidator,
) : ViewModel() {
    val errorMessageLiveData = MutableLiveData<Any>()
    val successEvent = MutableLiveData<Unit>()

    fun validateInput(email: String, password: String) {
        if (isEmailValid(email) && isPasswordValid(password)) {
            loginUseCase.validate(email, password)
                .onEach { result ->
                    successEvent.postValue(Unit)
                }
                .catch { throwable ->
                    errorMessageLiveData.postValue(throwable.message)
                }
                .launchIn(viewModelScope)
        }
    }

    fun isEmailValid(email: String): Boolean {
        when (val emailValidatorResult = emailValidator.validate(email)) {
            ValidEmailEnum.Valid -> {
                return true
            }

            ValidEmailEnum.INVALID_NO_DOMAIN, ValidEmailEnum.INVALID_TOO_SHORT -> errorMessageLiveData.postValue(
                emailValidatorResult.ordinal,
            )
        }
        return false
    }

    fun isPasswordValid(password: String): Boolean {
        when (val passwordValidatorResult = passwordValidator.validate(password)) {
            ValidPasswordEnum.Valid -> return true
            ValidPasswordEnum.INVALID_NO_NUMERIC_CHARACTER_FOUND,
            ValidPasswordEnum.INVALID_LENGTH_TOO_SHORT,
            -> errorMessageLiveData.postValue(
                passwordValidatorResult.ordinal,
            )
        }
        return false
    }
}

enum class ValidEmailEnum(message: Int) {
    Valid(R.string.success),
    INVALID_NO_DOMAIN(R.string.error_no_domain_found),
    INVALID_TOO_SHORT(R.string.error_email_too_short),
}

enum class ValidPasswordEnum(success: Int) {
    Valid(R.string.success),
    INVALID_NO_NUMERIC_CHARACTER_FOUND(R.string.error_no_numeric_in_password),
    INVALID_LENGTH_TOO_SHORT(R.string.error_password_too_short),
}

class EmailValidator {
    fun validate(email: String): ValidEmailEnum {
        return ValidEmailEnum.Valid
    }
}

class PasswordValidator {
    fun validate(password: String): ValidPasswordEnum {
        return ValidPasswordEnum.Valid
    }
}

class LoginUseCase {
    fun validate(email: String, password: String): Flow<Unit> {
        return flowOf()
    }
}

class BackgroundThread : Thread() {
    override fun run() {
        super.run()
        Log.d("BackgroundThread", Thread.currentThread().name)
    }
}

fun main() {
    val thread = BackgroundThread()
    thread.run()
}
