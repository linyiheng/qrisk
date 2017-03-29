package com.ssnakeTech.qrisk.dao;

import com.ssnakeTech.qrisk.entity.BodyInfo;
import com.ssnakeTech.qrisk.entity.HealthInfo;
import com.ssnakeTech.qrisk.entity.QriskInfo;

import java.util.List;

/**
 * Created by leo on 2017/3/13.
 */
public interface QriskDao {
    public void save(BodyInfo bodyInfo, HealthInfo healthInfo);
    public List<QriskInfo> getQriskInfoList(String fromDate,String toDate);
}
