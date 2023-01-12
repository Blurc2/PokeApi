package com.gshp.desktop.framework.login

import com.cgsa.artifacts.base.baseNetwork.ApiService
import com.cgsa.artifacts.data.dto.LoginResponse
import com.cgsa.artifacts.data.dto.S3RestDto
import com.gshp.desktop.data.login.LoginDataSource
import com.gshp.desktop.domain.utils.Error.*
import com.gshp.desktop.domain.utils.Error
import com.gshp.desktop.domain.utils.Result
import com.gshp.desktop.domain.utils.Failure
import com.gshp.desktop.domain.utils.Success
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException

class LoginDataSourceImpl(private val loginService: LoginService) : LoginDataSource {

    override fun login(username: String, password: String): Result<LoginResponse, Error> {
        return handleRequest {

            val response = loginService.login(ApiService.getHeaders(), username, password).execute().body()

            response?.let { Success(it) } ?: Failure(Unknown())
        }
    }

    override fun getS3Params(headers: Map<String,String>, appName: String): Result<S3RestDto, Error> {
        return handleRequest {

            val response = loginService.getConfigParamsS3(headers, appName).execute().body()

            response?.let { Success(it) } ?: Failure(Unknown())
        }
    }

    override fun logout(headers: Map<String,String>): Result<ResponseBody, Error> {
        return handleRequest {

            val response = loginService.logout(headers).execute().body()

            response?.let { Success(it) } ?: Failure(Unknown())
        }
    }

    private fun <T> handleRequest(block: () -> Result<T, Error>): Result<T, Error> {
        return try {
            block()
        } catch (e: ConnectException) {
            Failure(NetworkError())
        } catch (e: HttpException) {
            Failure(HTTPError(e.code(), e.message(), e))
        } catch (e: RuntimeException) {
            Failure(Unknown())
        } catch (e: IOException) {
            Failure(Unknown())
        }
    }
}