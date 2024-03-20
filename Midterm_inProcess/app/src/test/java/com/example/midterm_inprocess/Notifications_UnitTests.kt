package com.example.midterm_inprocess
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.example.midterm_inprocess.ui.notifications.NotificationsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.junit.Before
import org.junit.After
import org.junit.Assert.*

@ExperimentalCoroutinesApi
class NotificationsViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: NotificationsViewModel

    @Before
    fun setUp() {
        // Initialize your ViewModel here with a new SavedStateHandle() for each test to simulate starting fresh
        viewModel = NotificationsViewModel(SavedStateHandle())
    }

    @Test
    fun whenTimerIsStarted_AndThenAdvancedBy2Seconds_TimerReflects2SecondsElapsed() = runBlockingTest {
        val viewModel = NotificationsViewModel(SavedStateHandle())
        viewModel.startTimer()

        // Advance time by 2000 milliseconds (2 seconds)
        advanceTimeBy(2000)

        // Assuming the formatTime function correctly formats the elapsed time,
        // and that the timer updates the _time LiveData accordingly.
        // This assertion checks if the timer's LiveData reflects 2 seconds have passed.
        val expected = "00:00:02"
        assertEquals(expected, viewModel.time.getOrAwaitValue())
    }



    @Test
    fun whenTimerIsReset_EnsureTimeResetsToZero() = runBlockingTest {
        // Start and then immediately reset the timer
        viewModel.startTimer()
        viewModel.resetTimer()

        // Check if the timer was reset
        assertEquals("00:00:00", viewModel.time.value)
    }

// Add more tests as needed..
}