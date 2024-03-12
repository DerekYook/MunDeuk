var imgObj = new Image();
function showImgWin(imgName) {
	imgObj.src = imgName;
	setTimeout("createImgWin(imgObj)", 100);
}
function createImgWin(imgObj) {
	if (!imgObj.complete) {
		setTimeout("createImgWin(imgObj)", 100);
		return;
	}
	imageWin = window.open("", "imageWin", "width=" + imgObj.width + ",height="
		+ imgObj.height);
	imageWin.document.write("<html><body style='margin:0'>");
	imageWin.document.write("<a href=javascript:window.close()><img src='"
		+ imgObj.src + "' border=0></a>");
	imageWin.document.write("</body><html>");
	imageWin.document.title = imgObj.src;
}

function getCheckBoxValue(objid) {
	return $('#' + objid).is(":checked");
}

function getRadioValue(objid) {
	return $('input:radio[name="' + objid + '"]').val();
}

function getSelectBoxValue(objid) {
	return $('#' + objid + ' > option:selected').val();
}

function getTextValue(objid) {
	return $('#' + objid).val();
}

function getTextAreaValue(objid) {
	return $('#' + objid).val();
}

function getTextAreaHtmlValue(objid) {
	return new Function("return" + 'CKEDITOR.instances.' + objid + '.getData()');



}

function fnMoibaAjax(url, formName, callBackFunction, methodType) {
	//$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
	$.ajax({
		url: url,
		type: methodType,
		cache: false,
		data: $('#' + formName + '').serialize(),
		success: function(result) {
			callBackFunction(result);
		}
	});
}

/**
 * Ajax 사용후 결과 리턴
 * 
 * @param url
 * @param formName
 * @param callBackFunction
 * @returns
 */
function fnAriAjaxString(url, formName, callBackFunction) {
	$(document).ajaxStart($.blockUI).ajaxStop($.unblockUI);
	$.ajax({
		url: url,
		type: "POST",
		cache: false,
		data: $('#' + formName + '').serialize(),
		success: function(result) {
			callBackFunction(result);
		},
		error: function(status, request, error) {
			alert("에러가 발생하였습니다.\n 상태코드:" + request.status + "\n 에러메시지:"
				+ request.responseText);
		}
	});
}
/**
 * ajax 사용후 해당 페이지 HTML 호출
 * 
 * @param url
 * @param formName
 * @param callBackFunction
 */
function fnAriAjaxHtml(url, formName, callBackFunction) {
	$.ajax({
		url: url,
		type: "POST",
		cache: false,
		data: $('#' + formName + '').serialize(),
		success: function(html) {
			callBackFunction(html);
		},
		error: function(status, request, error) {
			loadingStop();
			alert("에러가 발생하였습니다.\n 상태코드:" + request.status + "\n 에러메시지:"
				+ request.responseText);
		}
	});
}
/**
 * ajax 사용후 해당 페이지 HTML 호출2
 * 
 * @param url
 * @param formName
 * @param callBackFunction
 */
function fnMoibaAjaxHtml(url, formName, callBackFunction) {
	$.ajax({
		url: url,
		type: "POST",
		cache: false,
		data: $('#' + formName + '').serialize(),
		success: function(html) {
			callBackFunction(html);
		},
		error: function(status, request, error) {
			loadingStop();
		}
	});
}
/**
 * v1이 null이면 v2반환
 * 
 * @param v1
 * @param v2
 * @return
 */
function fnNvl(v1, v2) {
	if (!v1 || v1 == null || v1 == 'undefined') {
		return v2;
	}
	return v1;
}

/**
 * 쿠키값 세팅
 * 
 * @param cookieName
 *            쿠키명
 * @param cookieValue
 *            쿠키값
 * @param expiredays
 *            활성일수
 */
//function setCookie(cookieName, cookieValue, expiredays) {
//	var todayDate = new Date();
//	todayDate.setDate(todayDate.getDate() + expiredays);
//	document.cookie = cookieName + "=" + escape(cookieValue)
//			+ "; path=/; expires=" + todayDate.toGMTString() + ";";
//}
/**
 * 쿠키값 추출
 * 
 * @param cookieName
 *            쿠키명
 */
/*
function getCookie(cookieName) {
	var search = cookieName + "=";
	var cookie = document.cookie;

	// 현재 쿠키가 존재할 경우
	if (cookie.length > 0) {
		// 해당 쿠키명이 존재하는지 검색한 후 존재하면 위치를 리턴.
		startIndex = cookie.indexOf(cookieName);

		// 만약 존재한다면
		if (startIndex != -1) {
			// 값을 얻어내기 위해 시작 인덱스 조절
			startIndex += cookieName.length;

			// 값을 얻어내기 위해 종료 인덱스 추출
			endIndex = cookie.indexOf(";", startIndex);

			// 만약 종료 인덱스를 못찾게 되면 쿠키 전체길이로 설정
			if (endIndex == -1)
				endIndex = cookie.length;

			// 쿠키값을 추출하여 리턴
			return unescape(cookie.substring(startIndex + 1, endIndex));
		} else {
			// 쿠키 내에 해당 쿠키가 존재하지 않을 경우
			return false;
		}
	} else {
		// 쿠키 자체가 없을 경우
		return false;
	}
}
*/
function parseDateFont() {
	$("font.dateType").each(function() {
		var va = new String($(this).html());
		$(this).html(parseStr2Date(va));
		$(this).removeClass("dateType");
	});
}
/**
 * 특정 문자열을 날짜형식으로 출력한다.
 * 
 * @param input
 * @return
 */
function writeDate(input, d1, d2) {
	document.write(parseStr2Date(input, d1, d2));
}
function parseStr2Date(input, d1, d2) {
	var resultMsg = input;
	if (input != null) {
		if (d1 != null)
			var div1 = d1;
		else
			var div1 = ".";
		if (d2 != null)
			var div2 = d2;
		else
			var div2 = ":";
		if (input.length > 7) {// yyyy.mm.dd hh:mm:ss
			resultMsg = input.substr(0, 4) + div1 + input.substr(4, 2) + div1
				+ input.substr(6, 2);
		}
		if (input.length == 14 && div2 != 'N') {
			resultMsg += " " + input.substr(8, 2) + div2 + input.substr(10, 2)
				+ div2 + input.substr(12, 2);
		}
	}
	return resultMsg;
}
/**
 * 특정 문자열에서 엔터를 <br/>로 변경하여 반환한다.
 * 
 * @param input
 * @return
 */
function writeContents(input) {
	var resultMsg = input.split("\n").join("<br/>");
	resultMsg = input.split("<").join("&lt;");
	resultMsg = input.split(">").join("&gt;");
	document.write(resultMsg);
}

