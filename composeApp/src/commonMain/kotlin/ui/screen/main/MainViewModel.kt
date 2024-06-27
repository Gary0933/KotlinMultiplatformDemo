package ui.screen.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel : ViewModel() {

    private val _isBottomBarVisible = MutableStateFlow(true)
    var isBottomBarVisible: StateFlow<Boolean> = _isBottomBarVisible

    fun hideBottomBar(hide: Boolean) {
        _isBottomBarVisible.value = !hide
    }

}