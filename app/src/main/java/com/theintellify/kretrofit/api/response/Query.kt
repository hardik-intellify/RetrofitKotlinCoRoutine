package com.theintellify.kretrofit.api.response


import com.google.gson.annotations.SerializedName

data class Query(
    val search: List<Search>,
    val searchinfo: Searchinfo
)