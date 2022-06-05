package org.zjut.spydocuploadbrowser.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.zjut.spydocuploadbrowser.entity.DocInfo;

import java.util.List;

/**
* @author Tao
* @description 针对表【doc_info】的数据库操作Service
* @createDate 2022-06-05 18:45:53
*/
public interface DocInfoService extends IService<DocInfo> {

    List<DocInfo> getFileInfo(String fileName, String filePath, String fileType, String uuid);

    Boolean delFile(String fileName, String uuid);

    Boolean update(String newFileName, String newFileType, String uuid);
}
