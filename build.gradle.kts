buildscript {
    val agp_version by extra("8.0.1")
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.0.1" apply false
    id("org.jetbrains.kotlin.android") version "1.8.22" apply false
    id("org.jetbrains.dokka") version "1.9.10" apply false
    id("com.vanniktech.maven.publish") version "0.25.3" apply false
}