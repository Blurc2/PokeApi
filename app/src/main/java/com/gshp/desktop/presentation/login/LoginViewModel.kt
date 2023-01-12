package com.gshp.desktop.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cgsa.artifacts.Constants
import com.cgsa.artifacts.base.baseApplication.BaseApplication
import com.cgsa.artifacts.base.baseNetwork.ApiService
import com.cgsa.artifacts.base.baseRoom.entity.User
import com.cgsa.artifacts.domain.repository.PreferenceRepository
import com.gshp.desktop.domain.login.LoginUseCase
import com.gshp.desktop.domain.utils.Failure
import com.gshp.desktop.domain.utils.Success
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginViewModel(private val loginUseCase: LoginUseCase, private val preferenceRepository: PreferenceRepository) : ViewModel() {

    private val _onDisplayLoader = MutableLiveData<Boolean>()
    val onDisplayLoader: LiveData<Boolean> get() = _onDisplayLoader

    private val _onError = MutableLiveData<String>()
    val onError: LiveData<String> get() = _onError

    fun login(username: String, password: String) {
        viewModelScope.launch {
            loginUseCase.login(username, password)
                .onEach { result ->
                    if (result is Success) {
                        with(preferenceRepository) {
                            if (getPreference<String>(Constants.Preferences.USERNAME) != username) {
                                setOrDeletePreference(Constants.Preferences.USERNAME.key, username)
                                setOrDeletePreference(Constants.Preferences.PASSWORD.key, password)
//                                setOrDeletePreference(Constants.Preferences.FIRSTCHECKIN.key, 0L)
//                                setOrDeletePreference(Constants.Preferences.DEVICEID.key, 0.toInt())
                                setOrDeletePreference(Constants.Preferences.FIREBASE_TOKEN.key, false)
                                setOrDeletePreference(Constants.Preferences.TERMS_ACCEPTED.key, false)
                            }
                        }
                    }
                }
                .flatMapConcat { result ->
                    when (result) {
                        is Success -> loginUseCase.getS3Params(result.value.token)
                        is Failure -> flowOf(result)
                    }
                }
                .collect { result ->
                    if (result is Success) {
                        preferenceRepository.setOrDeletePreference(Constants.Preferences.KEYS3.key, result.value.username)
                        preferenceRepository.setOrDeletePreference(Constants.Preferences.KEY_SECRET.key, result.value.password)
                    }
                }
        }
    }

//    override fun login(user: String, password: String): Observable<User> {
//        return userDataRepository.getMainUser().onErrorResumeNext(Single.just(User(id = -1)))
//            .toObservable()
//            .flatMap {
//                if (it.id != -1)
//                    userDataRepository.logout(it.accessToken)
//                else
//                    Observable.just("")
//            }
//            .flatMap {
//                userDataRepository.login(user, password)
//            }
//            .flatMap { user ->
//                userDataRepository.getConfigParamsS3(
//                    ApiService.getHeaders(user.accessToken),
//                    Constants.SERVICES.S3_REST_PATH
//                )
//                    .doOnNext {
//                        preferenceRepository.setOrDeletePreference(
//                            Constants.Preferences.KEYS3.key,
//                            it.username
//                        )
//                    }
//                    .doOnNext {
//                        preferenceRepository.setOrDeletePreference(
//                            Constants.Preferences.KEY_SECRET.key,
//                            it.password
//                        )
//                    }
//                    .map { user }
//            }
//            .flatMap { u ->
////                if (preferenceRepository.getPreference<String>(Constants.Preferences.USERNAME) != user){
//                userDataRepository.deleteAllObservable()
//                    .flatMap { pdvRepository.deleteAllObservable() }
//                    .flatMap { itemDataRepository.deleteAllObservable() }
//                    .flatMap { kpiUser.deleteAllObservable() }
//                    .flatMap { fileRepository.deleteAllObservable() }
//                    .flatMap { moduleRepository.deleteAllObservable() }
//                    .flatMap { chatDataRepository.deleteAllObservable() }
//                    .flatMap { geoDataRepository.deleteAllObservable() }
//                    .flatMap { firebaseTokenDataRepository.deleteAllObservable() }
//                    .flatMap { reportDataRepository.deleteAllObservable() }
//                    .subscribeOn(BaseApplication.application.scheduler.io())
//                    .map { u }
//
////                }else {
////                    Observable.just(u)
////                }
//
//            }
//            .doOnNext { u ->
//                u.isMain = true
//                if (preferenceRepository.getPreference<String>(Constants.Preferences.USERNAME) != user) {
//                    preferenceRepository.setOrDeletePreference(
//                        Constants.Preferences.USERNAME.key,
//                        user
//                    )
//                    preferenceRepository.setOrDeletePreference(
//                        Constants.Preferences.PASSWORD.key,
//                        password
//                    )
//                    preferenceRepository.setOrDeletePreference(
//                        Constants.Preferences.USERMAIN.key,
//                        u.id
//                    )
//                    preferenceRepository.setOrDeletePreference(
//                        Constants.Preferences.APP_BLOCKED.key,
//                        false
//                    )
//                    preferenceRepository.setOrDeletePreference(
//                        Constants.Preferences.FIRSTCHECKIN.key,
//                        0L
//                    )
//                    preferenceRepository.setOrDeletePreference(
//                        Constants.Preferences.DEVICEID.key,
//                        0.toInt()
//                    )
//                    preferenceRepository.setOrDeletePreference(
//                        Constants.Preferences.FIREBASE_TOKEN.key,
//                        false
//                    )
//                    preferenceRepository.setOrDeletePreference(
//                        Constants.Preferences.TERMS_ACCEPTED.key,
//                        false
//                    )
//
//                }
//
//            }
//            .flatMap { u ->
//                userDataRepository.insertRxReplaceStrategy(u).toObservable()
//                    .subscribeOn(BaseApplication.application.scheduler.io())
//                    .map { u }
//            }
//
//    }
}