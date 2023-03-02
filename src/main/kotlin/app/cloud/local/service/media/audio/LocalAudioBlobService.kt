package app.cloud.local.service.media.audio

import app.service.blob.media.AbstractMediaBlobService
import app.service.blob.media.audio.AudioBlobService
import app.storage.blob.media.audio.AudioStorage
import io.micronaut.context.annotation.Primary
import jakarta.inject.Named
import jakarta.inject.Singleton

@Primary
@Singleton
class LocalAudioBlobService(@Named("local") audioStorage: AudioStorage): AbstractMediaBlobService(audioStorage), AudioBlobService
