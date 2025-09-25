package com.diavolo.githubuserfinder.domain.usecase

import com.diavolo.githubuserfinder.domain.repository.GitHubRepository
import javax.inject.Inject

class ClearSearchHistoryUseCase @Inject constructor(
    private val repository: GitHubRepository
) {
    suspend operator fun invoke() {
        repository.clearSearchHistory()
    }
}
