package com.diavolo.githubuserfinder.domain.usecase

import com.diavolo.githubuserfinder.domain.model.GitHubUser
import com.diavolo.githubuserfinder.domain.repository.GitHubRepository
import javax.inject.Inject

class GetUserDetailsUseCase @Inject constructor(
    private val repository: GitHubRepository
) {
    suspend operator fun invoke(username: String): Result<GitHubUser> {
        if (username.isBlank()) {
            return Result.failure(IllegalArgumentException("Username cannot be empty"))
        }
        
        return repository.getUserDetails(username.trim())
    }
}