/**
 * 특정 길이로 잘라서 반환한다. ID를 입력하면 잘라낸 값을 그 ID에 넣어준다.
 */
function writeSubStr(input, c1, c2, target) {
	var result = input;
	if (c2 == null) {
		c2 = c1;
		c1 = 0;
	}
	if (input != null && input.length != 0 && input.length >= c2) {
		result = String(input).substring(c1, c2);
	}
	if (target != null) {
		if (document.getElementById(target) != null) {
			target = document.getElementById(target);
		}
		target.value = result;
	} else {
		document.write(result);
	}
	return result;
}

/**
 * 특정 길이로 잘라서 반환한다. ID를 입력하면 잘라낸 값을 그 ID에 넣어준다.
 */
function writeSubStr2(input, c1, c2, target) {
	var result = input;
	if (c2 == null) {
		c2 = c1;
		c1 = 0;
	}

	if (input != null && input.length != 0 && input.length >= c2) {
		if (input.length > c2) {
			result = String(input).substring(c1, c2) + "...";
		} else {
			result = input;
		}
	}
	if (target != null) {
		if (document.getElementById(target) != null) {
			target = document.getElementById(target);
		}
		target.value = result;
	} else {
		document.write(result);
	}
	return result;
}

/**
 * 특정 이후로 잘라서 반환한다. ID를 입력하면 잘라낸 값을 그 ID에 넣어준다.
 */
function writeLastSubStr(input, c1, target) {
	var result = input;
	result = String(input).substring(c1, input.length);
	if (target != null) {
		if (document.getElementById(target) != null) {
			target = document.getElementById(target);
		}
		target.value = result;
	} else {
		document.write(result);
	}
	return result;
}

/**
 * 이벤트 발생시 절대좌표를 반환한다.
 */
function GetAbsPosition(object) {
	var position = new Object;
	position.x = 0;
	position.y = 0;

	if (object != null) {
		position.x = object.offsetLeft;
		position.y = object.offsetTop;

		if (object.offsetParent) {
			var parentpos = GetAbsPosition(object.offsetParent);
			position.x += parentpos.x;
			position.y += parentpos.y;
		}
	}

	position.cx = object.offsetWidth;
	position.cy = object.offsetHeight;

	return position;
}

/**
 * 전체선택
 * 
 * @param type :
 *            전체선택 하고자 하는 checkbox의 id. 호출자의 id는 반드시 선택하고자 하는 id+'Checked'형식이어야
 *            한다.
 * @return
 */
function selectAll(type) {
	var c = $('#' + type + 'Checked').attr('checked');
	$('input[name=' + type + ']').attr('checked', c);
}

/**
 * Ajax를 이용한 간단한 Select의 option 변경
 * 
 * @param url :
 *            호출될 action 주소
 * @param data :
 *            파라미터들.. get방식의 파라미터 형식으로 하는게 편하다.
 * @param target :
 *            targetSelectBox의 id
 * @param defaultValue :
 *            초기 선택값
 * @param isAll :
 *            전체여부
 */
function ajaxSelect(url, data, target, defaultValue, isAll, headerKey) {
	if (!headerKey)
		headerKey = '';
	$
		.ajax({
			url: url,
			type: 'post',
			data: data,
			error: function(xhr, status, error) {
				alert(status);
			},
			beforeSend: function(x) {
				if (x && x.overrideMimeType) {
					x.overrideMimeType("application/xml;charset=UTF-8");
				}
			},
			success: function(data) {
				$("#" + target).hide();

				if ("true" == isAll || "y" == isAll || 'yes' == isAll) {
					$("#" + target).html(
						"<option value='" + headerKey + "'>전체</option>"
						+ data);
				} else if ("select" == isAll) {
					$("#" + target).html(
						"<option value='" + headerKey + "'>선택</option>"
						+ data);
				} else if ("false" == isAll || "n" == isAll
					|| 'no' == isAll) {
					$("#" + target).html(data);
				} else if (isAll != null) {
					$("#" + target).html(
						"<option value='" + headerKey + "'>" + isAll
						+ "</option>" + data);
				} else {
					$("#" + target).html(data);
				}

				$("#" + target).fadeIn(10);

				if (defaultValue && defaultValue != null
					&& defaultValue != '') {

					$set(target, defaultValue);
					// var targetName = $("#"+target)[0].name;
					// alert("target="+targetName+",
					// defaultValue="+defaultValue);

					// $("select[name="+targetName+"]
					// option[value="+defaultValue+"]").attr("selected",
					// "true");
				}
			}
		});
}

/**
 * Ajax를 이용한 간단한 Select의 option 변경
 * 
 * @param url :
 *            호출될 action 주소
 * @param data :
 *            파라미터들.. get방식의 파라미터 형식으로 하는게 편하다.
 * @param target :
 *            targetSelectBox의 id
 * @param defaultValue :
 *            초기 선택값
 * @param isAll :
 *            전체여부
 */
function ajaxMultiSelect(url, data, target, defaultValue, isAll, headerKey,
	onChangeScript) {
	if (!headerKey)
		headerKey = '';
	$
		.ajax({
			url: url,
			type: 'post',
			data: data,
			error: function(xhr, status, error) {
				alert(status);
			},
			beforeSend: function(x) {
				if (x && x.overrideMimeType) {
					x.overrideMimeType("application/xml;charset=UTF-8");
				}
			},
			success: function(data) {
				$("#" + target).hide();

				if ("true" == isAll || "y" == isAll || 'yes' == isAll) {
					$("#" + target).html(
						"<option value='" + headerKey + "'>전체</option>"
						+ data);
				} else if ("select" == isAll) {
					$("#" + target).html(
						"<option value='" + headerKey + "'>선택</option>"
						+ data);
				} else if ("false" == isAll || "n" == isAll
					|| 'no' == isAll) {
					$("#" + target).html(data);
				} else if (isAll != null) {
					$("#" + target).html(
						"<option value='" + headerKey + "'>" + isAll
						+ "</option>" + data);
				} else {
					$("#" + target).html(data);
				}

				$("#" + target).fadeIn(10);

				if (defaultValue && defaultValue != null
					&& defaultValue != '') {

					$set(target, defaultValue);
					// var targetName = $("#"+target)[0].name;
					// alert("target="+targetName+",
					// defaultValue="+defaultValue);

					// $("select[name="+targetName+"]
					// option[value="+defaultValue+"]").attr("selected",
					// "true");
				}
			}
		});
}

