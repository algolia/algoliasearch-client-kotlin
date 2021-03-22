-keepattributes *Annotation*, InnerClasses
-dontnote kotlinx.serialization.AnnotationsKt
-keepclassmembers class kotlinx.serialization.json.** {
    *** Companion;
}
-keepclasseswithmembers class kotlinx.serialization.json.** {
    kotlinx.serialization.KSerializer serializer(...);
}
# Serializable models
-keep,includedescriptorclasses class com.algolia.search.model.**$$serializer { *; }
-keepclassmembers class com.algolia.search.model.** {
    *** Companion;
}
-keepclasseswithmembers class com.algolia.search.model.** {
    kotlinx.serialization.KSerializer serializer(...);
}
