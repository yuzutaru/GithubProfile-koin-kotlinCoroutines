package com.yuzu.githubprofile.repository.local.network.local

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.yuzu.githubprofile.repository.data.UserData
import com.yuzu.githubprofile.repository.local.db.UserDAO
import com.yuzu.githubprofile.repository.local.db.UserDB
import com.yuzu.githubprofile.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.RunWith

/**
 * Created by Yustar Pramudana on 03/07/22.
 */

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class UserDAOTest {
    // A JUnit Test Rule that swaps the background executor
    // used by the Architecture Components with a different one
    // which executes each task synchronously
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var db: UserDB
    private lateinit var dao: UserDAO

    // execute before every test case
    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, UserDB::class.java)
            .allowMainThreadQueries()
            .build()
        dao = db.userDAO()
    }

    // execute after every test case
    @After
    fun teardown() {
        db.close()
    }

    /*
    test case to insert user in room database
    */
    @Test
    fun insertUser() = runTest {
        val data = UserData(0, 0)
        dao.insert(data)
        val users = dao.getUserBySinceId(0).getOrAwaitValue()
        Assert.assertEquals(users.size, 1)
    }

    @Test
    fun insertListUser() = runTest {
        val list = listOf(UserData(0, 0), UserData(1, 0))
        dao.insert(list)
        val userList = dao.getUserBySinceId(0).getOrAwaitValue()
        Assert.assertEquals(userList.size, 2)
    }
}