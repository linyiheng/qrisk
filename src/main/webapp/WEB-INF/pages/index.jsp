<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>QRisk中文</title>
<link rel="stylesheet" href="${ctx}/static/css/bootstrap.min.css"/>
<style>
    body{
        color:#666;
    }
    form{
        line-height:40px;
        font-size:15px;
    }
    p{
        line-height:40px;
        font-size:15px;
    }
    input[type="text"]{
        display:inline-block;
        height:33.6px;
        font-size:15px;
        margin:0px 0px 0px 8px;
        padding:6px 12px;
        width:70px;
    }
    strong{
        font-size:30px;
    }
    .btn-default{
        color:#666;
    }

    select{
        height:20px;
        font-size:12px;
    }


</style>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/css/bootstrap-select.min.css"/>
<link href="//cdn.bootcss.com/iCheck/1.0.2/skins/all.css" rel="stylesheet"/>


<script src="${ctx}/static/js/jquery-1.12.1.min.js"></script>
<script src="${ctx}/static/js/form.v002.js"></script>
<script src="${ctx}/static/js/bootstrap.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.2/js/bootstrap-select.min.js"></script>
<script src="//cdn.bootcss.com/iCheck/1.0.2/icheck.min.js"></script>

<script>
$(function(){
        $('input').iCheck({
            checkboxClass: 'icheckbox_flat-grey',
            radioClass: 'iradio_flat-grey'
          });
        $("input[name='username']").attr("value","${username}");
        $("input[name='idnum']").attr("value","${idnum}");
        $("input[name='age']").attr("value","${age}");
        if("${sex}"==="0"){
          $(":radio").last().prop("checked",true);
        }else{
          $(":radio").first().prop("checked",true);
        }
        $("select[name='ethnicity'] > option[value=\"${ethnicity}\"").prop("selected",true);
        $("select[name='smoke_cat'] > option[value=\"${smoke_cat}\"").prop("selected",true);
        $("select[name='diabetes_cat']>option[value=\"${diabetes_cat}\"").prop("selected",true);
        if("${fh_cvd}"==="on"){
            $("input[name='fh_cvd']").prop("checked",true);
        }else{
            $("input[name='fh_cvd']").prop("checked",false);
        }
        if("${b_renal}"==="on"){
            $("input[name='b_renal']").prop("checked",true);
        }else{
            $("input[name='b_renal']").prop("checked",false);
        }
        if("${b_AF}"==="on"){
            $("input[name='b_AF']").prop("checked",true);
        }else{
            $("input[name='b_AF']").prop("checked",false);
        }
        if("${b_treatedhyp}"==="on"){
            $("input[name='b_treatedhyp']").prop("checked",true);
        }else{
            $("input[name='b_treatedhyp']").prop("checked",false);
        }
        if("${b_ra}"==="on"){
            $("input[name='b_ra']").prop("checked",true);
        }else{
            $("input[name='b_ra']").prop("checked",false);
        }
        $("input[name='rati']").attr("value","${rati}");
        $("input[name='sbp']").attr("value","${sbp}");
        $("input[name='height']").attr("value","${height}");
        $("input[name='weight']").attr("value","${weight}");

        $("form").on("submit",
         function(){
                return checkCalculatorForm(this);
         }
        );

    });
</script>

</head>
<body>
    <div class="container-fluid">
    <div class="row">
    <div class="col-xs-12 col-sm-12 col-md-12">

    <form name="calculator" action='${ctx}/riskCount' method='POST'>
        用户姓名:&nbsp;<input type="text" name="username" class="form-control"/><br/>
        用户ID号:&nbsp;<input type="text" name="idnum" class="form-control"/><br/>
	    年龄(25-84):&nbsp;<input type="text" name="age" class="form-control"/><br/>
	    性别:&nbsp;<input type='radio' name='sex' value='1' checked/>男性&nbsp;&nbsp<input type='radio' name='sex' value='0'/>女性<br/>
	    种族:&nbsp;<select name="ethnicity" class="selectpicker">
                  <option value='1' selected="selected">白种人</option>
                  <option value='2'>印度人</option>
                  <option value='3' >巴基斯坦人</option>
                  <option value='4' >孟加拉人</option>
                  <option value='5' >其他亚洲人种</option>
                  <option value='6' >加勒比黑人</option>
                  <option value='7' >非洲黑人</option>
                  <option value='8' >中国人</option>
                  <option value='9' >其他人种</option>
                </select><br/>
        吸烟状态:&nbsp;
        <select name="smoke_cat" class="selectpicker">
             <option value='0' selected="selected">从未吸烟</option>
             <option value='1'>以前吸烟</option>
             <option value='2' >轻度吸烟者 (less than 10)</option>
             <option value='3' >中度吸烟者 (10 to 19)</option>
             <option value='4' >重度吸烟者 (20 or over)</option>
        </select><br/>
        糖尿病状态:&nbsp;<select name="diabetes_cat" class="selectpicker">
                   <option value='0' selected>无</option>
                   <option value='1' >1型糖尿病</option>
                   <option value='2' >2型糖尿病</option>
                 </select><br />
        1级糖尿病或心绞痛:&nbsp;<input type='checkbox' name='fh_cvd' id="fh_cvd" /><br/>
        慢性肾病（阶段4或5）:&nbsp;<input type='checkbox' name='b_renal'  /><br/>
        心房纤维性颤动:&nbsp;<input type='checkbox' name='b_AF'  /><br/>
        关于血压的治疗:&nbsp;<input type='checkbox' name='b_treatedhyp'  /><br/>
        类风湿性关节炎:&nbsp;<input type='checkbox' name='b_ra'  /><br/>
        胆固醇/ HDL比值:&nbsp;<input type='text' name='rati' id='rati' class="form-control"/><br/>
        收缩压(mmHg):&nbsp;<input type='text' name='sbp' id='sbp' class="form-control"/><br/>
        身高(cm):&nbsp;<INPUT type='text' name='height' id='height' class="form-control"/><br/>
        体重(kg):&nbsp;<INPUT type='text' name='weight' id='weight' class="form-control"/><br/>
        计算超过10年的风险:&nbsp;<select name="yearsRiskCalculatedFor" class="selectpicker">
                   <option value='10' selected>10</option>
                   </select>
        <input type="submit" class="btn btn-default" name="calculate" value='计算风险' aria-label="Left Align"/>


	</form>
    <br/>
    <br/>
    <p>
	未来十年患心脏病或中风的概率:&nbsp;<strong>${qriskScore}</strong><br/>
	该分数在同样年龄性别与种族的健康人群重的比例为:&nbsp;<strong>${scoreOfaHealthyPersonWithSameAgeSexAndEthnicity}</strong><br/>
	相对风险:&nbsp;<strong>${relativeRisk}</strong><br/>
	你的QRisk健康心脏年龄:&nbsp;<strong>${qriskHealthHeartAge}</strong><br/>
	<a href="${ctx}/riskCount/download">下载QRisk统计报表</a>
	</p>

	</div>
    </div>
    </div>

</body>
</html>