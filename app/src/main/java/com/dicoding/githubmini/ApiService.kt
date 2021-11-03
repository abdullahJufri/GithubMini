package com.dicoding.githubmini

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getUsers(
        @Query("q") query: String
    ): Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getUserDetail(
        @Path("username") username: String
    ): Call<User>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollower(
        @Path("username") username: String
    ): Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ): Call<ArrayList<User>>
}