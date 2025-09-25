package com.diavolo.githubuserfinder.data.mapper

import com.diavolo.githubuserfinder.data.remote.dto.GitHubUserDto
import com.diavolo.githubuserfinder.data.remote.dto.SearchResultDto
import com.diavolo.githubuserfinder.domain.model.GitHubUser
import com.diavolo.githubuserfinder.domain.model.SearchResult

fun GitHubUserDto.toDomain(): GitHubUser {
    return GitHubUser(
        id = id,
        login = login,
        avatarUrl = avatarUrl,
        htmlUrl = htmlUrl,
        type = type,
        name = name,
        company = company,
        blog = blog,
        location = location,
        email = email,
        bio = bio,
        publicRepos = publicRepos,
        publicGists = publicGists,
        followers = followers,
        following = following,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun SearchResultDto.toDomain(): SearchResult {
    return SearchResult(
        totalCount = totalCount,
        incompleteResults = incompleteResults,
        items = items.map { it.toDomain() }
    )
}
