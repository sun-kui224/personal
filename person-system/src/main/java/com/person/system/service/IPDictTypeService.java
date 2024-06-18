package com.person.system.service;

import com.person.common.base.domain.AjaxResult;
import com.person.common.domain.PDictType;
import com.person.system.query.DictTypeQuery;

import java.util.List;

/**
 * 字典类型Service接口
 *
 * @author sk
 * @date 2024-06-04
 */
public interface IPDictTypeService
{
    /**
     * 查询字典类型
     *
     * @param id 主键
     * @return
     */
    public PDictType selectPDictTypeById(Long id);

    /**
     * 查询字典类型列表
     *
     * @param query
     * @return
     */
    public List<PDictType> selectPDictTypeList(DictTypeQuery query);

    /**
     * 新增字典类型
     *
     * @param pDictType
     * @return
     */
    public AjaxResult insertPDictType(PDictType pDictType);

    /**
     * 修改字典类型
     *
     * @param pDictType
     * @return
     */
    public AjaxResult updatePDictType(PDictType pDictType);

    /**
     * 删除字典类型信息
     *
     * @param id 主键
     * @return 结果
     */
    public AjaxResult deletePDictTypeById(Long id);

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
