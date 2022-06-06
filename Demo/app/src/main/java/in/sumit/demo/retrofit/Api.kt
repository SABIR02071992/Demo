package `in`.sumit.demo.retrofit

import `in`.sumit.demo.model.WalkInResponse
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("api/customer_home")
    fun getData():Call<WalkInResponse>
}