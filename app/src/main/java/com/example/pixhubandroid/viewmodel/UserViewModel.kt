package com.example.pixhubandroid.viewmodel



import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pixhubandroid.model.AccountBean
import com.example.pixhubandroid.model.SessionManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private val _account = MutableStateFlow<AccountBean?>(null)
    val account: StateFlow<AccountBean?> get() = _account

    fun loadAccount(context: Context) {
        viewModelScope.launch {
            _account.value = SessionManager.getAccount(context)
        }
    }
    fun setAccount(account: AccountBean) {
        _account.value = account
    }

    fun logout(context: Context) {
        SessionManager.logout(context)
        _account.value = null
    }
}