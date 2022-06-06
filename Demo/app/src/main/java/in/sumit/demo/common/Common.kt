package `in`.sumit.demo.common

import `in`.sumit.demo.model.Category
import `in`.sumit.demo.model.WalkInResponse
import `in`.sumit.demo.retrofit.Api
import `in`.sumit.demo.retrofit.RetrofitClient
import android.util.Log
import com.denzcoskun.imageslider.constants.ActionTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemChangeListener
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.interfaces.TouchListener
import com.denzcoskun.imageslider.models.SlideModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class Common() {
    var api: Api? =null
    fun getBanners(callback:(List<Category>?)->Unit){
        try{
            api= RetrofitClient.getClient()!!.create(Api::class.java)
            api?.getData()?.enqueue(object : Callback<WalkInResponse> {
                override fun onResponse(
                    call: Call<WalkInResponse>,
                    response: Response<WalkInResponse>
                ) {
                    Log.d("#response", response.body().toString())
                    if(response.isSuccessful){
                        val result= response.body()?.data?.categories
                        callback(result)
                    }

                }

                override fun onFailure(call: Call<WalkInResponse>, t: Throwable) {
                    Log.e("#error",t.message.toString())
                }

            })
        }catch (e: Exception){
            Log.w("#exception",e.message.toString())
        }
    }
}