package com.atak4n.rickandmorty.characters

import com.airbnb.epoxy.EpoxyModel
import com.airbnb.epoxy.paging.PagedListEpoxyController
import com.atak4n.rickandmorty.R
import com.atak4n.rickandmorty.databinding.ModelCharacterListItemBinding
import com.atak4n.rickandmorty.epoxy.ViewBindingKotlinModel
import com.atak4n.rickandmorty.network.response.GetCharacterByIdResponse
import com.squareup.picasso.Picasso

class CharacterListPagingEpoxyController : PagedListEpoxyController<GetCharacterByIdResponse>(){
    override fun buildItemModel(
        currentPosition: Int,
        item: GetCharacterByIdResponse?
    ): EpoxyModel<*> {
        return CharacterGridItemEpoxyModel(item!!.image, item.name).id(item.id)
    }

    data class  CharacterGridItemEpoxyModel(
        val imageUrl: String,
        val name: String
    ): ViewBindingKotlinModel<ModelCharacterListItemBinding>(R.layout.model_character_list_item) {

        override fun ModelCharacterListItemBinding.bind() {
            Picasso.get().load(imageUrl).into(characterImageView)
            characterNameTextView.text = name
        }
    }

}