package com.diavolo.githubuserfinder.domain.model

data class SearchResult(
    val totalCount: Int,
    val incompleteResults: Boolean,
    val items: List<GitHubUser>
)
