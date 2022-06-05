package org.zjut.spydocuploadbrowser.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.zjut.spydocuploadbrowser.entity.DocInfo;
import org.zjut.spydocuploadbrowser.mapper.DocInfoMapper;
import org.zjut.spydocuploadbrowser.service.DocInfoService;

import javax.annotation.Resource;
import java.util.List;

/**
* @author Tao
* @description 针对表【doc_info】的数据库操作Service实现
* @createDate 2022-06-05 18:45:53
*/
@Service
public class DocInfoServiceImpl extends ServiceImpl<DocInfoMapper, DocInfo>
implements DocInfoService{

    @Resource
    private DocInfoMapper docInfoMapper;

    @Override
    public List<DocInfo> getFileInfo(String fileName, String filePath, String fileType, String uuid) {
        QueryWrapper<DocInfo> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(uuid)) wrapper.eq("uuid", uuid);
        if (!StringUtils.isEmpty(fileType)) wrapper.eq("file_type", fileType);
        if (!StringUtils.isEmpty(fileName)) extract(wrapper, "file_name", fileName);
        if (!StringUtils.isEmpty(filePath)) extract(wrapper, "file_path", filePath);

        return list(wrapper);
    }

    @Override
    public Boolean delFile(String fileName, String uuid) {
        QueryWrapper<DocInfo> wrapper = new QueryWrapper<>();

        if (!StringUtils.isEmpty(fileName)) wrapper.eq("file_name", fileName);
        if (StringUtils.isEmpty(uuid)) return false;

        wrapper.eq("uuid", uuid);

        return remove(wrapper);
    }

    @Override
    public Boolean update(String newFileName, String newFileType, String uuid) {
        DocInfo docInfo = new DocInfo();
        if (!StringUtils.isEmpty(newFileName)) docInfo.setFileName(newFileName);
        if (!StringUtils.isEmpty(newFileType)) docInfo.setFileType(newFileType);
        docInfo.setUuid(uuid);

        int update = docInfoMapper.update(docInfo, null);
        return update > 0;
    }

    public void extract(QueryWrapper<DocInfo> wrapper, String column, String value) {
        Boolean isLeft = '*' == value.charAt(0);
        Boolean isRight = '*' == value.charAt(value.length() - 1);

        //删去通配符
        if (isLeft) value = value.substring(1);
        if (isRight) value = value.substring(0, value.length() - 2);

        //设置统配路径
        if (isLeft && isRight) wrapper.like(column, value);
        else if (isLeft) wrapper.likeLeft(column, value);
        else if (isRight) wrapper.likeRight(column, value);
        else wrapper.eq(column, value);
    }

}
