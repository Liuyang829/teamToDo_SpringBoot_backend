package com.example.demo.service;

import com.example.demo.domain.FileLog;
import com.example.demo.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Service
public class FileSeviceImpl implements FileService {
    @Autowired
    FileMapper fileMapper;

    @Override
    public FileLog getById(Integer id) {
        return fileMapper.getById(id);
    }

    @Override
    public void addFile(FileLog file) {
        fileMapper.addFile(file);
    }

    @Override
    public void delFile(Integer id) {
        FileLog file=new FileLog(id,new java.sql.Date(System.currentTimeMillis()));
        fileMapper.delFile(file);
    }

    @Override
    public List<Map> getByProjectId(Integer project_id) {
        return fileMapper.getByProjectId(project_id);
    }

    @Override
    public void delFromBin(Integer id) {
        fileMapper.delFromBin(id);
    }

    @Override
    public void recovery(Integer id) {
        fileMapper.recovery(id);
    }
}
