package com.yuzu.githubprofile.repository.remote.contract

import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.data.UserData
import com.yuzu.githubprofile.repository.remote.api.ProfileApi
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException

/**
 * Created by Yustar Pramudana on 07/07/22.
 */

@RunWith(JUnit4::class)
class ProfileRepositoryTest {

    private lateinit var api: ProfileApi
    private lateinit var repository: ProfileRepository

    private val profileData = ProfileData(0, "yuzu")
    private val userList = listOf(UserData(0,0))

    private val response = Result.success(profileData)
    private val responseList = Result.success(userList)
    private val errorResponse = Result.success(Result.failure<RuntimeException>(RuntimeException("Something went wrong", null)))


    @Before
    fun setUp() {
        api = mockk()
        val mockException: HttpException = mockk()
        every { mockException.code() } returns 401

        runBlocking {
            every { api.userDetail("yuzu") } returns response
            every { api.userList(0) } returns responseList
        }

        repository = ProfileRepositoryImpl(api)
    }

    @Test
    fun `test userDetail when valid username is requested, then ProfileData is returned`() =
        runBlocking {
            assertEquals(response, repository.userDetail("yuzu"))
        }

    @Test
    fun `test userDetail when invalid username is requested, then error is returned`() =
        runBlocking {
            assertEquals(errorResponse, repository.userDetail("Naruto"))
        }

    @Test
    fun `test userList when valid since is requested, then userList is returned`() =
        runBlocking {
            assertEquals(responseList, repository.userList(0))
        }

    @Test
    fun `test userList when invalid since is requested, then error is returned`() =
        runBlocking {
            assertEquals(errorResponse, repository.userList(1))
        }
}