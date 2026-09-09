package com.example.data

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import com.example.model.Category
import com.example.model.ColoringPage
import com.example.model.ColoringShape

object ColoringCatalog {

    val categories = listOf(
        Category(
            id = "animals",
            title = "Animals",
            emoji = "🐶",
            accentColor = Color(0xFFFF8A65),
            description = "Friendly puppy, cute kitty, gentle elephant and more!"
        ),
        Category(
            id = "vehicles",
            title = "Vehicles",
            emoji = "🚗",
            accentColor = Color(0xFF4FC3F7),
            description = "Cars, buses, trains, trucks and airplanes on the move!"
        ),
        Category(
            id = "dinosaurs",
            title = "Dinosaurs",
            emoji = "🦖",
            accentColor = Color(0xFF81C784),
            description = "Mighty T-Rex, long neck Bronto, and armored Stego!"
        ),
        Category(
            id = "fruits",
            title = "Fruits",
            emoji = "🍎",
            accentColor = Color(0xFFFF80AB),
            description = "Sweet juicy apples, bananas, strawberries and oranges!"
        ),
        Category(
            id = "nature",
            title = "Nature",
            emoji = "🌈",
            accentColor = Color(0xFFFFD54F),
            description = "Bright sunshine, colorful rainbows, trees and flowers!"
        ),
        Category(
            id = "space",
            title = "Space",
            emoji = "🚀",
            accentColor = Color(0xFFBA68C8),
            description = "Rockets zooming to moons, planets, stars and UFOs!"
        ),
        Category(
            id = "toys",
            title = "Toys",
            emoji = "🧸",
            accentColor = Color(0xFFFFB74D),
            description = "Cuddly teddy bear, playful robot, toy drum and balls!"
        ),
        Category(
            id = "alphabet",
            title = "Alphabet",
            emoji = "🔤",
            accentColor = Color(0xFF4DD0E1),
            description = "Learn ABC letters with fun coloring objects!"
        )
    )

    val pages: List<ColoringPage> = listOf(
        // ==================== ANIMALS ====================
        createCatPage(),
        createDogPage(),
        createLionPage(),
        createElephantPage(),
        createMonkeyPage(),
        createBunnyPage(),

        // ==================== VEHICLES ====================
        createCarPage(),
        createBusPage(),
        createTruckPage(),
        createTrainPage(),
        createAirplanePage(),
        createBoatPage(),

        // ==================== DINOSAURS ====================
        createTRexPage(),
        createTriceratopsPage(),
        createStegosaurusPage(),
        createBrontosaurusPage(),
        createPterodactylPage(),

        // ==================== FRUITS ====================
        createApplePage(),
        createBananaPage(),
        createOrangePage(),
        createStrawberryPage(),
        createWatermelonPage(),
        createCherryPage(),

        // ==================== NATURE ====================
        createSunPage(),
        createRainbowPage(),
        createFlowerPage(),
        createTreePage(),
        createCloudPage(),
        createButterflyPage(),

        // ==================== SPACE ====================
        createRocketPage(),
        createPlanetPage(),
        createAstronautPage(),
        createUFOPage(),
        createStarPage(),

        // ==================== TOYS ====================
        createTeddyBearPage(),
        createRobotPage(),
        createDrumPage(),
        createToyBallPage(),
        createKitePage(),

        // ==================== ALPHABET ====================
        createLetterAPage(),
        createLetterBPage(),
        createLetterCPage(),
        createLetterDPage()
    )

    fun getPagesForCategory(categoryId: String): List<ColoringPage> {
        return pages.filter { it.categoryId == categoryId }
    }

    fun getPageById(pageId: String): ColoringPage? {
        return pages.find { it.id == pageId } ?: pages.firstOrNull()
    }

    // ----------------- ANIMAL PAGE BUILDERS -----------------

