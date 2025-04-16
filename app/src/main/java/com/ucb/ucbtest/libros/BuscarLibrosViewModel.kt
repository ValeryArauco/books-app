package com.ucb.ucbtest.libros

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ucb.data.NetworkResult
import com.ucb.domain.Book
import com.ucb.ucbtest.R
import com.ucb.ucbtest.service.InternetConnection
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
class BuscarLibrosViewModel @Inject constructor (private val searchBooks: SearchBooks, @ApplicationContext private val context: Context): ViewModel(){
    sealed class SearchState {
        object Initial : SearchState()
        object Loading : SearchState()
        object Empty : SearchState()
        data class Success(val books: List<Book>) : SearchState()
        data class Error(val message: String) : SearchState()
    }

    private val _state = MutableStateFlow<SearchState>(SearchState.Initial)
    val state: StateFlow<SearchState> = _state

    fun loadBooks(title : String){
        _state.value = SearchState.Loading
        viewModelScope.launch {
            if ( InternetConnection.isConnected(context) ) {
                val result = withContext(Dispatchers.IO) { searchBooks.invoke(title) }
                when (result) {
                    is NetworkResult.Success -> {
                        if (result.data.isEmpty()) {
                            _state.value = SearchState.Empty
                        } else {
                            _state.value = SearchState.Success(result.data)
                        }
                    }
                    is NetworkResult.Error -> {
                        _state.value = SearchState.Error(result.error)
                    }
                }
            } else {
                _state.value = SearchState.Error(context.getString(R.string.internet_conection_error))
            }

        }
    }
}
