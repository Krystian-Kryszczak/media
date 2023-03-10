package app.cloud.local.blob.media.video

import app.storage.blob.media.video.VideoStorage
import jakarta.inject.Singleton
import app.cloud.Cloud.MediaType.Video
import app.cloud.local.blob.media.LocalMediaStorage
import io.micronaut.context.annotation.Primary

@Primary
@Singleton
class LocalVideoStorage: LocalMediaStorage(Video), VideoStorage
