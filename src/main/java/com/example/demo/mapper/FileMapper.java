package com.example.demo.mapper;

import com.example.demo.domain.FileLog;

import java.util.List;
import java.util.Map;

public interface FileMapper {
    FileLog getById(Integer id);
    void addFile(FileLog file);
    void delFile(FileLog file);
    void delFromBin(Integer id);
    void recovery(Integer id);
    List<Map> getByProjectId(Integer project_id);
}
