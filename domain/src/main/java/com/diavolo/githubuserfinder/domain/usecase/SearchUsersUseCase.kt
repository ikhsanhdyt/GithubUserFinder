package com.diavolo.githubuserfinder.domain.usecase

import com.diavolo.githubuserfinder.domain.model.SearchResult
import com.diavolo.githubuserfinder.domain.repository.GitHubRepository
import javax.inject.Inject

class SearchUsersUseCase @Inject constructor(
    private val repository: GitHubRepository
) {
    suspend operator fun invoke(
        query: String,
        page: Int = 1,
        perPage: Int = 30
    ): Result<SearchResult> {
        if (query.isBlank()) {
            return Result.failure(IllegalArgumentException("Query cannot be empty"))
        }
        
        return try {
            val result = repository.searchUsers(query.trim(), page, perPage)
            if (result.isSuccess) {
                repository.saveSearchQuery(query.trim())
            }
            result
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
