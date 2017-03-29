package com.ssnakeTech.qrisk.dao.impl;

import com.ssnakeTech.qrisk.dao.RoleDao;
import com.ssnakeTech.qrisk.entity.Role;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by leo on 2017/3/27.
 */
@Repository
public class RoleDaoImpl extends JdbcDaoSupport implements RoleDao {
    @Resource(name="dataSource")
    public void setDS(DataSource ds) {
        super.setDataSource(ds);
    }
    @Override
    public Role findOne(Long roleId) {
        final String sql = "select id, role, description, resource_ids as resourceIdsStr, available from sys_role where id=?";
        List<Role> roleList = this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Role.class), roleId);
        if(roleList.size() == 0) {
            return null;
        }
        return roleList.get(0);
    }
}
