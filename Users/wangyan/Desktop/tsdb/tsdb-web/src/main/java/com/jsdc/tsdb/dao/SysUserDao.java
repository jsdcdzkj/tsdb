package com.jsdc.tsdb.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jsdc.tsdb.common.utils.GlobalData;
import com.jsdc.tsdb.core.base.BaseDao;
import com.jsdc.tsdb.model.SysUser;
import org.springframework.stereotype.Repository;

@Repository
public class SysUserDao extends BaseDao<SysUser> {


    public QueryWrapper<SysUser> queryByName(String name, String pass) {
        QueryWrapper<SysUser> queryHandler = new QueryWrapper<>();
        if (notEmpty(name)) {
            queryHandler.eq("login_name", name);
        }
        if (notEmpty(pass)) {
            queryHandler.eq("pass", pass);
        }
        return queryHandler;
    }
}
