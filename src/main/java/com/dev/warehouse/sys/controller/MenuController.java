package com.dev.warehouse.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dev.warehouse.sys.common.*;
import com.dev.warehouse.sys.entity.Permission;
import com.dev.warehouse.sys.entity.User;
import com.dev.warehouse.sys.service.IPermissionService;
import com.dev.warehouse.sys.service.IRoleService;
import com.dev.warehouse.sys.service.IUserService;
import com.dev.warehouse.sys.vo.PermissionVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private IPermissionService permissionService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping("loadIndexLeftMenuJson")
    public DataGridView loadIndexLeftMenuJson(PermissionVo permissionVo){
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<Permission>();
        queryWrapper.eq("type", Constast.TYPE_MENU);
        queryWrapper.eq("available", Constast.AVAILABLE_TRUE);
        User user = (User) WebUtils.getSession().getAttribute("user");
        List<Permission> list = null;
        if (user.getType().equals(Constast.USER_TYPE_SUPER)){
            list = permissionService.list(queryWrapper);
        }else {
            Integer userId = user.getId();
            List<Integer> currentUserRoleIds = roleService.queryUserRoleIdsByUid(userId);
            Set<Integer> pids = new HashSet<>();
            for (Integer rid : currentUserRoleIds) {
                List<Integer> permissionIds = roleService.queryRolePermissionIdsByRid(rid);
                pids.addAll(permissionIds);
            }
            if (pids.size()>0){
                queryWrapper.in("id",pids);
                list = permissionService.list(queryWrapper);
            }else {
                list=new ArrayList<>();
            }

        }
        List<TreeNode> treeNodes = new ArrayList<TreeNode>();
        for (Permission p : list) {
            Integer id =p.getId();
            Integer pid = p.getPid();
            String title = p.getTitle();
            String icon = p.getIcon();
            String href = p.getHref();
            Boolean spread = p.getOpen().equals(Constast.OPEN_TRUE)?true:false;
            treeNodes.add(new TreeNode(id,pid,title,icon,href,spread));
        }

        //??????????????????
        List<TreeNode> list2 = TreeNodeBuilder.build(treeNodes,1);
        return new DataGridView(list2);

    }
    
    
    /************************????????????*********************************/

    /**
     * ??????????????????????????????
     * @param permissionVo
     * @return
     */
    @RequestMapping("loadMenuManagerLeftTreeJson")
    public DataGridView loadMenuManagerLeftTreeJson(PermissionVo permissionVo){

        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type",Constast.TYPE_MENU);
        //????????????????????????????????????list???
        List<Permission> list = permissionService.list(queryWrapper);
        List<TreeNode> treeNodes = new ArrayList<>();
        //???????????????treeNodes???????????????json
        for (Permission menu : list) {
            Boolean open = menu.getOpen()==1?true:false;
            treeNodes.add(new TreeNode(menu.getId(),menu.getPid(),menu.getTitle(),open));
        }
        return new DataGridView(treeNodes);
    }

    /**
     * ????????????????????????
     * @param permissionVo
     * @return
     */
    @RequestMapping("loadAllMenu")
    public DataGridView loadAllMenu(PermissionVo permissionVo){
        IPage<Permission> page = new Page<>(permissionVo.getPage(),permissionVo.getLimit());
        //??????????????????
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(permissionVo.getId()!=null,"id",permissionVo.getId()).or().eq(permissionVo.getId()!=null,"pid",permissionVo.getId());
        //??????????????????
        queryWrapper.eq("type",Constast.TYPE_MENU);
        queryWrapper.like(StringUtils.isNotBlank(permissionVo.getTitle()),"title",permissionVo.getTitle());
        queryWrapper.orderByAsc("ordernum");
        //????????????
        permissionService.page(page,queryWrapper);
        //??????DataGridView
        return new DataGridView(page.getTotal(),page.getRecords());
    }

    /**
     * ????????????
     * @param permissionVo
     * @return
     */
    @RequestMapping("addMenu")
    public ResultObj addMenu(PermissionVo permissionVo){
        try {
            //????????????????????? menu
            permissionVo.setType(Constast.TYPE_MENU);
            permissionService.save(permissionVo);
            return ResultObj.ADD_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.ADD_ERROR;
        }
    }

    /**
     * ???????????????
     * @return
     */
    @RequestMapping("loadMenuMaxOrderNum")
    public Map<String,Object> loadMenuMaxOrderNum(){
        Map<String,Object> map = new HashMap<String,Object>();
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("ordernum");
        IPage<Permission> page = new Page<>(1,1);
        List<Permission> list = permissionService.page(page,queryWrapper).getRecords();
        if (list.size()>0){
            map.put("value",list.get(0).getOrdernum()+1);
        }else {
            map.put("value",1);
        }
        return map;
    }

    /**
     * ????????????
     * @param permissionVo
     * @return
     */
    @RequestMapping("updateMenu")
    public ResultObj updateMenu(PermissionVo permissionVo){
        try {
            permissionService.updateById(permissionVo);
            return ResultObj.UPDATE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.UPDATE_ERROR;
        }
    }

    /**
     * ????????????????????????????????????
     * @param permissionVo
     * @return
     */
    @RequestMapping("checkMenuHasChildrenNode")
    public Map<String,Object> checkMenuHasChildrenNode(PermissionVo permissionVo){
        Map<String,Object> map = new HashMap<String, Object>();
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("pid",permissionVo.getId());
        List<Permission> list = permissionService.list(queryWrapper);
        if (list.size()>0){
            map.put("value",true);
        }else {
            map.put("value",false);
        }
        return map;
    }

    /**
     * ????????????
     * @param permissionVo
     * @return
     */
    @RequestMapping("deleteMenu")
    public ResultObj deleteMenu(PermissionVo permissionVo){
        try {
            permissionService.removeById(permissionVo.getId());
            return ResultObj.DELETE_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultObj.DELETE_ERROR;
        }
    }
    
    
}
