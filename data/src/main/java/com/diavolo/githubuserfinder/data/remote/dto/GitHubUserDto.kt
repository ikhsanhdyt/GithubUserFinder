package com.diavolo.githubuserfinder.data.remote.dto

import com.google.gson.annotations.SerializedName

data class GitHubUserDto(
    val id: Long,
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    @SerializedName("html_url")
    val htmlUrl: String,
    val type: String,
    val name: String? = null,
    val company: String? = null,
    val blog: String? = null,
    val location: String? = null,
    val email: String? = null,
    val bio: String? = null,
    @SerializedName("public_repos")
    val publicRepos: Int = 0,
    @SerializedName("public_gists")
    val publicGists: Int = 0,
    val followers: Int = 0,
    val following: Int = 0,
    @SerializedName("created_at")
    val createdAt: String? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
)
