package com.jsdc.tsdb.dao;

import cn.hutool.core.util.StrUtil;
import com.jsdc.tsdb.core.base.BaseDao;
import com.jsdc.tsdb.model.BizCaseInfo;
import com.jsdc.tsdb.model.SysUser;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

@Repository
public class BizCaseInfoDao extends BaseDao<BizCaseInfo> {


    public String selectCaseRecord(BizCaseInfo bizCaseInfo, SysUser sysUser) {
        String sql = "select a.id, a.trial_name, " +
                "a.case_code, " +
                "a.trial_time, " +
                "a.plaintiff, " +
                "a.defendant, " +
                "a.case_name, " +
                "a.undertake_dept, " +
                "a.presiding_judges, " +
                "a.case_type, " +
                "a.case_time, " +
                "a.case_memo, " +
                "a.is_pub, " +
                "a.backup_status, " +
                "a.video_num, " +
                "a.record_num, " +
                "count(b.id) as num " +
                "from biz_case_info a " +
                "left join biz_case_record b " +
                "on a.case_code = b.case_code  where 1=1";
        if (!StrUtil.hasEmpty(bizCaseInfo.getCase_code())) {
            sql += " and a.case_code='" + bizCaseInfo.getCase_code() + "'";
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getCase_name())) {
            sql += " and a.case_name like '%" + bizCaseInfo.getCase_name() + "%'";
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getCase_type())) {
            sql += " and a.case_type='" + bizCaseInfo.getCase_type() + "'";
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getUndertake_dept())) {
            sql += " and a.undertake_dept='" + bizCaseInfo.getUndertake_dept() + "'";
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getTrial_time())) {
            sql += " and a.trial_time >='" + bizCaseInfo.getTrial_time() + " 00:00:00'";
            sql += " and a.trial_time <='" + bizCaseInfo.getTrial_time() + " 23:59:59'";
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getCase_time())) {
            sql += " and a.case_time >='" + bizCaseInfo.getTrial_time() + " 00:00:00'";
            sql += " and a.case_time <='" + bizCaseInfo.getTrial_time() + " 23:59:59'";
        }
        sql += " and a.is_pub='1' or (a.undertake_dept ='" + sysUser.getDept_name() + "' and is_pub='0')";
        sql += " group by a.case_code order by a.trial_time desc,a.case_time desc";
        return sql;
    }

    public String getTotal(BizCaseInfo bizCaseInfo) {
        String sql = "SELECT " +
                " bi.`undertake_dept` dept_name, " +//承办部门
                " bi.`case_type` type_name, " +//案件类型
                " IFNULL(SUM( bi.video_num ), 0) video_num, " +//视频数
                " IFNULL(SUM( bi.record_num ), 0) record_num, " +//笔录数
                " COUNT(*) case_num  ";//案件数
        sql += " FROM " +
                " biz_case_info AS bi ";
        sql = getSql(bizCaseInfo, sql);
        return sql;
    }

    public String getTypeRatio(BizCaseInfo bizCaseInfo) {
        String sql = "SELECT " +
                " bi.`case_type` name," +
                " count(bi.`case_type`) value";
        sql += " FROM " +
                " biz_case_info AS bi ";
        sql = getSql(bizCaseInfo, sql);
        sql += " GROUP BY bi.case_type ";
        return sql;
    }

    public String getDeptRatio(BizCaseInfo bizCaseInfo) {
        String sql = "SELECT " +
                " bi.`undertake_dept` name," +
                " count(bi.`undertake_dept`) value";
        sql += " FROM " +
                " biz_case_info AS bi ";
        sql = getSql(bizCaseInfo, sql);
        sql += " GROUP BY bi.undertake_dept ";
        return sql;
    }

    public String getWorkload(BizCaseInfo bizCaseInfo) {
        String sql = "SELECT " +
                " su.`name` user_name, " +//用户名
                " IFNULL(COUNT(DISTINCT su.`id`), 0)  user_num, " +//用户数
                " su.`dept_name` dept_name, " +//所属部门
                " su.`role_name` role_name, " +//所属角色
                " bi.`case_type` type_name, " +//案件类型
                " IFNULL(SUM( bi.video_num ), 0) video_num, " +//视频数
                " IFNULL(SUM( bi.record_num ), 0) record_num, " +//笔录数
                " IFNULL(COUNT(DISTINCT br.case_code), 0) case_num, " +//案件数
                " COUNT(*) total  ";//总点播次数
        sql += " FROM " +
                " biz_case_record AS br " +
                " INNER JOIN sys_user AS su ON br.user_id = su.id " +
                " INNER JOIN biz_case_info bi ON br.case_code = bi.case_code  ";
        sql = getSql(bizCaseInfo, sql);
        sql += " GROUP BY br.user_id ";
        return sql;
    }

    private String getSql(BizCaseInfo bizCaseInfo, String sql) {
        sql += " WHERE 1 = 1 ";
        if (!StrUtil.hasEmpty(bizCaseInfo.getUser_name())) {
            sql += " and su.`name` like '%" + bizCaseInfo.getUser_name() + "%'";
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getCase_code())) {
            sql += " and bi.case_code='" + bizCaseInfo.getCase_code() + "'";
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getCase_name())) {
            sql += " and bi.case_name like '%" + bizCaseInfo.getCase_name() + "%'";
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getCase_type())) {
            sql += " and bi.case_type='" + bizCaseInfo.getCase_type() + "'";
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getUndertake_dept())) {
            sql += " and bi.undertake_dept='" + bizCaseInfo.getUndertake_dept() + "'";
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getDate())) {
            String[] date = bizCaseInfo.getDate().split(" - ");
            sql += " and bi.trial_time >='" + date[0] + " 00:00:00'";
            sql += " and bi.trial_time <='" + date[1] + " 23:59:59'";
            System.err.println();
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getTrial_time())) {
            sql += " and bi.trial_time >='" + bizCaseInfo.getTrial_time() + " 00:00:00'";
            sql += " and bi.trial_time <='" + bizCaseInfo.getTrial_time() + " 23:59:59'";
        }
        if (!StrUtil.hasEmpty(bizCaseInfo.getCase_time())) {
            sql += " and bi.case_time >='" + bizCaseInfo.getTrial_time() + " 00:00:00'";
            sql += " and bi.case_time <='" + bizCaseInfo.getTrial_time() + " 23:59:59'";
        }
        return sql;
    }
}
