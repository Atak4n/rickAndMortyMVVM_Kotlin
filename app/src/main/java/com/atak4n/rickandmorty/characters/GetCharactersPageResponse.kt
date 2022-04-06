package com.atak4n.rickandmorty.characters

import com.atak4n.rickandmorty.network.response.GetCharacterByIdResponse

data class GetCharactersPageResponse(
    val info: Info = Info(),
    val results: List<GetCharacterByIdResponse> = emptyList()
) {

    data class Info(
        val count: Int = 0,
        val pages: Int = 0,
        val next: String? = null,
        val prev: String? = null,
    )
}
