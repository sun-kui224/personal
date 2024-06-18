package com.person.system.service;

import java.util.List;
import com.person.common.domain.PFile;
import com.person.system.query.FileQuery;

/**
 * 文件Service接口
 *
 * @author sk
 * @date 2024-05-17
 */
public interface IPFileService
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
     * @param query
     * @return
     */
    public List<PFile> selectPFileList(FileQuery query);

    /**
     * 新增文件集合
     *
     * @param pFileList
     * @return
     */
    public int insertPFileList(List<PFile> pFileList);

    /**
     * 修改文件名
     *
     * @param id
     * @param name
     * @return
     */
    public int updateFileName(Long id , String name);

    /**
     * 删除文件信息
     *
     * @param ids 主键
     * @return 结果
     */
    public int deletePFileByIds(Long[] ids);
}
