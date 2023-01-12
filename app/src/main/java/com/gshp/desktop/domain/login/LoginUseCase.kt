package com.gshp.desktop.domain.login

import com.cgsa.artifacts.Constants
import com.cgsa.artifacts.base.baseNetwork.ApiService
import com.cgsa.artifacts.data.dto.LoginResponse
import com.cgsa.artifacts.data.dto.S3RestDto
import com.gshp.desktop.domain.utils.Failure
import com.gshp.desktop.domain.utils.Error
import com.gshp.desktop.domain.utils.Error.*
import com.gshp.desktop.domain.utils.Result
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import okhttp3.ResponseBody

class LoginUseCase(private val loginRepository: LoginRepository, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

    fun login(username: String, password: String): Flow<Result<LoginResponse, Error>> {
        return flow {
            emit(loginRepository.login(username, password))
        }
            .catch { emit(Failure(Unknown(message = it.message.toString()))) }
            .flowOn(dispatcher)
            .conflate()
    }

    fun getS3Params(token: String): Flow<Result<S3RestDto, Error>> {
        return flow {
            emit(loginRepository.getS3Params(ApiService.getHeaders(token), Constants.SERVICES.S3_REST_PATH))
        }
            .catch { emit(Failure(Unknown(message = it.message.toString()))) }
            .flowOn(dispatcher)
            .conflate()
    }

    fun logout(token: String): Flow<Result<ResponseBody, Error>> {
        return flow {
            emit(loginRepository.logout(ApiService.getHeaders(token)))
        }
            .catch { emit(Failure(Unknown(message = it.message.toString()))) }
            .flowOn(dispatcher)
            .conflate()
    }
}