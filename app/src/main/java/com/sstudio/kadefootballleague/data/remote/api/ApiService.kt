package com.sstudio.kadefootballleague.data.remote.api

import com.sstudio.kadefootballleague.data.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("api/v1/json/{api_key}/all_leagues.php")
    fun getLeague(
        @Path("api_key") apiKey: String
    ): Call<LeagueResponse>

    @GET("api/v1/json/{api_key}/lookupleague.php")
    fun getLeagueDetail(
        @Path("api_key") apiKey: String,
        @Query("id") id: String
    ): Call<LeagueDetailResponse>

    @GET("api/v1/json/{api_key}/eventsnextleague.php")
    fun getNextMatch(
        @Path("api_key") apiKey: String,
        @Query("id") id: String
    ): Call<MatchResponse>

    @GET("api/v1/json/{api_key}/eventspastleague.php")
    fun getLastMatch(
        @Path("api_key") apiKey: String,
        @Query("id") id: String
    ): Call<MatchResponse>

    @GET("api/v1/json/{api_key}/lookupteam.php")
    fun getTeam(
        @Path("api_key") apiKey: String,
        @Query("id") id: String
    ): Call<TeamResponse>

    @GET("api/v1/json/{api_key}/searchevents.php")
    fun findMatch(
        @Path("api_key") apiKey: String,
        @Query("e") query: String
    ): Call<MatchSearchResponse>
}