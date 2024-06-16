package com.techevent.events_api.service;

import com.amazonaws.services.s3.AmazonS3;
import com.techevent.events_api.domain.event.Event;
import com.techevent.events_api.domain.event.EventRequestDTO;
import com.techevent.events_api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventService {

    @Value("${aws.bucket.name}")
    private String bucketName;
    private final AmazonS3 s3Client;

    private final EventRepository eventRepository;

    public EventService(AmazonS3 s3Client, EventRepository eventRepository) {
        this.s3Client = s3Client;
        this.eventRepository = eventRepository;
    }

    public Event createEvent(EventRequestDTO data) {
        String imgUrl = null;
        if (data.image() != null) {
            imgUrl = this.uploadImg(data.image());
        }

        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setRemote(data.remote());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImgUrl(imgUrl);

        return eventRepository.save(newEvent);
    }

    private String uploadImg(MultipartFile multipartFile) {
        String fileName = UUID.randomUUID()+"-"+multipartFile.getOriginalFilename();
        try {
            File file = this.convertMultipartTofile(multipartFile);
            s3Client.putObject(bucketName, fileName, file);
            file.delete();
            return s3Client.getUrl(bucketName, fileName).toString();
        } catch (Exception e) {
            System.out.println("Erro ao subir arquivo");
            return null;
        }
    }

    private File convertMultipartTofile(MultipartFile multipartFile) throws IOException {
        File confFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        FileOutputStream outputStream = new FileOutputStream(confFile);
        outputStream.write(multipartFile.getBytes());
        outputStream.close();
        return confFile;
    }
}
