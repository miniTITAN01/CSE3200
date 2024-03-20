package com.example.midterm_inprocess

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.example.midterm_inprocess.ui.notifications.NotificationsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
class NotificationsViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: NotificationsViewModel

    @Before
    fun setup() {
        // Initialize the ViewModel with a SavedStateHandle that can be empty since we are not testing saved state logic here
        viewModel = NotificationsViewModel(SavedStateHandle())
    }

    @Test
    fun `formatTime returns correctly formatted time`() = runTest {
        // Directly test the formatTime method
        val formattedTime = viewModel.formatTime(3661L)
        assertEquals("01:01:01", formattedTime)
    }

    @Test
    fun `timer is reset to 00 on resetTimer`() = runTest {
        viewModel.resetTimer()
        assertEquals("00:00:00", viewModel.time.getOrAwaitValue())
    }

    //https://gist.github.com/akueisara/1d711fc03e129ac320ecb13fda48cc03
    fun <T> LiveData<T>.getOrAwaitValue(): T {
        var data: T? = null
        val latch = CountDownLatch(1)
        val observer = object : Observer<T> {
            override fun onChanged(t: T) {
                data = t
                latch.countDown()
                this@getOrAwaitValue.removeObserver(this)
            }
        }
        this.observeForever(observer)
        try {
            // Wait a bit for the LiveData to emit, but don't wait forever
            if (!latch.await(2, TimeUnit.SECONDS)) {
                throw TimeoutException("LiveData value was never set.")
            }
        } finally {
            this.removeObserver(observer)
        }
        @Suppress("UNCHECKED_CAST")
        return data as T
    }
}


