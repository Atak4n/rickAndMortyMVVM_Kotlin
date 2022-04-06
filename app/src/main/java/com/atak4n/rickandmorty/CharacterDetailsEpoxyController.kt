package com.atak4n.rickandmorty

import com.airbnb.epoxy.EpoxyController
import com.atak4n.rickandmorty.databinding.ModelCharacterDetailsDataPointBinding
import com.atak4n.rickandmorty.databinding.ModelCharacterDetailsHeaderBinding
import com.atak4n.rickandmorty.databinding.ModelCharacterDetailsImageBinding
import com.atak4n.rickandmorty.epoxy.LoadingEpoxyModel
import com.atak4n.rickandmorty.epoxy.ViewBindingKotlinModel
import com.atak4n.rickandmorty.network.response.GetCharacterByIdResponse
import com.squareup.picasso.Picasso


class CharacterDetailsEpoxyController : EpoxyController() {

    var isLoading: Boolean = true
        set(value) {
            field = value
            if (field) {
                requestModelBuild()
            }
        }

    var characterResponse: GetCharacterByIdResponse? = null
        set (value) {
            field = value
            if (field != null) {
                isLoading = false
                requestModelBuild()
            }
        }

    override fun buildModels() {
        if(isLoading) {
            LoadingEpoxyModel().id("loading").addTo(this)
            return
        }

        if(characterResponse == null) {
            return
        }

        HeaderEpoxyModel(
            name = characterResponse!!.name,
            status = characterResponse!!.status,
            location = characterResponse!!.location.name
        ).id("header").addTo(this)

        ImageEpoxyModel(
            imageUrl = characterResponse!!.image,
        ).id("image").addTo(this)

        DataPointEpoxyModel(
            title = "Location",
            description = characterResponse!!.location.name,
        ).id("data_point_1").addTo(this)

    }
    data class HeaderEpoxyModel(
        val name: String,
        val status: String,
        val location: String,
    ): ViewBindingKotlinModel<ModelCharacterDetailsHeaderBinding>(R.layout.model_character_details_header){
        override fun ModelCharacterDetailsHeaderBinding.bind() {
            nameTextView.text = name
            aliveTextView.text = status
            locationTextView.text = location
        }
    }
}

data class ImageEpoxyModel(
    val imageUrl: String
): ViewBindingKotlinModel<ModelCharacterDetailsImageBinding>(R.layout.model_character_details_image) {
    override fun ModelCharacterDetailsImageBinding.bind() {
        Picasso.get().load(imageUrl).into(headerImageView)

    }
}

data class DataPointEpoxyModel(
    val title: String,
    val description: String
): ViewBindingKotlinModel<ModelCharacterDetailsDataPointBinding>(R.layout.model_character_details_data_point) {
    override fun ModelCharacterDetailsDataPointBinding.bind() {
        labelTextView.text = title
        textView.text = description

    }
}


