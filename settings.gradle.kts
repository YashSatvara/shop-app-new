pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") } // Add JitPack repository
        maven { url = uri("https://storage.zego.im/maven") } // Add another custom Maven repository
        maven { url = uri("https://maven.google.com") }
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") } // Add JitPack repository
        maven { url = uri("https://storage.zego.im/maven") }
        maven { url = uri("https://maven.google.com") } // Add another custom Maven repository
    }
}

rootProject.name = "Shop App"
include(":app")
