package com.example.composeperros
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiService {

    @GET("breeds/list/all")
    suspend fun getRazas(): Response<RazaRespuesta>

    @GET("breed/{raza}/images")
    suspend fun getImagenes(@Path("raza") raza: String): Response<DogRespuesta>

    @GET("breed/{raza}/{subraza}/images")
    suspend fun getImagenesSubraza(
        @Path("raza") raza: String,
        @Path("subraza") subraza: String
    ): Response<DogRespuesta>
}