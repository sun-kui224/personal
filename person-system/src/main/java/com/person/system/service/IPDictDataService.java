package com.person.system.service;

import com.person.common.base.domain.AjaxResult;
import com.person.common.domain.PDictData;
import com.person.system.query.PDictDataQuery;

import java.util.List;

/**
 * 字典数据Service接口
 *
 * @author sk
 * @date 2024-06-04
 */
public interface IPDictDataService
{
    /**
     * 查询字典数据
     *
     * @param id 主键
     * @return
     */
    public PDictData selectPDictDataById(Long id);

    /**
     * 查询字典数据列表
     *
     * @param query
     * @return
     */
    public List<PDictData> selectPDictDataList(PDictDataQuery query);

    /**
     * 新增字典数据
     *
     * @param pDictData
     * @return
     */
    public AjaxResult insertPDictData(PDictData pDictData);

    /**
     * 修改字典数据
     *
     * @param pDictData
     * @return
     */
    public AjaxResult updatePDictData(PDictData pDictData);

    /**
     * 删除字典数据信息
     *
     * @param id 主键
     * @return 结果
     */
    public int deletePDictDataById(Long id);

    /**
     * 停用字典数据
     * @param id
     * @return
     */
    public int stopPDictDataById(Long id);

    /**
     * 删除字典数据
     * @param id
     * @return
     */
    public int startPDictDataById(Long id);
}
