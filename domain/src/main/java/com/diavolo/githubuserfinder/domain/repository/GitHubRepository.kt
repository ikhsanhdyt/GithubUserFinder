package com.diavolo.githubuserfinder.domain.repository

import com.diavolo.githubuserfinder.domain.model.GitHubUser
import com.diavolo.githubuserfinder.domain.model.SearchResult
import kotlinx.coroutines.flow.Flow

interface GitHubRepository {
    suspend fun searchUsers(query: String, page: Int = 1, perPage: Int = 30): Result<SearchResult>
    suspend fun getUserDetails(username: String): Result<GitHubUser>
    fun getSearchHistory(): Flow<List<String>>
    suspend fun saveSearchQuery(query: String)
    suspend fun clearSearchHistory()
}
