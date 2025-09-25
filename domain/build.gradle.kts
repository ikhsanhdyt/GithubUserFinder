plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

kotlin {
    jvmToolchain(11)
}

dependencies {
    // Coroutines
    implementation(libs.kotlinx.coroutines.core)
    
    // Javax inject for dependency injection annotations
    implementation("javax.inject:javax.inject:1")
    
    testImplementation(libs.junit)
}
