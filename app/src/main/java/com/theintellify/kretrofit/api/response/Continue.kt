package com.theintellify.kretrofit.api.response


import com.google.gson.annotations.SerializedName

data class Continue(
    @SerializedName("continue")
    val continueX: String,
    val sroffset: Int
)