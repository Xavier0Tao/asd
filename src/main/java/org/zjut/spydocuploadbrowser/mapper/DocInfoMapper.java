package org.zjut.spydocuploadbrowser.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.zjut.spydocuploadbrowser.entity.DocInfo;

/**
* @author Tao
* @description 针对表【doc_info】的数据库操作Mapper
* @createDate 2022-06-05 18:45:53
* @Entity org.zjut.spydocuploadbrowser.entity.DocInfo
*/
@Mapper
@Repository
public interface DocInfoMapper extends BaseMapper<DocInfo> {


}