/**
 * Ajax를 이용한 간단한 Page 조회
 * 
 * @param url :
 *            호출될 action 주소
 * @param data :
 *            파라미터들.. get방식의 파라미터 형식으로 하는게 편하다.
 * @param target :
 *            targetSelectBox의 id
 * @param preHtml :
 *            결과로 받아온 문구 앞에 붙일 내용(필수 아님)
 */
function ajaxPage(url, data, target, preHtml, debug, debugTarget) {
	$.ajax({
		url: url,
		type: 'post',
		data: data,
		async: true,
		error: function(xhr, status, error) {
			alert(status);
			('#loading').fadeOut(150);
		},
		beforeSend: function(x) {
			$('#loading').fadeIn(150);
			if (x && x.overrideMimeType) {
				x.overrideMimeType("application/xml;charset=UTF-8");
			}
		},
		success: function(data) {
			$('#loading').fadeOut(10);
			if (debug) {
				if (debugTarget == null) {
					alert(data);
				} else {
					$("#debugMod").val(data);
					$("#debugMod").show();
				}
			}
			$("#" + target).hide();
			if (preHtml != null)
				data = preHtml + data;
			$("#" + target).html(data);
			$("#" + target).fadeIn(10);
		}
	});
}
/**
 * Ajax를 이용한 간단한 Page 조회
 * 
 * @param url :
 *            호출될 action 주소
 * @param data :
 *            파라미터들.. get방식의 파라미터 형식으로 하는게 편하다.
 * @param target :
 *            targetSelectBox의 id
 * @param preHtml :
 *            결과로 받아온 문구 앞에 붙일 내용(필수 아님)
 */
function ajaxPageSyn(url, data, target, preHtml, debug, debugTarget) {
	$.ajax({
		url: url,
		type: 'post',
		data: data,
		async: false,
		error: function(xhr, status, error) {
			alert(status);
			('#loading').fadeOut(150);
		},
		beforeSend: function(x) {
			$('#loading').fadeIn(150);
			if (x && x.overrideMimeType) {
				x.overrideMimeType("application/xml;charset=UTF-8");
			}
		},
		success: function(data) {
			$('#loading').fadeOut(10);
			if (debug) {
				if (debugTarget == null) {
					alert(data);
				} else {
					$("#debugMod").val(data);
					$("#debugMod").show();
				}
			}
			$("#" + target).hide();
			if (preHtml != null)
				data = preHtml + data;
			$("#" + target).html(data);
			$("#" + target).fadeIn(10);
		}
	});
}
/**
 * Ajax를 이용한 간단한 Page 조회
 * 
 * @param url :
 *            호출될 action 주소
 * @param data :
 *            파라미터들.. get방식의 파라미터 형식으로 하는게 편하다.
 * @param target :
 *            targetSelectBox의 id
 * @param preHtml :
 *            결과로 받아온 문구 앞에 붙일 내용(필수 아님)
 */
function ajaxValue(url, data, target, preHtml, debug) {
	$.ajax({
		url: url,
		type: 'post',
		data: data,
		error: function(xhr, status, error) {
			alert(status + "||" + error);
		},
		beforeSend: function(x) {
			if (x && x.overrideMimeType) {
				x.overrideMimeType("application/xml;charset=UTF-8");
			}
		},
		success: function(data) {
			if (debug) {
				alert(data);
			}
			$("#" + target).hide();
			if (preHtml != null && preHtml != "")
				data = preHtml + data;
			$("#" + target).val(data);
			$("#" + target).fadeIn(0);
			if ($("#" + target)[0].onChange != null) {
				$("#" + target)[0].onChange();
			}
		}
	});
}
function ajaxValues(url, data, target, preHtml, debug) {
	$.ajax({
		url: url,
		type: 'post',
		data: data,
		error: function(xhr, status, error) {
			alert(status);
			('#loading').fadeOut(150);
		},
		beforeSend: function(x) {
			if (x && x.overrideMimeType) {
				x.overrideMimeType("application/xml;charset=UTF-8");
			}
		},
		success: function(data) {
			if (debug) {
				if (data.length == 0)
					alert("아이디가 존재하지 않습니다 ");
			}
			$("#" + target).hide();
			if (preHtml != null)
				data = preHtml + data;
			$("#" + target).val(data);
			$("#" + target).fadeIn(10);
		}
	});
}

/**
 * Ajax를 이용한 간단한 Page 조회
 * 
 * @param url :
 *            호출될 action 주소
 * @param data :
 *            파라미터들.. get방식의 파라미터 형식으로 하는게 편하다.
 * @param target :
 *            targetSelectBox의 id
 */
function ajaxAppendPage(url, data, target, debug) {
	$.ajax({
		url: url,
		type: 'post',
		data: data,
		error: function(xhr, status, error) {
			alert(status);
			('#loading').fadeOut(150);
		},
		beforeSend: function(x) {
			$('#loading').fadeIn(150);
			if (x && x.overrideMimeType) {
				x.overrideMimeType("application/xml;charset=UTF-8");
			}
		},
		success: function(data) {
			if (debug) {
				alert(data);
			}
			$('#loading').fadeOut(10);
			$("#" + target).hide();
			$("#" + target).fadeIn(10);
			$("#" + target).append(data);
		}
	});
}
function ajaxSubmit(submitForm, action, iFrameName) {
	if ($("#isSubmit").val() == 'true') {
		alert('저장 중입니다 잠시만 기다려 주십시오.');
		return false;
	} else {

		$add("isSubmit", "true", submitForm);
		if (action != null) {
			submitForm.action = action;
		}
		if (iFrameName != null) {
			// alert(iFrameName);
			submitForm.target = iFrameName;
		} else {
			submitForm.target = "saveFrame";
		}
		submitForm.submit();
		return true;

	}
}

function ajaxSubmitNoAlert(submitForm, action, iFrameName) {
	$add("isSubmit", "true", submitForm);
	if (action != null) {
		submitForm.action = action;
	}
	if (iFrameName != null) {
		alert(iFrameName);
		submitForm.target = iFrameName;
	} else {
		submitForm.target = "saveFrame";
	}
	submitForm.submit();
	return true;
}

function ajaxSubmitNoAdd(submitForm, action, iFrameName) {

	if (action != null) {
		submitForm.action = action;
	}
	if (iFrameName != null) {
		alert(iFrameName);
		submitForm.target = iFrameName;
	} else {
		submitForm.target = "saveFrame";
	}
	submitForm.submit();
	return true;
}

/**
 * 이미지 업로드 미리보기
 * 
 * @return
 */
