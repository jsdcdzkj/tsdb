package com.jsdc.tsdb.common.security;


import com.jsdc.tsdb.mapper.SysUserMapper;
import com.jsdc.tsdb.model.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class SystemAuthorizingRealm extends AuthorizingRealm {

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        // Host用逗号分拆，第二位是userId
        String[] strings = token.getHost().split(",");
        if (strings.length < 2) {
            return null;
        }
        String userId = strings[1];
        SysUser user = userMapper.selectById(Integer.parseInt(userId));
        return new SimpleAuthenticationInfo(new ShiroUser(user.getId().toString(), user.getName()), user.getPass(), getName());
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        SysUser user = userMapper.selectById(Integer.parseInt(shiroUser.getUserId()));
//        Role role = new Role();
//        if (user != null){
//            role = roleMapper.selectById(user.getRole_ID());
//        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addRole(role.getName());
        return info;
    }
}
