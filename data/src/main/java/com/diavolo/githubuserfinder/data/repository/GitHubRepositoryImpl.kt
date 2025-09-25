package com.diavolo.githubuserfinder.data.repository

import com.diavolo.githubuserfinder.data.local.dao.SearchHistoryDao
import com.diavolo.githubuserfinder.data.local.entity.SearchHistoryEntity
import com.diavolo.githubuserfinder.data.mapper.toDomain
import com.diavolo.githubuserfinder.data.remote.api.GitHubApiService
import com.diavolo.githubuserfinder.domain.model.GitHubUser
import com.diavolo.githubuserfinder.domain.model.SearchResult
import com.diavolo.githubuserfinder.domain.repository.GitHubRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GitHubRepositoryImpl @Inject constructor(
    private val apiService: GitHubApiService,
    private val searchHistoryDao: SearchHistoryDao
) : GitHubRepository {
    
    override suspend fun searchUsers(query: String, page: Int, perPage: Int): Result<SearchResult> {
        return try {
            val response = apiService.searchUsers(query, page, perPage)
            if (response.isSuccessful) {
                response.body()?.let { searchResultDto ->
                    Result.success(searchResultDto.toDomain())
                } ?: Result.failure(Exception("Empty response body"))
            } else {
                Result.failure(Exception("API Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override suspend fun getUserDetails(username: String): Result<GitHubUser> {
        return try {
            val response = apiService.getUserDetails(username)
            if (response.isSuccessful) {
                response.body()?.let { userDto ->
                    Result.success(userDto.toDomain())
                } ?: Result.failure(Exception("Empty response body"))
            } else {
                Result.failure(Exception("API Error: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
    
    override fun getSearchHistory(): Flow<List<String>> {
        return searchHistoryDao.getAllSearchHistory().map { entities ->
            entities.map { it.query }
        }
    }
    
    override suspend fun saveSearchQuery(query: String) {
        searchHistoryDao.insertSearchQuery(SearchHistoryEntity(query))
    }
    
    override suspend fun clearSearchHistory() {
        searchHistoryDao.clearAllHistory()
    }
}
