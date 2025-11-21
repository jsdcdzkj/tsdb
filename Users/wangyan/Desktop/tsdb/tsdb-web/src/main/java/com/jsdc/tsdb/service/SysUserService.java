package com.jsdc.tsdb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jsdc.tsdb.common.security.ShiroUser;
import com.jsdc.tsdb.common.utils.GlobalData;
import com.jsdc.tsdb.common.utils.ResultInfo;
import com.jsdc.tsdb.core.base.BaseService;
import com.jsdc.tsdb.dao.SysUserDao;
import com.jsdc.tsdb.mapper.SysUserMapper;
import com.jsdc.tsdb.mapper.SysUserRoleMapper;
import com.jsdc.tsdb.model.SysUser;
import com.jsdc.tsdb.model.SysUserRole;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author yanbin123
 * @since 2019-08-14
 */
@Service
@Transactional
public class SysUserService extends BaseService<SysUserDao, SysUser> {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    /**
     * 登录
     */
    public ResultInfo login(String userName, String passWord) {
        QueryWrapper<SysUser> queryWrapper = dao.queryByName(userName, passWord);
        SysUser user = sysUserMapper.selectOne(queryWrapper);
        if (user == null) {
            return ResultInfo.error("");
        }
        Subject subject = SecurityUtils.getSubject();
        // 利用Host属性存放其他属性，用逗号分隔保存，第二位是userId
        String host = "0.0.0.0," + user.getId();
        UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPass(), true, host);
        if(GlobalData.ISENABLE_YES.equals(user.getIs_enable())){
            subject.login(token);
        }
        return ResultInfo.success(user);
    }


    /**
     * 获取用户
     *
     * @return
     */
    public SysUser getUser() {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        if (shiroUser == null) {
            return null;
        }
        SysUser sysUser = selectById(shiroUser.getUserId());
        return sysUser;
    }


    public List<SysUser> getList(SysUser sysUser) {
        List<SysUser> sysUsers = selectList(Wrappers.<SysUser>lambdaQuery()
                .eq(StringUtils.isNotBlank(sysUser.getLogin_name()), SysUser::getLogin_name, sysUser.getLogin_name())
                .eq(SysUser::getIs_delete, GlobalData.ISDEL_NO)
                .eq(null != sysUser.getDept_id(), SysUser::getDept_id, sysUser.getDept_id())
                .eq(null != sysUser.getRole_id(), SysUser::getRole_id, sysUser.getRole_id())
                .ne(sysUser.getId() != null, SysUser::getId, sysUser.getId())
                .like(StringUtils.isNotBlank(sysUser.getQuery_where()), SysUser::getName, sysUser.getQuery_where()).or()
                .like(StringUtils.isNotBlank(sysUser.getQuery_where()), SysUser::getLogin_name, sysUser.getQuery_where()).or()
                .like(StringUtils.isNotBlank(sysUser.getQuery_where()), SysUser::getPhone, sysUser.getQuery_where())
        );


        return sysUsers;
    }

    public ResultInfo edit(SysUser bean) {
        List<SysUser> sysUsers = getList(bean);
        if (null == bean.getId() && !sysUsers.isEmpty()) {
            return ResultInfo.error("用户名重复，请重新命名！");
        } else if (!sysUsers.isEmpty()) {
            return ResultInfo.error("用户名重复，请重新命名！");
        }
        bean.insertOrUpdate();
        if (null != bean.getRole_id()) {
            SysUserRole sysUserRole = sysUserRoleMapper.selectOne(Wrappers.<SysUserRole>lambdaQuery().eq(SysUserRole::getUserId, bean.getId()));
            if (sysUserRole == null) {
                sysUserRole = new SysUserRole();
            }
            sysUserRole.setUserId(bean.getId());
            sysUserRole.setRoleId(bean.getRole_id());
            sysUserRole.insertOrUpdate();
        }
        return ResultInfo.success("成功！");
    }
}
