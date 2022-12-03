package com.dev.warehouse.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dev.warehouse.bus.entity.Provider;
import com.dev.warehouse.bus.service.IProviderService;
import com.dev.warehouse.bus.vo.ProviderVo;
import com.dev.warehouse.sys.common.Constast;
import com.dev.warehouse.sys.common.DataGridView;
import com.dev.warehouse.sys.common.ResultObj;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private IProviderService providerService;

    /**
     * 查询所有的供应商
     * @param providerVo
     * @return
     */
    @RequestMapping("loadAllProvider")
    public DataGridView loadAllProvider(ProviderVo providerVo){
        //1.声明一个分页page对象
        @SuppressWarnings({"unchecked","rawtypes"})
        IPage<Provider> page = new Page(providerVo.getPage(),providerVo.getLimit());
        //2.声明一个queryWrapper
        @SuppressWarnings({"unchecked","rawtypes"})
        QueryWrapper<Provider> queryWrapper = new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(providerVo.getProvidername()),"providername",providerVo.getProvidername());
        queryWrapper.like(StringUtils.isNotBlank(providerVo.getConnectionperson()),"connectionperson",providerVo.getConnectionperson());
        queryWrapper.like(StringUtils.isNotBlank(providerVo.getPhone()),"phone",providerVo.getPhone());
        providerService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加一个供应商
     * @param providerVo
     * @return
     */
    @RequestMapping("addProvider")
    public ResultObj addProvider(ProviderVo providerVo){
        try {
            providerService.save(providerVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改一个供应商
     * @param providerVo
     * @return
     */
    @RequestMapping("updateProvider")
    public ResultObj updateProvider(ProviderVo providerVo){
        try {
            providerService.updateById(providerVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除一个供应商
     * @param id 供应商的ID
     * @return
     */
    @RequestMapping("deleteProvider")
    public ResultObj deleteProvider(Integer id){
        try {
            providerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除供应商
     * @param providerVo 选中的供应商
     * @return
     */
    @RequestMapping("batchDeleteProvider")
    public ResultObj batchDeleteProvider(ProviderVo providerVo){
        try {
            Collection<Serializable> idList = new ArrayList<Serializable>();
            for (Integer id : providerVo.getIds()) {
                idList.add(id);
            }
            providerService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载所有可用的供应商
     * @return
     */
    @RequestMapping("loadAllProviderForSelect")
    public DataGridView loadAllProviderForSelect(){
        @SuppressWarnings({"unchecked","rawtypes"})
        QueryWrapper<Provider> queryWrapper = new QueryWrapper();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<Provider> list = providerService.list(queryWrapper);
        return new DataGridView(list);
    }


}

