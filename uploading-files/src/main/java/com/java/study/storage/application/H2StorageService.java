package com.java.study.storage.application;

import com.java.study.storage.StorageService;
import com.java.study.storage.domain.File;
import com.java.study.storage.exception.StorageException;
import com.java.study.storage.exception.StorageFileNotFoundException;
import com.java.study.storage.infra.H2Repository;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;
import org.apache.catalina.webresources.FileResource;
import org.apache.naming.factory.ResourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Profile("h2")
public class H2StorageService implements StorageService {

    private final H2Repository h2Repository;

    @Autowired
    public H2StorageService(H2Repository h2Repository) {
        this.h2Repository = h2Repository;
    }

    @Override
    public void init() {

    }

    @Override
    public void store(MultipartFile file) {
        try {
            h2Repository.save(new File(
                    null,
                    file.getOriginalFilename(),
                    file.getResource().getContentAsByteArray()
            ));
        } catch (IOException e) {
            throw new StorageException("파일 저장에 실패했습니다.");
        }
    }

    @Override
    public Stream<Path> loadAll() {
        return h2Repository.findAll().stream()
                .map(file -> Paths.get(file.getName()));
    }

    @Override
    public Path load(String filename) {
        return null;
    }

    @Override
    public Resource loadAsResource(String filename) {
        File file = h2Repository.findFileByName(filename);

        if (file == null) {
            throw new StorageFileNotFoundException("존재하지 않는 파일입니다.");
        }

        return new ByteArrayResource(file.getContent());
    }

    @Override
    public void deleteAll() {
        h2Repository.deleteAll();
    }
}
