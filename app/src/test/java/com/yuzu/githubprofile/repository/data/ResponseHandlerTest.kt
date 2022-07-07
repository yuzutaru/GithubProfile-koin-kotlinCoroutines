package com.yuzu.githubprofile.repository.data

import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException

/**
 * Created by Yustar Pramudana on 07/07/22.
 */

@RunWith(JUnit4::class)
class ResponseHandlerTest {
    lateinit var responseHandler: ResponseHandler

    @Before
    fun setUp() {
        responseHandler = ResponseHandler()
    }

    @Test
    fun `when exception code is 401 then return unauthorised`() {
        var httpException: HttpException
        lateinit var result: Resource<ProfileData>

        every {
            httpException = HttpException(Response.error<ProfileData>(401, mockk()))
            result = responseHandler.handleException<ProfileData>(httpException)
        }

        assertEquals("Unauthorised", result.message)
    }

    @Test
    fun `when timeout then return timeout error`() {
        val socketTimeoutException = SocketTimeoutException()
        val result = responseHandler.handleException<ProfileData>(socketTimeoutException)
        assertEquals("Timeout", result.message)
    }
}