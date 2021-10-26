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