package app.service.blob.azure

import app.service.blob.BlobServiceTest
import app.service.blob.media.audio.AudioBlobService
import app.service.blob.media.image.ImageBlobService
import app.service.blob.media.video.VideoBlobService
import io.kotest.core.spec.style.StringSpec
import io.micronaut.test.extensions.kotest.annotation.MicronautTest
import jakarta.inject.Named
import java.util.*

@MicronautTest
class AzureMediaServiceTest(
    @Named("azure") private val videoBlobService: VideoBlobService,
    @Named("azure") private val imageBlobService: ImageBlobService,
    @Named("azure") private val audioBlobService: AudioBlobService
) : StringSpec({

    "test `save and download by id` (video)" {
        assert(BlobServiceTest.testVideoBlobServiceSaveAndDownload(videoBlobService))
    }
    "test `save and download by id` (image)" {
        assert(BlobServiceTest.testImageBlobServiceSaveAndDownload(imageBlobService))
    }
    "test `save and download by id` (audio)" {
        assert(BlobServiceTest.testAudioBlobServiceSaveAndDownload(audioBlobService))
    }

    "test `save and delete by id` (video)" {
        assert(BlobServiceTest.testVideoBlobServiceSaveAndDeleteById(videoBlobService))
    }
    "test `save and delete by id` (image)" {
        assert(BlobServiceTest.testImageBlobServiceSaveAndDeleteById(imageBlobService))
    }
    "test `save and delete by id` (audio)" {
        assert(BlobServiceTest.testAudioBlobServiceSaveAndDeleteById(audioBlobService))
    }
})
