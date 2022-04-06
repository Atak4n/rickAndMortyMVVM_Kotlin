package com.atak4n.rickandmorty.network.response


data class GetCharacterByIdResponse(
    val id: Int,
    val image: String,
    val location: Location = Location(),
    val name: String,
    val origin: Origin = Origin(),
    val status: String,
) {

    data class Location(
        val name: String = "",
        val url: String = ""
    )
    data class Origin(
    val name: String = "",
    val url: String = ""
    )
}