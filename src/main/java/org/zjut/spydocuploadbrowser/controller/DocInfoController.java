package org.zjut.spydocuploadbrowser.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.BooleanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;
import org.zjut.spydocuploadbrowser.dto.DocInfoDto;
import org.zjut.spydocuploadbrowser.dto.Result;
import org.zjut.spydocuploadbrowser.entity.DocInfo;
import org.zjut.spydocuploadbrowser.service.DocInfoService;

import javax.annotation.Resource;
import javax.print.Doc;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/sPyDocUploadBrowser")
public class DocInfoController {

    @Resource
    private DocInfoService docInfoService;

    @GetMapping
    public Result getFileInfo(@RequestParam(name = "fileName", required = false) String fileName,
                              @RequestParam(name = "filePath", required = false) String filePath,
                              @RequestParam(name = "fileType", required = false) String fileType,
                              @RequestParam(name = "uuid", required = false) String uuid) {
        List<DocInfo> fileInfos = docInfoService.getFileInfo(fileName, filePath, fileType, uuid);
        if (fileInfos == null || fileInfos.isEmpty()) return Result.fail("没有文件信息！");
        else return Result.ok(fileInfos);
    }

    @PostMapping
    public Result upload(@RequestBody DocInfoDto docInfoDto) {
        DocInfo docInfo = BeanUtil.copyProperties(docInfoDto, DocInfo.class);
        boolean save = docInfoService.save(docInfo);
        if (BooleanUtil.isTrue(save)) return Result.ok(save);
        else return Result.fail("上传失败!");
    }

    @DeleteMapping
    public Result delFile(@RequestParam(name = "fileName", required = false) String fileName,
                          @RequestParam(name = "uuid") String uuid) {
        Boolean isDel = docInfoService.delFile(fileName, uuid);
        if (BooleanUtil.isTrue(isDel)) return Result.ok();
        else return Result.fail("删除失败！");
    }

    @GetMapping("/lastModified")
    public Result getLastModifiedTime(@RequestParam(name = "uuid") String uuid) {
        Date lastModifiedTime = docInfoService.getOne(new QueryWrapper<DocInfo>().eq("uuid", uuid)).getLastModifiedTime();
        if (lastModifiedTime == null) return Result.fail("查询失败！");
        else return Result.ok(lastModifiedTime);
    }

    @PutMapping
    public Result update(@RequestParam(name = "newFileName", required = false) String newFileName,
                         @RequestParam(name = "newFileType", required = false) String newFileType,
                         @RequestParam(name = "uuid") String uuid) {
        docInfoService.update(newFileName, newFileType, uuid);
        return null;
    }
}

