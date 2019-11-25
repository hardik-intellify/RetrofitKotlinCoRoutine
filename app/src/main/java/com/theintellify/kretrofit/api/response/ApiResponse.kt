package com.theintellify.kretrofit.api.response


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    val batchcomplete: String,
    @SerializedName("continue")
    val continueX: Continue,
    val query: Query
)