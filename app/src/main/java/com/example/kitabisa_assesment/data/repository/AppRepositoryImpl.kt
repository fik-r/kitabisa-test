package com.example.kitabisa_assesment.data.repository

import com.example.kitabisa_assesment.domain.abstraction.datasource.AppLocalDataSource
import com.example.kitabisa_assesment.domain.abstraction.datasource.AppRemoteDataSource
import com.example.kitabisa_assesment.domain.abstraction.repository.AppRepository
import com.example.kitabisa_assesment.domain.model.UniversityDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Implementation of the AppRepository interface, responsible for managing data flow between
 * the remote and local data sources. This class provides a single source of truth for the data,
 * prioritizing local data when available and falling back to remote data when necessary.
 *
 * @param remote The remote data source for fetching data from a server or API.
 * @param local The local data source for fetching and storing data in local storage or a database.
 */
class AppRepositoryImpl(
    private val remote: AppRemoteDataSource,
    private val local: AppLocalDataSource
) : AppRepository {
    override fun getList(): Flow<List<UniversityDomainModel>> = flow {
        val localData = local.getList()
        if (localData.isNotEmpty()) {
            // Emit local data if available
            emit(localData)
        } else {
            // Fetch data from the remote data source if local data is empty
            remote.getList()
                .takeIf { it.isNotEmpty() } // Check if remote data is not empty
                ?.also { local.addList(it) } // Save the fetched remote data to the local data source
                ?.let { emit(it) } // Emit the remote data
        }
    }
}