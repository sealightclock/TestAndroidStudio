# TestAndroidStudio
1st repository for testing Android Studio.

Various tests can be performed in this dummy Android Studio project.

Baseline technologies:
- Kotlin
- Jetpack Compose
- libs.versions.toml
- build.gradle.kts

Tests:
- Navigate among Composable screens (similar to full-screen Fragments) using NavHost.
    - WelcomeScreen
    - NextScreen
    - WebViewScreen
    - HandwritingScreen
    - HelloJniScreen
- Create an Android Library module (project) "utillib" and use it module "app".
- Test "internal interface" - not accessible by other modules.
- Create file ".github/workflows/android-ci-cd.yml" (GitHub Actions) for CI/CD. Make sure: 
    chmod=+x ./gradlew
    Java 17+ in the .yml file.
- Set up file structure based on the MVVM Clean architecture
    presentation
        view
        viewmodel
    domain
        datamodel
        usecase
    data
        repository
        localdb
        restful
        
     

