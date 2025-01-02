package com.java.study.storage.infra;

import com.java.study.storage.domain.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface H2Repository extends JpaRepository<File, Long> {

    File findFileByName(String name);
}
