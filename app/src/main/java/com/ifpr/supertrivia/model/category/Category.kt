package com.ifpr.supertrivia.model.category

import com.google.gson.annotations.SerializedName

data class Category (
    @SerializedName("name") var name: String,
) {
    var id: Long? = null

//    override fun equals(other: Any?) = other is User && this.id == other.id


}