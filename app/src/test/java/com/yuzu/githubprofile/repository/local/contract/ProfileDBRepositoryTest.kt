package com.yuzu.githubprofile.repository.local.contract

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.data.UserData
import com.yuzu.githubprofile.repository.local.db.ProfileDAO
import com.yuzu.githubprofile.repository.local.db.ProfileDB
import com.yuzu.githubprofile.repository.model.Response
import com.yuzu.githubprofile.repository.model.Status
import com.yuzu.githubprofile.repository.remote.contract.ProfileRepositoryImpl
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.HttpException
import java.io.IOException
import kotlin.coroutines.coroutineContext

/**
 * Created by Yustar Pramudana on 25/07/22.
 */

class ProfileDBRepositoryTest {

    private lateinit var dao: ProfileDAO
    private lateinit var repository: ProfileDBRepository

    private val profileData = ProfileData(0, "yuzu")
    private val profileList = listOf(ProfileData(0,"yuzu"))

    @Before
    fun setUp() {
        dao = mockk()
        val mockException: HttpException = mockk()
        every { mockException.code() } returns 401

        runBlocking {
            every { dao.getAllProfiles() } returns profileList
            every { dao.getProfile("yuzu") } returns profileData
            every { dao.getProfile("Naruto") } returns null
        }

        repository = ProfileDBRepositoryImpl(dao)
    }

    @Test
    fun `test getProfile when valid username is requested, then ProfileData is returned`() =
        runBlocking {
            Assert.assertEquals(profileData, repository.getProfile("yuzu"))
        }

    @Test
    fun `test getProfile when invalid username is requested, then error is returned`() =
        runBlocking {
            Assert.assertEquals(null, repository.getProfile("Naruto"))
        }

    @Test
    fun `test getAllProfiles when valid since is requested, then profileList is returned`() =
        runBlocking {
            Assert.assertEquals(profileList, repository.getAllProfiles())
        }
}