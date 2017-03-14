package com.ssnakeTech.qrisk.entity;

/**
 * Created by leo on 2017/3/5.
 */
public class HealthInfo {


    private String qriskScore;
    private String scoreOfaHealthyPersonWithSameAgeSexAndEthnicity;
    private String relativeRisk;
    private String qriskHealthHeartAge;

    public String getQriskHealthHeartAge() {
        return qriskHealthHeartAge;
    }

    public void setQriskHealthHeartAge(String qriskHealthHeartAge) {
        this.qriskHealthHeartAge = qriskHealthHeartAge;
    }



    public String getQriskScore() {
        return qriskScore;
    }

    public void setQriskScore(String qriskScore) {
        this.qriskScore = qriskScore;
    }

    public String getScoreOfaHealthyPersonWithSameAgeSexAndEthnicity() {
        return scoreOfaHealthyPersonWithSameAgeSexAndEthnicity;
    }

    public void setScoreOfaHealthyPersonWithSameAgeSexAndEthnicity(String scoreOfaHealthyPersonWithSameAgeSexAndEthnicity) {
        this.scoreOfaHealthyPersonWithSameAgeSexAndEthnicity = scoreOfaHealthyPersonWithSameAgeSexAndEthnicity;
    }

    public String getRelativeRisk() {
        return relativeRisk;
    }

    public void setRelativeRisk(String relativeRisk) {
        this.relativeRisk = relativeRisk;
    }


}
