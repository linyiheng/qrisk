package com.ssnakeTech.qrisk.dao.impl;

import com.ssnakeTech.qrisk.dao.ResourceDao;
import com.ssnakeTech.qrisk.entity.Resource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by leo on 2017/3/27.
 */
@Repository
public class ResourceDaoImpl extends JdbcDaoSupport implements ResourceDao {
    @javax.annotation.Resource(name="dataSource")
    public void setDS(DataSource ds) {
        super.setDataSource(ds);
    }
    @Override
    public Resource findOne(Long resourceId) {
        final String sql = "select id, name, type, url, permission, parent_id, parent_ids, available from sys_resource where id=?";
        List<Resource> resourceList = this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(Resource.class), resourceId);
        if(resourceList.size() == 0) {
            return null;
        }
        return resourceList.get(0);
    }
}
