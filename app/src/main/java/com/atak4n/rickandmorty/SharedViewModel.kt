package com.atak4n.rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.atak4n.rickandmorty.network.response.GetCharacterByIdResponse
import kotlinx.coroutines.launch

class SharedViewModel: ViewModel() {

    private val repository = SharedRepository()

    private val _characterByLiveData = MutableLiveData<GetCharacterByIdResponse?>()
    val characterByLiveData: LiveData<GetCharacterByIdResponse?> = _characterByLiveData

    fun refreshCharacter(characterId: Int) {
        viewModelScope.launch {
            val response = repository.getCharacterById(characterId)

            _characterByLiveData.postValue(response)

        }

    }
}