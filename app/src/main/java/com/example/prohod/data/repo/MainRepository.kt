package com.example.prohod.data.repo

import com.example.prohod.data.api.ApiHelper
import com.example.prohod.data.model.create_user.AccountInfo
import com.example.prohod.data.model.create_user.UserCreateRequest
import com.example.prohod.data.model.create_user.UserInfo
import com.example.prohod.data.model.login.LoginRequest
import com.example.prohod.data.model.visit.Form
import com.example.prohod.data.model.Passport
import com.example.prohod.data.model.visit.VisitRequest
import com.example.prohod.utils.AuthSharedPref
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiHelper: ApiHelper,
    private val authSharedPref: AuthSharedPref
) {

//    suspend fun loginAdmin() = login()

    private suspend fun loginExistedUser() = login(authSharedPref.login, authSharedPref.password)

    suspend fun sendRequest(
        name: String,
        surname: String,
        patronymic: String,
        passportNumberAndSeries: String,
        passportIssueDate: String,
        passportIssuedBy: String,
        visitDate: String,
        whoToVisit: String,
        visitReason: String,
        email: String
    ): Boolean {

                return sendVisitRequest(
                    name,
                    surname,
                    patronymic,
                    passportNumberAndSeries,
                    passportIssueDate,
                    passportIssuedBy,
                    visitDate,
                    whoToVisit,
                    visitReason,
                    email
                )
    }

    private suspend fun createUser(
        name: String,
        surname: String,
        patronymic: String,
        email: String
    ): Boolean {
        val accountInfo = AccountInfo(
            userInfo = UserInfo(
                name,
                surname,
                email
            ),
            login = "${name.first()}.${patronymic.first()}.$surname"
        )
        val credentials = apiHelper.createUser(UserCreateRequest(accountInfo)).body()?.credentials
        credentials?.let {
            authSharedPref.login = it.login
            authSharedPref.password = it.password
            return true
        }
        return false
    }

    private suspend fun login(login: String?, password: String?): Boolean {

        if (login == null || password == null) {
            return false
        }

        val loginRequest = LoginRequest(
            login,
            password
        )

        val loginResponse = apiHelper.login(loginRequest).body()
        loginResponse?.let {
            authSharedPref.token = it.jwtToken.value
            if (it.user.role == "User") {
                authSharedPref.userId = it.user.id
            }
            return true
        }

        return false
    }

    suspend fun isVisitRequestAccepted(): Boolean {
        apiHelper.getVisitRequests().body()?.let {
            val form = it.visitRequests.last { visitRequest ->
                visitRequest.form.passport.fullName == authSharedPref.login
            }

            return form.status == "Accept"
        }
        return false
    }

    suspend fun sendVisitRequest(
        name: String,
        surname: String,
        patronymic: String,
        passportNumberAndSeries: String,
        passportIssueDate: String,
        passportIssuedBy: String,
        visitDate: String,
        whoToVisit: String,
        visitReason: String,
        email: String
    ): Boolean {
        val splittedNumberAndSeries = passportNumberAndSeries.split(" ")
        val fullName = "$surname $name $patronymic"
        val passport = Passport(
            fullName = fullName,
            series = splittedNumberAndSeries.first(),
            number = splittedNumberAndSeries.last(),
            whoIssued = passportIssuedBy,
            issueDate = passportIssueDate
        )
        val visitForm = Form(passport, visitDate, visitReason, whoToVisit, email)

        val response = apiHelper.sendVisitRequest(VisitRequest(visitForm))
        if (response.isSuccessful) {
            authSharedPref.login = fullName
        }
        return response.isSuccessful
    }
}