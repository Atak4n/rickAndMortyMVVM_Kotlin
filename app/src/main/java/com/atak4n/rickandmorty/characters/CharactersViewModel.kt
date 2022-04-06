package com.atak4n.rickandmorty.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.atak4n.rickandmorty.network.response.GetCharacterByIdResponse

class CharactersViewModel : ViewModel() {

    private val repository = CharactersRepository()
    private val pageListConfig: PagedList.Config = PagedList.Config.Builder()
        .setPageSize(20)
        .setPrefetchDistance(20*2)
        .build()

    private val dataSourceFactory = CharactersDataSourceFactory(viewModelScope, repository)
    val charactersPagedListLiveData: LiveData<PagedList<GetCharacterByIdResponse>> =
        LivePagedListBuilder(dataSourceFactory, pageListConfig).build()
}