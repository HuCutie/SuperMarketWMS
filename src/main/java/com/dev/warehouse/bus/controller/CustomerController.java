package com.dev.warehouse.bus.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dev.warehouse.bus.entity.Customer;
import com.dev.warehouse.bus.service.ICustomerService;
import com.dev.warehouse.bus.vo.CustomerVo;
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
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService customerService;

    /**
     * 查询所有的客户
     * @param customerVo
     * @return
     */
    @RequestMapping("loadAllCustomer")
    public DataGridView loadAllCustomer(CustomerVo customerVo){
        //1.声明一个分页page对象
        @SuppressWarnings({"unchecked","rawtypes"})
        IPage<Customer> page = new Page(customerVo.getPage(),customerVo.getLimit());
        //2.声明一个queryWrapper
        @SuppressWarnings({"unchecked","rawtypes"})
        QueryWrapper<Customer> queryWrapper = new QueryWrapper();
        queryWrapper.like(StringUtils.isNotBlank(customerVo.getCustomername()),"customername",customerVo.getCustomername());
        queryWrapper.like(StringUtils.isNotBlank(customerVo.getConnectionpersion()),"connectionpersion",customerVo.getConnectionpersion());
        queryWrapper.like(StringUtils.isNotBlank(customerVo.getPhone()),"phone",customerVo.getPhone());
        customerService.page(page,queryWrapper);
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * 添加一个客户
     * @param customerVo
     * @return
     */
    @RequestMapping("addCustomer")
    public ResultObj addCustomer(CustomerVo customerVo){
        try {
            customerService.save(customerVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * 修改一个客户
     * @param customerVo
     * @return
     */
    @RequestMapping("updateCustomer")
    public ResultObj updateCustomer(CustomerVo customerVo){
        try {
            customerService.updateById(customerVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * 删除一个客户
     * @param id 客户的ID
     * @return
     */
    @RequestMapping("deleteCustomer")
    public ResultObj deleteCustomer(Integer id){
        try {
            customerService.removeById(id);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 批量删除客户
     * @param customerVo 选中的客户
     * @return
     */
    @RequestMapping("batchDeleteCustomer")
    public ResultObj batchDeleteCustomer(CustomerVo customerVo){
        try {
            Collection<Serializable> idList = new ArrayList<Serializable>();
            for (Integer id : customerVo.getIds()) {
                idList.add(id);
            }
            customerService.removeByIds(idList);
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }

    /**
     * 加载所有客户的下拉列表
     * @return
     */
    @RequestMapping("loadAllCustomerForSelect")
    public DataGridView loadAllCustomerForSelect(){
        QueryWrapper<Customer> queryWrapper = new QueryWrapper<Customer>();
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        List<Customer> list = customerService.list(queryWrapper);
        return new DataGridView(list);
    }

}

