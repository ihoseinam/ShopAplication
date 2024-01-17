package ir.hoseinahmadi.shopapplication.Api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("send")
    fun senTextToTelegram(
        @Query("to") token: String,
        @Query("text") message: String,
    ): Call<MainModel>

}