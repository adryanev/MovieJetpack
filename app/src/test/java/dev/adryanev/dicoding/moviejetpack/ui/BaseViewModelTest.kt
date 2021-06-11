package dev.adryanev.dicoding.moviejetpack.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dev.adryanev.dicoding.moviejetpack.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
open class BaseViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Before
    open fun setup() {
        MockitoAnnotations.openMocks(this)
    }
}