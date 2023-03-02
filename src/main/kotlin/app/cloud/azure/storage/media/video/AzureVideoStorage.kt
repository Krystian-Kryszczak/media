package app.cloud.azure.storage.media.video

import app.storage.blob.media.video.VideoStorage
import com.azure.storage.blob.BlobServiceClient
import jakarta.inject.Singleton
import app.cloud.Cloud.MediaType.Video
import app.cloud.azure.storage.AzureStorage

@Singleton
class AzureVideoStorage(blobServiceClient: BlobServiceClient): VideoStorage, AzureStorage(blobServiceClient, Video)
