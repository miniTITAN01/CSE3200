package com.example.midterm_inprocess

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.example.midterm_inprocess.ui.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.*
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = StandardTestDispatcher()

    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        // Use StandardTestDispatcher for the main dispatcher
        kotlinx.coroutines.Dispatchers.setMain(testDispatcher)
        viewModel = HomeViewModel(SavedStateHandle())
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher to the original Dispatcher.Main
        kotlinx.coroutines.Dispatchers.resetMain()
    }


    @Test
    fun `initial counter value is zero`() {
        assertEquals(0, viewModel.counter.getOrAwaitValue())
    }

    @Test
    fun `incrementCounter increments counter by one`() {
        viewModel.incrementCounter()
        assertEquals(1, viewModel.counter.getOrAwaitValue())
    }

    @Test
    fun `resetCounter resets counter to zero`() {
        viewModel.incrementCounter() // Increment at least once to change the state
        viewModel.resetCounter()
        assertEquals(0, viewModel.counter.getOrAwaitValue())
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

