package com.atak4n.rickandmorty.characters

import com.atak4n.rickandmorty.NetworkLayer


class CharactersRepository {

    suspend fun getCharactersPage(pageIndex: Int): GetCharactersPageResponse?{
        val request = NetworkLayer.apiClient.getCharactersPage(pageIndex)

        if (request.failed || !request.isSuccessful) {
            return null
        }
        return request.body


    }




}