    private fun createCatPage() = ColoringPage(
        id = "cat_page",
        categoryId = "animals",
        title = "Cute Kitty",
        emoji = "🐱",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("body", "Body", { w, h -> ShapeGeometry.ovalPath(0.28f, 0.45f, 0.72f, 0.88f, w, h) }, zIndex = 1),
            ColoringShape("left_ear", "Left Ear", { w, h -> ShapeGeometry.polygonPath(listOf(0.25f to 0.40f, 0.32f to 0.18f, 0.45f to 0.30f), w, h) }, zIndex = 2),
            ColoringShape("right_ear", "Right Ear", { w, h -> ShapeGeometry.polygonPath(listOf(0.55f to 0.30f, 0.68f to 0.18f, 0.75f to 0.40f), w, h) }, zIndex = 2),
            ColoringShape("head", "Head", { w, h -> ShapeGeometry.ovalPath(0.25f, 0.26f, 0.75f, 0.62f, w, h) }, zIndex = 3),
            ColoringShape("belly", "Belly Patch", { w, h -> ShapeGeometry.ovalPath(0.38f, 0.58f, 0.62f, 0.82f, w, h) }, zIndex = 4),
            ColoringShape("snout", "Snout Area", { w, h -> ShapeGeometry.ovalPath(0.40f, 0.44f, 0.60f, 0.56f, w, h) }, zIndex = 5),
            ColoringShape("left_eye", "Left Eye", { w, h -> ShapeGeometry.ovalPath(0.36f, 0.36f, 0.44f, 0.44f, w, h) }, defaultFill = Color(0xFF212121), zIndex = 6),
            ColoringShape("right_eye", "Right Eye", { w, h -> ShapeGeometry.ovalPath(0.56f, 0.36f, 0.64f, 0.44f, w, h) }, defaultFill = Color(0xFF212121), zIndex = 6),
            ColoringShape("nose", "Nose", { w, h -> ShapeGeometry.polygonPath(listOf(0.46f to 0.47f, 0.54f to 0.47f, 0.50f to 0.52f), w, h) }, defaultFill = Color(0xFFFF80AB), zIndex = 7)
        )
    )

    private fun createDogPage() = ColoringPage(
        id = "dog_page",
        categoryId = "animals",
        title = "Happy Puppy",
        emoji = "🐶",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("body", "Body", { w, h -> ShapeGeometry.ovalPath(0.26f, 0.46f, 0.74f, 0.88f, w, h) }, zIndex = 1),
            ColoringShape("left_ear", "Left Floppy Ear", { w, h -> ShapeGeometry.ovalPath(0.18f, 0.32f, 0.34f, 0.60f, w, h) }, zIndex = 2),
            ColoringShape("right_ear", "Right Floppy Ear", { w, h -> ShapeGeometry.ovalPath(0.66f, 0.32f, 0.82f, 0.60f, w, h) }, zIndex = 2),
            ColoringShape("head", "Head", { w, h -> ShapeGeometry.ovalPath(0.28f, 0.22f, 0.72f, 0.58f, w, h) }, zIndex = 3),
            ColoringShape("muzzle", "Muzzle", { w, h -> ShapeGeometry.ovalPath(0.36f, 0.40f, 0.64f, 0.54f, w, h) }, zIndex = 4),
            ColoringShape("tongue", "Happy Tongue", { w, h -> ShapeGeometry.ovalPath(0.45f, 0.52f, 0.55f, 0.60f, w, h) }, defaultFill = Color(0xFFFF5252), zIndex = 5),
            ColoringShape("collar", "Collar", { w, h -> ShapeGeometry.roundRectPath(0.36f, 0.57f, 0.64f, 0.63f, 8f, w, h) }, zIndex = 5),
            ColoringShape("left_eye", "Left Eye", { w, h -> ShapeGeometry.ovalPath(0.37f, 0.32f, 0.45f, 0.40f, w, h) }, defaultFill = Color(0xFF212121), zIndex = 6),
            ColoringShape("right_eye", "Right Eye", { w, h -> ShapeGeometry.ovalPath(0.55f, 0.32f, 0.63f, 0.40f, w, h) }, defaultFill = Color(0xFF212121), zIndex = 6),
            ColoringShape("nose", "Nose", { w, h -> ShapeGeometry.ovalPath(0.46f, 0.42f, 0.54f, 0.47f, w, h) }, defaultFill = Color(0xFF212121), zIndex = 7)
        )
    )

    private fun createLionPage() = ColoringPage(
        id = "lion_page",
        categoryId = "animals",
        title = "King Lion",
        emoji = "🦁",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("mane", "Lion Mane", { w, h -> ShapeGeometry.ovalPath(0.18f, 0.15f, 0.82f, 0.72f, w, h) }, defaultFill = Color(0xFFFFB74D), zIndex = 1),
            ColoringShape("body", "Body", { w, h -> ShapeGeometry.ovalPath(0.30f, 0.55f, 0.70f, 0.90f, w, h) }, zIndex = 2),
            ColoringShape("head", "Face", { w, h -> ShapeGeometry.ovalPath(0.28f, 0.25f, 0.72f, 0.64f, w, h) }, zIndex = 3),
            ColoringShape("left_ear", "Left Ear", { w, h -> ShapeGeometry.ovalPath(0.24f, 0.20f, 0.36f, 0.32f, w, h) }, zIndex = 4),
            ColoringShape("right_ear", "Right Ear", { w, h -> ShapeGeometry.ovalPath(0.64f, 0.20f, 0.76f, 0.32f, w, h) }, zIndex = 4),
            ColoringShape("snout", "Snout", { w, h -> ShapeGeometry.ovalPath(0.39f, 0.44f, 0.61f, 0.58f, w, h) }, zIndex = 5),
            ColoringShape("nose", "Nose", { w, h -> ShapeGeometry.polygonPath(listOf(0.46f to 0.46f, 0.54f to 0.46f, 0.50f to 0.51f), w, h) }, defaultFill = Color(0xFF795548), zIndex = 6)
        )
    )

    private fun createElephantPage() = ColoringPage(
        id = "elephant_page",
        categoryId = "animals",
        title = "Gentle Elephant",
        emoji = "🐘",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("left_ear", "Big Left Ear", { w, h -> ShapeGeometry.ovalPath(0.10f, 0.26f, 0.36f, 0.62f, w, h) }, zIndex = 1),
            ColoringShape("right_ear", "Big Right Ear", { w, h -> ShapeGeometry.ovalPath(0.64f, 0.26f, 0.90f, 0.62f, w, h) }, zIndex = 1),
            ColoringShape("body", "Body", { w, h -> ShapeGeometry.ovalPath(0.25f, 0.42f, 0.75f, 0.90f, w, h) }, zIndex = 2),
            ColoringShape("head", "Head", { w, h -> ShapeGeometry.ovalPath(0.28f, 0.20f, 0.72f, 0.58f, w, h) }, zIndex = 3),
            ColoringShape("trunk", "Long Trunk", { w, h -> ShapeGeometry.roundRectPath(0.44f, 0.46f, 0.56f, 0.78f, 18f, w, h) }, zIndex = 4)
        )
    )

    private fun createMonkeyPage() = ColoringPage(
        id = "monkey_page",
        categoryId = "animals",
        title = "Playful Monkey",
        emoji = "🐵",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("body", "Body", { w, h -> ShapeGeometry.ovalPath(0.30f, 0.48f, 0.70f, 0.88f, w, h) }, zIndex = 1),
            ColoringShape("left_ear", "Left Ear", { w, h -> ShapeGeometry.ovalPath(0.16f, 0.26f, 0.32f, 0.44f, w, h) }, zIndex = 2),
            ColoringShape("right_ear", "Right Ear", { w, h -> ShapeGeometry.ovalPath(0.68f, 0.26f, 0.84f, 0.44f, w, h) }, zIndex = 2),
            ColoringShape("head", "Head", { w, h -> ShapeGeometry.ovalPath(0.26f, 0.18f, 0.74f, 0.56f, w, h) }, zIndex = 3),
            ColoringShape("face_mask", "Face Mask", { w, h -> ShapeGeometry.ovalPath(0.33f, 0.24f, 0.67f, 0.52f, w, h) }, zIndex = 4),
            ColoringShape("belly", "Tummy", { w, h -> ShapeGeometry.ovalPath(0.38f, 0.58f, 0.62f, 0.82f, w, h) }, zIndex = 5)
        )
    )

    private fun createBunnyPage() = ColoringPage(
        id = "bunny_page",
        categoryId = "animals",
        title = "Fluffy Bunny",
        emoji = "🐰",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("left_ear", "Left Long Ear", { w, h -> ShapeGeometry.ovalPath(0.32f, 0.10f, 0.44f, 0.40f, w, h) }, zIndex = 1),
            ColoringShape("right_ear", "Right Long Ear", { w, h -> ShapeGeometry.ovalPath(0.56f, 0.10f, 0.68f, 0.40f, w, h) }, zIndex = 1),
            ColoringShape("body", "Fluffy Body", { w, h -> ShapeGeometry.ovalPath(0.28f, 0.48f, 0.72f, 0.88f, w, h) }, zIndex = 2),
            ColoringShape("head", "Head", { w, h -> ShapeGeometry.ovalPath(0.28f, 0.30f, 0.72f, 0.62f, w, h) }, zIndex = 3),
            ColoringShape("cheeks", "Cheeks", { w, h -> ShapeGeometry.ovalPath(0.37f, 0.46f, 0.63f, 0.58f, w, h) }, zIndex = 4)
        )
    )

    // ----------------- VEHICLE PAGE BUILDERS -----------------

    private fun createCarPage() = ColoringPage(
        id = "car_page",
        categoryId = "vehicles",
        title = "Speedy Car",
        emoji = "🚗",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("cabin", "Roof & Cabin", { w, h -> ShapeGeometry.roundRectPath(0.26f, 0.32f, 0.74f, 0.56f, 24f, w, h) }, zIndex = 1),
            ColoringShape("car_body", "Car Body", { w, h -> ShapeGeometry.roundRectPath(0.12f, 0.48f, 0.88f, 0.70f, 20f, w, h) }, zIndex = 2),
            ColoringShape("front_window", "Front Window", { w, h -> ShapeGeometry.roundRectPath(0.53f, 0.36f, 0.70f, 0.48f, 10f, w, h) }, defaultFill = Color(0xFFE1F5FE), zIndex = 3),
            ColoringShape("back_window", "Back Window", { w, h -> ShapeGeometry.roundRectPath(0.30f, 0.36f, 0.48f, 0.48f, 10f, w, h) }, defaultFill = Color(0xFFE1F5FE), zIndex = 3),
            ColoringShape("wheel_front", "Front Wheel", { w, h -> ShapeGeometry.ovalPath(0.64f, 0.62f, 0.82f, 0.82f, w, h) }, defaultFill = Color(0xFF424242), zIndex = 4),
            ColoringShape("wheel_back", "Back Wheel", { w, h -> ShapeGeometry.ovalPath(0.18f, 0.62f, 0.36f, 0.82f, w, h) }, defaultFill = Color(0xFF424242), zIndex = 4),
            ColoringShape("hub_front", "Front Hubcap", { w, h -> ShapeGeometry.ovalPath(0.70f, 0.68f, 0.76f, 0.76f, w, h) }, defaultFill = Color(0xFFEEEEEE), zIndex = 5),
            ColoringShape("hub_back", "Back Hubcap", { w, h -> ShapeGeometry.ovalPath(0.24f, 0.68f, 0.30f, 0.76f, w, h) }, defaultFill = Color(0xFFEEEEEE), zIndex = 5)
        )
    )

    private fun createBusPage() = ColoringPage(
        id = "bus_page",
        categoryId = "vehicles",
        title = "School Bus",
        emoji = "🚌",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("bus_body", "Bus Body", { w, h -> ShapeGeometry.roundRectPath(0.14f, 0.30f, 0.86f, 0.68f, 24f, w, h) }, zIndex = 1),
            ColoringShape("window_1", "Window 1", { w, h -> ShapeGeometry.roundRectPath(0.20f, 0.36f, 0.34f, 0.48f, 8f, w, h) }, defaultFill = Color(0xFFE1F5FE), zIndex = 2),
            ColoringShape("window_2", "Window 2", { w, h -> ShapeGeometry.roundRectPath(0.38f, 0.36f, 0.52f, 0.48f, 8f, w, h) }, defaultFill = Color(0xFFE1F5FE), zIndex = 2),
            ColoringShape("window_3", "Window 3", { w, h -> ShapeGeometry.roundRectPath(0.56f, 0.36f, 0.70f, 0.48f, 8f, w, h) }, defaultFill = Color(0xFFE1F5FE), zIndex = 2),
            ColoringShape("windshield", "Windshield", { w, h -> ShapeGeometry.roundRectPath(0.74f, 0.36f, 0.82f, 0.52f, 8f, w, h) }, defaultFill = Color(0xFFE1F5FE), zIndex = 2),
            ColoringShape("wheel_back", "Back Wheel", { w, h -> ShapeGeometry.ovalPath(0.22f, 0.62f, 0.38f, 0.80f, w, h) }, defaultFill = Color(0xFF424242), zIndex = 3),
            ColoringShape("wheel_front", "Front Wheel", { w, h -> ShapeGeometry.ovalPath(0.66f, 0.62f, 0.82f, 0.80f, w, h) }, defaultFill = Color(0xFF424242), zIndex = 3)
        )
    )

    private fun createTruckPage() = ColoringPage(
        id = "truck_page",
        categoryId = "vehicles",
        title = "Big Truck",
        emoji = "🚚",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("cargo", "Cargo Box", { w, h -> ShapeGeometry.roundRectPath(0.12f, 0.28f, 0.62f, 0.68f, 16f, w, h) }, zIndex = 1),
            ColoringShape("cabin", "Truck Cabin", { w, h -> ShapeGeometry.roundRectPath(0.62f, 0.40f, 0.88f, 0.68f, 16f, w, h) }, zIndex = 2),
            ColoringShape("window", "Cabin Window", { w, h -> ShapeGeometry.roundRectPath(0.68f, 0.44f, 0.84f, 0.54f, 8f, w, h) }, defaultFill = Color(0xFFE1F5FE), zIndex = 3),
            ColoringShape("wheel_1", "Wheel 1", { w, h -> ShapeGeometry.ovalPath(0.18f, 0.64f, 0.32f, 0.80f, w, h) }, defaultFill = Color(0xFF424242), zIndex = 4),
            ColoringShape("wheel_2", "Wheel 2", { w, h -> ShapeGeometry.ovalPath(0.42f, 0.64f, 0.56f, 0.80f, w, h) }, defaultFill = Color(0xFF424242), zIndex = 4),
            ColoringShape("wheel_3", "Wheel 3", { w, h -> ShapeGeometry.ovalPath(0.70f, 0.64f, 0.84f, 0.80f, w, h) }, defaultFill = Color(0xFF424242), zIndex = 4)
        )
    )

    private fun createTrainPage() = ColoringPage(
        id = "train_page",
        categoryId = "vehicles",
        title = "Choo-Choo Train",
        emoji = "🚂",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("boiler", "Engine Boiler", { w, h -> ShapeGeometry.roundRectPath(0.36f, 0.42f, 0.86f, 0.68f, 18f, w, h) }, zIndex = 1),
            ColoringShape("cab", "Driver Cab", { w, h -> ShapeGeometry.roundRectPath(0.16f, 0.30f, 0.40f, 0.68f, 16f, w, h) }, zIndex = 2),
            ColoringShape("chimney", "Chimney Funnel", { w, h -> ShapeGeometry.roundRectPath(0.72f, 0.28f, 0.82f, 0.44f, 8f, w, h) }, zIndex = 2),
            ColoringShape("window", "Cab Window", { w, h -> ShapeGeometry.roundRectPath(0.20f, 0.36f, 0.34f, 0.48f, 8f, w, h) }, defaultFill = Color(0xFFE1F5FE), zIndex = 3),
            ColoringShape("big_wheel", "Big Driver Wheel", { w, h -> ShapeGeometry.ovalPath(0.18f, 0.62f, 0.38f, 0.84f, w, h) }, defaultFill = Color(0xFF424242), zIndex = 4),
            ColoringShape("small_wheel_1", "Front Wheel 1", { w, h -> ShapeGeometry.ovalPath(0.48f, 0.66f, 0.62f, 0.82f, w, h) }, defaultFill = Color(0xFF424242), zIndex = 4),
            ColoringShape("small_wheel_2", "Front Wheel 2", { w, h -> ShapeGeometry.ovalPath(0.68f, 0.66f, 0.82f, 0.82f, w, h) }, defaultFill = Color(0xFF424242), zIndex = 4)
        )
    )

    private fun createAirplanePage() = ColoringPage(
        id = "airplane_page",
        categoryId = "vehicles",
        title = "Flying Airplane",
        emoji = "✈️",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("fuselage", "Airplane Body", { w, h -> ShapeGeometry.roundRectPath(0.14f, 0.44f, 0.86f, 0.60f, 26f, w, h) }, zIndex = 1),
            ColoringShape("top_wing", "Top Wing", { w, h -> ShapeGeometry.polygonPath(listOf(0.42f to 0.46f, 0.52f to 0.22f, 0.64f to 0.22f, 0.58f to 0.46f), w, h) }, zIndex = 2),
            ColoringShape("bottom_wing", "Bottom Wing", { w, h -> ShapeGeometry.polygonPath(listOf(0.42f to 0.58f, 0.58f to 0.58f, 0.64f to 0.82f, 0.52f to 0.82f), w, h) }, zIndex = 2),
            ColoringShape("tail", "Tail Fin", { w, h -> ShapeGeometry.polygonPath(listOf(0.16f to 0.44f, 0.14f to 0.26f, 0.26f to 0.26f, 0.28f to 0.44f), w, h) }, zIndex = 2),
            ColoringShape("cockpit", "Cockpit Window", { w, h -> ShapeGeometry.roundRectPath(0.74f, 0.46f, 0.84f, 0.56f, 10f, w, h) }, defaultFill = Color(0xFFE1F5FE), zIndex = 3)
        )
    )

    private fun createBoatPage() = ColoringPage(
        id = "boat_page",
        categoryId = "vehicles",
        title = "Sailboat",
        emoji = "⛵",
        shapes = listOf(
            ColoringShape("bg", "Sky & Sea", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("hull", "Boat Hull", { w, h -> ShapeGeometry.polygonPath(listOf(0.16f to 0.64f, 0.84f to 0.64f, 0.74f to 0.82f, 0.26f to 0.82f), w, h) }, zIndex = 1),
            ColoringShape("mast", "Sail Mast", { w, h -> ShapeGeometry.roundRectPath(0.48f, 0.20f, 0.52f, 0.64f, 4f, w, h) }, defaultFill = Color(0xFF8D6E63), zIndex = 2),
            ColoringShape("main_sail", "Main Sail", { w, h -> ShapeGeometry.polygonPath(listOf(0.53f to 0.22f, 0.82f to 0.60f, 0.53f to 0.60f), w, h) }, zIndex = 3),
            ColoringShape("front_sail", "Front Sail", { w, h -> ShapeGeometry.polygonPath(listOf(0.47f to 0.28f, 0.47f to 0.60f, 0.24f to 0.60f), w, h) }, zIndex = 3)
        )
    )

    // ----------------- DINOSAURS PAGE BUILDERS -----------------

    private fun createTRexPage() = ColoringPage(
        id = "trex_page",
        categoryId = "dinosaurs",
        title = "T-Rex",
        emoji = "🦖",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("tail", "Tail", { w, h -> ShapeGeometry.polygonPath(listOf(0.36f to 0.58f, 0.12f to 0.70f, 0.32f to 0.72f), w, h) }, zIndex = 1),
            ColoringShape("body", "Dino Body", { w, h -> ShapeGeometry.ovalPath(0.30f, 0.38f, 0.72f, 0.76f, w, h) }, zIndex = 2),
            ColoringShape("head", "Big Head", { w, h -> ShapeGeometry.roundRectPath(0.52f, 0.20f, 0.86f, 0.46f, 22f, w, h) }, zIndex = 3),
            ColoringShape("leg_left", "Left Foot", { w, h -> ShapeGeometry.roundRectPath(0.38f, 0.72f, 0.50f, 0.88f, 12f, w, h) }, zIndex = 3),
            ColoringShape("leg_right", "Right Foot", { w, h -> ShapeGeometry.roundRectPath(0.54f, 0.72f, 0.66f, 0.88f, 12f, w, h) }, zIndex = 3),
            ColoringShape("snout", "Snout & Jaw", { w, h -> ShapeGeometry.roundRectPath(0.66f, 0.28f, 0.84f, 0.42f, 12f, w, h) }, zIndex = 4)
        )
    )

    private fun createTriceratopsPage() = ColoringPage(
        id = "triceratops_page",
        categoryId = "dinosaurs",
        title = "Triceratops",
        emoji = "🦏",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("frill", "Neck Shield", { w, h -> ShapeGeometry.ovalPath(0.48f, 0.22f, 0.80f, 0.54f, w, h) }, zIndex = 1),
            ColoringShape("body", "Body", { w, h -> ShapeGeometry.ovalPath(0.20f, 0.40f, 0.68f, 0.78f, w, h) }, zIndex = 2),
            ColoringShape("head", "Head", { w, h -> ShapeGeometry.ovalPath(0.52f, 0.36f, 0.86f, 0.62f, w, h) }, zIndex = 3),
            ColoringShape("horn_top", "Top Horn", { w, h -> ShapeGeometry.polygonPath(listOf(0.68f to 0.36f, 0.78f to 0.16f, 0.74f to 0.38f), w, h) }, defaultFill = Color(0xFFFFF9C4), zIndex = 4),
            ColoringShape("horn_nose", "Nose Horn", { w, h -> ShapeGeometry.polygonPath(listOf(0.82f to 0.46f, 0.94f to 0.42f, 0.84f to 0.50f), w, h) }, defaultFill = Color(0xFFFFF9C4), zIndex = 4)
        )
    )

    private fun createStegosaurusPage() = ColoringPage(
        id = "stegosaurus_page",
        categoryId = "dinosaurs",
        title = "Stegosaurus",
        emoji = "🦕",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("plate_1", "Back Plate 1", { w, h -> ShapeGeometry.polygonPath(listOf(0.32f to 0.42f, 0.38f to 0.26f, 0.44f to 0.42f), w, h) }, zIndex = 1),
            ColoringShape("plate_2", "Back Plate 2", { w, h -> ShapeGeometry.polygonPath(listOf(0.46f to 0.38f, 0.54f to 0.20f, 0.60f to 0.38f), w, h) }, zIndex = 1),
            ColoringShape("plate_3", "Back Plate 3", { w, h -> ShapeGeometry.polygonPath(listOf(0.62f to 0.42f, 0.68f to 0.26f, 0.74f to 0.42f), w, h) }, zIndex = 1),
            ColoringShape("body", "Body", { w, h -> ShapeGeometry.ovalPath(0.25f, 0.38f, 0.78f, 0.74f, w, h) }, zIndex = 2),
            ColoringShape("head", "Little Head", { w, h -> ShapeGeometry.ovalPath(0.74f, 0.52f, 0.90f, 0.66f, w, h) }, zIndex = 3)
        )
    )

    private fun createBrontosaurusPage() = ColoringPage(
        id = "brontosaurus_page",
        categoryId = "dinosaurs",
        title = "Brontosaurus",
        emoji = "🦕",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("body", "Round Body", { w, h -> ShapeGeometry.ovalPath(0.20f, 0.46f, 0.72f, 0.82f, w, h) }, zIndex = 1),
            ColoringShape("neck", "Long Neck", { w, h -> ShapeGeometry.polygonPath(listOf(0.54f to 0.54f, 0.68f to 0.22f, 0.78f to 0.22f, 0.66f to 0.56f), w, h) }, zIndex = 2),
            ColoringShape("head", "Cute Head", { w, h -> ShapeGeometry.ovalPath(0.70f, 0.16f, 0.88f, 0.28f, w, h) }, zIndex = 3),
            ColoringShape("tail", "Wavy Tail", { w, h -> ShapeGeometry.polygonPath(listOf(0.24f to 0.58f, 0.10f to 0.52f, 0.16f to 0.68f), w, h) }, zIndex = 1)
        )
    )

    private fun createPterodactylPage() = ColoringPage(
        id = "pterodactyl_page",
        categoryId = "dinosaurs",
        title = "Pterodactyl",
        emoji = "🦅",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("left_wing", "Left Wing", { w, h -> ShapeGeometry.polygonPath(listOf(0.44f to 0.45f, 0.12f to 0.32f, 0.26f to 0.56f), w, h) }, zIndex = 1),
            ColoringShape("right_wing", "Right Wing", { w, h -> ShapeGeometry.polygonPath(listOf(0.56f to 0.45f, 0.88f to 0.32f, 0.74f to 0.56f), w, h) }, zIndex = 1),
            ColoringShape("body", "Body", { w, h -> ShapeGeometry.ovalPath(0.44f, 0.40f, 0.56f, 0.70f, w, h) }, zIndex = 2),
            ColoringShape("head", "Beak Head", { w, h -> ShapeGeometry.polygonPath(listOf(0.46f to 0.38f, 0.40f to 0.22f, 0.58f to 0.28f, 0.54f to 0.38f), w, h) }, zIndex = 3)
        )
    )

    // ----------------- FRUITS PAGE BUILDERS -----------------

    private fun createApplePage() = ColoringPage(
        id = "apple_page",
        categoryId = "fruits",
        title = "Sweet Apple",
        emoji = "🍎",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("apple_body", "Apple Fruit", { w, h -> ShapeGeometry.ovalPath(0.22f, 0.28f, 0.78f, 0.84f, w, h) }, defaultFill = Color(0xFFFF5252), zIndex = 1),
            ColoringShape("stem", "Apple Stem", { w, h -> ShapeGeometry.roundRectPath(0.47f, 0.16f, 0.53f, 0.32f, 6f, w, h) }, defaultFill = Color(0xFF795548), zIndex = 2),
            ColoringShape("leaf", "Green Leaf", { w, h -> ShapeGeometry.ovalPath(0.52f, 0.16f, 0.74f, 0.28f, w, h) }, defaultFill = Color(0xFF66BB6A), zIndex = 3)
        )
    )

    private fun createBananaPage() = ColoringPage(
        id = "banana_page",
        categoryId = "fruits",
        title = "Ripe Banana",
        emoji = "🍌",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("banana", "Banana Curve", { w, h -> ShapeGeometry.ovalPath(0.20f, 0.25f, 0.80f, 0.75f, w, h) }, defaultFill = Color(0xFFFFEE58), zIndex = 1),
            ColoringShape("tip", "Stem Tip", { w, h -> ShapeGeometry.roundRectPath(0.70f, 0.24f, 0.78f, 0.32f, 4f, w, h) }, defaultFill = Color(0xFF8D6E63), zIndex = 2)
        )
    )

    private fun createOrangePage() = ColoringPage(
        id = "orange_page",
        categoryId = "fruits",
        title = "Juicy Orange",
        emoji = "🍊",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("orange_body", "Orange Fruit", { w, h -> ShapeGeometry.ovalPath(0.20f, 0.25f, 0.80f, 0.85f, w, h) }, defaultFill = Color(0xFFFFA726), zIndex = 1),
            ColoringShape("leaf", "Leaf", { w, h -> ShapeGeometry.ovalPath(0.48f, 0.14f, 0.72f, 0.28f, w, h) }, defaultFill = Color(0xFF66BB6A), zIndex = 2)
        )
    )

    private fun createStrawberryPage() = ColoringPage(
        id = "strawberry_page",
        categoryId = "fruits",
        title = "Berry Strawberry",
        emoji = "🍓",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("berry", "Berry Body", { w, h -> ShapeGeometry.polygonPath(listOf(0.24f to 0.35f, 0.76f to 0.35f, 0.50f to 0.84f), w, h) }, defaultFill = Color(0xFFFF1744), zIndex = 1),
            ColoringShape("leaves", "Green Crown", { w, h -> ShapeGeometry.polygonPath(listOf(0.22f to 0.35f, 0.34f to 0.24f, 0.50f to 0.32f, 0.66f to 0.24f, 0.78f to 0.35f, 0.50f to 0.40f), w, h) }, defaultFill = Color(0xFF4CAF50), zIndex = 2)
        )
    )

    private fun createWatermelonPage() = ColoringPage(
        id = "watermelon_page",
        categoryId = "fruits",
        title = "Watermelon Slice",
        emoji = "🍉",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("rind", "Green Rind", { w, h -> ShapeGeometry.ovalPath(0.18f, 0.30f, 0.82f, 0.84f, w, h) }, defaultFill = Color(0xFF2E7D32), zIndex = 1),
            ColoringShape("flesh", "Red Flesh", { w, h -> ShapeGeometry.ovalPath(0.22f, 0.34f, 0.78f, 0.78f, w, h) }, defaultFill = Color(0xFFFF5252), zIndex = 2)
        )
    )

    private fun createCherryPage() = ColoringPage(
        id = "cherry_page",
        categoryId = "fruits",
        title = "Twin Cherries",
        emoji = "🍒",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("cherry_1", "Left Cherry", { w, h -> ShapeGeometry.ovalPath(0.22f, 0.52f, 0.50f, 0.80f, w, h) }, defaultFill = Color(0xFFD50000), zIndex = 1),
            ColoringShape("cherry_2", "Right Cherry", { w, h -> ShapeGeometry.ovalPath(0.50f, 0.52f, 0.78f, 0.80f, w, h) }, defaultFill = Color(0xFFD50000), zIndex = 1),
            ColoringShape("stem", "Green Stems", { w, h -> ShapeGeometry.polygonPath(listOf(0.36f to 0.54f, 0.50f to 0.22f, 0.64f to 0.54f), w, h) }, defaultFill = Color(0xFF43A047), zIndex = 2)
        )
    )

    // ----------------- NATURE PAGE BUILDERS -----------------

    private fun createSunPage() = ColoringPage(
        id = "sun_page",
        categoryId = "nature",
        title = "Sunny Sunshine",
        emoji = "☀️",
        shapes = listOf(
            ColoringShape("bg", "Sky Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("sun_core", "Sun Center", { w, h -> ShapeGeometry.ovalPath(0.30f, 0.30f, 0.70f, 0.70f, w, h) }, defaultFill = Color(0xFFFFEE58), zIndex = 2),
            ColoringShape("sun_smile", "Smile Cheeks", { w, h -> ShapeGeometry.ovalPath(0.40f, 0.44f, 0.60f, 0.56f, w, h) }, zIndex = 3)
        )
    )

    private fun createRainbowPage() = ColoringPage(
        id = "rainbow_page",
        categoryId = "nature",
        title = "Magic Rainbow",
        emoji = "🌈",
        shapes = listOf(
            ColoringShape("bg", "Sky Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("arc_red", "Red Arc", { w, h -> ShapeGeometry.ovalPath(0.12f, 0.20f, 0.88f, 0.88f, w, h) }, defaultFill = Color(0xFFFF5252), zIndex = 1),
            ColoringShape("arc_yellow", "Yellow Arc", { w, h -> ShapeGeometry.ovalPath(0.20f, 0.30f, 0.80f, 0.88f, w, h) }, defaultFill = Color(0xFFFFEE58), zIndex = 2),
            ColoringShape("arc_green", "Green Arc", { w, h -> ShapeGeometry.ovalPath(0.28f, 0.40f, 0.72f, 0.88f, w, h) }, defaultFill = Color(0xFF69F0AE), zIndex = 3),
            ColoringShape("arc_blue", "Blue Arc", { w, h -> ShapeGeometry.ovalPath(0.36f, 0.50f, 0.64f, 0.88f, w, h) }, defaultFill = Color(0xFF40C4FF), zIndex = 4),
            ColoringShape("cloud_left", "Left Cloud", { w, h -> ShapeGeometry.ovalPath(0.08f, 0.62f, 0.36f, 0.86f, w, h) }, defaultFill = Color(0xFFFFFFFF), zIndex = 5),
            ColoringShape("cloud_right", "Right Cloud", { w, h -> ShapeGeometry.ovalPath(0.64f, 0.62f, 0.92f, 0.86f, w, h) }, defaultFill = Color(0xFFFFFFFF), zIndex = 5)
        )
    )

    private fun createFlowerPage() = ColoringPage(
        id = "flower_page",
        categoryId = "nature",
        title = "Spring Flower",
        emoji = "🌸",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("stem", "Green Stem", { w, h -> ShapeGeometry.roundRectPath(0.47f, 0.46f, 0.53f, 0.86f, 6f, w, h) }, defaultFill = Color(0xFF4CAF50), zIndex = 1),
            ColoringShape("leaf_left", "Left Leaf", { w, h -> ShapeGeometry.ovalPath(0.32f, 0.62f, 0.48f, 0.74f, w, h) }, defaultFill = Color(0xFF81C784), zIndex = 2),
            ColoringShape("petal_top", "Top Petal", { w, h -> ShapeGeometry.ovalPath(0.40f, 0.16f, 0.60f, 0.38f, w, h) }, zIndex = 3),
            ColoringShape("petal_bottom", "Bottom Petal", { w, h -> ShapeGeometry.ovalPath(0.40f, 0.44f, 0.60f, 0.66f, w, h) }, zIndex = 3),
            ColoringShape("petal_left", "Left Petal", { w, h -> ShapeGeometry.ovalPath(0.24f, 0.30f, 0.46f, 0.52f, w, h) }, zIndex = 3),
            ColoringShape("petal_right", "Right Petal", { w, h -> ShapeGeometry.ovalPath(0.54f, 0.30f, 0.76f, 0.52f, w, h) }, zIndex = 3),
            ColoringShape("flower_center", "Center Core", { w, h -> ShapeGeometry.ovalPath(0.40f, 0.32f, 0.60f, 0.50f, w, h) }, defaultFill = Color(0xFFFFD54F), zIndex = 4)
        )
    )

    private fun createTreePage() = ColoringPage(
        id = "tree_page",
        categoryId = "nature",
        title = "Big Forest Tree",
        emoji = "🌳",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("trunk", "Tree Trunk", { w, h -> ShapeGeometry.roundRectPath(0.42f, 0.48f, 0.58f, 0.88f, 10f, w, h) }, defaultFill = Color(0xFF795548), zIndex = 1),
            ColoringShape("foliage", "Green Canopy", { w, h -> ShapeGeometry.ovalPath(0.20f, 0.16f, 0.80f, 0.60f, w, h) }, defaultFill = Color(0xFF43A047), zIndex = 2)
        )
    )

    private fun createCloudPage() = ColoringPage(
        id = "cloud_page",
        categoryId = "nature",
        title = "Fluffy Cloud",
        emoji = "☁️",
        shapes = listOf(
            ColoringShape("bg", "Sky Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("cloud_body", "Puffy Cloud", { w, h -> ShapeGeometry.ovalPath(0.18f, 0.32f, 0.82f, 0.68f, w, h) }, defaultFill = Color(0xFFFFFFFF), zIndex = 1)
        )
    )

    private fun createButterflyPage() = ColoringPage(
        id = "butterfly_page",
        categoryId = "nature",
        title = "Pretty Butterfly",
        emoji = "🦋",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("wing_top_left", "Top Left Wing", { w, h -> ShapeGeometry.ovalPath(0.12f, 0.22f, 0.46f, 0.54f, w, h) }, zIndex = 1),
            ColoringShape("wing_top_right", "Top Right Wing", { w, h -> ShapeGeometry.ovalPath(0.54f, 0.22f, 0.88f, 0.54f, w, h) }, zIndex = 1),
            ColoringShape("wing_bot_left", "Bottom Left Wing", { w, h -> ShapeGeometry.ovalPath(0.22f, 0.50f, 0.46f, 0.78f, w, h) }, zIndex = 1),
            ColoringShape("wing_bot_right", "Bottom Right Wing", { w, h -> ShapeGeometry.ovalPath(0.54f, 0.50f, 0.78f, 0.78f, w, h) }, zIndex = 1),
            ColoringShape("body", "Body", { w, h -> ShapeGeometry.roundRectPath(0.46f, 0.28f, 0.54f, 0.74f, 10f, w, h) }, defaultFill = Color(0xFF212121), zIndex = 2)
        )
    )

    // ----------------- SPACE PAGE BUILDERS -----------------

    private fun createRocketPage() = ColoringPage(
        id = "rocket_page",
        categoryId = "space",
        title = "Speedy Rocket",
        emoji = "🚀",
        shapes = listOf(
            ColoringShape("bg", "Cosmic Sky", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("fin_left", "Left Rocket Fin", { w, h -> ShapeGeometry.polygonPath(listOf(0.36f to 0.56f, 0.20f to 0.76f, 0.36f to 0.72f), w, h) }, zIndex = 1),
            ColoringShape("fin_right", "Right Rocket Fin", { w, h -> ShapeGeometry.polygonPath(listOf(0.64f to 0.56f, 0.80f to 0.76f, 0.64f to 0.72f), w, h) }, zIndex = 1),
            ColoringShape("flame", "Rocket Flame", { w, h -> ShapeGeometry.polygonPath(listOf(0.40f to 0.72f, 0.50f to 0.92f, 0.60f to 0.72f), w, h) }, defaultFill = Color(0xFFFF5722), zIndex = 1),
            ColoringShape("hull", "Rocket Hull", { w, h -> ShapeGeometry.roundRectPath(0.34f, 0.26f, 0.66f, 0.72f, 28f, w, h) }, zIndex = 2),
            ColoringShape("nose_cone", "Nose Cone", { w, h -> ShapeGeometry.polygonPath(listOf(0.36f to 0.30f, 0.50f to 0.14f, 0.64f to 0.30f), w, h) }, zIndex = 3),
            ColoringShape("window", "Porthole Window", { w, h -> ShapeGeometry.ovalPath(0.42f, 0.36f, 0.58f, 0.52f, w, h) }, defaultFill = Color(0xFF81D4FA), zIndex = 4)
        )
    )

    private fun createPlanetPage() = ColoringPage(
        id = "planet_page",
        categoryId = "space",
        title = "Ringed Planet",
        emoji = "🪐",
        shapes = listOf(
            ColoringShape("bg", "Deep Space", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("ring", "Planet Rings", { w, h -> ShapeGeometry.ovalPath(0.10f, 0.44f, 0.90f, 0.56f, w, h) }, defaultFill = Color(0xFFFFD54F), zIndex = 1),
            ColoringShape("globe", "Planet Sphere", { w, h -> ShapeGeometry.ovalPath(0.28f, 0.28f, 0.72f, 0.72f, w, h) }, zIndex = 2)
        )
    )

    private fun createAstronautPage() = ColoringPage(
        id = "astronaut_page",
        categoryId = "space",
        title = "Brave Astronaut",
        emoji = "👨‍🚀",
        shapes = listOf(
            ColoringShape("bg", "Space Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("suit", "Spacesuit Body", { w, h -> ShapeGeometry.roundRectPath(0.32f, 0.48f, 0.68f, 0.86f, 20f, w, h) }, zIndex = 1),
            ColoringShape("helmet", "Helmet", { w, h -> ShapeGeometry.ovalPath(0.28f, 0.18f, 0.72f, 0.52f, w, h) }, zIndex = 2),
            ColoringShape("visor", "Visor Glass", { w, h -> ShapeGeometry.ovalPath(0.36f, 0.24f, 0.64f, 0.44f, w, h) }, defaultFill = Color(0xFFFFD54F), zIndex = 3)
        )
    )

    private fun createUFOPage() = ColoringPage(
        id = "ufo_page",
        categoryId = "space",
        title = "Alien UFO",
        emoji = "🛸",
        shapes = listOf(
            ColoringShape("bg", "Space Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("dome", "Glass Dome", { w, h -> ShapeGeometry.ovalPath(0.34f, 0.28f, 0.66f, 0.52f, w, h) }, defaultFill = Color(0xFF80D8FF), zIndex = 1),
            ColoringShape("saucer", "Flying Saucer", { w, h -> ShapeGeometry.ovalPath(0.16f, 0.46f, 0.84f, 0.68f, w, h) }, zIndex = 2)
        )
    )

    private fun createStarPage() = ColoringPage(
        id = "star_page",
        categoryId = "space",
        title = "Twinkle Star",
        emoji = "⭐",
        shapes = listOf(
            ColoringShape("bg", "Night Sky", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("star", "Five-Point Star", { w, h ->
                ShapeGeometry.polygonPath(
                    listOf(
                        0.50f to 0.18f, 0.60f to 0.38f, 0.82f to 0.38f, 0.64f to 0.52f,
                        0.71f to 0.74f, 0.50f to 0.60f, 0.29f to 0.74f, 0.36f to 0.52f,
                        0.18f to 0.38f, 0.40f to 0.38f
                    ), w, h
                )
            }, defaultFill = Color(0xFFFFEE58), zIndex = 1)
        )
    )

    // ----------------- TOYS PAGE BUILDERS -----------------

    private fun createTeddyBearPage() = ColoringPage(
        id = "teddy_bear_page",
        categoryId = "toys",
        title = "Teddy Bear",
        emoji = "🧸",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("left_ear", "Left Round Ear", { w, h -> ShapeGeometry.ovalPath(0.24f, 0.18f, 0.38f, 0.32f, w, h) }, zIndex = 1),
            ColoringShape("right_ear", "Right Round Ear", { w, h -> ShapeGeometry.ovalPath(0.62f, 0.18f, 0.76f, 0.32f, w, h) }, zIndex = 1),
            ColoringShape("body", "Soft Body", { w, h -> ShapeGeometry.ovalPath(0.28f, 0.48f, 0.72f, 0.88f, w, h) }, zIndex = 2),
            ColoringShape("head", "Round Head", { w, h -> ShapeGeometry.ovalPath(0.26f, 0.24f, 0.74f, 0.56f, w, h) }, zIndex = 3),
            ColoringShape("muzzle", "Muzzle", { w, h -> ShapeGeometry.ovalPath(0.38f, 0.40f, 0.62f, 0.52f, w, h) }, zIndex = 4)
        )
    )

    private fun createRobotPage() = ColoringPage(
        id = "robot_page",
        categoryId = "toys",
        title = "Toy Robot",
        emoji = "🤖",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("antenna", "Antenna", { w, h -> ShapeGeometry.roundRectPath(0.48f, 0.12f, 0.52f, 0.24f, 4f, w, h) }, zIndex = 1),
            ColoringShape("head", "Boxy Head", { w, h -> ShapeGeometry.roundRectPath(0.30f, 0.24f, 0.70f, 0.48f, 16f, w, h) }, zIndex = 2),
            ColoringShape("screen", "Screen Face", { w, h -> ShapeGeometry.roundRectPath(0.36f, 0.30f, 0.64f, 0.42f, 8f, w, h) }, defaultFill = Color(0xFF80DEEA), zIndex = 3),
            ColoringShape("body", "Metal Body", { w, h -> ShapeGeometry.roundRectPath(0.28f, 0.50f, 0.72f, 0.86f, 16f, w, h) }, zIndex = 2)
        )
    )

    private fun createDrumPage() = ColoringPage(
        id = "drum_page",
        categoryId = "toys",
        title = "Toy Drum",
        emoji = "🥁",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("drum_base", "Drum Barrel", { w, h -> ShapeGeometry.roundRectPath(0.22f, 0.38f, 0.78f, 0.76f, 12f, w, h) }, zIndex = 1),
            ColoringShape("drum_top", "Drum Head", { w, h -> ShapeGeometry.ovalPath(0.22f, 0.28f, 0.78f, 0.46f, w, h) }, defaultFill = Color(0xFFFFF9C4), zIndex = 2)
        )
    )

    private fun createToyBallPage() = ColoringPage(
        id = "ball_page",
        categoryId = "toys",
        title = "Beach Ball",
        emoji = "⚽",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("ball_base", "Round Ball", { w, h -> ShapeGeometry.ovalPath(0.20f, 0.20f, 0.80f, 0.80f, w, h) }, zIndex = 1),
            ColoringShape("center_patch", "Center Circle", { w, h -> ShapeGeometry.ovalPath(0.42f, 0.42f, 0.58f, 0.58f, w, h) }, defaultFill = Color(0xFFFFFFFF), zIndex = 2)
        )
    )

    private fun createKitePage() = ColoringPage(
        id = "kite_page",
        categoryId = "toys",
        title = "Flying Kite",
        emoji = "🪁",
        shapes = listOf(
            ColoringShape("bg", "Sky Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("kite_top", "Kite Top Half", { w, h -> ShapeGeometry.polygonPath(listOf(0.50f to 0.16f, 0.76f to 0.46f, 0.50f to 0.46f, 0.24f to 0.46f), w, h) }, zIndex = 1),
            ColoringShape("kite_bottom", "Kite Bottom Half", { w, h -> ShapeGeometry.polygonPath(listOf(0.24f to 0.46f, 0.76f to 0.46f, 0.50f to 0.82f), w, h) }, zIndex = 1)
        )
    )

    // ----------------- ALPHABET PAGE BUILDERS -----------------

    private fun createLetterAPage() = ColoringPage(
        id = "letter_a_page",
        categoryId = "alphabet",
        title = "Letter A is for Apple",
        emoji = "🅰️",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("letter_a", "Letter A", { w, h ->
                ShapeGeometry.polygonPath(
                    listOf(
                        0.40f to 0.18f, 0.60f to 0.18f, 0.78f to 0.78f, 0.64f to 0.78f,
                        0.58f to 0.58f, 0.42f to 0.58f, 0.36f to 0.78f, 0.22f to 0.78f
                    ), w, h
                )
            }, defaultFill = Color(0xFFFF5252), zIndex = 1),
            ColoringShape("mini_apple", "Little Apple", { w, h -> ShapeGeometry.ovalPath(0.42f, 0.34f, 0.58f, 0.50f, w, h) }, defaultFill = Color(0xFF4CAF50), zIndex = 2)
        )
    )

    private fun createLetterBPage() = ColoringPage(
        id = "letter_b_page",
        categoryId = "alphabet",
        title = "Letter B is for Ball",
        emoji = "🅱️",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("letter_b_stem", "Letter B Stem", { w, h -> ShapeGeometry.roundRectPath(0.24f, 0.18f, 0.38f, 0.78f, 8f, w, h) }, defaultFill = Color(0xFF448AFF), zIndex = 1),
            ColoringShape("loop_top", "Top Round Loop", { w, h -> ShapeGeometry.ovalPath(0.36f, 0.18f, 0.72f, 0.48f, w, h) }, defaultFill = Color(0xFF448AFF), zIndex = 2),
            ColoringShape("loop_bottom", "Bottom Round Loop", { w, h -> ShapeGeometry.ovalPath(0.36f, 0.46f, 0.76f, 0.78f, w, h) }, defaultFill = Color(0xFF448AFF), zIndex = 2)
        )
    )

    private fun createLetterCPage() = ColoringPage(
        id = "letter_c_page",
        categoryId = "alphabet",
        title = "Letter C is for Cat",
        emoji = "🔤",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("letter_c", "Letter C Curve", { w, h -> ShapeGeometry.ovalPath(0.24f, 0.18f, 0.76f, 0.78f, w, h) }, defaultFill = Color(0xFFFFB300), zIndex = 1)
        )
    )

    private fun createLetterDPage() = ColoringPage(
        id = "letter_d_page",
        categoryId = "alphabet",
        title = "Letter D is for Dog",
        emoji = "🔤",
        shapes = listOf(
            ColoringShape("bg", "Background", { w, h -> ShapeGeometry.roundRectPath(0.04f, 0.04f, 0.96f, 0.96f, 32f, w, h) }),
            ColoringShape("letter_d_stem", "Letter D Stem", { w, h -> ShapeGeometry.roundRectPath(0.24f, 0.18f, 0.38f, 0.78f, 8f, w, h) }, defaultFill = Color(0xFFAB47BC), zIndex = 1),
            ColoringShape("loop", "Big Loop", { w, h -> ShapeGeometry.ovalPath(0.34f, 0.18f, 0.76f, 0.78f, w, h) }, defaultFill = Color(0xFFAB47BC), zIndex = 2)
        )
    )
}
