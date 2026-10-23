package com.arnix.typey.repository.data

import com.arnix.typey.common.core.Resource
import com.arnix.typey.data.remote.model.DataApiResponse
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface defining operations for fetching remote API data.
 */
interface DataApiRepository {

    fun fetchDataStream(): Flow<Resource<DataApiResponse>>

    suspend fun fetchData(): Resource<DataApiResponse>

}
