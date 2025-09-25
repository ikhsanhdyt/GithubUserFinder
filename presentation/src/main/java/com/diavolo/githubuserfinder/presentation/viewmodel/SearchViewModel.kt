package com.diavolo.githubuserfinder.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.diavolo.githubuserfinder.domain.usecase.ClearSearchHistoryUseCase
import com.diavolo.githubuserfinder.domain.usecase.GetSearchHistoryUseCase
import com.diavolo.githubuserfinder.domain.usecase.SearchUsersUseCase
import com.diavolo.githubuserfinder.presentation.state.SearchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUsersUseCase: SearchUsersUseCase,
    private val getSearchHistoryUseCase: GetSearchHistoryUseCase,
    private val clearSearchHistoryUseCase: ClearSearchHistoryUseCase
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState.asStateFlow()
    
    init {
        observeSearchHistory()
    }
    
    private fun observeSearchHistory() {
        getSearchHistoryUseCase()
            .onEach { history ->
                _uiState.value = _uiState.value.copy(searchHistory = history)
            }
            .launchIn(viewModelScope)
    }
    
    fun searchUsers(query: String, isNewSearch: Boolean = true) {
        if (query.isBlank()) return
        
        viewModelScope.launch {
            try {
                val page = if (isNewSearch) 1 else _uiState.value.currentPage + 1
                
                _uiState.value = _uiState.value.copy(
                    isLoading = true,
                    errorMessage = null,
                    searchQuery = query,
                    users = if (isNewSearch) emptyList() else _uiState.value.users
                )
                
                searchUsersUseCase(query, page).fold(
                    onSuccess = { searchResult ->
                        val newUsers = if (isNewSearch) {
                            searchResult.items
                        } else {
                            _uiState.value.users + searchResult.items
                        }
                        
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            users = newUsers,
                            hasMorePages = searchResult.items.isNotEmpty() && searchResult.items.size >= 30,
                            currentPage = page
                        )
                    },
                    onFailure = { error ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            errorMessage = error.message ?: "Unknown error occurred"
                        )
                    }
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    errorMessage = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
    
    fun loadMoreUsers() {
        if (!_uiState.value.isLoading && _uiState.value.hasMorePages && _uiState.value.searchQuery.isNotBlank()) {
            searchUsers(_uiState.value.searchQuery, isNewSearch = false)
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(errorMessage = null)
    }
    
    fun clearSearchHistory() {
        viewModelScope.launch {
            clearSearchHistoryUseCase()
        }
    }
}