function existFile() {
	var targetElement = document.getElementById(event.srcElement.id + "Exist");
	if (targetElement) {
		targetElement.value = event.srcElement.value;
	}
}
/**
 * Select 에서 Option을 p에서 t로 복사한다. selectedCopyYn가 n일 때만 선택값을 복사하지 않는다.
 * 
 * @param p
 * @param t
 * @param selectedCopyYn
 * @return
 */
function copyOptions(p, t, selectedCopyYn) {
	$(t).html('');
	for (var i = 0; i < p.options.length; i++) {
		if (p.selectedIndex != i && selectedCopyYn == 'n') {
			var newOpt = new Option(p.options[i].text, p.options[i].value);
			t.options.add(newOpt);
		}
	}
}
function getSelectedOptionsText(p) {
	p = document.getElementById(p);
	if (p != null && p.selectedIndex != null) {
		return p.options[p.selectedIndex].text;
	}
	return "";
}
/**
 * 글자수를 새서 자른다.
 * 
 * @param inputObjId
 * @param maxLen
 * @param cntObjId
 * @return
 */
function fnMultiCharCountCut(content, maxLen) {
	var tempStr = "";
	var str_cnt = 0;

	for (i = 0; i < content.length; i++) {
		tempStr = content.charAt(i);
		if (escape(tempStr).length > 4) {
			str_cnt += 3;
		} else {
			str_cnt += 1;
		}
	}
	if (str_cnt > maxLen) {
		str_cnt = 0;
		tempStr2 = "";
		for (i = 0; i < maxLen; i++) {
			tempStr = content.charAt(i);
			if (escape(tempStr).length > 4) {
				str_cnt += 3;
			} else {
				str_cnt += 1;
			}
			if (str_cnt > maxLen) {
				if (escape(tempStr).length > 4) {
					str_cnt -= 3;
				} else {
					str_cnt -= 1;
				}
				tempStr2 += "…";
				break;
			} else
				tempStr2 += tempStr;
		}
		content = tempStr2;
	}
	return content;
}
/*
 * 글자수 제한 inputObjId : input text or textarea .... maxLen : 최대값 cntObjId : 생략
 * 가능, byte 표시 obj( ex> div, span..._
 */
function fnTypingCheck(inputObjId, maxLen, cntObjId) {
	var sContent = document.getElementById(inputObjId);
	if (sContent == null)
		sContent = inputObjId;
	var txtCnt;
	// cntObjId 가 지정되었을때만 사용
	if (cntObjId != null && cntObjId != "")
		txtCnt = document.getElementById(cntObjId);

	var tempStr = "";
	var str_cnt = 0;

	for (i = 0; i < sContent.value.length; i++) {
		tempStr = sContent.value.charAt(i);
		if (escape(tempStr).length > 4) {
			str_cnt += 3;
		} else {
			str_cnt += 1;
		}
	}

	if (str_cnt > maxLen) {
		alert(maxLen + " Bytes를 초과하였습니다.");
		str_cnt = 0;
		tempStr2 = "";
		for (i = 0; i < maxLen; i++) {
			tempStr = sContent.value.charAt(i);
			if (escape(tempStr).length > 4)
				str_cnt += 3;
			else
				str_cnt += 1;
			if (str_cnt > maxLen) {
				if (escape(tempStr).length > 4)
					str_cnt -= 3;
				else
					str_cnt -= 1;
				break;
			} else
				tempStr2 += tempStr;
		}
		sContent.value = tempStr2;
	}

	if (cntObjId != null && cntObjId != "")
		txtCnt.innerHTML = str_cnt + "/" + maxLen + " Bytes&nbsp;&nbsp;&nbsp;";
}
function fnMinLengthCheck(inputObjId, minLen) {
	var sContent = document.getElementById(inputObjId);
	if (sContent == null)
		sContent = inputObjId;
	var txtCnt;

	var tempStr = "";
	var str_cnt = 0;

	for (i = 0; i < sContent.value.length; i++) {
		tempStr = sContent.value.charAt(i);
		if (escape(tempStr).length > 4)
			str_cnt += 3;
		else
			str_cnt += 1;
	}

	if (str_cnt < minLen) {
		sContent.focus();
		alert(minLen + " Bytes보다 적을 수 없습니다.");
		return true;
	}
}

/**
 * 입력된 숫자에 자릿수를 구분하여 반환한다. 만약 초기값이 있으면서 값이 없으면 초기값을 반환한다.
 * 
 * @param number
 * @param def
 * @return
 */
function fnNumberFormatComma(number, def) {
	number = new String(number);
	tmp = number.split('.');
	var str = new Array();
	var v = tmp[0].replace(/,/gi, '');
	for (var i = 0; i <= v.length; i++) {
		str[str.length] = v.charAt(v.length - i);
		if (i % 3 == 0 && i != 0 && i != v.length) {
			str[str.length] = '.';
		}
	}
	str = str.reverse().join('').replace(/\./gi, ',');
	if (tmp.length == 2) {
		str = str + '.' + tmp[1];
	}
	if (str == '' && def != null) {
		str = def;
	}
	return str;
}
function writeNumberFormatComma(number, def) {
	document.write(fnNumberFormatComma(number, def));
}

/**
 * 단일문자 대체 str : 원본문자열 tarCh : 대체대상 문자 repCh : 대체할 문자
 */
function fnReplaceChar(str, tarCh, repCh) {
	var nowCh = "";
	var sumStr = "";
	var len = str.length;

	for (i = 0; i < len; i++) {
		if (str.charAt(i) == tarCh) {
			nowCh = repCh;
		} else {
			nowCh = str.charAt(i);
		}
		sumStr = sumStr + nowCh;
	}
	return sumStr;
}
function writeReplaceStr(str, tarCh, repCh) {
	var sumStr = String(str).replace(tarCh, repCh);
	document.write(sumStr);
	return sumStr;
}

function fnShowErrorMessage(divId, msg) {
	var target = document.getElementById(divId);
	$(target).html(msg);
	target.style.pixelLeft = 100;
	target.style.pixelTop = 70;
	target.style.width = 500;
	target.style.height = 430;
}

