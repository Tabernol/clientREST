package com.example.clientrest.service;

import com.example.clientrest.entity.MediaCollection;
import com.example.clientrest.entity.Schedule;
import com.example.clientrest.entity.TimePeriod;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MediaDownloadservice {
    private final RestTemplate restTemplate;

    public MediaDownloadservice(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void downloadMedia(String fileName) {
        String savePath = "src/main/resources/content/";
        // Define the server endpoint URL
        String serverUrl = "http://localhost:21322/media/download/" + fileName;

        // Define the local file path to save the downloaded file
        String localFilePath = Path.of(savePath, fileName).toString();

        // Perform the GET request to download the media file
        ResponseEntity<Void> responseEntity = restTemplate.execute(
                serverUrl,
                HttpMethod.GET,
                null,
                new MediaFileResponseExtractor(localFilePath)
        );

        // Check the response status if needed
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            log.info("Media file downloaded successfully to: " + localFilePath);
        } else {
            log.info("Failed to download media file. Status code: " + responseEntity.getStatusCode());
        }
    }

    public void downloadAllUniqueFiles(Schedule schedule){
        // obtain all unique file name in schedule
        Set<String> uniqueFileName = schedule.getTimePeriodList()
                .stream()
                .map(TimePeriod::getMediaCollection)
                .flatMap(mediaCollections ->
                        mediaCollections.stream()
                                .map(MediaCollection::getFileName))
                .collect(Collectors.toSet());
        log.info("Downloading all files...");
        uniqueFileName.forEach(this::downloadMedia);
        log.info("Files have saved");
    }


    private record MediaFileResponseExtractor(String savePath) implements ResponseExtractor<ResponseEntity<Void>> {

        @Override
            public ResponseEntity<Void> extractData(ClientHttpResponse response) throws IOException {
                try (InputStream inputStream = response.getBody();
                     FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {

                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fileOutputStream.write(buffer, 0, bytesRead);
                    }
                    fileOutputStream.flush();
                }
                return ResponseEntity.status(response.getRawStatusCode()).build();
            }
        }
}
