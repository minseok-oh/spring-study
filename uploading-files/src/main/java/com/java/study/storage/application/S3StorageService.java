package com.java.study.storage.application;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.java.study.storage.StorageService;
import com.java.study.storage.exception.StorageException;
import com.java.study.storage.exception.StorageFileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Profile("s3")
public class S3StorageService implements StorageService {

    private final AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String BUCKET_NAME;

    @Autowired
    public S3StorageService(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    @Override
    public void init() {

    }

    @Override
    public void store(MultipartFile file) {
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(file.getContentType());
        objectMetadata.setContentLength(file.getSize());

        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    BUCKET_NAME,
                    file.getOriginalFilename(),
                    file.getInputStream(),
                    objectMetadata
            );
            amazonS3.putObject(putObjectRequest);
        } catch (IOException e) {
            throw new StorageException("파일을 저장할 수 없습니다.", e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return amazonS3.listObjects(BUCKET_NAME).getObjectSummaries().stream()
                .map(summary -> Paths.get(summary.getKey()));
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        try {
            S3Object s3Object = amazonS3.getObject(new GetObjectRequest(BUCKET_NAME, filename));

            if (s3Object == null) {
                throw new StorageFileNotFoundException("해당 파일이 존재하지 않습니다.");
            }

            return new InputStreamResource(s3Object.getObjectContent());
        } catch (Exception e) {
            throw new StorageFileNotFoundException("해당 파일이 존재하지 않습니다.", e);
        }
    }

    @Override
    public void deleteAll() {
        ObjectListing objectListing = amazonS3.listObjects(BUCKET_NAME);
        for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {
            amazonS3.deleteObject(BUCKET_NAME, summary.getKey());
        }
    }
}
