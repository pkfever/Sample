package com.tooltrip.domain.repositoryimpl

import android.content.Context
import com.tooltrip.data.entities.Interchange
import com.tooltrip.data.getInterchangeDataFromJson
import com.tooltrip.domain.repository.InterchangeRepository
import com.tooltrip.domain.utils.Resource
import javax.inject.Inject

class InterchangeRepositoryImpl @Inject constructor(private val context: Context) :
    InterchangeRepository {

    override suspend fun getInterchangeList(): Resource<MutableList<Interchange>> {
        return try {

            val data = getInterchangeDataFromJson(context = context)
            Resource.success(data)

        } catch (e: Exception) {
            Resource.error(e)
        }
    }
}