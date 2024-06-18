package com.person.system.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.person.common.domain.PFileCorr;

/**
 * 文件关联Mapper接口
 *
 * @author sk
 * @date 2024-05-17
 */
@Mapper
public interface PFileCorrMapper
{
    /**
     * 查询文件关联
     *
     * @param fileId 主键
     * @return
     */
    public PFileCorr selectPFileCorrByFileId(Long fileId);

    /**
     * 查询文件关联列表
     *
     * @param pFileCorr 文件关联
     * @return 集合
     */
    public List<PFileCorr> selectPFileCorrList(PFileCorr pFileCorr);

    /**
     * 新增文件关联
     *
     * @param pFileCorr
     * @return 结果
     */
    public int insertPFileCorr(PFileCorr pFileCorr);

    /**
     * 修改文件关联
     *
     * @param pFileCorr
     * @return 结果
     */
    public int updatePFileCorr(PFileCorr pFileCorr);

    /**
     * 删除文件关联
     *
     * @param fileId 主键
     * @return 结果
     */
    public int deletePFileCorrByFileId(Long fileId);
}
