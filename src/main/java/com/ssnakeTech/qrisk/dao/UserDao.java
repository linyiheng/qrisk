package com.ssnakeTech.qrisk.dao;

import com.ssnakeTech.qrisk.entity.User;

/**
 * Created by leo on 2017/3/27.
 */
public interface UserDao {
    User findByUsername(String username);
    User findOne(Long userId);
}
