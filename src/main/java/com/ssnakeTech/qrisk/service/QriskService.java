package com.ssnakeTech.qrisk.service;

import com.ssnakeTech.qrisk.entity.BodyInfo;
import com.ssnakeTech.qrisk.entity.HealthInfo;
import org.springframework.stereotype.Service;

/**
 * Created by leo on 2017/3/5.
 */
@Service
public interface QriskService {
    public String doBodyInfoSend(BodyInfo bodyInfo);
    public HealthInfo doCrawler(String pageContent);
    public void doSaveToExcel(String fromDate,String toDate);
    public void doSaveQriskInfo(BodyInfo bodyInfo,HealthInfo healthInfo);
}
