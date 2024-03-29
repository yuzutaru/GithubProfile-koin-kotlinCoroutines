package com.yuzu.githubprofile.repository.remote.contract

import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.data.UserData
import com.yuzu.githubprofile.repository.model.Response
import com.yuzu.githubprofile.repository.model.Status
import com.yuzu.githubprofile.repository.remote.api.ProfileApi
import io.mockk.coEvery
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

    private val profileDataResponse = Response(Status.SUCCEED, profileData, null)
    private val userListResponse = Response(Status.SUCCEED, userList, null)

    private val runtimeException = RuntimeException("Something went wrong", null)

    private val errorProfile = Response<ProfileData>(Status.EMPTY, null, runtimeException)
    private val errorUser = Response<List<UserData>>(Status.EMPTY, null, runtimeException)


    @Before
    fun setUp() {
        api = mockk()
        val mockException: HttpException = mockk()
        every { mockException.code() } returns 401

        runBlocking {
            coEvery { api.userDetail("yuzu") } returns profileDataResponse
            coEvery { api.userDetail("Naruto") } returns errorProfile

            coEvery { api.userList(0) } returns userListResponse
            coEvery { api.userList(1) } returns errorUser
        }

        repository = ProfileRepositoryImpl(api)
    }

    @Test
    fun `test userDetail when valid username is requested, then ProfileData is returned`() =
        runBlocking {
            assertEquals(profileDataResponse, repository.userDetail("yuzu"))
        }

    @Test
    fun `test userDetail when invalid username is requested, then error is returned`() =
        runBlocking {
            assertEquals(errorProfile, repository.userDetail("Naruto"))
        }

    @Test
    fun `test userList when valid since is requested, then userList is returned`() =
        runBlocking {
            assertEquals(userListResponse, repository.userList(0))
        }

    @Test
    fun `test userList when invalid since is requested, then error is returned`() =
        runBlocking {
            assertEquals(errorUser, repository.userList(1))
        }
}