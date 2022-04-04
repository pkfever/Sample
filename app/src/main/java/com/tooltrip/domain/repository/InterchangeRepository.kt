package com.tooltrip.domain.repository

import com.tooltrip.data.entities.Interchange
import com.tooltrip.domain.utils.Resource

interface InterchangeRepository {

    suspend fun getInterchangeList(): Resource<MutableList<Interchange>>
}