function fnShowDiv(divId) {
	var target = document.getElementById(divId);
	target.style.pixelLeft = 200;
	target.style.pixelTop = 100;
	target.style.width = 500;
	target.style.height = 430;
}
function fnChangePage(page, pageingUrl) {
	if (document.searchForm != null) {
		if ($('#pageNo')[0] == null) {
			$(document.searchForm).append(
				"<input type='hidden' name='pageNo' id='pageNo' value='"
				+ page + "'/>");
		}
		$('#pageNo').val(page);
		document.searchForm.submit();
	} else {
		document.location.href = pageingUrl;
	}
}
function WindowReset(win /* Window Object */) { // 새창의 크기와 위치 재설정
	//
	// 새창의 진행상황 체크 완료되면 코드실행
	//
	// while(win.document.readyState != 'complete'){}
	win.document.body.scroll = "auto";
	var winBody = win.document.body;

	//
	// 새창의 사이즈에 더해줄 marginWidth와 marginHeight
	//
	var marginWidth = parseInt(winBody.leftMargin)
		+ parseInt(winBody.rightMargin);
	//
	// 새창의 사이즈 설정
	//
	var wid = winBody.scrollWidth + (winBody.offsetWidth - winBody.clientWidth)
		+ marginWidth - 5;
	var hei = '700';

	win.resizeTo(wid, hei);
}
function scrollOn(win /* Window Object */) { // 새창의 크기와 위치 재설정
	//
	// 새창의 진행상황 체크 완료되면 코드실행
	//
	// while(win.document.readyState != 'complete'){}
	win.document.body.scroll = "auto";
}

/**
 * checkbox 체킹용
 * 
 * @param name
 * @param chk
 * @return
 */
function checkboxCheck(name, chk) {
	chk = chk.split(", ");
	name = document.getElementsByName(name);
	for (i = 0; i < name.length; i++) {
		for (j = 0; j < chk.length; j++) {
			if (String(name[i].value) == String(chk[j]) || chk == '') {
				name[i].checked = true;
			}
		}
	}
}

function excelDown(form) {
	form = $form(form);
	if ($("#excelYn")[0] == null) {
		$(form)
			.append(
				"<input type='hidden' name='excelYn' id='excelYn' value='Excel'/>");
	}
	form.submit();
	$("#excelYn").remove();
}

function getDate(target) {
	var Today = new Date();
	var MyYear = Today.getYear();
	var MyMonth = Today.getMonth() + 1; // 0부터 시작 +1을 더하여 1월은 1, 2월은 2...
	var MyDate = Today.getDate();
	$(target).val(MyYear + '' + MyMonth + MyDate);
	if ($(target).val() == '') {
		$('#' + target).val(MyYear + '' + MyMonth + MyDate);
	}
}

/**
 * 테이블을 그리드로 전환
 * 
 * @param id
 * @return
 */
function fnTableToGrid(id) {
	tableToGrid("#" + id);
	$("#" + id).setGridWidth(750);
	$("#" + id).setGridHeight(285);
}

function viewYn(id, b) {
	if (b) {
		$("#" + id).fadeIn(400);
		$("." + id).each(function() {
			$(this)[0].disabled = false;
		});
	} else {
		$("#" + id).hide(150);
		$("." + id).each(function() {
			$(this)[0].disabled = true;
		});
	}
}
function checkedYn(id, val) {
	if (val.length > 0) {
		$("#" + id)[0].checked = true;
		return true;
	} else {
		return false;
	}
}

function resNoCheck() {
	var resResult = true;
	$(".resNo").each(
		function() {
			if (this.id.indexOf('1') != -1) {
				if (this.type != 'hidden' && this.readonly != true
					&& this.disabled != true) {
					var resId = this.id.substr(0, this.id.length - 1);
					var resNo = $("#" + resId + 1).val()
						+ $("#" + resId + 2).val();
					if (!idNo_validate(resNo, this)) {
						return false;
					}
				}
			}
		});
	return resResult;
}
function idNo_validate(value, target) {
	temp = value;
	var temp1 = temp.substring(0, 6);
	var temp2 = temp.substring(6, 13);

	var titleMessage = '';
	var resId = target.id.substr(0, target.id.length - 1);
	$("#" + resId + 1 + "ResNoCheck").remove();
	$("#" + resId + 2 + "ResNoCheck").remove();
	if (temp.length < 13) {
		if (target.title && target.title != '' && target.title != 'undefined') {
			titleMessage = "'" + target.title + "'은(는) ";
		}
		$(
			"<font class='isValidation' color='red' id='"
			+ fnReplaceChar(target.id, '.', '_')
			+ "ResNoCheck'><br/></font>").html(
				titleMessage + ' 주민등록번호가 길이가 유효하지 않습니다. 다시 입력하세요.').appendTo(
					$(target).parent());
		return false;
	}

	if (temp2.charAt(0) <= "4") {// 내국인
		for (i = 0, sum = 0; i < 12; i++) {
			sum += (((i % 8) + 2) * (temp.charAt(i) - "0"));
		}
		sum = 11 - (sum % 11);
		sum = sum % 10;
		if (sum != temp.charAt(12)) {
			if (target.title && target.title != ''
				&& target.title != 'undefined') {
				titleMessage = "'" + target.title + "'은(는) ";
			}
			$(
				"<font class='isValidation' color='red' id='"
				+ fnReplaceChar(target.id, '.', '_')
				+ "ResNoCheck'></font>").html(
					titleMessage + ' 주민등록번호가 유효하지 않습니다. 다시 입력하세요.').appendTo(
						$(target).parent());
			return false;
		}
	} else {// 외국인
		if (fgn_no_chksum(temp) == false) {
			if (target.title && target.title != ''
				&& target.title != 'undefined') {
				titleMessage = "'" + target.title + "'은(는) ";
			}
			$(
				"<font class='isValidation' color='red' id='"
				+ fnReplaceChar(target.id, '.', '_')
				+ "ResNoCheck'></font>").html(
					titleMessage + ' 주민등록번호가 유효하지 않습니다. 다시 입력하세요.').appendTo(
						$(target).parent());
			return false;
		}
	}
	return true;
}

function fgn_no_chksum(reg_no) {
	var sum = 0;
	var odd = 0;
	var buf = new Array(13);
	for (i = 0; i < 13; i++) {
		buf[i] = parseInt(reg_no.charAt(i));
	}
	odd = buf[7] * 10 + buf[8];
	if (odd % 2 != 0) {
		return false;
	}
	if ((buf[11] != 6) && (buf[11] != 7) && (buf[11] != 8) && (buf[11] != 9)) {
		return false;
	}
	var multipliers = [2, 3, 4, 5, 6, 7, 8, 9, 2, 3, 4, 5];

	for (i = 0, sum = 0; i < 12; i++) {
		sum += (buf[i] *= multipliers[i]);
	}
	sum = 11 - (sum % 11);
	if (sum >= 10) {
		sum -= 10;
	}
	sum += 2;
	if (sum >= 10) {
		sum -= 10;
	}
	if (sum != buf[12]) {
		return false;
	} else {
		return true;
	}
}
function fnIsNotNumber(target) {
	if (isNaN(target.value)) {
		alert("숫자만 입력이 가능합니다.");
		$(target).val('');
	}
}

