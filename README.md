**Architectural Decisions**

**Repository Implementation:**

- Implemented AppRepositoryImpl to provide a single source of truth for data.

- The repository prioritizes local data (AppLocalDataSource) and falls back to remote data (AppRemoteDataSource) if local data is unavailable.

- Introduced caching logic where fetched remote data is stored locally for future use.

**UI Implementation:**

- Developed HomeScreen composable function with Jetpack Compose to display a list of universities.

- Used UIState to manage and reflect different states: Loading, Success, and Error.

- Incorporated search functionality to filter the list of universities dynamically.

**Dependency Injection:**

- Utilized Hilt for injecting AppRepository, AppRemoteDataSource, and AppLocalDataSource into the ViewModel.

- Injected CoroutinesDispatcherProvider to manage coroutine contexts for better testability and control.

**Testing:**

- Tested HomeViewModel for both success and error scenarios using MockK and Turbine.

**Evidence UI**

![WhatsApp Image 2024-12-22 at 22 34 34_2576ca14](https://github.com/user-attachments/assets/b115fefe-35d5-4a3e-850f-c6b5b6c612b7)

![WhatsApp Image 2024-12-22 at 22 34 35_18e23d2b](https://github.com/user-attachments/assets/6d40b9f0-f02a-405d-ae0f-5c20cbccaba7)

![WhatsApp Image 2024-12-22 at 22 34 35_4632748d](https://github.com/user-attachments/assets/c95e1c0e-4ef9-4356-a281-e8def5560ce9)

**Evidence Unit Testing**

![WhatsApp Image 2024-12-22 at 23 19 28_a9f0c63d](https://github.com/user-attachments/assets/5c5a0d7c-4e57-437b-8bf2-4e120d6bffe1)
