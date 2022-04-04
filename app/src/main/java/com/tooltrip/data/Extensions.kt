package com.tooltrip.data

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.tooltrip.data.entities.Interchange

val moshi: Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

fun getInterchangeDataFromJson(context: Context): MutableList<Interchange>? {

    val listType = Types.newParameterizedType(MutableList::class.java, Interchange::class.java)
    val adapter: JsonAdapter<MutableList<Interchange>> = moshi.adapter(listType)
    val file = "data.json"
    val data = context.assets.open(file).bufferedReader().use { it.readText() }
    return adapter.fromJson(data)
}