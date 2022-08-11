package com.yuzu.githubprofile.repository.local.network.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.yuzu.githubprofile.repository.data.ProfileData
import com.yuzu.githubprofile.repository.model.local.ProfileDAO
import com.yuzu.githubprofile.repository.model.local.ProfileDB
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.*
import org.junit.runner.RunWith

/**
 * Created by Yustar Pramudana on 03/07/22.
 */

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class ProfileDAOTest {
    // A JUnit Test Rule that swaps the background executor
    // used by the Architecture Components with a different one
    // which executes each task synchronously
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: ProfileDB
    private lateinit var dao: ProfileDAO

    // execute before every test case
    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, ProfileDB::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.profileDAO()
    }

    // execute after every test case
    @After
    fun teardown() {
        db.close()
    }

    //getAllProfilesBySingle
    @Test
    fun getAllProfileSingleInputTest() {
        db.profileDAO().insert(ProfileData(0))
        val profileDataList = db.profileDAO().getAllProfiles()
        Assert.assertEquals(profileDataList.size, 1)
    }

    @Test
    fun getAllProfileSingleInputOneByOneTest() {
        db.profileDAO().insert(ProfileData(0))
        db.profileDAO().insert(ProfileData(1))
        val profileDataList = db.profileDAO().getAllProfiles()
        Assert.assertEquals(profileDataList.size, 2)
    }

    @Test
    fun getAllProfileSingleInputOnConflictTest() {
        db.profileDAO().insert(ProfileData(0))
        db.profileDAO().insert(ProfileData(0))
        val profileDataList = db.profileDAO().getAllProfiles()
        Assert.assertEquals(profileDataList.size, 1)
    }

    @Test
    fun getAllProfileListInputTest() {
        db.profileDAO().insert(listOf(ProfileData(0), ProfileData(1)))
        val profileDataList = db.profileDAO().getAllProfiles()
        Assert.assertEquals(profileDataList.size, 2)
    }

    //GetProfileByLogin
    @Test
    fun getProfileByLoginSingleInputTest() {
        db.profileDAO().insert(ProfileData(0, "Yuzutaru"))
        val profileData = db.profileDAO().getProfile("Yuzutaru")
        Assert.assertNotNull(profileData)
    }

    @Test
    fun getProfileByLoginSingleInputOneByOneTest() {
        db.profileDAO().insert(ProfileData(0, "Yuzutaru"))
        db.profileDAO().insert(ProfileData(1, "Yustar"))
        val profileData = db.profileDAO().getProfile("Yuzutaru")
        Assert.assertNotNull(profileData)
    }

    @Test
    fun getProfileByLoginSingleInputOnConflictTest() {
        db.profileDAO().insert(ProfileData(0, "Yuzutaru"))
        db.profileDAO().insert(ProfileData(0, "Yustar"))
        val profileData = db.profileDAO().getProfile("Yustar")
        Assert.assertNotNull(profileData)
    }

    @Test
    fun getProfileByLoginListInputTest() {
        db.profileDAO().insert(listOf(ProfileData(0, "Yuzutaru"), ProfileData(1, "Yustar")))
        val profileData = db.profileDAO().getProfile("Yuzutaru")
        Assert.assertNotNull(profileData)
    }

    @Test
    fun insertProfileDataTest() {
        db.profileDAO().insert(ProfileData(0, "yuzutaru"))
        val profileDataList = db.profileDAO().getAllProfiles().size
        Assert.assertEquals(profileDataList, 1)
    }

    @Test
    fun insertProfileDataOneByOneTest() {
        db.profileDAO().insert(ProfileData(0, "yuzutaru"))
        db.profileDAO().insert(ProfileData(1, "yustar"))
        val profileDataList = db.profileDAO().getAllProfiles().size
        Assert.assertEquals(profileDataList, 2)
    }

    @Test
    fun insertProfileDataOnConflictTest() {
        db.profileDAO().insert(ProfileData(0, "yuzutaru"))
        db.profileDAO().insert(ProfileData(0, "yustar"))
        val profileDataList = db.profileDAO().getAllProfiles().size
        Assert.assertEquals(profileDataList, 1)
    }

    @Test
    fun insertProfileDataListInputTest() {
        db.profileDAO().insert(listOf(ProfileData(0, "yuzutaru"), ProfileData(1, "yustar")))
        val profileDataList = db.profileDAO().getAllProfiles().size
        Assert.assertEquals(profileDataList, 2)
    }
}