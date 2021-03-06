package Portal.repository

import Portal.api.RetrofitInstance
import Portal.model.Post
import retrofit2.Response

class Repository {

    suspend fun getPost(): Response<Post>{
        return RetrofitInstance.api.getPost()
    }

    suspend fun getPost2(number: Int): Response<Post>{
        return RetrofitInstance.api.getPost2(number)
    }

    suspend fun getCustomPosts(userId: Int,sort: String, order: String): Response<List<Post>>{
        return RetrofitInstance.api.getCustomPost(userId,sort,order)
    }

    suspend fun getCustomPost2(userId: Int,options: Map<String,String>): Response<List<Post>>{
        return RetrofitInstance.api.getCustomPosts2(userId,options)
    }

}