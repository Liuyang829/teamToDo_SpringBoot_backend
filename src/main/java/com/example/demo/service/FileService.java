package com.example.demo.service;

import com.example.demo.domain.FileLog;

import java.util.List;
import java.util.Map;

public interface FileService {
    FileLog getById(Integer id);
    void addFile(FileLog file);
    void delFile(Integer id);
    void delFromBin(Integer id);
    void recovery(Integer id);
    List<Map> getByProjectId(Integer project_id);
}
