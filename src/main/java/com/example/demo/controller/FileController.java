package com.example.demo.controller;


import com.example.demo.domain.FileLog;
import com.example.demo.domain.Project_User;
import com.example.demo.domain.User;
import com.example.demo.service.FileService;
import com.example.demo.service.ProjectService;
import com.example.demo.utils.Result;
import com.example.demo.utils.ResultFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/files")
@CrossOrigin
public class FileController {
    @Autowired
    ProjectService projectService;

    @Autowired
    FileService fileService;

    @RequestMapping(method = RequestMethod.POST)
    public Result UploadFile(MultipartHttpServletRequest multiReq,Integer project_id) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project_User project_user = new Project_User(user.getId(), project_id);
        String role = projectService.getRelation(project_user);
        if(role!=null){
            // 获取上传文件的路径
            String uploadFilePath = multiReq.getFile("file").getOriginalFilename();
            System.out.println("uploadFlePath:" + uploadFilePath);
            // 截取上传文件的文件名
            String uploadFileName = uploadFilePath.substring(
                    uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
            System.out.println("multiReq.getFile()" + uploadFileName);
            // 截取上传文件的后缀
            String uploadFileSuffix = uploadFilePath.substring(
                    uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
            System.out.println("uploadFileSuffix:" + uploadFileSuffix);
            FileOutputStream fos = null;
            FileInputStream fis = null;

            DateFormat df = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
            String current = df.format(System.currentTimeMillis());
            String outFilename=user.getId()+"_"+current;


            try {
                fis = (FileInputStream) multiReq.getFile("file").getInputStream();
                fos = new FileOutputStream(new File(  "uploadFiles/"+outFilename
                        + ".")
                        + uploadFileSuffix);
                byte[] temp = new byte[1024];
                int i = fis.read(temp);
                while (i != -1){
                    fos.write(temp,0,temp.length);
                    fos.flush();
                    i = fis.read(temp);
                }
                FileLog file=new FileLog(project_id,uploadFileName+"."+uploadFileSuffix,"uploadFiles/"+outFilename+"."+uploadFileSuffix,user.getId(),new java.sql.Date(System.currentTimeMillis()));
                fileService.addFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return ResultFactory.buildSuccessResult(uploadFileName+uploadFileSuffix);
        }
        return ResultFactory.buildFailResult("无法操作");
    }

    @RequestMapping(method = RequestMethod.GET)
    public Result getFileList(Integer project_id) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project_User project_user = new Project_User(user.getId(), project_id);
        String role = projectService.getRelation(project_user);
        if(role!=null) {
            List<Map> fileList=fileService.getByProjectId(project_id);
            return ResultFactory.buildSuccessResult(fileList);
        }
        return ResultFactory.buildFailResult("无法操作");
    }

    @RequestMapping(method =RequestMethod.DELETE)
    public Result delFile(Integer file_id,Integer project_id){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project_User project_user = new Project_User(user.getId(), project_id);
        String role = projectService.getRelation(project_user);
        if(role!=null&&role.equals("creator")) {
            fileService.delFile(file_id);
            return ResultFactory.buildSuccessResult(null);
        }
        return ResultFactory.buildFailResult("无法操作");
    }

    @RequestMapping(value = "/bin",method =RequestMethod.DELETE)
    public Result delFromBin(Integer file_id,Integer project_id){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project_User project_user = new Project_User(user.getId(), project_id);
        String role = projectService.getRelation(project_user);
        if(role!=null&&role.equals("creator")) {
            fileService.delFromBin(file_id);
            return ResultFactory.buildSuccessResult(null);
        }
        return ResultFactory.buildFailResult("无法操作");
    }

    @RequestMapping(value = "/bin",method =RequestMethod.POST)
    public Result recovery(Integer file_id,Integer project_id){
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project_User project_user = new Project_User(user.getId(), project_id);
        String role = projectService.getRelation(project_user);
        if(role!=null&&role.equals("creator")) {
            fileService.recovery(file_id);
            return ResultFactory.buildSuccessResult(null);
        }
        return ResultFactory.buildFailResult("无法操作");
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void testDownload(HttpServletResponse res,Integer file_id,Integer project_id) {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getPrincipal();

        Project_User project_user = new Project_User(user.getId(), project_id);
        String role = projectService.getRelation(project_user);
        if(role!=null) {
            FileLog fileLog=fileService.getById(file_id);

            String fileName = fileLog.getOriginal_name();
            String filePath = fileLog.getPath();
            res.setHeader("content-type", "application/octet-stream");
            res.setContentType("application/octet-stream");
            res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            byte[] buff = new byte[1024];
            BufferedInputStream bis = null;
            OutputStream os = null;
            try {
                os = res.getOutputStream();
                bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
                int i = bis.read(buff);
                while (i != -1) {
                    os.write(buff, 0, buff.length);
                    os.flush();
                    i = bis.read(buff);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}




