# GitHub User Finder

A modern Android app that allows users to search for GitHub users using the GitHub API. Built with clean architecture principles and the latest Android development technologies.

## Features

- **Search GitHub Users**: Search for GitHub users by username or name
- **User Details**: View detailed information about a selected user
- **Search History**: Keep track of recent searches for quick access
- **Infinite Scrolling**: Load more results as you scroll
- **Modern UI**: Built with Jetpack Compose and Material Design 3
- **Offline**: Local database caching for search history

## Architecture

The app follows **Clean Architecture** principles with a **4-module structure**:

### Modules

- **app**: Application entry point, MainActivity, Application class, and DI setup
- **presentation**: UI layer (Compose screens, ViewModels, Navigation, Theme)
- **data**: Data layer (API, Database, Repository implementations)
- **domain**: Domain layer (Use cases, Models, Repository interfaces)

### Architecture Layers

1. **App Layer**: 
   - Application entry point
   - Dependency injection configuration
   - MainActivity as host

2. **Presentation Layer**: 
   - Jetpack Compose UI screens and components
   - ViewModels with StateFlow
   - Navigation with Navigation Compose
   - UI themes and styling

3. **Domain Layer**: 
   - Use cases for business logic
   - Domain models
   - Repository interfaces

4. **Data Layer**: 
   - GitHub API integration with Retrofit
   - Local database with Room
   - Repository implementations

## Tech Stack

- **Language**: Kotlin
- **UI**: Jetpack Compose
- **Architecture**: MVVM + Clean Architecture
- **Dependency Injection**: Hilt
- **Networking**: Retrofit + OkHttp
- **Database**: Room
- **Image Loading**: Coil
- **Navigation**: Navigation Compose
- **Async**: Coroutines + Flow

## API

This app uses the [GitHub REST API](https://docs.github.com/en/rest) to fetch user data:
- Search users: `GET /search/users`
- Get user details: `GET /users/{username}`

## Setup

1. Clone the repository
2. Open in Android Studio
3. Build and run the project

No API key is required as the app uses GitHub's public API endpoints.

## 📱 App Requirements

- **Target SDK**: 34 (Android 14)
- **Min SDK**: 24 (Android 7.0+)
- **Compile SDK**: 34

```
## 📂 Project Structure

```
GithubUserFinder/
├── app/                    # Main application module
├── presentation/           # UI layer (Screens, ViewModels)
├── data/                   # Data layer (API, Database)
├── domain/                 # Business logic layer
└── gradle/                 # Gradle configuration
```
