package com.person.system.mapper;

import java.util.List;

import com.person.common.domain.PDictData;
import com.person.system.query.PDictDataQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典数据Mapper接口
 *
 * @author sk
 * @date 2024-06-04
 */
@Mapper
public interface PDictDataMapper
{
    /**
     * 查询字典数据
     *
     * @param id 主键
     * @return
     */
    public PDictData selectPDictDataById(Long id);

    /**
     * 校验排序号
     * @param pDictData
     * @return
     */
    public PDictData selectPDictDataByDictSort(PDictData pDictData);

    /**
     * 校验字典键值
     * @param pDictData
     * @return
     */
    public PDictData selectPDictDataByDictValue(PDictData pDictData);

    /**
     * 校验标签
     * @param pDictData
     * @return
     */
    public PDictData selectPDictDataByDictLabel(PDictData pDictData);

    /**
     * 根据字典id查询字典数据数量
     * @param id
     * @return
     */
    public int selectPDictDataListByTypeId(Long id);

    /**
     * 查询字典数据列表
     *
     * @param query 字典数据
     * @return 集合
     */
    public List<PDictData> selectPDictDataList(PDictDataQuery query);

    /**
     * 新增字典数据
     *
     * @param pDictData
     * @return 结果
     */
    public int insertPDictData(PDictData pDictData);

    /**
     * 修改字典数据
     *
     * @param pDictData
     * @return 结果
     */
    public int updatePDictData(PDictData pDictData);

    /**
     * 删除字典数据
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePDictDataById(Long id);

    /**
     * 停用字典数据
     *
     * @param id 主键
     * @return 结果
     */
    public int stopPDictDataById(Long id);

    /**
     * 启用字典数据
     *
     * @param id 主键
     * @return 结果
     */
    public int startPDictDataById(Long id);
}
