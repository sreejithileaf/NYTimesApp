package com.nyt.nytimes.ui

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nyt.nytimes.data.model.NewsResponse
import com.nyt.nytimes.data.model.ResultsItem
import com.nyt.nytimes.di.appModule
import com.nyt.nytimes.di.vmModule
import com.nyt.nytimes.rest.APIInterface
import com.nyt.nytimes.rest.NYTimesRepository
import com.nyt.nytimes.ui.main.MainActivityViewModel
import com.nyt.nytimes.utils.TestCoroutines
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class MainActivityViewModelUnitTest : KoinTest {

    @get:Rule
    val testCoroutineRule = TestCoroutines()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var context: Application

    @Mock
    private lateinit var apiInterfaceTest: APIInterface

    @Mock
    private lateinit var appRepositoryTest: NYTimesRepository

    @Mock
    private lateinit var itemsObserver: Observer<List<ResultsItem?>?>

    @Mock
    private lateinit var errorObserver: Observer<String?>

    @Mock
    private lateinit var progressObserver: Observer<Boolean>

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            androidContext(context)
            modules(appModule, vmModule)
        }

        mainActivityViewModel = MainActivityViewModel(
            appRepositoryTest,
            apiInterfaceTest,
        )
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun testNews() {
        testCoroutineRule.runBlockingTest {
            val result =
                NewsResponse(
                    copyright = "copyright",
                    results = arrayListOf<ResultsItem>(),
                    numResults = 20,
                    status = "OK"
                )
            val path = 7
            val key = "api-key"
            mainActivityViewModel.newsList.observeForever(itemsObserver)
            Mockito.doReturn(result)
                .`when`(apiInterfaceTest)
                .getNews(path, key)
            mainActivityViewModel.newsList.value = result.results
            apiInterfaceTest.getNews(path, key)
            Mockito.verify(apiInterfaceTest, Mockito.times(1))
                .getNews(path, key)
            Mockito.verify(itemsObserver, Mockito.times(1))
                .onChanged(mainActivityViewModel.newsList.value)
            mainActivityViewModel.newsList.removeObserver(itemsObserver)
        }
    }

    @Test
    fun testError() {
        mainActivityViewModel.errorMessage.observeForever(errorObserver)
        mainActivityViewModel.errorMessage.value = "error message"
        Mockito.verify(errorObserver, Mockito.times(1))
            .onChanged(mainActivityViewModel.errorMessage.value)
        mainActivityViewModel.errorMessage.removeObserver(errorObserver)
    }

    @Test
    fun testProgress() {
        mainActivityViewModel.progress.observeForever(progressObserver)
        mainActivityViewModel.progress.value = true
        Mockito.verify(progressObserver, Mockito.times(1))
            .onChanged(mainActivityViewModel.progress.value)
        mainActivityViewModel.progress.removeObserver(progressObserver)
    }
}