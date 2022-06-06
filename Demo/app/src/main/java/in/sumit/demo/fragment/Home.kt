package `in`.sumit.demo.fragment

import `in`.sumit.demo.R
import `in`.sumit.demo.common.Common
import `in`.sumit.demo.common.RecyclerAdapter
import `in`.sumit.demo.model.Category
import `in`.sumit.demo.model.WalkInResponse
import `in`.sumit.demo.retrofit.Api
import `in`.sumit.demo.retrofit.RetrofitClient
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.constants.ActionTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.interfaces.ItemChangeListener
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.interfaces.TouchListener
import com.denzcoskun.imageslider.models.SlideModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Home : Fragment() {
    var imageSlider : ImageSlider? =null
    var api: Api? =null
    val imageList = ArrayList<SlideModel>() // Create image list

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root:View =inflater.inflate(R.layout.fragment_home, container, false)
        imageSlider= root.findViewById(R.id.image_slider)
        api= RetrofitClient.getClient()!!.create(Api::class.java)

        getBanners()

        val arrayList: ArrayList<Category> = ArrayList<Category>()
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler)
        Common().getBanners() { obj ->
            for (p in obj!!) {
                Log.d("##RES", p.icon)
                arrayList.add(p)
            }
            val mLayoutManager: RecyclerView.LayoutManager = GridLayoutManager(context, 3)
            recyclerView.layoutManager = mLayoutManager
//            recyclerView?.layoutManager = LinearLayoutManager(requireContext())
            val recyclerAdapter = RecyclerAdapter(
                requireContext(),
                R.layout.card,
                arrayList
            )

            recyclerView?.adapter = recyclerAdapter
        }
        return root

    }
    fun getBanners(){
        try{
            api?.getData()?.enqueue(object : Callback<WalkInResponse> {
                override fun onResponse(
                    call: Call<WalkInResponse>,
                    response: Response<WalkInResponse>
                ) {
                    Log.d("#response", response.body().toString())
                    if(response.isSuccessful){
                        for (banner in response.body()?.data?.banners!!){
                            imageList.add(SlideModel(banner.image, ScaleTypes.FIT))
                        }
                        imageSlider?.setImageList(imageList)
                        /*imageList.add(SlideModel("https://bit.ly/37Rn50u", "Baby Owl", ScaleTypes.FIT))
                        imageList.add(SlideModel("https://bit.ly/2BteuF2", "Elephants and tigers may become extinct.", ScaleTypes.FIT ))
                        imageList.add(SlideModel("https://bit.ly/3fLJf72", "The population of elephants is decreasing in the world.", ScaleTypes.FIT))*/

                        imageSlider?.setItemClickListener(object : ItemClickListener {
                            override fun onItemSelected(position: Int) {
                                // You can listen here.
                            }
                        })

                        imageSlider?.setItemChangeListener(object : ItemChangeListener {
                            override fun onItemChanged(position: Int) {
                                //println("Pos: " + position)
                            }
                        })

                        imageSlider?.setTouchListener(object : TouchListener {
                            override fun onTouched(touched: ActionTypes) {
                                if (touched == ActionTypes.DOWN){
                                    imageSlider!!.stopSliding()
                                } else if (touched == ActionTypes.UP ) {
                                    imageSlider!!.startSliding(1000)
                                }
                            }
                        })
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