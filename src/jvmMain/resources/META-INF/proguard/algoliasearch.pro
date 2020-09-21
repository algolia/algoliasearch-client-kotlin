# Serializable models
-keep,includedescriptorclasses class com.algolia.search.model.**$$serializer { *; }
-keepclassmembers class com.algolia.search.model.** {
    *** Companion;
}
-keepclasseswithmembers class com.algolia.search.model.** {
    kotlinx.serialization.KSerializer serializer(...);
}
