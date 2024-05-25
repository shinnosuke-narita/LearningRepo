package com.example.project.platform

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
expect val num: Int