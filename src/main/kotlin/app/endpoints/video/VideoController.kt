package app.endpoints.video

import app.endpoints.MediaController
import app.service.blob.media.video.VideoBlobService
import io.micronaut.http.annotation.Controller

@Controller("/video")
class VideoController(videoBlobService: VideoBlobService): MediaController(videoBlobService)