function fn_checkNumber() {

	if ((event.keyCode < 48) || (event.keyCode > 57)) {
		event.returnValue = false;
	}

}

function fnAddData(id) {
	return "&" + id + "=" + $get(id);
}

/**
 * form을 반환한다. id, name, form(tag)등을 뒤져서 없을 경우 새로 생성하여 반환한다.
 * 
 * @param form :
 *            form의 이름이나 id
 * @return
 */
function $form(form) {
	if (form != null) {
		return form;
	} else {
		var formName = "commandMap";
		form = $("form");
		if (form == null || form.length == 0) {
			var target = event;
			if (target == null) {
				target = $("#container")[0];
			} else {
				target = target.srcElement;
			}
			$(
				"<form name='" + formName + "' id='" + formName
				+ "' method='post'><input type='hidden' id='csrf_token' name='csrf_token' value=''></form>").appendTo(
					$(target).parent());
			form = document.commandMap;
		} else {
			form = form[0];
		}
		return form;
	}
}
/**
 * form의 해당 id를 지우고 새로 추가한다.
 * 
 * @param id
 * @param val
 * @param form
 * @return
 */
function $add(id, val, form) {
	form = $form(form);

	$("#" + id).remove();
	$(form).append(
		"<input type='hidden' name='" + id + "' id='" + id + "' value='"
		+ val + "'/>");
	return form;
}

function $val(name) {
	return $("input[name=" + name + "]").filter(function() {
		if (this.checked)
			return this;
	}).val();
}
function $get(id) {
	if (!(o = document.getElementById(id))) {
		name = document.getElementsByName(id);
		o = name[0];
		if (!o) {
			return "";
		}
	}
	var targetName = o.name;
	if (o != null && targetName != '') {
		switch (o.type) {
			case 'checkbox':
			case 'radio':
				var result = $("input[name=" + targetName + "]").filter(function() {
					if (this.checked)
						return this;
				}).val();
				if (result == null)
					result = "";
				return result;
			case 'select-one':
				return o.value;
			case 'text':
			case 'hidden':
			case 'textarea':
			case 'password':
				return $("input[name=" + targetName + "]").val();
		}
	}
	return '';
}
function $set(id, value) {
	try {
		if (!(o = document.getElementById(id))) {
			name = document.getElementsByName(id);
			o = name[0];
		}
		var targetName = o.name;
		if (o != null && targetName != '') {
			switch (o.type) {
				case 'checkbox':
				case 'radio':
					$("input[name=" + targetName + "]").filter(
						'input[value=' + value + ']').attr("checked", "true");
					break;
				case 'select-one':
					$("#" + id + " option[value=" + value + "]").attr("selected",
						"true");
					break;
				case 'text':
				case 'textarea':
					$("#" + id).val(value);
					break;
			}
		}
	} catch (e) {
		console.log("set id, value exception!");
	}
}
function $setData(value) {
	var result = Object();
	if (value != null && value.indexOf("&") != -1) {
		var list = value.split("&");
		for (i = 0; i < list.length; i++) {
			if (list[i] != null && list[i].indexOf("=") != -1) {
				var inputData = list[i].split("=");
				if (inputData[0] != null && inputData[0].length > 0
					&& inputData.length == 2 && inputData[1] != null) {
					if ((o = $("#" + inputData[0])[0]) != null) {
						switch (o.type) {
							case 'checkbox':
							case 'radio':
								$("input[id=" + inputData[0] + "]").filter(
									'input[value=' + inputData[1] + ']').attr(
										"checked", "true");
								break;
							case 'text':
							case 'textarea':
								$("input[id=" + inputData[0] + "]").attr("value",
									inputData[1]);
								break;
						}
					}
					try {
						var o = new Function("return" + "result." + inputData[0] + "='" + inputData[1] + "'");


					} catch (e) {
						console.log("$setData value exception!");
					}
				}
			}
		}
	}
	return result;
}

function $file(id, param) {
	var maxFileNum = $("#posblAtchFileNumber").val();
	if (maxFileNum == null || maxFileNum == "") {
		maxFileNum = 9;
	}
	var fileObj = document.getElementById(id);
	$("<div id='" + id + "ListDiv'></div>").appendTo($(fileObj).parent());
	var multi_selector = new MultiSelector(document.getElementById(id
		+ 'ListDiv'), maxFileNum, id, param);
	multi_selector.addElement(fileObj);
}

String.prototype.comma = function() {
	tmp = this.split('.');
	var str = new Array();
	var v = tmp[0].replace(/,/gi, '');
	for (var i = 0; i <= v.length; i++) {
		str[str.length] = v.charAt(v.length - i);
		if (i % 3 == 0 && i != 0 && i != v.length) {
			str[str.length] = '.';
		}
	}
	str = str.reverse().join('').replace(/\./gi, ',');
	return (tmp.length == 2) ? str + '.' + tmp[1] : str;
}

function onlyNum(obj) {
	obj.value = fnToNumber(obj.value);
}

function fnToNumber(val) {
	var re = /[^0-9\.\,\-]/gi;
	return val.replace(re, '');
}

// comma 제거
function fnRemoveComma(val) {
	var re = /,/g;
	return val.replace(re, "");
}

/**
 * 1원 절삭
 * 
 * @param val
 * @return
 */
function fnRound(val, depth) {
	if (depth == null) {
		return Math.round(val / 10) * 10;
	} else {
		for (dep = 0; dep < depth; dep++) {
			val = val * 10;
		}
		val = Math.round(val * 1);
		for (dep = 0; dep < depth; dep++) {
			val = val / 10;
		}
	}
	if (val == null || val == '' || val == ' ') {
		val = 0;
	}
	return val;
}
// -----------------------------------------------------------------------------
// 문자의 좌, 우 공백 제거
// -----------------------------------------------------------------------------
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

// -----------------------------------------------------------------------------
// 문자의 좌 공백
// -----------------------------------------------------------------------------
String.prototype.ltrim = function() {
	return this.replace(/(^\s*)/, "");
};

// -----------------------------------------------------------------------------
// 문자의 우 공백 제거
// -----------------------------------------------------------------------------
String.prototype.rtrim = function() {
	return this.replace(/(\s*$)/, "");
};

// -----------------------------------------------------------------------------
// 숫자만 가져 오기
// -----------------------------------------------------------------------------
String.prototype.num = function() {
	return (this.trim().replace(/[^0-9]/g, ""));
}

