object Versions {
    const val compile_sdk = 29
    const val min_sdk = 21
    const val target_sdk = 29

    val app_version_major = 1
    val app_version_minor = 0
    val app_version_fix = 0

    val app_version_name = "$app_version_major.$app_version_minor.$app_version_fix"
    val app_version_code = app_version_major * 1000000 + app_version_minor * 10000 + app_version_fix * 100

    const val gradle_plugin = "3.6.1"
    const val kotlin = "1.3.61"

    const val androidx_appcompat = "1.1.0"
    const val androidx_activity = "1.1.0"
    const val androidx_fragment = "1.2.4"
    const val androidx_lifecycle = "2.2.0"
    const val androidx_livedata = "2.2.0"
    const val androidx_viewmodel = "2.2.0"
    const val androidx_reactive_streams = "2.2.0"
    const val androidx_room = "2.2.5"
    const val androidx_core = "1.2.0"
    const val androidx_multidex = "2.0.1"
    const val androidx_cardview = "1.0.0"
    const val androidx_constraint_layout = "1.1.3"
    const val androidx_browser = "1.2.0"
    const val androidx_navigation = "2.2.1"
    const val material_design = "1.2.0-alpha05"

    const val google_services = "4.2.0"

    const val coroutines = "1.3.5"

    const val firebase_messaging = "19.0.1"
    const val firebase_firestore = "21.1.1"
    const val firebase_storage = "19.1.0"
    const val firebase_storage_ui = "4.3.1"
    const val firebase_remote_config = "18.0.0"
    const val firebase_core = "17.0.1"
    const val firebase_analytics = "17.2.1"

    const val retrofit = "2.8.1"
    const val okhttp = "4.5.0"
    const val rxjava = "2.2.18"
    const val rxandroid = "2.1.1"
    const val gson = "2.8.5"
    const val dagger = "2.27"
    const val glide = "4.11.0"
    const val joda = "2.10.2"
    const val groupie = "2.8.0"

    // Debugging
    const val timber = "4.7.0"
    const val stetho = "1.5.1"

    // TEST
    const val junit = "4.12"
    const val mockito = "3.3.3"
    const val mockito_kotlin = "2.2.0"

    const val espresso = "3.2.0"
    const val support_test_runner = "1.1.0"
    const val live_data_test = "1.1.2"
    const val androidx_test_core = "1.2.0"
    const val androidx_test_runner = "1.2.0"
    const val androidx_test_rules = "1.2.0"
    const val androidx_junit = "1.1.1"
    const val androidx_truth = "1.2.0"
    const val fragment_test = "1.1.0-alpha05"
}
