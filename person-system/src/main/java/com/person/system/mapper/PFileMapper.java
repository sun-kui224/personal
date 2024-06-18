package com.person.system.mapper;

import java.util.List;

import com.person.system.query.FileQuery;
import org.apache.ibatis.annotations.Mapper;
import com.person.common.domain.PFile;
import org.apache.ibatis.annotations.Param;

/**
 * 文件Mapper接口
 *
 * @author sk
 * @date 2024-05-17
 */
@Mapper
public interface PFileMapper
{
    /**
     * 查询文件
     *
     * @param id 主键
     * @return
     */
    public PFile selectPFileById(Long id);

    /**
     * 查询文件列表
     *
     * @param query 文件
     * @return 集合
     */
    public List<PFile> selectPFileList(FileQuery query);

    /**
     * 新增文件
     *
     * @param pFileList
     * @return 结果
     */
    public int insertPFileList(List<PFile> pFileList);

    /**
     * 修改文件名
     *
     * @param id
     * @param name
     * @return 结果
     */
    public int updateFileName(@Param("id") Long id ,@Param("name") String name);

    /**
     * 删除文件
     *
     * @param ids 主键
     * @return 结果
     */
    public int deletePFileByIds(Long[] ids);
}