// -----------------------------------------------------------------------------
// 숫자에 3자리마다 , 를 찍어서 반환
// -----------------------------------------------------------------------------
String.prototype.cvtNumber = function() {
	var num = this.trim();
	while ((/(-?[0-9]+)([0-9]{3})/).test(num)) {
		num = num.replace((/(-?[0-9]+)([0-9]{3})/), "$1,$2");
	}
	return num;
};

// -----------------------------------------------------------------------------
// 숫자와 영어만 허용 - arguments[0] : 추가 허용할 문자들
// -----------------------------------------------------------------------------

String.prototype.isEngNum = function() {
	return (/^[0-9a-zA-Z]+$/).test(this.remove(arguments[0])) ? true : false;
};

// -----------------------------------------------------------------------------
// 숫자와 영어만 허용 - arguments[0] : 추가 허용할 문자들
// -----------------------------------------------------------------------------

String.prototype.isNumEng = function() {
	return this.isEngNum(arguments[0]);
};

/*
 * Doosim Dealer Grid 검색 파라미터를 생성한다.
 * 
 * @objid - Object Id
 */
function fnGenerateParam(objid) {
	var result = "";

	// $(document).ready(function() {

	$('#' + objid + ' :input').each(function(index) {
		result += "&" + $(this).get(0).name + "=" + $(this).get(0).value;
	});

	// alert(result);

	// });
	return result;

}

/*******************************************************************************
 * 숫자만 입력가능하게. 48~57(0번 ~ 9번), 95~105(키패드0번~9번), 37~40(방향키), 8(백스페이스), 46(Del키),
 * 9(TAB키) ex1) <input type="text" name="aaa" onKeyDown="onlynumber(event);"/>
 * ex2) $("#cpsno").keydown(function() { onlynumber(event); });
 ******************************************************************************/
function onlyNumber(event) {
	// var eventKey = event.which;
	var eventKey = event.keyCode ? event.keyCode : event.which ? event.which
		: event.charCode;
	if ((eventKey > 47 && eventKey < 58) || (eventKey > 95 && eventKey < 106)
		|| (eventKey > 36 && eventKey < 41) || eventKey == 8
		|| eventKey == 46 || eventKey == 9) {

	} else {
		// alert("숫자만 입력가능");
		// event.preventDefault();
		(event.preventDefault) ? event.preventDefault()
			: event.returnValue = false;
	}
}

function onlyCnAlpha(event) {
	// 숫자, 특수문자 입력 불가능
	// var eventKey = event.which;
	var eventKey = event.keyCode ? event.keyCode : event.which ? event.which
		: event.charCode;
	if (!((eventKey > 32 && eventKey < 65) || (eventKey > 90 && eventKey < 97) || (eventKey > 122 && eventKey < 126))) {

	} else {
		// alert("숫자만 입력가능");
		// event.preventDefault();
		(event.preventDefault) ? event.preventDefault()
			: event.returnValue = false;
	}
}

/*******************************************************************************
 * Number Field Focus Event.
 ******************************************************************************/
function fnOnFocusNumField(obj) {
	obj.value = ("" + obj.value).num();
}

/*******************************************************************************
 * Number Field Blur Event.
 ******************************************************************************/
function fnOnBlurNumField(obj) {
	obj.value = ("" + obj.value).cvtNumber();
}

/*******************************************************************************
 * 숫자를 체크하다가 6자 등 원하는 만큼 이동후 다음 input 박스로 이동 시키는...
 ******************************************************************************/
function goJump(fname, len, goname) {
	if (document.all[fname].value.length == len)
		document.all[goname].focus();
}

/*******************************************************************************
 * 문자열이 숫자형태 인지 체크
 * 
 * @param str
 * @param inputName
 * @return (kc < 48 || kc > 57) //키보드 0,1,2,3,4,5,6,7,8,9 를 제외한 키코드 (kc < 96 ||
 *         kc > 105) //키보드 num0, num1 ........ num9를 제외한 키코드 (kc != 8 && kc !=
 *         9) //키보드 backspace키와 tab키를 제외한 키코드 *
 ******************************************************************************/
function strCheckNum(inputName) {
	var kc = event.keyCode;
	if (!((kc > 47 && kc < 58) || (kc > 95 && kc < 106) || kc == 8 || kc == 9 || kc == 13)) {
		alert('숫자만 입력할 수 있습니다.');
		$("[name = " + inputName + "]").val("");
		$("[name = " + inputName + "]").focus();
		return false;
	}
}
/*******************************************************************************
 * 시간 체크
 * 
 * @param 시간
 *            입력 value , 분 입력 value , 시간 입력 input Name , 분 입력 input name
 * @description 시간 체크 및 한자리 일경우 앞에 0을 붙여준다.
 ******************************************************************************/
function timeCheck(Hour, Minute, hourName, minuteName) {
	var resultTime = new Date(0, 0, 0, Hour, Minute, 0);

	if (resultTime.getHours() != Hour || resultTime.getMinutes() != Minute) {
		alert("시간을 제대로 입력하세요.");
		return false;
	}
	if (Hour.length < 2) {
		$("#" + hourName).val("0" + Hour);
	}
	if (Minute.length < 2) {
		$("#" + minuteName).val("0" + Minute);
	}

	return true;
}

/*
 * ----------------------------------------------------------------------------
 * 특정 날짜에 대해 지정한 값만큼 가감(+-)한 날짜를 반환
 * 
 * 입력 파라미터 ----- pInterval : "yyyy" 는 연도 가감, "m" 은 월 가감, "d" 는 일 가감 pAddVal : 가감
 * 하고자 하는 값 (정수형) pYyyymmdd : 가감의 기준이 되는 날짜 pDelimiter : pYyyymmdd 값에 사용된 구분자를
 * 설정 (없으면 "" 입력)
 * 
 * 반환값 ---- yyyymmdd 또는 함수 입력시 지정된 구분자를 가지는 yyyy?mm?dd 값
 * 
 * 사용예 --- 2008-01-01 에 3 일 더하기 ==> addDate("d", 3, "2008-08-01", "-"); 20080301
 * 에 8 개월 더하기 ==> addDate("m", 8, "20080301", "");
 * ---------------------------------------------------------------------------
 */
