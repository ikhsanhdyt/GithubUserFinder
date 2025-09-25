package com.diavolo.githubuserfinder.data.remote.api

import com.diavolo.githubuserfinder.data.remote.dto.GitHubUserDto
import com.diavolo.githubuserfinder.data.remote.dto.SearchResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {
    
    @GET("search/users")
    suspend fun searchUsers(
        @Query("q") query: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 30
    ): Response<SearchResultDto>
    
    @GET("users/{username}")
    suspend fun getUserDetails(
        @Path("username") username: String
    ): Response<GitHubUserDto>
    
    companion object {
        const val BASE_URL = "https://api.github.com/"
    }
}
