package com.sk.cavistaassignment.network

import com.sk.cavistaassignment.model.ImageSearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * interface for app APIs
 *
 */
interface AppAPIs {

    /**
     * get Search Image API
     *
     * @param results
     * @return
     */
    @GET("3/gallery/search/{page}")
    suspend fun getImagesFromSearch(
        @Path("page") page: Int,
        @Query("q") queryString: String
    ): Response<ImageSearchResponse>
}