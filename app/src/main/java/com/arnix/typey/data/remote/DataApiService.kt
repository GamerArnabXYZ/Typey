package com.arnix.typey.data.remote

import com.arnix.typey.data.remote.model.DataApiResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit network service interface for fetching data from remote endpoint.
 */
interface DataApiService {

    @GET("api/data.json")
    suspend fun getData(): Response<DataApiResponse>

}
