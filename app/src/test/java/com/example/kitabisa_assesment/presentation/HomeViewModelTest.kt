package com.example.kitabisa_assesment.presentation

import app.cash.turbine.test
import com.example.kitabisa_assesment.domain.abstraction.repository.AppRepository
import com.example.kitabisa_assesment.domain.model.UniversityDomainModel
import com.example.kitabisa_assesment.presentation.screens.HomeViewModel
import com.example.kitabisa_assesment.presentation.view.university.model.University
import com.example.kitabisa_assesment.presentation.view.university.model.toUIModel
import com.example.kitabisa_assesment.presentation.view.university.model.uiToUniversityDomainModel
import com.example.kitabisa_assesment.utils.CoroutinesDispatcherProvider
import com.example.kitabisa_assesment.utils.UIState
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    private val repository: AppRepository = mockk()
    private lateinit var dispatcher: CoroutinesDispatcherProvider
    private lateinit var viewModel: HomeViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
        dispatcher = CoroutinesDispatcherProvider(
            Dispatchers.Unconfined, Dispatchers.Unconfined, Dispatchers.Unconfined
        )
        viewModel = HomeViewModel(repository, dispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getData should emit Loading and Success states`() = runTest {
        val universityList = University.getPreviewListData().uiToUniversityDomainModel()
        val flow = flowOf(universityList)
        every { repository.getList() } returns flow
        viewModel.data.test {
            viewModel.getData()
            assert(awaitItem() is UIState.Loading)
            val successState = awaitItem() as UIState.Success
            assert(successState.data == universityList.map { it.toUIModel() })

            cancelAndConsumeRemainingEvents()
        }
        verify { repository.getList() }
    }

    @Test
    fun `getData should emit Loading and Error states on failure`() = runTest {
        val exceptionMessage = "Network error"
        val flow = flow<List<UniversityDomainModel>> {
            throw RuntimeException(exceptionMessage)
        }
        every { repository.getList() } returns flow
        viewModel.data.test {
            viewModel.getData()
            assert(awaitItem() is UIState.Loading)
            val errorState = awaitItem() as UIState.Error
            assert(errorState.message == exceptionMessage)

            cancelAndConsumeRemainingEvents()
        }
        verify { repository.getList() }
    }
}