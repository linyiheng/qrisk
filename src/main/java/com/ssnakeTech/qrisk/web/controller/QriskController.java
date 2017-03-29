package com.ssnakeTech.qrisk.web.controller;

import com.mchange.io.FileUtils;
import com.ssnakeTech.qrisk.entity.BodyInfo;
import com.ssnakeTech.qrisk.entity.HealthInfo;
import com.ssnakeTech.qrisk.entity.User;
import com.ssnakeTech.qrisk.service.QriskService;
import com.ssnakeTech.qrisk.service.ResourceService;
import com.ssnakeTech.qrisk.service.UserService;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by leo on 2017/3/5.
 */
@Controller
@RequestMapping(value="riskCount")
public class QriskController {
    @Autowired
    QriskService qriskService;
    @Autowired
    UserService userService;
    @Autowired
    ResourceService resourceService;

    private Logger logger= LoggerFactory.getLogger(this.getClass());
    @RequestMapping(value="",method={RequestMethod.POST,RequestMethod.GET})
    public ModelAndView qriskInfoSubmitGet(HttpServletRequest request, HttpServletResponse response){
        ModelAndView mv=new ModelAndView();
        String calculate=request.getParameter("calculate");
        /*String account=request.getParameter("account");
        if(account!=null){
            Set<String> permissions= userService.findPermissions(account);
        }*/
        String exceptionClassName = (String)request.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)){
            error = "登陆失败多次，账户锁定10分钟";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }

        mv.addObject("error",error);

        if (calculate!=null) {
            if(calculate.equals("计算风险")){
                String username=request.getParameter("username");
                String idNum=request.getParameter("idnum");
                String age=request.getParameter("age");
                String sex=request.getParameter("sex");
                String ethnicity=request.getParameter("ethnicity");
                String smoke_cat=request.getParameter("smoke_cat");
                String diabetes_cat=request.getParameter("diabetes_cat");
                String rati=request.getParameter("rati");
                String sbp=request.getParameter("sbp");
                String height=request.getParameter("height");
                String weight=request.getParameter("weight");
                String yearsRiskCalculatedFor=request.getParameter("yearsRiskCalculatedFor");
                List<String> paramNames= Collections.list(request.getParameterNames());
                BodyInfo bodyInfo=new BodyInfo();
                if(paramNames.contains("fh_cvd")){
                    String fh_cvd=request.getParameter("fh_cvd");
                    bodyInfo.setFh_cvd(fh_cvd);
                    mv.addObject("fh_cvd",fh_cvd);
                }
                if(paramNames.contains("b_renal")){
                    String b_renal=request.getParameter("b_renal");
                    bodyInfo.setB_renal(b_renal);
                    mv.addObject("b_renal",b_renal);
                }
                if(paramNames.contains("b_AF")){
                    String b_AF=request.getParameter("b_AF");
                    bodyInfo.setB_AF(b_AF);
                    mv.addObject("b_AF",b_AF);
                }
                if(paramNames.contains("b_treatedhyp")){
                    String b_treatedhyp=request.getParameter("b_treatedhyp");
                    bodyInfo.setB_treatedhyp(b_treatedhyp);
                    mv.addObject("b_treatedhyp",b_treatedhyp);
                }
                if(paramNames.contains("b_ra")){
                    String b_ra=request.getParameter("b_ra");
                    bodyInfo.setB_ra(b_ra);
                    mv.addObject("b_ra",b_ra);
                }
                bodyInfo.setUsername(username);
                bodyInfo.setIdNum(idNum);
                bodyInfo.setAge(age);
                bodyInfo.setSex(sex);
                bodyInfo.setEthnicity(ethnicity);
                bodyInfo.setSmoke_cat(smoke_cat);
                bodyInfo.setDiabetes_cat(diabetes_cat);
                bodyInfo.setRati(rati);
                bodyInfo.setSbp(sbp);
                bodyInfo.setHeight(height);
                bodyInfo.setWeight(weight);
                bodyInfo.setYearsRiskCalculatedFor(yearsRiskCalculatedFor);
                String pageContent=qriskService.doBodyInfoSend(bodyInfo);
                HealthInfo healthInfo=qriskService.doCrawler(pageContent);
                qriskService.doSaveQriskInfo(bodyInfo,healthInfo);
                //qriskService.doSaveToExcel(bodyInfo,healthInfo);

                //个人信息
                mv.addObject("username",username);
                mv.addObject("idnum",idNum);
                mv.addObject("age",age);
                mv.addObject("sex",sex);
                mv.addObject("ethnicity",ethnicity);
                mv.addObject("smoke_cat",smoke_cat);
                mv.addObject("diabetes_cat",diabetes_cat);
                mv.addObject("rati",rati);
                mv.addObject("sbp",sbp);
                mv.addObject("height",height);
                mv.addObject("weight",weight);

                //健康信息
                mv.addObject("qriskHealthHeartAge",healthInfo.getQriskHealthHeartAge());
                mv.addObject("qriskScore",healthInfo.getQriskScore());
                mv.addObject("relativeRisk",healthInfo.getRelativeRisk());
                mv.addObject("scoreOfaHealthyPersonWithSameAgeSexAndEthnicity",healthInfo.getScoreOfaHealthyPersonWithSameAgeSexAndEthnicity());


            }

        }
        mv.setViewName("index");

        return mv;
    }

    @RequestMapping(value="download",method=RequestMethod.GET)
    public void downloadQRiskReport(HttpServletRequest request,HttpServletResponse response){
        String fromDate=request.getParameter("fromDate");
        String toDate=request.getParameter("toDate");
        logger.info(request.getRequestURI());
        qriskService.doSaveToExcel(fromDate,toDate);
        File file=new File("QRiskReport.xlsx");
        String fileName="";
        try {
            fileName = URLEncoder.encode("QRiskReport.xlsx", "UTF-8");
        }catch(Exception e){
            logger.info(e.getMessage());
        }
        //response.setContentType("application/force-download");
        //response.setContentType(request.getServletContext().getMimeType(fileName));
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.addHeader("Content-Disposition","attachment;fileName="+fileName);
        try {
            response.getOutputStream().write(FileUtils.getBytes(file));
        }catch(Exception e){
            logger.info(e.getMessage());
        }
    }
    @RequestMapping(value="login",method = RequestMethod.POST)
    public void login(){

    }

}
