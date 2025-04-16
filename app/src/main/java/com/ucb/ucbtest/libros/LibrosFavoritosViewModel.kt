package com.ucb.ucbtest.libros

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.data.NetworkResult
import com.ucb.domain.Book
import com.ucb.ucbtest.R
import com.ucb.ucbtest.service.InternetConnection
import com.ucb.usecases.GetFavoriteBooks
import com.ucb.usecases.SaveBook
import com.ucb.usecases.SearchBooks
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LibrosFavoritosViewModel @Inject constructor (
    private val getFavoriteBooks: GetFavoriteBooks,
    @ApplicationContext private val context: Context
): ViewModel(){
    sealed class FavoritesState {
        object Loading : FavoritesState()
        object Empty : FavoritesState()
        data class Success(val books: List<Book>) : FavoritesState()
        data class Error(val message: String) : FavoritesState()
    }

    private val _state = MutableStateFlow<FavoritesState>(FavoritesState.Loading)
    val state: StateFlow<FavoritesState> = _state

    init {
        loadFavoriteBooks()
    }

    fun loadFavoriteBooks() {
        _state.value = FavoritesState.Loading
        viewModelScope.launch {
            try {
                val favoriteBooks = withContext(Dispatchers.IO) { getFavoriteBooks.invoke() }
                if (favoriteBooks.isEmpty()) {
                    _state.value = FavoritesState.Empty
                } else {
                    _state.value = FavoritesState.Success(favoriteBooks)
                }
            } catch (e: Exception) {
                _state.value = FavoritesState.Error(e.message ?: "Error desconocido")
            }
        }
    }

}
