package com.ssnakeTech.qrisk.dao;

import com.ssnakeTech.qrisk.entity.Role;

/**
 * Created by leo on 2017/3/27.
 */
public interface RoleDao {
    public Role findOne(Long roleId);
}
