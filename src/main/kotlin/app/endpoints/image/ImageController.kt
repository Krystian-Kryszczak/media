package app.endpoints.image

import app.endpoints.MediaController
import app.service.blob.media.image.ImageBlobService
import io.micronaut.http.annotation.Controller

@Controller("/images")
class ImageController(imageBlobService: ImageBlobService): MediaController(imageBlobService)
