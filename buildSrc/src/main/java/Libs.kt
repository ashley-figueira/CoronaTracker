object Libs {
    const val gradle_plugin = "com.android.tools.build:gradle:${Versions.gradle_plugin}"
    const val kotlin_plugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    //support
    const val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    const val androidx_core = "androidx.core:core-ktx:${Versions.androidx_core}"
    const val androidx_activity = "androidx.activity:activity-ktx:${Versions.androidx_activity}"
    const val androidx_fragment = "androidx.fragment:fragment-ktx:${Versions.androidx_fragment}"
    const val androidx_card_view = "androidx.cardview:cardview:${Versions.androidx_cardview}"
    const val androidx_constraint_layout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraint_layout}"
    const val androidx_multidex = "androidx.multidex:multidex:${Versions.androidx_multidex}"
    const val material_design = "com.google.android.material:material:${Versions.material_design}"
    const val androidx_browser = "androidx.browser:browser:${Versions.androidx_browser}"

    //Architecture components
    const val androidx_lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidx_lifecycle}"
    const val androidx_liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidx_livedata}"
    const val androidx_viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidx_viewmodel}"
    const val androidx_reactive_streams = "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.androidx_reactive_streams}"
    const val androidx_java8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.androidx_lifecycle}"
    const val androidx_arch_testing =  "androidx.arch.core:core-testing:2.1.0"

    //Coroutines
    const val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

    //Google
    const val google_services = "com.google.gms:google-services:${Versions.google_services}"
    const val firebase_core = "com.google.firebase:firebase-core:${Versions.firebase_core}"
    const val firebase_config = "com.google.firebase:firebase-config:${Versions.firebase_remote_config}"
    const val firebase_messaging = "com.google.firebase:firebase-messaging:${Versions.firebase_messaging}"
    const val firebase_firestore = "com.google.firebase:firebase-firestore:${Versions.firebase_firestore}"
    const val firebase_storage = "com.google.firebase:firebase-storage:${Versions.firebase_storage}"
    const val firebase_storage_ui = "com.firebaseui:firebase-ui-storage:${Versions.firebase_storage_ui}"
    const val firebase_analytics = "com.google.firebase:firebase-analytics:${Versions.firebase_analytics}"

    //Room
    const val androidx_room_ktx = "androidx.room:room-ktx:${Versions.androidx_room}"
    const val androidx_room_runtime = "androidx.room:room-runtime:${Versions.androidx_room}"
    const val androidx_room_compiler ="androidx.room:room-compiler:${Versions.androidx_room}"
    const val androidx_room_rxjava = "androidx.room:room-rxjava2:${Versions.androidx_room}"
    const val androidx_room_testing =  "androidx.room:room-testing:${Versions.androidx_room}"

    //Navigation
    const val androidx_nav_runtime = "androidx.navigation:navigation-runtime-ktx:${Versions.androidx_navigation}"
    const val androidx_nav_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.androidx_navigation}"
    const val androidx_nav_ui = "androidx.navigation:navigation-ui-ktx:${Versions.androidx_navigation}"
    const val androidx_nav_safe_args_plugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidx_navigation}"

    //retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_rxjava_adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val retrofit_gson_coverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    //okhttp
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttp_logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    //rxjava
    const val rxjava = "io.reactivex.rxjava2:rxjava:${Versions.rxjava}"
    const val rxandroid = "io.reactivex.rxjava2:rxandroid:${Versions.rxandroid}"
    const val rxrelay = "com.jakewharton.rxrelay2:rxrelay:2.1.0"
    const val rxSharedPrefernces = "com.f2prateek.rx.preferences2:rx-preferences:2.0.0"

    //dagger
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val dagger_support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    const val dagger_processor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    const val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    //Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val glide_okhttp_integration = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide}@aar"

    //others
    const val joda = "joda-time:joda-time:${Versions.joda}"
    const val gson = "com.google.code.gson:gson:${Versions.gson}"

    const val javaxAnnotation = "javax.annotation:javax.annotation-api:1.2"
    const val javaxInject = "javax.inject:javax.inject:1"

    const val groupie = "com.xwray:groupie:${Versions.groupie}"
    const val groupie_extensions = "com.xwray:groupie-kotlin-android-extensions:${Versions.groupie}"

    // Debugging
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
    const val stetho_okhttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"

    // TEST
    const val junit = "junit:junit:${Versions.junit}"

    const val kotlin_test_runtime = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
    const val kotlin_test_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val kotlin_test_annotations = "org.jetbrains.kotlin:kotlin-test-annotations-common:${Versions.kotlin}"
    const val kotlin_test_junit = "org.jetbrains.kotlin:kotlin-test-junit:${Versions.kotlin}"

    const val mock_webserver = "com.squareup.okhttp3:mockwebserver:${Versions.okhttp}"

    const val mockito = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockito_android = "org.mockito:mockito-android:${Versions.mockito}"
    const val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito}"
    const val mockito_kotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockito_kotlin}"

    //Androidx test helpers
    const val androidx_test_runner = "androidx.test:runner:${Versions.androidx_test_runner}"
    const val androidx_test_rules = "androidx.test:rules:${Versions.androidx_test_rules}"
    const val androidx_test_core = "androidx.test:core:${Versions.androidx_test_core}"
    const val androidx_junit = "androidx.test.ext:junit:${Versions.androidx_junit}"
    const val androidx_truth = "androidx.test.ext:truth:${Versions.androidx_truth}"
    const val live_data_test = "com.jraska.livedata:testing-ktx:${Versions.live_data_test}"

    const val androidx_fragment_testing = "androidx.fragment:fragment-testing:${Versions.fragment_test}"

    const val support_test_runner = "androidx.test.ext:junit:1.0.0"
    const val support_test_rules = "androidx.test:rules:${Versions.support_test_runner}"

    //Espresso
    const val androidx_espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val androidx_espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
    const val androidx_espresso_intent = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
    const val androidx_espresso_accessibility = "androidx.test.espresso:espresso-accessibility:${Versions.espresso}"
    const val androidx_espresso_web = "androidx.test.espresso:espresso-web:${Versions.espresso}"
    const val androidx_espresso_idling = "androidx.test.espresso.idling:idling-concurrent:${Versions.espresso}"

}
