package com.gshp.desktop.framework.login

import com.cgsa.artifacts.Constants
import com.cgsa.artifacts.data.dto.LoginResponse
import com.cgsa.artifacts.data.dto.S3RestDto
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface LoginService {

    @FormUrlEncoded
    @POST(Constants.SERVICES.LOGIN)
    fun login(
        @HeaderMap headerMap: Map<String, String>,
        @Field("username") username: String,
        @Field("password") password: String,
        @Field("grant_type") type: String = "password"
    ): Call<LoginResponse>

    @GET(Constants.SERVICES.S3)
    fun getConfigParamsS3(@HeaderMap header: Map<String, String>, @Path("name") name: String): Call<S3RestDto>

    @POST(Constants.SERVICES.LOGOUT)
    fun logout(@HeaderMap headerMap: Map<String,String>): Call<ResponseBody>
}