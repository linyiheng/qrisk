package com.ssnakeTech.qrisk.dao.impl;

import com.ssnakeTech.qrisk.dao.QriskDao;
import com.ssnakeTech.qrisk.entity.BodyInfo;
import com.ssnakeTech.qrisk.entity.HealthInfo;
import com.ssnakeTech.qrisk.entity.QriskInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by leo on 2017/3/13.
 */
@Repository
public class QriskDaoImpl extends JdbcDaoSupport implements QriskDao{
    @Resource(name="dataSource")
    public void setDS(DataSource ds) {
        super.setDataSource(ds);
    }

    @Override
    public void save(final BodyInfo bodyInfo,final HealthInfo healthInfo) {

        String sql="insert into qrisk_info(username,idNum,age,sex,ethnicity,smoke_cat,diabetes_cat,rati,sbp,height,weight,yearsRiskCalculatedFor,fh_cvd,b_renal,b_AF,b_treatedhyp,b_ra,qriskScore,scoreOfaHealthyPersonWithSameAgeSexAndEthnicity,relativeRisk,qriskHealthHeartAge,create_time) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now())";
        this.getJdbcTemplate().update(sql,new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setString(1,bodyInfo.getUsername());
                preparedStatement.setString(2,bodyInfo.getIdNum());
                preparedStatement.setString(3,bodyInfo.getAge());
                preparedStatement.setString(4,bodyInfo.getSex());
                preparedStatement.setString(5,bodyInfo.getEthnicity());
                preparedStatement.setString(6,bodyInfo.getSmoke_cat());
                preparedStatement.setString(7,bodyInfo.getDiabetes_cat());
                preparedStatement.setString(8,bodyInfo.getRati());
                preparedStatement.setString(9,bodyInfo.getSbp());
                preparedStatement.setString(10,bodyInfo.getHeight());
                preparedStatement.setString(11,bodyInfo.getWeight());
                preparedStatement.setString(12,bodyInfo.getYearsRiskCalculatedFor());
                preparedStatement.setString(13,bodyInfo.getFh_cvd());
                preparedStatement.setString(14,bodyInfo.getB_renal());
                preparedStatement.setString(15,bodyInfo.getB_AF());
                preparedStatement.setString(16,bodyInfo.getB_treatedhyp());
                preparedStatement.setString(17,bodyInfo.getB_ra());
                preparedStatement.setString(18,healthInfo.getQriskScore());
                preparedStatement.setString(19,healthInfo.getScoreOfaHealthyPersonWithSameAgeSexAndEthnicity());
                preparedStatement.setString(20,healthInfo.getRelativeRisk());
                preparedStatement.setString(21,healthInfo.getQriskHealthHeartAge());
            }
        });
    }

    @Override
    public List<QriskInfo> getQriskInfoList(String fromDate,String toDate) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        try {
            final Date from = sdf.parse(fromDate);
            final Date to = sdf.parse(toDate);

            String sql = "select username,idNum,age,sex,ethnicity,smoke_cat,diabetes_cat,rati,sbp,height,weight,yearsRiskCalculatedFor,fh_cvd,b_renal,b_AF,b_treatedhyp,b_ra,qriskScore,scoreOfaHealthyPersonWithSameAgeSexAndEthnicity,relativeRisk,qriskHealthHeartAge from qrisk_info where date(create_time) between ? and ?";
            List<Map<String, Object>> qriskInfoMapList = this.getJdbcTemplate().queryForList(sql,new java.sql.Date(from.getTime()), new java.sql.Date(to.getTime()));
            List<QriskInfo> qriskInfoList = new ArrayList<>();
            for (Map<String, Object> map : qriskInfoMapList) {
                QriskInfo qriskInfo = new QriskInfo();
                BeanWrapper beanWrapper = new BeanWrapperImpl(qriskInfo);
                beanWrapper.setPropertyValues(map);
                qriskInfoList.add(qriskInfo);
            }
            return qriskInfoList;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;

    }
}