function addDate(pInterval, pAddVal, pYyyymmdd, pDelimiter) {
	var yyyy;
	var mm;
	var dd;
	var cDate;
	var oDate;
	var cYear, cMonth, cDay;

	if (pDelimiter != "") {
		pYyyymmdd = pYyyymmdd.replace(new Function("return" + "/\\" + pDelimiter + "/g"), "");
	}


	yyyy = pYyyymmdd.substr(0, 4);
	mm = pYyyymmdd.substr(4, 2);
	dd = pYyyymmdd.substr(6, 2);

	if (pInterval == "yyyy") {
		yyyy = (yyyy * 1) + (pAddVal * 1);
	} else if (pInterval == "m") {
		mm = (mm * 1) + (pAddVal * 1);
	} else if (pInterval == "d") {
		dd = (dd * 1) + (pAddVal * 1);
	}

	cDate = new Date(yyyy, mm - 1, dd) // 12월, 31일을 초과하는 입력값에 대해 자동으로 계산된 날짜가
	// 만들어짐.
	cYear = cDate.getFullYear();
	cMonth = cDate.getMonth() + 1;
	cDay = cDate.getDate();

	cMonth = cMonth < 10 ? "0" + cMonth : cMonth;
	cDay = cDay < 10 ? "0" + cDay : cDay;

	if (pDelimiter != "") {
		return cYear + pDelimiter + cMonth + pDelimiter + cDay;
	} else {
		return cYear + cMonth + cDay;
	}

}

// 현재 날짜 가져오기
// dayType d를 넘기면 일까지만!!
function getTimeStamp(dayType) {
	var d = new Date();

	var s = "";
	if (dayType == "d") {
		s = leadingZeros(d.getFullYear(), 4) + '-'
			+ leadingZeros(d.getMonth() + 1, 2) + '-'
			+ leadingZeros(d.getDate(), 2);
	} else {
		s = leadingZeros(d.getFullYear(), 4) + '-'
			+ leadingZeros(d.getMonth() + 1, 2) + '-'
			+ leadingZeros(d.getDate(), 2) + ' ' +

			leadingZeros(d.getHours(), 2) + ':'
			+ leadingZeros(d.getMinutes(), 2) + ':'
			+ leadingZeros(d.getSeconds(), 2);
	}

	return s;
}

// 0으로 채운다.
function leadingZeros(n, digits) {
	var zero = '';
	n = n.toString();

	if (n.length < digits) {
		for (var i = 0; i < digits - n.length; i++)
			zero += '0';
	}
	return zero + n;
}

/****************************************************
tbl      : 병합할 대상 table object
startRow : 병합 시작 row, title 한 줄일 경우 1
cNum     : 병합 실시할 컬럼번호, 0부터 시작
length   : 병합할 row의 길이, 보통 1
add      : 비교할 기준에 추가할 컬럼번호
		  A | 1
		  B | 1
		 을 서로 구분하고 싶다면, add에 0번째
		 컬럼을 추가
*****************************************************/
function mergeCell(tbl, startRow, cNum, length, add) {
	var isAdd = false;
	if (tbl == null)
		return;
	if (startRow == null || startRow.length == 0)
		startRow = 1;
	if (cNum == null || cNum.length == 0)
		return;
	if (add == null || add.length == 0) {
		isAdd = false;
	} else {
		isAdd = true;
		add = parseInt(add);
	}
	cNum = parseInt(cNum);
	length = parseInt(length);

	rows = tbl.rows;
	rowNum = rows.length;

	tempVal = '';
	cnt = 0;
	startRow = parseInt(startRow);

	for (var i = startRow; i < rowNum; i++) {
		curVal = rows[i].cells[cNum].innerHTML;
		if (isAdd)
			curVal += rows[i].cells[add].innerHTML;
		if (curVal == tempVal) {
			if (cnt == 0) {
				cnt++;
				startRow = i - 1;
			}
			cnt++;
		} else if (cnt > 0) {
			merge(tbl, startRow, cnt, cNum, length);
			startRow = endRow = 0;
			cnt = 0;
		} else {
		}
		tempVal = curVal;
	}

	if (cnt > 0) {
		merge(tbl, startRow, cnt, cNum, length);
	}
}

/*******************************************************************************
 * mergeCell에서 사용하는 함수
 ******************************************************************************/
function merge(tbl, startRow, cnt, cellNum, length) {
	rows = tbl.rows;
	row = rows[startRow];

	for (var i = startRow + 1; i < startRow + cnt; i++) {
		for (var j = 0; j < length; j++) {
			rows[i].deleteCell(cellNum);
		}
	}
	for (var j = 0; j < length; j++) {
		row.cells[cellNum + j].rowSpan = cnt;
	}
}

/**
 * 날짜 기간 검색 조회시 검색 시작 날짜와 종료 날짜 vaildation check
 * 시작 날짜와 종료 날짜의 차이는 최대 dd일(0은 제한없음)
 */
function validDate(fromDate, toDate, dd) {
	if (fromDate > toDate) {
		alert("날짜 선택이 잘못 되었습니다.");
		return false;
	} else {
		var fromCal = new Date(fromDate);
		var toCal = new Date(toDate);

		var dates = (toCal - fromCal) / 86400000;
		if (dates > dd) {
			alert("최대 " + dd + "일 까지만 조회할 수 있습니다.");
			return false;
		} else {
			return true;
		}

	}
}
function loadingStart() {
	var agt = navigator.userAgent.toLowerCase();
	if (agt.indexOf("chrome") != -1) {
		$("[id^='newregister_loading']").attr("style", "display:block");
	} else {
		$("#popup").append('<div id="newregister_loading" style="display:block;"><div class="transparent"></div> <img id="lodingIMG" name="lodingIMG" class="pop_listwrap_loading" src="/images/ajax-loader03.gif" alt="로딩 중입니다" /> </div>');
	}
}
function loadingStop() {
	$("[id^='newregister_loading']").attr("style", "display:none");
	//	$("[id^='newregister_loading']").html("");
}


/**
 * 이진수를 십진수로 변환
 */
function binToDecimal(bin) {
	var len = bin.length;
	var sum = 0;
	var tt = 1;
	for (x = len - 1, y = len; x >= 0; x--, y--) {
		var ch = bin.substring(x, y);
		if (ch == "1") {
			sum = sum + tt;
			tt = tt * 2;
		} else {
			sum = sum;
			tt = tt * 2;
		}
	}
	return sum;
}

/**
 * 입력한 문자열 왼쪽에서 입력한 자리수 만큼의 문자열을 가져온다.
 */
function Left(Str, Num) {
	if (Num <= 0)
		return "";
	else if (Num > String(Str).length)
		return Str;
	else
		return String(Str).substring(0, Num);
}

/**
 * 입력한 문자열 오른쪽에서 입력한 자리수 만큼의 문자열을 가져온다.
 */
function Right(Str, Num) {
	if (Num <= 0)
		return "";
	else if (Num > String(Str).length)
		return Str;
	else {
		var iLen = String(Str).length;
		return String(Str).substring(iLen, iLen - Num);
	}
}

