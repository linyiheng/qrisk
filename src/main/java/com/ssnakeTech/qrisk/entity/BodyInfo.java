package com.ssnakeTech.qrisk.entity;

/**
 * Created by leo on 2017/3/5.
 */
public class BodyInfo {
    private String username;
    private String idNum;
    private String age;
    private String sex;
    private String ethnicity;
    private String smoke_cat;
    private String diabetes_cat;
    private String rati;
    private String sbp;
    private String height;
    private String weight;
    private String yearsRiskCalculatedFor;
    private String fh_cvd;
    private String b_renal;
    private String b_AF;
    private String b_treatedhyp;
    private String b_ra;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getFh_cvd() {
        return fh_cvd;
    }

    public void setFh_cvd(String fh_cvd) {
        this.fh_cvd = fh_cvd;
    }

    public String getB_renal() {
        return b_renal;
    }

    public void setB_renal(String b_renal) {
        this.b_renal = b_renal;
    }

    public String getB_AF() {
        return b_AF;
    }

    public void setB_AF(String b_AF) {
        this.b_AF = b_AF;
    }

    public String getB_treatedhyp() {
        return b_treatedhyp;
    }

    public void setB_treatedhyp(String b_treatedhyp) {
        this.b_treatedhyp = b_treatedhyp;
    }

    public String getB_ra() {
        return b_ra;
    }

    public void setB_ra(String b_ra) {
        this.b_ra = b_ra;
    }



    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public String getSmoke_cat() {
        return smoke_cat;
    }

    public void setSmoke_cat(String smoke_cat) {
        this.smoke_cat = smoke_cat;
    }

    public String getDiabetes_cat() {
        return diabetes_cat;
    }

    public void setDiabetes_cat(String diabetes_cat) {
        this.diabetes_cat = diabetes_cat;
    }

    public String getRati() {
        return rati;
    }

    public void setRati(String rati) {
        this.rati = rati;
    }

    public String getSbp() {
        return sbp;
    }

    public void setSbp(String sbp) {
        this.sbp = sbp;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getYearsRiskCalculatedFor() {
        return yearsRiskCalculatedFor;
    }

    public void setYearsRiskCalculatedFor(String yearsRiskCalculatedFor) {
        this.yearsRiskCalculatedFor = yearsRiskCalculatedFor;
    }

}
