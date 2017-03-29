package com.ssnakeTech.qrisk.service.impl;

import com.ssnakeTech.qrisk.dao.QriskDao;
import com.ssnakeTech.qrisk.entity.BodyInfo;
import com.ssnakeTech.qrisk.entity.HealthInfo;
import com.ssnakeTech.qrisk.entity.QriskInfo;
import com.ssnakeTech.qrisk.service.QriskService;
import com.ssnakeTech.qrisk.util.HttpsClientTool;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.List;


/**
 * Created by leo on 2017/3/5.
 */
@Service
public class QriskServiceImpl implements QriskService {
    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private QriskDao qriskDao;

    @Override
    public String doBodyInfoSend(BodyInfo bodyInfo) {
        String url="https://qrisk.org/2016/index.php";
        StringBuilder reqBodyBuilder=new StringBuilder();
        reqBodyBuilder.append("age=").append(bodyInfo.getAge()).append("&");
        reqBodyBuilder.append("sex=").append(bodyInfo.getSex()).append("&");
        reqBodyBuilder.append("ethnicity=").append(bodyInfo.getEthnicity()).append("&");
        reqBodyBuilder.append("postcode").append("&");
        reqBodyBuilder.append("smoke_cat=").append(bodyInfo.getSmoke_cat()).append("&");
        reqBodyBuilder.append("diabetes_cat=").append(bodyInfo.getDiabetes_cat()).append("&");
        if(bodyInfo.getFh_cvd()!=null && !bodyInfo.getFh_cvd().trim().equals("")){
            reqBodyBuilder.append("fh_cvd=").append(bodyInfo.getFh_cvd()).append("&");
        }
        if(bodyInfo.getB_renal()!=null && !bodyInfo.getB_renal().trim().equals("")){
            reqBodyBuilder.append("b_renal=").append(bodyInfo.getB_renal()).append("&");
        }
        if(bodyInfo.getB_AF()!=null && !bodyInfo.getB_AF().trim().equals("")){
            reqBodyBuilder.append("b_AF=").append(bodyInfo.getB_AF()).append("&");
        }
        if(bodyInfo.getB_treatedhyp()!=null && !bodyInfo.getB_treatedhyp().trim().equals("")){
            reqBodyBuilder.append("b_treatedhyp").append(bodyInfo.getB_treatedhyp()).append("&");
        }
        if(bodyInfo.getB_ra()!=null && !bodyInfo.getB_ra().trim().equals("")){
            reqBodyBuilder.append("b_ra").append(bodyInfo.getB_ra()).append("&");
        }
        if(bodyInfo.getRati().trim().equals("")){
            reqBodyBuilder.append("rati").append("&");
        }else {
            reqBodyBuilder.append("rati=").append(bodyInfo.getRati()).append("&");
        }
        if(bodyInfo.getSbp().trim().equals("")){
            reqBodyBuilder.append("sbp").append("&");
        }else{
            reqBodyBuilder.append("sbp=").append(bodyInfo.getSbp()).append("&");
        }
        reqBodyBuilder.append("height=").append(bodyInfo.getHeight()).append("&");
        reqBodyBuilder.append("weight=").append(bodyInfo.getWeight()).append("&");
        reqBodyBuilder.append("yearsRiskCalculatedFor=").append(bodyInfo.getYearsRiskCalculatedFor()).append("&");
        reqBodyBuilder.append("calculate=").append("Calculate+risk");
        String reqBody=reqBodyBuilder.toString();
        logger.info("个人身体信息发送内容："+reqBody);
        CloseableHttpClient httpClient= HttpsClientTool.createSSLClientDefault();
        HttpPost httpPost=new HttpPost(url);
        String entityContent="";
        try {
            StringEntity stringEntity = new StringEntity(reqBody, ContentType.create("application/x-www-form-urlencoded"));
            httpPost.setEntity(stringEntity);
            HttpResponse response=httpClient.execute(httpPost);
            HttpEntity entity=response.getEntity();
            entityContent=IOUtils.toString(entity.getContent());
            logger.info("接收的健康信息页面内容为："+entityContent);
        }catch (Exception e){
            logger.info(e.getMessage());
        }
        return entityContent;
    }

    @Override
    public HealthInfo doCrawler(String pageContent) {
        Document doc= Jsoup.parse(pageContent);
        //doc.getElementsByTag("table").get(0).get;
        Elements allHealthInfoContent=doc.getElementsByTag("body").get(0).child(0).child(0).child(2).child(0).child(0).child(0).child(0).child(1).getElementsByTag("table").get(2).getElementsByTag("td");
        HealthInfo healthInfo=new HealthInfo();
        healthInfo.setQriskScore(allHealthInfoContent.get(2).ownText());
        healthInfo.setScoreOfaHealthyPersonWithSameAgeSexAndEthnicity(allHealthInfoContent.get(4).ownText());
        healthInfo.setRelativeRisk(allHealthInfoContent.get(6).ownText());
        healthInfo.setQriskHealthHeartAge(allHealthInfoContent.get(8).ownText());
        return healthInfo;
    }

    @Override
    public void doSaveToExcel(String fromDate,String toDate) {
        List<QriskInfo> qriskInfoList= qriskDao.getQriskInfoList(fromDate,toDate);
        String fileName="QRiskReport.xlsx";
        Workbook workbook =new XSSFWorkbook();
        Sheet sheet =workbook.createSheet("QRisk报表");
        int i=0;
        for(QriskInfo qriskInfo:qriskInfoList){
            Row row=sheet.createRow(i);
            row.createCell(0).setCellValue(qriskInfo.getUsername());
            row.createCell(1).setCellValue(qriskInfo.getFh_cvd());
            row.createCell(2).setCellValue(qriskInfo.getB_renal());
            row.createCell(3).setCellValue(qriskInfo.getB_AF());
            row.createCell(4).setCellValue(qriskInfo.getB_treatedhyp());
            row.createCell(5).setCellValue(qriskInfo.getB_ra());
            row.createCell(6).setCellValue(qriskInfo.getAge());
            row.createCell(7).setCellValue(qriskInfo.getSex());
            row.createCell(8).setCellValue(qriskInfo.getEthnicity());
            row.createCell(9).setCellValue(qriskInfo.getSmoke_cat());
            row.createCell(10).setCellValue(qriskInfo.getDiabetes_cat());
            row.createCell(11).setCellValue(qriskInfo.getRati());
            row.createCell(12).setCellValue(qriskInfo.getSbp());
            row.createCell(13).setCellValue(qriskInfo.getHeight());
            row.createCell(14).setCellValue(qriskInfo.getWeight());
            row.createCell(15).setCellValue(qriskInfo.getYearsRiskCalculatedFor());
            row.createCell(16).setCellValue(qriskInfo.getQriskHealthHeartAge());
            row.createCell(17).setCellValue(qriskInfo.getQriskScore());
            row.createCell(18).setCellValue(qriskInfo.getScoreOfaHealthyPersonWithSameAgeSexAndEthnicity());
            row.createCell(19).setCellValue(qriskInfo.getRelativeRisk());
            i++;
        }

        //lets write to file  
        try {
            FileOutputStream fos=new FileOutputStream(fileName);
            workbook.write(fos);
            fos.close();
        }catch(Exception e){
            logger.info(e.getMessage());
        }
        logger.info(fileName+"写入成功");


    }

    @Override
    public void doSaveQriskInfo(BodyInfo bodyInfo, HealthInfo healthInfo) {
        qriskDao.save(bodyInfo,healthInfo);
    }
}
