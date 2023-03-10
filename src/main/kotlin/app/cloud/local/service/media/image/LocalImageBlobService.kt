package app.cloud.local.service.media.image

import app.service.blob.media.AbstractMediaBlobService
import app.service.blob.media.image.ImageBlobService
import app.storage.blob.media.image.ImageStorage
import io.micronaut.context.annotation.Primary
import jakarta.inject.Named
import jakarta.inject.Singleton

@Primary
@Singleton
class LocalImageBlobService(@Named("local") imageStorage: ImageStorage): AbstractMediaBlobService(imageStorage), ImageBlobService
