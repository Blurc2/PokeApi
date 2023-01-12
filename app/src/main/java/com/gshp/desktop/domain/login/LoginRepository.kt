package com.gshp.desktop.domain.login

import com.cgsa.artifacts.data.dto.LoginResponse
import com.cgsa.artifacts.data.dto.S3RestDto
import com.gshp.desktop.domain.utils.Error
import com.gshp.desktop.domain.utils.Result
import okhttp3.ResponseBody

interface LoginRepository {
    fun login(username: String, password: String): Result<LoginResponse, Error>
    fun getS3Params(headers: Map<String, String>, appName: String): Result<S3RestDto, Error>
    fun logout(headers: Map<String, String>): Result<ResponseBody, Error>
}