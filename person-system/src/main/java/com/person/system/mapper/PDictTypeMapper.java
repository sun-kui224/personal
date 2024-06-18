package com.person.system.mapper;

import java.util.List;

import com.person.common.domain.PDictType;
import com.person.system.query.DictTypeQuery;
import org.apache.ibatis.annotations.Mapper;

/**
 * 字典类型Mapper接口
 *
 * @author sk
 * @date 2024-06-04
 */
@Mapper
public interface PDictTypeMapper
{
    /**
     * 查询字典类型
     *
     * @param id 主键
     * @return
     */
    public PDictType selectPDictTypeById(Long id);

    /**
     * 根据字典类型查询字典
     * @param pDictType
     * @return
     */
    public PDictType selectPDictTypeByDictType(PDictType pDictType);

    /**
     * 查询字典类型列表
     *
     * @param query 字典类型
     * @return 集合
     */
    public List<PDictType> selectPDictTypeList(DictTypeQuery query);

    /**
     * 新增字典类型
     *
     * @param pDictType
     * @return 结果
     */
    public int insertPDictType(PDictType pDictType);

    /**
     * 修改字典类型
     *
     * @param pDictType
     * @return 结果
     */
    public int updatePDictType(PDictType pDictType);

    /**
     * 删除字典类型
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePDictTypeById(Long id);

    /**
     * 停用字典
     * @param id
     * @return
     */
    public int stopPDictTypeById(Long id);

    /**
     * 启用字典
     * @param id
     * @return
     */
    public int startPDictTypeById(Long id);
}
