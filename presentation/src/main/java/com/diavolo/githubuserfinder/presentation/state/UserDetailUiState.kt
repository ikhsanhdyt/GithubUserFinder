package com.diavolo.githubuserfinder.presentation.state

import com.diavolo.githubuserfinder.domain.model.GitHubUser

data class UserDetailUiState(
    val isLoading: Boolean = false,
    val user: GitHubUser? = null,
    val errorMessage: String? = null
)
