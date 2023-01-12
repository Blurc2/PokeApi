package com.gshp.desktop.data.login

import com.gshp.desktop.domain.login.LoginRepository

class LoginRepositoryImpl(private val loginDataSource: LoginDataSource) : LoginRepository {

    override fun login(username: String, password: String) = loginDataSource.login(username, password)

    override fun getS3Params(headers: Map<String, String>, appName: String) = loginDataSource.getS3Params(headers, appName)

    override fun logout(headers: Map<String, String>) = loginDataSource.logout(headers)
}