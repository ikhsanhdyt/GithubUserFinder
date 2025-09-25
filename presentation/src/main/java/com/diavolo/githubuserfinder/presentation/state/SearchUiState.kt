package com.diavolo.githubuserfinder.presentation.state

import com.diavolo.githubuserfinder.domain.model.GitHubUser

data class SearchUiState(
    val isLoading: Boolean = false,
    val users: List<GitHubUser> = emptyList(),
    val searchQuery: String = "",
    val errorMessage: String? = null,
    val searchHistory: List<String> = emptyList(),
    val hasMorePages: Boolean = true,
    val currentPage: Int = 1
)
