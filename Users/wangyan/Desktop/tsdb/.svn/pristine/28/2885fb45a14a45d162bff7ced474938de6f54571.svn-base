package com.jsdc.tsdb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jsdc.tsdb.common.utils.GlobalData;
import com.jsdc.tsdb.common.utils.ResultInfo;
import com.jsdc.tsdb.core.base.BaseService;
import com.jsdc.tsdb.dao.SysDeptDao;
import com.jsdc.tsdb.dao.SysMenuDao;
import com.jsdc.tsdb.dao.SysUserDao;
import com.jsdc.tsdb.mapper.SysMenuMapper;
import com.jsdc.tsdb.mapper.SysRoleMenuMapper;
import com.jsdc.tsdb.mapper.SysUserRoleMapper;
import com.jsdc.tsdb.model.*;
import com.jsdc.tsdb.model.vo.SysMenuVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SysMenuService extends BaseService<SysMenuDao, SysMenu> {
    @Autowired
    private SysMenuMapper sysMenuMapper ;

    @Autowired
    private SysUserService sysUserService ;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper ;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper ;


    /**
    *菜单结构拼装
    * Author wzn
    * Date 2022/10/10 10:06
    */
    public StringBuilder getMenuList() {
        SysUser sysUser = sysUserService.getUser() ;
        QueryWrapper<SysUserRole> queryWrapper2 = new QueryWrapper<>() ;
        queryWrapper2.eq("userId",sysUser.getId()) ;
        SysUserRole sysUserRole = sysUserRoleMapper.selectOne(queryWrapper2) ;
        QueryWrapper<SysRoleMenu> qw = new QueryWrapper<>();
        qw.eq("role_id",sysUserRole.getRoleId()) ;

        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuMapper.selectList(qw) ;
        ArrayList<Integer> arrayList = new ArrayList<>() ;
        for(SysRoleMenu s:sysRoleMenuList){
            arrayList.add(Integer.valueOf(s.getMenu_id())) ;
        }


        StringBuilder sb = new StringBuilder() ;
        QueryWrapper<SysMenu> queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_id","0");
        queryWrapper.in("id",arrayList) ;
        queryWrapper.orderByAsc("sort") ;
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(queryWrapper) ;
        for(SysMenu s:sysMenuList){
            QueryWrapper<SysMenu> queryWrapper1 = new QueryWrapper();
            queryWrapper1.eq("parent_id",s.getId());
            queryWrapper1.in("id",arrayList) ;
            queryWrapper1.orderByAsc("sort") ;
            List<SysMenu> sysMenuList2 = sysMenuMapper.selectList(queryWrapper1) ;
            if("1".equals(s.getIs_open())){
                sb.append("<li class=\"nav-item active nav-item-has-subnav open\">\n" +
                        "              <a href=\"javascript:void(0)\"><i class=\"") ;

            }else {
                sb.append("<li class=\"nav-item nav-item-has-subnav\">\n" +
                        "              <a href=\"javascript:void(0)\"><i class=\"") ;
            }
            sb.append(s.getIcon()) ;
            sb.append("\"></i> <span>");
            sb.append(s.getTitle()) ;
            sb.append("</span></a>\n" +
                    "              <ul class=\"nav nav-subnav\">") ;
            for(SysMenu ss:sysMenuList2){
                sb.append("<li> <a class=\"multitabs\" href=\"") ;
                sb.append(ss.getHref()) ;
                sb.append("\">") ;
                sb.append(ss.getTitle()) ;
                sb.append("</a> </li>") ;
            }
            sb.append("</ul>\n" +
                    "            </li>") ;
        }
        return sb;
    }




    public List<SysMenuVo> tree() {

        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("is_del","0");
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(wrapper);
        List<SysMenuVo> lists = new ArrayList<>();
        for (SysMenu s : sysMenuList) {
            SysMenuVo vo = new SysMenuVo();
            vo.setId(s.getId());
            vo.setTitle(s.getTitle());
            vo.setParent_id(s.getParent_id());
            lists.add(vo);
        }
        List<SysMenuVo> tree = createTree(lists, 0);
        return tree;
    }

    /**
     * 递归建立树形结构 （算法）
     * @param lists
     * @param pid
     * @return
     */
    private List<SysMenuVo> createTree(List<SysMenuVo> lists, int pid) {
        List<SysMenuVo> tree = new ArrayList<>();
        for (SysMenuVo catelog : lists) {
            if (Integer.parseInt(catelog.getParent_id()) == pid) {
                catelog.setChildren(createTree(lists,catelog.getId()));
                tree.add(catelog);
            }
        }
        return tree;
    }



    /**
    *角色修改树形图回显
    * Author wzn
    * Date 2022/10/9 9:25
    */
    public List<SysMenuVo> roleEditTree(String roleId) {
        QueryWrapper<SysRoleMenu> qw = new QueryWrapper<>();
        qw.eq("role_id",roleId) ;
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuMapper.selectList(qw) ;
        ArrayList<Integer> arrayList = new ArrayList<>() ;
        for(SysRoleMenu s:sysRoleMenuList){
            arrayList.add(Integer.valueOf(s.getMenu_id())) ;
        }
        QueryWrapper<SysMenu> wrapper = new QueryWrapper<>();
        wrapper.eq("is_del","0");
        List<SysMenu> sysMenuList = sysMenuMapper.selectList(wrapper);
        List<SysMenuVo> lists = new ArrayList<>();
        for (SysMenu s : sysMenuList) {
            SysMenuVo vo = new SysMenuVo();
            vo.setId(s.getId());
            vo.setTitle(s.getTitle());
            vo.setParent_id(s.getParent_id());
            if(arrayList.contains(s.getId()) && !"0".equals(s.getParent_id())){
                vo.setChecked(true);
            }
            lists.add(vo);
        }
        List<SysMenuVo> tree = createTree(lists, 0);
        return tree;
    }


}
