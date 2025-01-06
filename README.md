# Wallsyfy

Wallsyfy is an Android application that allows users to explore and interact with a wide variety of high-quality wallpapers. Users can browse through the collection, set wallpapers, and download them with ease. The app is built using **Jetpack Compose** for the user interface (UI) and follows the **MVVM** architecture for a clean, maintainable, and testable codebase.

## Screenshots

<img src="https://github.com/user-attachments/assets/d00288ae-f08a-4ebe-83a8-6ee2d6278e71" alt="Screenshot 1" width="250">
<img src="https://github.com/user-attachments/assets/2d56b7f0-a937-450a-8ede-a533adaeaba1" alt="Screenshot 2" width="250">
<img src="https://github.com/user-attachments/assets/d8add655-bc44-432b-ba1c-e0442afd7c30" alt="Screenshot 3" width="250">
<img src="https://github.com/user-attachments/assets/4dba5f48-8bd5-470c-8b55-2eda7c8de981" alt="Screenshot 4" width="250">
<img src="https://github.com/user-attachments/assets/8df57e4a-d9b3-473a-84a3-5a3ee84d4312" alt="Screenshot 5" width="250">
<img src="https://github.com/user-attachments/assets/881c7f42-cec4-46ec-88fb-7b8fba6c27de" alt="Screenshot 6" width="250">
<img src="https://github.com/user-attachments/assets/f8a76860-f438-4f5f-b2c8-e647dc6b1865" alt="Screenshot 7" width="250">
<img src="https://github.com/user-attachments/assets/ff4d7dc0-0a56-4a94-8457-c30469306a65" alt="Screenshot 8" width="250">


## Features

- **Wallpaper Browsing**: Explore a wide range of high-quality wallpapers.
- **Set Wallpaper**: Set any wallpaper directly to your device from within the app.
- **Download Wallpaper**: Download wallpapers to your device.
- **Jetpack Compose UI**: The UI is built using Jetpack Compose, a modern, declarative framework for building Android UIs.
- **MVVM Architecture**: The app follows the **Model-View-ViewModel (MVVM)** architecture for better separation of concerns and maintainability.
- **Koin for Dependency Injection**: Uses **Koin** to manage dependencies, making the code more modular, testable, and easier to maintain.
- **Coroutines for Asynchronous Operations**: All network operations are performed asynchronously using **Coroutines**.
- **No User Authentication**: No sign-in or account creation required, allowing for a smooth, frictionless user experience.
- **Favorites (Planned)**: Future feature allowing users to save and manage their favorite wallpapers.

## Technologies

- **Kotlin**: The programming language used to build the app.
- **Jetpack Compose**: A modern UI toolkit for building native Android UIs.
- **MVVM Architecture**: A pattern that helps in separating concerns in the codebase.
- **Koin**: A lightweight dependency injection framework for Kotlin.
- **Ktor**: A framework used for making network calls.
- **Coil**: An image loading library used for efficient image loading and caching.
- **Coroutines**: For managing background threads and asynchronous operations.

## Setup

Follow the steps below to run the project locally:

1. Clone the repository:

    ```bash
    git clone https://github.com/remziakgoz/Wallsyfy.git
    ```

2. Open the project in **Android Studio**.

3. Wait for the Gradle files to sync.

4. Run the app on a physical device or emulator.

## Usage

Upon opening the app, you will be greeted with a list of wallpapers. You can:

1. Browse through the collection.
2. Tap on any wallpaper to view it in full size.
3. Set the wallpaper directly to your device by tapping the "Set Wallpaper" button.
4. Download the wallpaper to your device by tapping the "Download" button.

## Future Features

- **Favorites System**: Users will be able to favorite wallpapers to easily access them later.
- **Categories**: Ability to filter wallpapers by categories (e.g., nature, abstract, technology).

## Tests

The project includes unit and UI tests to ensure the application works as expected. The following tests are available:

### UI Tests
- **WallpaperScreenTest**: Verifies that wallpapers are loaded and displayed correctly.
- **WallpaperSetTest**: Ensures that setting a wallpaper works as expected.
- **WallpaperDownloadTest**: Verifies that the wallpaper can be downloaded successfully.

### Navigational Tests
- **NavigationTest**: Ensures smooth navigation between different screens of the app.

### ViewModel Tests
- **WallpaperViewModelTest**: Tests the business logic of handling wallpaper data.

### Running Tests

To run the tests:

1. In **Android Studio**, click on **Run** and select **Test**.
2. Alternatively, you can run the tests from the terminal:

    ```bash
    ./gradlew test
    ```

## Contributing

If you'd like to contribute to the project, follow these steps:

1. **Fork** the repository.
2. Create a new **feature branch** (`git checkout -b feature/your-feature-name`).
3. **Commit** your changes (`git commit -m 'Add new feature'`).
4. **Push** to your branch (`git push origin feature/your-feature-name`).
5. Open a **pull request** with a description of what your changes do.

## License

This project is licensed under the **MIT License** - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Developed by **Remzi Akgöz**.
- Showcases modern Android development practices using **Jetpack Compose**, **Koin**, and **MVVM** architecture.
- Aims to provide a simple yet powerful experience for users looking for high-quality wallpapers.

---
Enjoy exploring and setting beautiful wallpapers with Wallsyfy!
