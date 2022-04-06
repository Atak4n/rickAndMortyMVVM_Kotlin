package com.atak4n.rickandmorty

import com.atak4n.rickandmorty.characters.GetCharactersPageResponse
import com.atak4n.rickandmorty.network.response.GetCharacterByIdResponse
import retrofit2.Response

class ApiClient (
    private val rickandMortyService: RickandMortyService
        )
{
    suspend fun getCharacterById(characterId: Int): SimpleResponse<GetCharacterByIdResponse>{
        return safeApiCall { rickandMortyService.getCharacterById(characterId) }
    }
    suspend fun getCharactersPage(pageIndex: Int): SimpleResponse<GetCharactersPageResponse>{
        return safeApiCall { rickandMortyService.getCharactersPage(pageIndex) }
    }
    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            SimpleResponse.failure(e)
        }
    }
}