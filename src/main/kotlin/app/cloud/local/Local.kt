package app.cloud.local

import app.cloud.Cloud
import io.micronaut.context.annotation.Primary
import jakarta.inject.Singleton

@Primary
@Singleton
class Local(override val name: String = "Local") : Cloud
