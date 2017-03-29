package com.ssnakeTech.qrisk.dao.impl;

import com.ssnakeTech.qrisk.dao.UserDao;
import com.ssnakeTech.qrisk.entity.User;
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
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
    @Resource(name="dataSource")
    public void setDS(DataSource ds) {
        super.setDataSource(ds);
    }
    @Override
    public User findOne(Long userId) {
        String sql = "select id, username, password, salt, role_ids as roleIdsStr, locked from sys_user where id=?";
        List<User> userList = this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(User.class), userId);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }
    @Override
    public User findByUsername(String username) {
        String sql = "select id, username, password, salt, role_ids as roleIdsStr, locked from sys_user where username=?";
        List<User> userList = this.getJdbcTemplate().query(sql, new BeanPropertyRowMapper(User.class), username);
        if(userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }
}
