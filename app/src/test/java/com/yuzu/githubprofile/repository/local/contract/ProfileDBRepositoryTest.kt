package com.yuzu.githubprofile.repository.local.contract

import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.data.Resource
import com.yuzu.githubprofile.repository.data.ResponseHandler
import com.yuzu.githubprofile.repository.model.contract.ProfileDBRepository
import com.yuzu.githubprofile.repository.model.contract.ProfileDBRepositoryImpl
import com.yuzu.githubprofile.repository.model.local.ProfileDAO
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException

/**
 * Created by Yustar Pramudana on 25/07/22.
 */

@RunWith(JUnit4::class)
class ProfileDBRepositoryTest {
    private val responseHandler = ResponseHandler()

    private lateinit var dao: ProfileDAO
    private lateinit var repository: ProfileDBRepository

    private val profileData = ProfileData(0, "yuzu")
    private val profileList = listOf(ProfileData(0,"yuzu"))

    private val response = Resource.success(profileData)
    private val responseList = Resource.success(profileList)
    private val errorResponse = Resource.error("Something went wrong", null)

    @Before
    fun setUp() {
        dao = mockk()
        val mockException: HttpException = mockk()
        every { mockException.code() } returns 401

        runBlocking {
            every { dao.getProfile("yuzu") } returns profileData
            every { dao.getAllProfiles() } returns profileList
        }

        repository = ProfileDBRepositoryImpl(dao, responseHandler)
    }

    @Test
    fun `test getProfile when valid username is requested, then ProfileData is returned`() =
        runBlocking {
            Assert.assertEquals(response, repository.getProfile("yuzu"))
        }

    @Test
    fun `test getProfile when invalid username is requested, then error is returned`() =
        runBlocking {
            Assert.assertEquals(errorResponse, repository.getProfile("Naruto"))
        }

    @Test
    fun `test getAllProfiles when valid since is requested, then profileList is returned`() =
        runBlocking {
            Assert.assertEquals(responseList, repository.getAllProfiles())
        }
}