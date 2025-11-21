package com.jsdc.tsdb.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.jsdc.tsdb.common.utils.GlobalData;
import com.jsdc.tsdb.common.utils.ResultInfo;
import com.jsdc.tsdb.core.base.BaseService;
import com.jsdc.tsdb.dao.SysDeptDao;
import com.jsdc.tsdb.model.SysDept;
import com.jsdc.tsdb.model.SysUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SysDeptService extends BaseService<SysDeptDao, SysDept> {

    @Autowired
    private SysUserService sysUserService ;
    public List<SysDept> getList(SysDept sysDept) {
        List<SysDept> sysDepts = selectList(Wrappers.<SysDept>lambdaQuery()
                .eq(SysDept::getIs_delete, GlobalData.ISDEL_NO)
                .ne(sysDept.getId() != null, SysDept::getId, sysDept.getId())
                .like(StringUtils.isNotBlank(sysDept.getDept_name()), SysDept::getDept_name, sysDept.getDept_name())
        );
        return sysDepts;
    }

    public ResultInfo edit(SysDept bean) {
        SysUser sysUser = sysUserService.getUser() ;
        List<SysDept> sysDepts = getList(bean);
        if (null == bean.getId() && !sysDepts.isEmpty()) {
            return ResultInfo.error("部门名重复，请重新命名！");
        } else if (!sysDepts.isEmpty()) {
            return ResultInfo.error("部门名重复，请重新命名！");
        }
        if (null != bean.getId()) {
            bean.setUpdateUser(sysUser.getId()+"");
            bean.setUpdateTime(new Date());
        }
        bean.insertOrUpdate();
        if (null == bean.getId()) {
            return ResultInfo.success("部门新增成功！");
        } else {
            return ResultInfo.success("部门编辑成功！");
        }
    }
}
