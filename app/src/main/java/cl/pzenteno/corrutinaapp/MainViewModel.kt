package cl.pzenteno.corrutinaapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception


class MainViewModel : ViewModel() {
    var resultState by mutableStateOf("")
        private set
    var isLoading by mutableStateOf(false)
        private set

    fun bloqueoApp() {
        Thread.sleep(5000)
        resultState = "Respuesta de la API fetchData"
    }

    fun fetchData() {
        viewModelScope.launch {
            try {
                isLoading = true
                llamarAPI()
            } catch (e: Exception) {
                println("Error: ${e.message}")
            } finally {
                isLoading = false
            }
        }
    }

    private suspend fun llamarAPI() {
        val result = withContext(Dispatchers.IO) {
            delay(5000)
            "Respuesta de la API"
        }
        resultState = result
    }

}