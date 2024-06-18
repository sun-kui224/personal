package com.person.system.service.impl;

import java.util.List;

import com.person.common.utils.DateUtils;
import com.person.common.utils.IdUtils;
import com.person.common.utils.SecurityUtils;
import com.person.system.query.FileQuery;
import com.person.system.service.IPFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.person.system.mapper.PFileMapper;
import com.person.common.domain.PFile;

/**
 * 文件Service业务层处理
 *
 * @author sk
 * @date 2024-05-17
 */
@Service
public class PFileServiceImpl implements IPFileService
{
    @Autowired
    private PFileMapper pFileMapper;

    @Override
    public PFile selectPFileById(Long id)
    {
        return pFileMapper.selectPFileById(id);
    }

    @Override
    public List<PFile> selectPFileList(FileQuery query)
    {
        return pFileMapper.selectPFileList(query);
    }

    @Override
    @Transactional
    public int insertPFileList(List<PFile> pFileList)
    {
        for(PFile pFile: pFileList){
            pFile.setId(IdUtils.fastId());
            pFile.setCreateBy(SecurityUtils.getUsername());
            pFile.setCreateDate(DateUtils.getNowDate());
            pFile.setDelFlag(0);
        }
        return pFileMapper.insertPFileList(pFileList);
    }

    @Override
    @Transactional
    public int updateFileName(Long id , String name)
    {
        return pFileMapper.updateFileName(id,name);
    }

    @Override
    @Transactional
    public int deletePFileByIds(Long[] ids) {
        return pFileMapper.deletePFileByIds(ids);
    }
}
