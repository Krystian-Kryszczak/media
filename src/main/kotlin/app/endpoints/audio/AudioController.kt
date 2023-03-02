package app.endpoints.audio

import app.endpoints.MediaController
import app.service.blob.media.audio.AudioBlobService
import io.micronaut.http.annotation.Controller

@Controller("/audio")
class AudioController(audioBlobService: AudioBlobService): MediaController(audioBlobService)
