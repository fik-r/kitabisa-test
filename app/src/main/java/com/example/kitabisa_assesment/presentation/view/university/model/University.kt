package com.example.kitabisa_assesment.presentation.view.university.model

data class University(
    val title: String = "",
    val location: String = "",
    val url: String = ""
) {
    companion object {
        private const val PREVIEW_TITLE = "Universitas Nusa Putra"
        private const val PREVIEW_LOCATION = "Indonesia"
        private const val PREVIEW_URL = "https://nusaputra.ac.id/"

        private val previewData = University(
            title = PREVIEW_TITLE,
            location = PREVIEW_LOCATION,
            url = PREVIEW_URL
        )

        fun getPreviewListData() = listOf(
            previewData,
            previewData,
            previewData
        )
    }
}