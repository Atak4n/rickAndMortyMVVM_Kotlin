package com.atak4n.rickandmorty;

import com.atak4n.rickandmorty.characters.GetCharactersPageResponse
import com.atak4n.rickandmorty.network.response.GetCharacterByIdResponse
import retrofit2.Response
import retrofit2.http.GET;
import retrofit2.http.Path
import retrofit2.http.Query

interface RickandMortyService {

    @GET("character/{character-id}")
    suspend fun getCharacterById(
        @Path("character-id") characterId: Int
    ): Response<GetCharacterByIdResponse>

    @GET("character")
    suspend fun getCharactersPage(
        @Query("page") pageIndex: Int
    ): Response<GetCharactersPageResponse>
}
