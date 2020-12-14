package com.ifpr.supertrivia.model

import com.google.gson.annotations.SerializedName


data class User(
    @SerializedName("email") var email: String,
    @SerializedName("name") var name: String,
    var password: String,
) {
    var id: Long? = null

    override fun equals(other: Any?) = other is User && this.id == other.id


}

