function isPostcode(val) {
        var pattern = /^([A-Z]{1,2})([0-9R][0-9A-Z]{0,1})([0-9][ABD-HJLNP-UW-Z]{2})$/;
	if (pattern.test(val)) {
        	return true;
	} else {
		return false;
	}
}

function isNumeric(val) {
        var pattern = /^\d+.?\d*$/;
	if (pattern.test(val)) {
        	return true;
	} else {
		return false;
	}
}

function isInteger(val) {
        var pattern = /^\d+$/;
	if (pattern.test(val)) {
        	return true;
	} else {
		return false;
	}
}

function checkCholesterol(theValue) {
    if (theValue=="")
	return "";
    var error="胆固醇/HDL 比值: 保持空白或者输入数字在1.0到12.0之间.\n";
    if (!isNumeric(theValue)) {
	return error;
    }
    var dbl = parseFloat(theValue);
    if (dbl < 1.0 || dbl > 12.0) {
	return error;
    }
    return "";
}

function checkSystolicBloodPressure(theValue) {
    if (theValue=="")
	return "";
    var error="血收缩压: 保持空白或者输入数字在70到210间\n";
    if (!isInteger(theValue)) {
	return error;
    }
    var i = parseInt(theValue);
    if (i < 70 || i > 210) {
	return error;
    }
    return "";
}

function checkWeightAndHeight(weight, height) {
    var weightError="体重: 输入数字应该在40到180之间\n";
    var heightError="身高: 输入数字应该在140到210\n";
    var onlyOneError="您只输入了身高或体重的其中之一:\n请同时输入或同时不输入.";
    var error="";
    var weightErrorSet = false;
    var heightErrorSet = false;
    if (weight=="" && height=="") {
	return "";
    }
    if (weight=="" || height=="") {
	return onlyOneError;
    }
    if (!isInteger(weight)) {
	weightErrotSet = true;
    }
    if (!isInteger(height)) {
	heightErrotSet = true;
    }
    if (!weightErrorSet) {
      var i = parseInt(weight);
      if (i < 40 || i > 180) {
	weightErrorSet = true;
      }
    }
    if (!heightErrorSet) {
      var i = parseInt(height);
      if (i < 140 || i > 210) {
	heightErrorSet = true;
      }
    }
   if (heightErrorSet) {
     error += heightError;
   }
   if (weightErrorSet) {
     error += weightError;
   }
   return error;
}

function checkAge(theValue) {
    var error="年龄: 输入数字在25到84间\n";
    if (!isInteger(theValue)) {
	return error;
    }
    var i = parseInt(theValue);
    if (i < 25 || i > 84) {
	return error;
    }
    return "";
}
function checkName(theValue){
    var pattern=new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）;—|{}【】‘；：”“'。，、？]");
    if (pattern.test($.trim($(theValue).val()))){
        return "账户姓名只能是汉字字母或数字\n";
    }else if($.trim(theValue) == ""){
        return "请填写账户姓名\n";
    }else{
        return "";
    }
}
function checkIDNum(theValue){
    /*var pattern=/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if(pattern.test(theValue)){
        return "身份证号输入不合法";
    }*/
    if($.trim(theValue) == ""){
        return "请填写ID号\n";
    }else{
        return "";
    }
}
/*function checkPostcode(theValue) {
    if (theValue=="")
	return "";
    var error="Postcode: the postcode is not recognised.  Please re-enter or leave blank.\n";
    theValue = theValue.replace(/\s/g, "");
    if (!isPostcode(theValue)) {
	return error;
    }
    return "";
}*/

function checkCalculatorForm(theForm) {
    /*theForm.postcode.value=theForm.postcode.value.toUpperCase();*/
    var why = "";
    why += checkName(theForm.username.value);
    why += checkIDNum(theForm.idnum.value);
    why += checkAge(theForm.age.value);
    /*why += checkPostcode(theForm.postcode.value);*/
    why += checkCholesterol(theForm.rati.value);
    why += checkSystolicBloodPressure(theForm.sbp.value);
    why += checkWeightAndHeight(theForm.weight.value, theForm.height.value);
    if (why != "") {
       alert(why);
       return false;
    }
    return true;
}
