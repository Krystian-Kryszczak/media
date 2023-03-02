package app.cloud.local.service.media.video

import app.service.blob.media.AbstractMediaBlobService
import app.service.blob.media.video.VideoBlobService
import app.storage.blob.media.video.VideoStorage
import io.micronaut.context.annotation.Primary
import jakarta.inject.Named
import jakarta.inject.Singleton

@Primary
@Singleton
class LocalVideoBlobService(@Named("local") videoStorage: VideoStorage): AbstractMediaBlobService(videoStorage), VideoBlobService
