package com.example.navigation

object NavRoutes {
    const val SPLASH = "splash"
    const val HOME = "home"
    const val CATEGORY = "category/{categoryId}"
    const val CANVAS = "canvas/{pageId}"
    const val SAVED_GALLERY = "saved_gallery"
    const val SETTINGS = "settings"

    fun category(categoryId: String): String = "category/$categoryId"
    fun canvas(pageId: String): String = "canvas/$pageId"
}
