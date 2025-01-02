# TestAndroidStudio
1st repository for testing Android Studio.

Various tests can be performed in this dummy Android Studio project.

Baseline technologies:
- Kotlin
- Jetpack Compose
- libs.versions.toml
- build.gradle.kts

Tests:
[1] Navigate among Composable screens (similar to full-screen Fragments) using NavHost.
[2] Create an Android Library module (project) "utillib" and use it module "app".
[3] Test "internal interface" - not accessible by other modules.
[4] Create file ".github/workflows/android-ci-cd.yml" (GitHub Actions) for CI/CD. Make sure: 
    chmod=+x ./gradlew
    Java 17+ in the .yml file.
[5] Set up file structure based on the MVVM Clean architecture
    presentation
        view
        viewmodel
    domain
        datamodel
        usecase
    data
        repository
        restful
        
     

