/**
 * form serialize
 */
jQuery.fn.serializeObject = function() {
	var obj = null;
	try {
		if (this[0].tagName && this[0].tagName.toUpperCase() == "FORM") {
			var arr = this.serializeArray();
			if (arr) {
				obj = {};
				jQuery.each(arr, function() {
					obj[this.name] = this.value;
				});
			}
		}
	} catch (e) {
		alert(e.message);
	} finally { }
	return obj;
}

/**
 * print Error log
 */
var printError = function(error, explicit) {
	const errorGb = explicit ? 'EXPLICIT' : 'INEXPLICIT';
	//console.log("[" + errorGb + "] " + error.name + " " + error.message);
}

/**
 * ajax return message
 * 
 * @param resultCode 
 * @returns message
 */
function getResultMsg(code) {
	var msg = "";
	//alert(code);
	switch (code) {
		case -1000:
			msg = '요청이 실패하였습니다.';
			break;
		case -1001:
			msg = '로그인 정보가 잘못되었습니다.';
			break;
		case -1002:
			msg = '아이디 또는 비밀번호를 잘못 입력했습니다.\n로그인 5회 오류 시 계정이 잠금 처리됩니다.\n입력한 내용을 다시 확인해 주세요.';
			break;
		case -1003:
			msg = '이미 회원이 존재합니다.';
			break;
		case -1004:
			msg = '로그인 5회 오류로 계정이 잠금 처리되었습니다.\n비밀번호 찾기로 비밀번호를 변경해주세요.';
			break;
		case -10041:
			msg = '로그인 5회 오류로 계정이 잠금 처리되었습니다.\n다른 관리자 계정으로 비밀번호를 초기화 해주세요.';
			break;
		case -1005:
			msg = '검증키가 유효하지 않거나 만료되었습니다.';
			break;
		case -1006:
			msg = '외부 인증 공급자에서 이메일을 찾을 수 없습니다.';
			break;
		case -1007:
			msg = '외부 인증 공급자 정보가 일치하지 않습니다.';
			break;
		case -1008:
			msg = "이메일 인증 후 로그인이 가능합니다.";
			break;	
		case -1401:
			msg = '로그아웃 또는 로그인 정보가 만료되었습니다.';
			break;
		case -1403:
			msg = '접근가능한 권한을 가지고 있지 않습니다.';
			break;
		case -1404:
			msg = '페이지를 찾을 수 없습니다.';
			break;
		case -1500:
			msg = '브라우저 쿠키 설정을 허용해 주세요.';
			break;
		case -1600:
			msg = '이벤트 언어 정보가 잘못되었습니다.';
			break;
		case -1601:
			msg = '이벤트 정보가 잘못되었습니다.';
			break;
		case -1602:
			msg = '이벤트가 오픈되지 않았습니다.';
			break;
		case -1603:
			msg = '이벤트 인증키가 잘못되었습니다.';
			break;
		case -2001:
			msg = '내부 오류가 발생했습니다.';
			break;
		case -2002:
			msg = '잘못된 접근 오류가 발생했습니다.';
			break;
		case -2003:
			msg = '허용되지 않은 접근 방식입니다';
			break;
		case -2004:
			msg = '요청이 거부되었습니다.';
			break;
		case -2005:
			msg = '간사 계정만 로그인이 가능합니다.';
			break;
		case -2006:
			msg = '심의위원 계정만 로그인이 가능합니다.';
			break;
		case -2007:
			msg = '심의기간이 아닙니다.';
			break;
		case -5000:
			msg = "패스워드를 확인해주세요.";
			break;
		case -5004:
			msg = '기존 패스워드를 확인해주세요.';
			break;
		case -5005:
			msg = '신규 패스워드를 확인해주세요.';
			break;
		case -6000:
			msg = '주주명부 등록을 실패했습니다.';
			break;
		case -8010:
			msg = '중복된 코드 입니다.';
			break;
		case -9999:
			msg = '알수 없는 오류가 발생하였습니다.';
			break;
		case 0:
			msg = 'OK';
			break;
		default:
			msg = 'OK';
			break;
	}
	return msg;
}

/**
 * ajax return message
 * 
 * @param resultCode 
 * @returns message
 */
function getResultMsgEn(code) {
	var msg = "";
	//alert(code);
	switch (code) {
		case -1000:
			msg = 'Request fail.';
			break;
		case -1001:
			msg = 'Invalid login info.';
			break;
		case -1002:
			msg = 'No member found.';
			break;
		case -1003:
			msg = 'You are an existing member';
			break;
		case -1004:
			msg = 'Exeed the login error limit.';
			break;
		case -1005:
			msg = 'Invalid verification key.';
			break;
		case -1006:
			msg = 'Email not found';
			break;
		case -1007:
			msg = 'Invalid OAuth2 provider.';
			break;
		case -1008:
			msg = "이메일 인증 후 로그인이 가능합니다."		
			break;
		case -1401:
			msg = 'You do not have permission to access this resource.';
			break;
		case -1403:
			msg = 'A resource that can not be accessed with the privileges it has.';
			break;
		case -1404:
			msg = 'Page not found.';
			break;
		case -1500:
			msg = 'Browser cookie is disabled.';
			break;
		case -1600:
			msg = 'Invalid event language.';
			break;
		case -1601:
			msg = 'Invalid event info.';
			break;
		case -1602:
			msg = 'Event is closed.';
			break;
		case -1603:
			msg = 'Invalid event verification key.';
			break;
		case -2001:
			msg = 'Null-pointer error has occurred.';
			break;
		case -2002:
			msg = 'Invalid csrf token.';
			break;
		case -2003:
			msg = 'Invalid captcha.';
			break;
		case -2004:
			msg = 'Invalid parameter';
			break;
		case -5000:
			msg = "Invalid password.";
			break;
		case -5004:
			msg = 'Check current password.';
			break;
		case -5005:
			msg = 'Check new password.';
			break;
		case -6000:
			msg = 'Excel upload failed.';
			break;
		case -9999:
			msg = 'An unknown error has occurred.';
			break;
		case 0:
			msg = 'OK';
			break;
		default:
			msg = 'OK';
			break;
	}
	return msg;
}


/**
 * Ajax 사용후 결과 리턴
 * 
 * @param methodType 
 * @param url
 * @param jsonData
 * @param callBackFunction
 * @param callBackErrorFunction(optional) 
 * @returns
 */
function fnMcenAjaxJson(methodType, url, jsonData, callBackFunction, callBackErrorFunction) {

	const token = $("meta[name='_csrf']").attr("content");
	const header = $("meta[name='_csrf_header']").attr("content");
	const grecc = $("meta[name='_grecc']").attr("content");
	let action;
	if (url.lastIndexOf('/') == -1) {
		action = url;
	} else {
		action = url.substr(url.lastIndexOf('/') + 1);
	}
	//console.log("send token : " + token);
	//console.log("url : " + url);
	//console.log("action : " + action);
	//console.log("url lastIndexOf : "+url.lastIndexOf('#'));
	//console.log("url action : "+url.substr(url.lastIndexOf('/')));

	//	grecaptcha.ready(function() {
	//		grecaptcha.execute(grecc, {action: action}).then(function(responseToken) {
	//onsole.log(responseToken);
	$.ajax({
		type: methodType,
		url: url,
		contentType: 'application/json',
		data: jsonData,
		dataType: "json",
		//headers:{
		//	"grecc":responseToken
		//},
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data, status, xhr) {
			//console.log("After ajax call1 : " + data.newToken);
			if ( data.newToken != undefined){
				if (data.newToken != '') {
					$("meta[name='_csrf']").attr("content", data.newToken);
					if ($("input[name='_csrf']").val() != undefined) {
						$("input[name='_csrf']").val(data.newToken);
					}
					//$("input[name='_csrf']").val(data.newToken);
					//alert($("meta[name='_csrf']").attr("content"));
				}
			} else {
				alert(data.resultCode);
			} 
			callBackFunction(data);
		},
		error: function(xhr, textStatus, errorThrown) {
			//alert(textStatus + " " + errorThrown);
			//console.log("[Error1] call " + textStatus + " return error!");
			//console.log("[Error1] call " + errorThrown + " return error!");
			//console.log("[Error1] call " + url + " return error!");
			//console.log(jqXHR.responseText);
			if (callBackErrorFunction != "") {
				callBackErrorFunction(xhr.responseText);
			} else {
				try {
					//console.log("111111");
					const data = JSON.parse(xhr.responseText);
					//console.log("error data : " + data);
					//console.log("error data.resultCode : " + data.resultCode);
					//console.log(getResultMsg(data.resultCode));
					if (data.resultCode == 0 || data.resultCode == -8010) {
						//console.log("1111_1");
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						//console.log(data);
						alert(getResultMsg(data.resultCode));
						//callBackFunction(data);
					} else if (data.resultCode == -2002) {
						//console.log("2222");
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						//console.log(data);
						//alert(getResultMsg(data.resultCode));
						callBackFunction(data);
					} else {
						//console.log("3333");
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						//console.log(data);
						callBackFunction(data);
					}
				} catch (e) {
					alert('잘못된 호출입니다!');
					if (e instanceof SyntaxError) {
						printError(e, true);
					} else {
						printError(e, false);
					}
					location.reload();
				}

			}
		}
	});

	//		});
	//	});			


}
/**
 * Ajax 사용후 결과 리턴 netfunnel
 * 
 * @param methodType 
 * @param url
 * @param jsonData
 * @param callBackFunction
 * @param callBackErrorFunction(optional) 
 * @returns
 */
function fnMcenAjaxJson_net(methodType, url, jsonData, callBackFunction, callBackErrorFunction) {

	const token = $("meta[name='_csrf']").attr("content");
	const header = $("meta[name='_csrf_header']").attr("content");
	const grecc = $("meta[name='_grecc']").attr("content");
	let action;
	if (url.lastIndexOf('/') == -1) {
		action = url;
	} else {
		action = url.substr(url.lastIndexOf('/') + 1);
	}
	//console.log("send token : " + token);
	//console.log("url : " + url);
	//console.log("action : " + action);
	//console.log("url lastIndexOf : "+url.lastIndexOf('#'));
	//console.log("url action : "+url.substr(url.lastIndexOf('/')));

	//	grecaptcha.ready(function() {
	//		grecaptcha.execute(grecc, {action: action}).then(function(responseToken) {
	//onsole.log(responseToken);
	$.ajax({
		type: methodType,
		url: url,
		contentType: 'application/json',
		data: jsonData,
		dataType: "json",
		//headers:{
		//	"grecc":responseToken
		//},
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data, status, xhr) {
	
			//console.log("After ajax call : " + data.newToken);
			if (data.newToken != '') {
				$("meta[name='_csrf']").attr("content", data.newToken);
				if ($("input[name='_csrf']").val() != undefined) {
					$("input[name='_csrf']").val(data.newToken);
				}
				//$("input[name='_csrf']").val(data.newToken);
				//alert($("meta[name='_csrf']").attr("content"));
			}
			callBackFunction(data);
		},
		error: function(xhr, textStatus, errorThrown) {
		
			//alert(textStatus + " " + errorThrown);
			//console.log("[Error1] call " + url + " return error!");
			//console.log(jqXHR.responseText);
			if (callBackErrorFunction != "") {
				callBackErrorFunction(xhr.responseText);
			} else {
				try {
					//console.log("111111");
					const data = JSON.parse(xhr.responseText);
					//console.log("error data : " + data);
					//console.log("error data.resultCode : " + data.resultCode);
					//console.log(getResultMsg(data.resultCode));
					if (data.resultCode == 0) {
						//console.log("1111_1");
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						//console.log(data);
						alert(getResultMsg(data.resultCode));
						//callBackFunction(data);
					} else if (data.resultCode == -2002) {
						//console.log("2222");
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						//console.log(data);
						//alert(getResultMsg(data.resultCode));
						callBackFunction(data);
					} else {
						//console.log("3333");
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						//console.log(data);
						callBackFunction(data);
					}
				} catch (e) {
					alert('잘못된 호출입니다!');
					if (e instanceof SyntaxError) {
						printError(e, true);
					} else {
						printError(e, false);
					}
					location.reload();
				}

			}
		}
	});

	//		});
	//	});			


}

/**
 * Ajax 사용후 결과 리턴 - string type
 * 
 * @param methodType 
 * @param url
 * @param jsonData
 * @param callBackFunction
 * @param callBackErrorFunction(optional) 
 * @returns
 */
function fnMcenAjaxString(methodType, url, jsonData, callBackFunction, callBackErrorFunction) {

	const token = $("meta[name='_csrf']").attr("content");
	const header = $("meta[name='_csrf_header']").attr("content");
	//console.log("send token : " + token);
	$.ajax({
		type: methodType,
		url: url,
		contentType: 'application/json',
		data: jsonData,
		dataType: "text",
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data, status, xhr) {
			//console.log("After ajax call : " + data.newToken);
			if (data.newToken != '') {
				$("meta[name='_csrf']").attr("content", data.newToken);
				if ($("input[name='_csrf']").val() != undefined) {
					$("input[name='_csrf']").val(data.newToken);
				}
			}
			callBackFunction(data);
		},
		error: function(xhr, textStatus, errorThrown) {
			//console.log("[Error] call " + url + " return error!");
			//console.log(jqXHR.responseText);
			if (callBackErrorFunction != "") {
				//console.log("callBackErrorFunction exist!");
				callBackErrorFunction(xhr.responseText);
			} else {
				try {
					const data = JSON.parse(xhr.responseText);
					//console.log("error data : " + data);
					//console.log("error data.resultCode : " + data.resultCode);
					//console.log(getResultMsg(data.resultCode));
					if (data.resultCode == 0) {
						//console.log("1111_1");
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						//console.log(data);
						alert(getResultMsg(data.resultCode));
						//callBackFunction(data);
					} else if (data.resultCode == -2002) {
						//console.log("2222");
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						//console.log(data);
						//alert(getResultMsg(data.resultCode));
						callBackFunction(data);
					} else {
						//console.log("3333");
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						//console.log(data);
						callBackFunction(data);
					}
				} catch (e) {
					alert('잘못된 호출입니다!');
					if (e instanceof SyntaxError) {
						printError(e, true);
					} else {
						printError(e, false);
					}
					location.reload();
				}

			}
		}
	});
}


/**
 * Ajax 사용후 파일 리턴 - void
 * 
 * @param methodType 
 * @param url
 * @param jsonData
 * @param callBackFunction
 * @param callBackErrorFunction(optional) 
 * @returns
 */


function fnMcenAjaxFileDownLoad(methodType, url, jsonData, callBackFunction, callBackErrorFunction) {

	const token = $("meta[name='_csrf']").attr("content");
	const header = $("meta[name='_csrf_header']").attr("content");
	//console.log("send token : " + token);
	//console.log(url);
	//console.log(jsonData);
    var uAgent = navigator.userAgent;
	
	$.ajax({
		type: methodType,
		url: url,
		cache: false,
		contentType: 'application/json',
		data: jsonData,
		xhr: function() {
			var xhr = new XMLHttpRequest();
			//console.log("###1" + xhr);
			xhr.onreadystatechange = function() {
				//console.log("###2" + xhr.readyState);
		//		//console.log("###3" + xhr.onreadystatechange );
				if (xhr.readyState == 2) {
					if (xhr.status == 200) {
						xhr.responseType = "blob";
					} else {
						xhr.responseType = "text";
					}
				}
			};

			return xhr;
		},
	
		beforeSend: function(xhr) {
				//console.log("####4" + xhr);
			xhr.setRequestHeader(header, token);
		},
		success: function(data, textStatus, jqXhr) {

			//Convert the Byte Data to BLOB object.
			//console.log(data);
			var blob = new Blob([data], { type: "application/octetstream" });
			var fileName = jqXhr.getResponseHeader('X-Filename');
			
			var appDowonload = jqXhr.getResponseHeader('app-download-path');
			//console.log("appDowonload" + appDowonload);
			fileName = decodeURIComponent(fileName);
			//디코딩 추가
			//console.log("fileName" + fileName);
			//console.log("fileName" + decodeURIComponent(fileName));
			$("meta[name='_csrf']").attr("content", jqXhr.getResponseHeader('newToken'));
			if ($("input[name='_csrf']").val() != undefined) {
				$("input[name='_csrf']").val(jqXhr.getResponseHeader('newToken'));
			}
			var a = $("<a class='fileRemove' />");
			//Check the Browser type and download the File.
			var isIE = false || !!document.documentMode;
			if (isIE) {
				window.navigator.msSaveBlob(blob, fileName);
			} else {
				var url = window.URL || window.webkitURL;
				//console.log("url==" + url);

				//console.log("blob==" + blob);
				link = url.createObjectURL(blob);
				
				//console.log("link==" + link);
				

				a.attr("download", fileName);
				
		 if(uAgent.indexOf('APP_KT_ANDROID') > -1 || uAgent.indexOf('APP_KT_IOS') > -1) {
	         	a.attr("href", appDowonload);
            }else{
				a.attr("href", link);
			}
				
				
				
				
				$("body").append(a);
				a[0].click();
				$("body").remove(".fileRemove");
			}
		},
		error: function(xhr, textStatus, errorThrown) {
			//alert(textStatus + " " + errorThrown);
			//console.log("[Error1] call " +errorThrown );
			//console.log("[Error1] call " + url + " return error!");
			//console.log(jqXHR.responseText);
			if (callBackErrorFunction != "") {
				callBackErrorFunction(xhr.responseText);
			} else {
				try {
					//console.log("111111");
					const data = JSON.parse(xhr.responseText);
					//console.log("error data : " + data);
					//console.log("error data.resultCode : " + data.resultCode);
					//console.log(getResultMsg(data.resultCode));
					if (data.resultCode == 0) {
						//console.log("1111_1");
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						//console.log(data);
						alert(getResultMsg(data.resultCode));
						//callBackFunction(data);
					} else if (data.resultCode == -2002) {
						//console.log("2222");
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						//console.log(data);
						//alert(getResultMsg(data.resultCode));
						callBackFunction(data);
					} else {
						//console.log("3333");
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						//console.log(data);
						callBackFunction(data);
					}
				} catch (e) {
					alert('잘못된 호출입니다!');
					if (e instanceof SyntaxError) {
						printError(e, true);
					} else {
						printError(e, false);
					}
					location.reload();
				}

			}
		}
	});
}





function fnMcenAjaxFormData(methodType, url, jsonData, callBackFunction, callBackErrorFunction) {

	const token = $("meta[name='_csrf']").attr("content");
	const header = $("meta[name='_csrf_header']").attr("content");
	const grecc = $("meta[name='_grecc']").attr("content");
	//console.log("send token : " + token);
	//console.log(jsonData);
	//	grecaptcha.ready(function() {
	//		grecaptcha.execute(grecc, {action: 'submit'}).then(function(responseToken) {
	//			//console.log(responseToken);
	$.ajax({
		type: methodType,
		url: url,
		enctype: 'multipart/form-data',
		data: jsonData,
		processData: false,
		contentType: false,
		cache: false,
		//		        headers:{
		//					"grecc":responseToken
		//				},
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data, status, xhr) {
			//console.log("After ajax call : " + data.newToken);
			if (data.newToken != '') {
				$("meta[name='_csrf']").attr("content", data.newToken);
				if ($("input[name='_csrf']").val() != undefined) {
					$("input[name='_csrf']").val(data.newToken);
				}
				//$("input[name='_csrf']").val(data.newToken);
				//alert($("meta[name='_csrf']").attr("content"));
			}
			callBackFunction(data);
		},
		error: function(xhr, textStatus, errorThrown) {
			//alert(textStatus + " " + errorThrown);
			//console.log("[Error1] call " + url + " return error!");
			//console.log(jqXHR.responseText);
			if (callBackErrorFunction != "") {
				callBackErrorFunction(xhr.responseText);
			} else {
				try {
					//console.log("111111");
					const data = JSON.parse(xhr.responseText);
					//console.log("error data : " + data);
					//console.log("error data.resultCode : " + data.resultCode);
					//console.log(getResultMsg(data.resultCode));
					if (data.resultCode == 0) {
						//console.log("1111_1");
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						//console.log(data);
						alert(getResultMsg(data.resultCode));
						//callBackFunction(data);
					} else if (data.resultCode == -2002) {
						//console.log("2222");
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						//console.log(data);
						//alert(getResultMsg(data.resultCode));
						callBackFunction(data);
					} else {
						//console.log("3333");
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						//console.log(data);
						callBackFunction(data);
					}
				} catch (e) {
					alert('잘못된 호출입니다!');
					if (e instanceof SyntaxError) {
						printError(e, true);
					} else {
						printError(e, false);
					}
					location.reload();
				}

			}
		}
	});

	//		});
	//	});			


}


/**
 * initCheckBox
 */
function initCheckBox() {
	const checkBoxList = document.querySelectorAll('input[type="checkbox"]');
	Array.prototype.forEach.call(checkBoxList, function(checkBox) {
		checkBox.addEventListener('click', checkedBoxCount);
	})

	allCheck(false);
}

/**
 * allCheck
 */
function allCheck(state) {
	const checkList = document.querySelectorAll('.form-check-input');
	state = $("#all_check").is(":checked");
	if (checkList != null) {
		Array.prototype.forEach.call(checkList, function(item) {
			item.checked = state;
		})

		const selectState = state ? 'none' : 'flex';
		const cancelSelectState = state ? 'flex' : 'none';

		const btnAllSelect = document.getElementById('btn-all-select');
		const btnAllSelectCancel = document.getElementById('btn-all-select-cancel');
		if (btnAllSelect != null && btnAllSelectCancel != null) {
			btnAllSelect.style.display = selectState;
			btnAllSelectCancel.style.display = cancelSelectState;

			checkedBoxCount();
		}
	}
}

/**
 * checkedBoxCount
 */
function checkedBoxCount() {
	const checkList = document.querySelectorAll('input[type="checkbox"].check-item:checked');
	const checkedCount = checkList.length;

	if (checkedCount == 0) {
		document.getElementById('selectedCount').style.display = 'none';
	} else {
		document.getElementById('selectedCount').style.display = 'flex';
	}

	document.getElementById('selectedCount').children[0].innerText = checkedCount;
}

$(".order").on("click", function() {
	if ($(this).hasClass('up')) {
		$(this).removeClass('up');
		$(this).addClass('down');
	} else {
		$(this).removeClass('down');
		$(this).addClass('up');
	}
})

function onlyNumberChek(obj) {
	if (isNaN(obj.value)) { //숫자가 아니면 true
		//alert(obj.value.length)
		obj.value = "";
		//obj.value = obj.value.substring(0,obj.value.length-1);
		alert("숫자만 입력할수있습니다");
		return;
	}
}

$(function() {
	//숫자만 입력가능
	$(".numberOnly").keyup(function() {
		var keyID = event.which;

		if (isNaN(this.value)) {
			//	alert("숫자만 입력 가능합니다.");
			this.value = this.value.replace(/[^0-9\.]/g, ''); //숫자를 제외한 문자를 지워준다.
			this.value = this.value.replace(/\s/g, ''); //숫자를 제외한 문자를 지워준다.
		}
	});

	$(".drop-btn").on("click", function() {
		$(this).next(".dropdown-menu").addClass("drop");
	});


	// 	const token = $("meta[name='_csrf']").attr("content");
	//	const header = $("meta[name='_csrf_header']").attr("content");
	//	//console.log("send token : "+token);


	//	$(document).ajaxSend(function(e, xhr, options){
	//		xhr.setRequestHeader(header, token);
	//	});

})

function fn_disabledTrue() {

	for (var i in arguments) {
		$("#" + arguments[i]).attr("disabled", true);
	}
}
function fn_disabledFalse() {

	for (var i in arguments) {
		$("#" + arguments[i]).attr("disabled", false);
	}

}


function serialize(form) {
	if (!form || form.nodeName !== "FORM") {
		return;
	}

	var i, j, q = [];
	for (i = form.elements.length - 1; i >= 0; i = i - 1) {
		if (form.elements[i].name === "") {
			continue;
		}
		if (form.elements[i].disabled === true) {
			continue;
		}

		switch (form.elements[i].nodeName) {
			case 'INPUT':
				switch (form.elements[i].type) {
					case 'text':
					case 'hidden':
					case 'password':
					case 'button':
					case 'reset':
					case 'submit':
						q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
						break;
					case 'checkbox':
					case 'radio':
						if (form.elements[i].checked) {
							q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
						}
						break;
				}
				break;
			case 'file':
				break;
			case 'TEXTAREA':
				q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
				break;
			case 'SELECT':
				switch (form.elements[i].type) {
					case 'select-one':
						q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
						break;
					case 'select-multiple':
						for (j = form.elements[i].options.length - 1; j >= 0; j = j - 1) {
							if (form.elements[i].options[j].selected) {
								q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].options[j].value));
							}
						}
						break;
				}
				break;
			case 'BUTTON':
				switch (form.elements[i].type) {
					case 'reset':
					case 'submit':
					case 'button':
						q.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
						break;
				}
				break;
		}
	}
	return q.join("&");
}


function form2Obj(f) {
	var elemArray = f.elements;
	var formObj = {};
	for (var k in elemArray) {
		var input = elemArray[k];
		if (!input || !input.name || !input.value) continue;
		formObj[input.name] = input.value;
		// etc, need special handling for inputs of type radio 
		// checkbox, textarea, and select most likely
	}
	return formObj
}

function calculateByte(x) {
	let s = ['bytes', 'kB', 'MB', 'GB', 'TB', 'PB'];
	let e = Math.floor(Math.log(x) / Math.log(1024));
	return (x / Math.pow(1024, e)).toFixed(2) + " " + s[e];
}

function getC() {
	const jsonData = { "test": "test" };
	fnMcenAjaxJson("POST", "/main/getC", JSON.stringify(jsonData), resultGetC, "");
}

function resultGetC(data) {
	$("meta[name='_csrf']").attr("content", data.newToken);
	$("input[name='_csrf']").val(data.newToken);
}

/* checkbox */
function checkboxHandler(element) {
	const checkboxList = document.querySelectorAll('input[type="checkbox"][name="check-item"]');
	Array.prototype.forEach.call(checkboxList, function(item) {
		if (item != element) {
			item.checked = element.checked;
		}
	})
}

/* input */
// input value 공백 사용 방지
$("input.no-space").keyup(function() {
	const strSpace = /\s/;
	let inputValue = $(this).val();
	if (strSpace.exec(inputValue)) {
		inputValue = inputValue.replace(" ", "");
		$(this).val(inputValue);
	}
})

/* input 전화번호 숫자만 입력, 하이픈 제거 */
$("input.phone-input").keyup(function() {
	let inputValue = $(this).val();
	inputValue = inputValue.replace(/[^-0-9]/g, "").replaceAll("-", "");
	$(this).val(inputValue);
})

var loading = {
	start: function(msg) {
		$("#logingDesc").html(msg);
		$("#loading").removeClass("d-none");
		$("#loading").css("display", "flex");
	},
	end: function() {
		$("#loading").fadeOut();
		$("#loading").addClass("d-none");
	}
}


$(function() {
	loading.end();

	$("#gnb > ul > li").on("mouseenter", function() {
		if ($(this).find(".sub li").length >= 1) {
			$(this).find(".sub").stop().slideDown(100);
		}
	})
	$("#gnb > ul > li").on("mouseleave", function() {
		$(this).find(".sub").stop().slideUp(100);
	})
	$("#header .member-box .btn").on("click", function() {
		//$("#btn-box-check")
		$(this).toggleClass("rev");
		$(this).next(".btn-box").slideToggle(100);
		return false;
	})

	$.datepicker.setDefaults({
        dateFormat: 'yymmdd',
        prevText: '이전 달',
        nextText: '다음 달',
        monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
        dayNames: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
        showMonthAfterYear: true,
        //yearSuffix: '년'
    });

	$(".datepicker").each(function() {
		$(this).datepicker({
			dateFormat: "yy-mm-dd",
		});
	});
	
    $("body").on("click", function(e){
        let target = $(e.target);
        let btn = target.parent().hasClass("btn");
        let box = target.hasClass("btn-box");
        if ( !btn && !box ) {
            $("#header .member-box .btn").removeClass("rev");
            $("#header .member-box .btn-box").slideUp(100);
        }
    });

    //슬라이드 테이블
    $(".table-slide").on("click",function(event){
		if(event.target.type == 'checkbox') { return; }
		if(event.target.className == 'detailMember') { return; }
		
        $(this).toggleClass("on");
        $(this).siblings(".table-slide").removeClass("on");
        $(this).next().siblings(".table-slide-view").find(".slide-box").slideUp(100);
        $(this).next().find(".slide-box").slideToggle(100);
    })


    
    //파일업로드
    var fileTarget = $('#filebox .upload-hidden');
    fileTarget.on('change', function(){
        if(window.FileReader){
			var filename = "";
			if($(this)[0].files.length > 0) {				
            	filename = $(this)[0].files[0].name;
			}
        } else {
            var filename = $(this).val().split('/').pop().split('\\').pop();
        }
    //    $(this).siblings('#filebox .upload-name').val(filename);
    });
    
    //비밀번호찾기
    $(".pw-wrap .input-form .material-icons").on("click",function(){
        $(this).toggleClass("on")
        if($(this).hasClass("on")){
            $(this).text("visibility");
            $(this).siblings("input").attr("type","text")
        } else {
            $(this).text("visibility_off");
            $(this).siblings("input").attr("type","password")
        }
    })
    
    $(".table-header-wrap .select-box .btn").on("click",function(){
        $(this).toggleClass("on");
        $(this).next("ul").slideToggle(100);
        return false;
    })

});

function showPopup(id) {
 $("."+id).show();
}
function closePopup(id) {
$("."+id).hide();
$("#popup-cover").hide();
$(".popup .txt").empty();
$("body").css({ overflow: "visible" })
}
function popup_text2(text) {
	let $popup = $(".textPopup");
	let $popup_alert = $(".textPopup .txt");
	$popup.show();
	$("body").css({ overflow: "hidden" })
	$("<p>" + text + "</p>").appendTo($popup_alert);
}


function popup(type){
    if(type == "set"){ $(".settings-popup").show(); }
    if(type == "del"){
        $(".alert-popup").show();
        $(".alert-popup .txt").text("삭제 하시겠습니까?")
    }
}
function popup_close(){
    $(".popup").hide();
}
function alert_popup_close(){
    $(".alert-popup").hide();
}
//사업자 정보 확인
function onopen()
{
var url =
"http://www.ftc.go.kr/bizCommPop.do?wrkr_no="+frm1.wrkr_no.value;
window.open(url, "bizCommPop", "width=900, height=700;");
}


function netfunnel_set_url(url,type, lang){
	
	//console.log(lang);
	if(type == 'empty'){
		location.href=url;
	}else{
		 NetFunnel_Action({action_id: type, skin_id:"custom_"+lang },function(ev,ret) {netfunnel_go_url(url, type);});
	}
}
//  , skin_id:"blockSkin"  blockSkin  ipBlockSkin  skin1 skin2  skin3 skin4  , skin_id:"custom_en"

function netfunnel_go_url(url,type){
	
		location.href=url;
}



//클래스별 값 반환
function value_return(class_name) {
	var modal_objact = {};
	var objectName = null;
	var objectValue = null;
	$(class_name).each(function(i){
		objectName = $(this).attr("name");
		if ($(this).hasClass("qty")){
			var qty_send = $(this).val();
			if($(this).val() == ""){
				qty_send = "0";
			}
			objectValue = qty_send.replace(/[^0-9]/g,'');
		}else if($(this).hasClass("sendDate")){
			objectValue = $(this).val().replace(/\-/g, '');
		}else {
			objectValue = $(this).val();
		}
		modal_objact[objectName] = objectValue;
	});
	return modal_objact
}














////////////////kds

function wordchk1(msg){
	//console.log("#####" + msg);
	arrList = new Array();
	arrList[arrList.length]  = "캐쉬스파이더";
	arrList[arrList.length]  = "캐시스파이더";
	arrList[arrList.length]  = "목자르는동영상";
	arrList[arrList.length]  = "목자르는 동영상";
	arrList[arrList.length]  = "ogrish.com";
	arrList[arrList.length]  = "ogrish";
	arrList[arrList.length]  = "오그리시";
	arrList[arrList.length]  = "오그리쉬";
	arrList[arrList.length]  = "오그리쉬닷컴";
	arrList[arrList.length]="김선일동영상";
	arrList[arrList.length]="김선일 동영상";
	arrList[arrList.length]="참수";	
	arrList[arrList.length]="beheading";		
	arrList[arrList.length]="007섹스샵";
    arrList[arrList.length]="007섹스티브이";
    arrList[arrList.length]="007섹스티비";
    arrList[arrList.length]="009sex";
    arrList[arrList.length]="016섹스닷컴";
    arrList[arrList.length]="1004성인용품";
    arrList[arrList.length]="100boG";
    arrList[arrList.length]="100bozy";
    arrList[arrList.length]="10sextv";
    arrList[arrList.length]="10섹스티브이";
    arrList[arrList.length]="114성인용품";
    arrList[arrList.length]="18sex";
    arrList[arrList.length]="18sexTV";
    arrList[arrList.length]="18x";
    arrList[arrList.length]="21섹스넷";
    arrList[arrList.length]="24에로라이브";
    arrList[arrList.length]="25라이브섹스";
    arrList[arrList.length]="33sexymall";
    arrList[arrList.length]="365섹스웹";
    arrList[arrList.length]="365포르노";
    arrList[arrList.length]="365포르노테잎";
    arrList[arrList.length]="386섹시매거진";
    arrList[arrList.length]="3sextv";
    arrList[arrList.length]="4SEX";
    arrList[arrList.length]="4changamolca섹스코리아";
    arrList[arrList.length]="4kkasi";
    arrList[arrList.length]="588";
    arrList[arrList.length]="588show";
    arrList[arrList.length]="588섹스코리아";
    arrList[arrList.length]="588섹스코리아성인방송";
    arrList[arrList.length]="588섹스티비";
    arrList[arrList.length]="588포르노";
    arrList[arrList.length]="5sexshop";
    arrList[arrList.length]="69Time";
    arrList[arrList.length]="69XY성인디렉토리";
    arrList[arrList.length]="69n69성인몰";
    arrList[arrList.length]="69sex";
    arrList[arrList.length]="69timepornsex";
    arrList[arrList.length]="69섹스코리아";
    arrList[arrList.length]="69클럽TV";
    arrList[arrList.length]="섹스코리아";
    arrList[arrList.length]="ADULTMANA";
    arrList[arrList.length]="AVmolca";
    arrList[arrList.length]="AV갤러리";
    arrList[arrList.length]="AV재팬";
    arrList[arrList.length]="AdultSearch";
    arrList[arrList.length]="AdultSexshop";
    arrList[arrList.length]="Adulthumor";
    arrList[arrList.length]="Adultzone";
    arrList[arrList.length]="Bojyty";
    arrList[arrList.length]="Bozicam";
    arrList[arrList.length]="GO588TV";
    arrList[arrList.length]="GO섹시클럽";
    arrList[arrList.length]="HOTWEB";
    arrList[arrList.length]="HOTZONE";
    arrList[arrList.length]="HardcorePorno";
    arrList[arrList.length]="IJSEX";
    arrList[arrList.length]="IJ라이브";
    arrList[arrList.length]="IJ생방송";
    arrList[arrList.length]="IJ에로쇼";
    arrList[arrList.length]="IJ특별코너";
    arrList[arrList.length]="KRTV";
    arrList[arrList.length]="Korea포르노";
    arrList[arrList.length]="MANABOZI";
    arrList[arrList.length]="MolcaPornoTV";
    arrList[arrList.length]="MolcaTV";
    arrList[arrList.length]="Molcanara";
    arrList[arrList.length]="Molcaparty";
    arrList[arrList.length]="Molca테이프";
    arrList[arrList.length]="Molca포르노섹스코리아";
    arrList[arrList.length]="Molca포르노소라가이드";
    arrList[arrList.length]="Molka섹스코리아";
    arrList[arrList.length]="Molka코리아섹스";
    arrList[arrList.length]="Mulyosex";
    arrList[arrList.length]="NEVER-SEX";
    arrList[arrList.length]="OIO성인용품";
    arrList[arrList.length]="OK성인용품";
    arrList[arrList.length]="OK섹스SHOW";
    arrList[arrList.length]="PJ";
    arrList[arrList.length]="PORNOBOZI";
    arrList[arrList.length]="PORNO애니";
    arrList[arrList.length]="PleyboG";
    arrList[arrList.length]="Pleyboy";
    arrList[arrList.length]="Porno바다";
    arrList[arrList.length]="SEXBBS";
    arrList[arrList.length]="SEXEROS";
    arrList[arrList.length]="SEXJAPAN";
    arrList[arrList.length]="SEXMOLCA";
    arrList[arrList.length]="SEXPDS";
    arrList[arrList.length]="SEXWAREZ";
    arrList[arrList.length]="SEXYMAP";
    arrList[arrList.length]="SEXYX";
    arrList[arrList.length]="SEXY화상채팅";
    arrList[arrList.length]="SEX로리타";
    arrList[arrList.length]="SEX섹스코리아";
    arrList[arrList.length]="SOSSEX";
    arrList[arrList.length]="SexGoods";
    arrList[arrList.length]="SexPorno";
    arrList[arrList.length]="Sexpia";
    arrList[arrList.length]="Sexwal";
    arrList[arrList.length]="Sexyadong";
    arrList[arrList.length]="TV플레이보이";
    arrList[arrList.length]="X등급";
    arrList[arrList.length]="YASATV";
    arrList[arrList.length]="Yahanbozi";
    arrList[arrList.length]="adult";
    arrList[arrList.length]="adultlife";
    arrList[arrList.length]="adultvideo";
    arrList[arrList.length]="adultzon";
    arrList[arrList.length]="av섹스코리아";
    arrList[arrList.length]="awoodong";
    arrList[arrList.length]="backbojytv";
    arrList[arrList.length]="bbagury";
    arrList[arrList.length]="bestbozi";
    arrList[arrList.length]="binya";
    arrList[arrList.length]="byuntaesex";
    arrList[arrList.length]="carporno";
    arrList[arrList.length]="carsex";
    arrList[arrList.length]="clubero";
    arrList[arrList.length]="condom";
    arrList[arrList.length]="cosex";
    arrList[arrList.length]="cosex.net";
    arrList[arrList.length]="damoimsex";
    arrList[arrList.length]="enterchannel";
    arrList[arrList.length]="ero2030";
    arrList[arrList.length]="ero69";
    arrList[arrList.length]="eroasia";
    arrList[arrList.length]="erocine";
    arrList[arrList.length]="erosasia";
    arrList[arrList.length]="erosian";
    arrList[arrList.length]="erostyle";
    arrList[arrList.length]="fetish";
    arrList[arrList.length]="fetish7";
    arrList[arrList.length]="fetishwoman";
    arrList[arrList.length]="gabozi";
    arrList[arrList.length]="gagasexy";
    arrList[arrList.length]="goadult";
    arrList[arrList.length]="gocinepia";
    arrList[arrList.length]="gojasex";
    arrList[arrList.length]="hidden-korea";
    arrList[arrList.length]="hiddencam";
    arrList[arrList.length]="hotbojy";
    arrList[arrList.length]="hotsex";
    arrList[arrList.length]="hotsexkorea";
    arrList[arrList.length]="ilovesex";
    arrList[arrList.length]="joungyang";
    arrList[arrList.length]="jungyang";
    arrList[arrList.length]="kgirlmolca";
    arrList[arrList.length]="kingsex";
    arrList[arrList.length]="ksex";
    arrList[arrList.length]="ksex.net";
    arrList[arrList.length]="kukikorea";
    arrList[arrList.length]="live10tv";
    arrList[arrList.length]="molca";
    arrList[arrList.length]="molca365";
    arrList[arrList.length]="molca588";
    arrList[arrList.length]="molcaCD";
    arrList[arrList.length]="molcaav";
    arrList[arrList.length]="molcasex";
    arrList[arrList.length]="molcasexnipon";
    arrList[arrList.length]="molca섹스";
    arrList[arrList.length]="molca섹스코리아";
    arrList[arrList.length]="molca포르노";
    arrList[arrList.length]="mrcondom";
    arrList[arrList.length]="new소라가이드";
    arrList[arrList.length]="noode";
    arrList[arrList.length]="nopants";
    arrList[arrList.length]="nudcouple";
    arrList[arrList.length]="nude";
    arrList[arrList.length]="nudlnude";
    arrList[arrList.length]="oiotv";
    arrList[arrList.length]="olotv섹스nipon";
    arrList[arrList.length]="oralsextv";
    arrList[arrList.length]="playboy";
    arrList[arrList.length]="porno";
    arrList[arrList.length]="porno-tape";
    arrList[arrList.length]="pornomana";
    arrList[arrList.length]="pornoplayboy";
    arrList[arrList.length]="pornsex";
    arrList[arrList.length]="runsex";
    arrList[arrList.length]="sex";
    arrList[arrList.length]="sex-sayclub";
    arrList[arrList.length]="sex123";
    arrList[arrList.length]="sex18";
    arrList[arrList.length]="sex1818";
    arrList[arrList.length]="sex4969";
    arrList[arrList.length]="sexTV";
    arrList[arrList.length]="sexboard";
    arrList[arrList.length]="sexbuin";
    arrList[arrList.length]="sexcorea";
    arrList[arrList.length]="sexhangame";
    arrList[arrList.length]="sexjj";
    arrList[arrList.length]="sexkorea";
    arrList[arrList.length]="sexkorea,net";
    arrList[arrList.length]="sexkorea.com";
    arrList[arrList.length]="sexkorea.net";
    arrList[arrList.length]="sexkorea21";
    arrList[arrList.length]="sexkoreasexkorea";
    arrList[arrList.length]="sexmaxx";
    arrList[arrList.length]="sexmolka";
    arrList[arrList.length]="sexmovie";
    arrList[arrList.length]="sexmusa";
    arrList[arrList.length]="sexnipon";
    arrList[arrList.length]="sexsexy";
    arrList[arrList.length]="sexwal.com";
    arrList[arrList.length]="sexwall";
    arrList[arrList.length]="sexwall.com";
    arrList[arrList.length]="sexxmolcatv";
    arrList[arrList.length]="sexy";
    arrList[arrList.length]="sexyclik";
    arrList[arrList.length]="sexyjapan";
    arrList[arrList.length]="sexynmall";
    arrList[arrList.length]="sexysoul";
    arrList[arrList.length]="sora'sguide";
    arrList[arrList.length]="soraguide";
    arrList[arrList.length]="sorasguide";
    arrList[arrList.length]="sorasguide.com";
    arrList[arrList.length]="sunginsite";
    arrList[arrList.length]="togoero";
    arrList[arrList.length]="tvbozi";
    arrList[arrList.length]="tv섹스코리아";
    arrList[arrList.length]="twistkim";
    arrList[arrList.length]="twistkim.com";
    arrList[arrList.length]="videokiller";
    arrList[arrList.length]="vip24";
    arrList[arrList.length]="viva포르노";
    arrList[arrList.length]="warez섹스";
    arrList[arrList.length]="whoissex";
    arrList[arrList.length]="wonjosex";
    arrList[arrList.length]="worldsex";
    arrList[arrList.length]="www.porno-tape.com";
    arrList[arrList.length]="www.sex123.co.kr";
    arrList[arrList.length]="www.sexkorea.net";
    arrList[arrList.length]="www.sexwal.com";
    arrList[arrList.length]="www.sorasguide.com";
    arrList[arrList.length]="www.twistkim.com";
    arrList[arrList.length]="xxx";
    arrList[arrList.length]="yadong";
    arrList[arrList.length]="yadongclub";
    arrList[arrList.length]="yadongmolca";
    arrList[arrList.length]="yadong섹스코리아";
    arrList[arrList.length]="yagood";
    arrList[arrList.length]="yahanbamtv";
    arrList[arrList.length]="yahannom";
    arrList[arrList.length]="yasine";
    arrList[arrList.length]="yesmolca";
    arrList[arrList.length]="yessex";
    arrList[arrList.length]="youngsex";
    arrList[arrList.length]="zotte";
    arrList[arrList.length]="zungyang";
    arrList[arrList.length]="쎅스";
    arrList[arrList.length]="쎅스코리아";
    arrList[arrList.length]="쎅시넷";
    arrList[arrList.length]="쎅콜닷컴";
    arrList[arrList.length]="쎅클럽";
    arrList[arrList.length]="가가가가섹스샵";
    arrList[arrList.length]="가가가섹스WAREZ";
    arrList[arrList.length]="가가성인섹스샵";
    arrList[arrList.length]="가가성인용품";
    arrList[arrList.length]="가가성인용품샵";
    arrList[arrList.length]="가가섹스";
    arrList[arrList.length]="가가섹스포르노";
    arrList[arrList.length]="가가섹스하러다가라";
    arrList[arrList.length]="가고파성인용품";
    arrList[arrList.length]="가나성인용품전문쇼핑몰";
    arrList[arrList.length]="가람섹스성인용품";
    arrList[arrList.length]="가마성인쇼핑몰";
    arrList[arrList.length]="가면성인용품쇼핑몰";
    arrList[arrList.length]="가보자성인용품";
    arrList[arrList.length]="가시나무성인용품";
    arrList[arrList.length]="가이드성인쇼핑몰";
    arrList[arrList.length]="가이드섹스";
    arrList[arrList.length]="가인코리아성인용품";
    arrList[arrList.length]="가자Molca섹스코리아";
    arrList[arrList.length]="가자러브샵";
    arrList[arrList.length]="가자미아리";
    arrList[arrList.length]="가자성인용품";
    arrList[arrList.length]="가자섹스";
    arrList[arrList.length]="가자섹스몰";
    arrList[arrList.length]="가자섹스자료실";
    arrList[arrList.length]="가자섹스코리아";
    arrList[arrList.length]="가정섹스전문점";
    arrList[arrList.length]="가지와오이성인용품";
    arrList[arrList.length]="가지와오이섹스샵";
    arrList[arrList.length]="강간";
    arrList[arrList.length]="강남카페섹스비디오테이프";
    arrList[arrList.length]="강쇠닷컴";
    arrList[arrList.length]="강한성인영화";
    arrList[arrList.length]="걸섹스코리아갤러리";
    arrList[arrList.length]="경고섹스포르노";
    arrList[arrList.length]="경마장가는길성인용품";
    arrList[arrList.length]="경아닷컴";
    arrList[arrList.length]="고69섹슈얼";
    arrList[arrList.length]="고공섹스";
    arrList[arrList.length]="고려섹시인포디렉토리";
    arrList[arrList.length]="고섹스588";
    arrList[arrList.length]="고섹스PORNO";
    arrList[arrList.length]="고에로TV";
    arrList[arrList.length]="고우섹스";
    arrList[arrList.length]="고우섹스성인용품점";
    arrList[arrList.length]="고자섹스";
    arrList[arrList.length]="고추짱";
    arrList[arrList.length]="고투에로";
    arrList[arrList.length]="고패티쉬";
    arrList[arrList.length]="고혈압야동";
    arrList[arrList.length]="공짜만화";
    arrList[arrList.length]="과부촌";
    arrList[arrList.length]="국제성인마트";
    arrList[arrList.length]="굿걸TV";
    arrList[arrList.length]="굿나잇TV";
    arrList[arrList.length]="굿나잇티브이";
    arrList[arrList.length]="굿섹스클럽";
    arrList[arrList.length]="그룹섹스";
    arrList[arrList.length]="근친상간";
    arrList[arrList.length]="글래머샵";
    arrList[arrList.length]="김마담성인쇼핑몰";
    arrList[arrList.length]="꼬추닷컴";
    arrList[arrList.length]="나고야섹스미드머니";
    arrList[arrList.length]="나나성인용품";
    arrList[arrList.length]="나우누리어른만화방";
    arrList[arrList.length]="나이스69";
    arrList[arrList.length]="나이스성인샵";
    arrList[arrList.length]="나조아성인몰";
    arrList[arrList.length]="나체";
    arrList[arrList.length]="남녀섹시속옷";
    arrList[arrList.length]="남녀자위기구";
    arrList[arrList.length]="남성단련용품";
    arrList[arrList.length]="남성자위기구";
    arrList[arrList.length]="네버섹스";
    arrList[arrList.length]="네비로스섹스샵";
    arrList[arrList.length]="노노티브이";
    arrList[arrList.length]="노브라";
    arrList[arrList.length]="노브라TV";
    arrList[arrList.length]="노팬티브이";
    arrList[arrList.length]="논스톱러브성인유머";
    arrList[arrList.length]="누나몰카";
    arrList[arrList.length]="누두";
    arrList[arrList.length]="누드";
    arrList[arrList.length]="누드112";
    arrList[arrList.length]="누드119";
    arrList[arrList.length]="누드25시";
    arrList[arrList.length]="누드molcaTV";
    arrList[arrList.length]="누드갤러리";
    arrList[arrList.length]="누드다이어리";
    arrList[arrList.length]="누드모델";
    arrList[arrList.length]="누드모아";
    arrList[arrList.length]="누드몰카TV";
    arrList[arrList.length]="누드뮤직";
    arrList[arrList.length]="누드사진";
    arrList[arrList.length]="누드섹스";
    arrList[arrList.length]="누드쇼";
    arrList[arrList.length]="누드스케치";
    arrList[arrList.length]="누드집";
    arrList[arrList.length]="누드천사";
    arrList[arrList.length]="누드커플";
    arrList[arrList.length]="누드코리아";
    arrList[arrList.length]="누드클럽";
    arrList[arrList.length]="누드필름";
    arrList[arrList.length]="누드화보";
    arrList[arrList.length]="누들누드";
    arrList[arrList.length]="뉴스트립";
    arrList[arrList.length]="다보자성인영화관";
    arrList[arrList.length]="다음섹스";
    arrList[arrList.length]="다이섹스";
    arrList[arrList.length]="대박성인토탈몰";
    arrList[arrList.length]="더티섹스";
    arrList[arrList.length]="도쿄섹스";
    arrList[arrList.length]="도쿄섹스nipon";
    arrList[arrList.length]="동양최고성고전";
    arrList[arrList.length]="두두섹스";
    arrList[arrList.length]="뒷치기";
    arrList[arrList.length]="드림페티쉬";
    arrList[arrList.length]="등급보류성인영화관";
    arrList[arrList.length]="디지털섹스조선";
    arrList[arrList.length]="딸딸이";
    arrList[arrList.length]="떡걸";
    arrList[arrList.length]="떡치기";
    arrList[arrList.length]="라노비아섹스샵";
    arrList[arrList.length]="라이브10TV";
    arrList[arrList.length]="라이브스트립";
    arrList[arrList.length]="러브박사성인용품";
    arrList[arrList.length]="러브베드";
    arrList[arrList.length]="러브섹시클럽";
    arrList[arrList.length]="러브장";
    arrList[arrList.length]="러브젤";
    arrList[arrList.length]="러브하자성인용품";
    arrList[arrList.length]="러브호텔";
    arrList[arrList.length]="럭키성인몰";
    arrList[arrList.length]="레드섹스tv";
    arrList[arrList.length]="레이싱걸";
    arrList[arrList.length]="로리타";
    arrList[arrList.length]="로리타.";
    arrList[arrList.length]="롤리타";
    arrList[arrList.length]="류미오";
    arrList[arrList.length]="리얼섹스플레이";
    arrList[arrList.length]="리얼에로";
    arrList[arrList.length]="마나보지";
    arrList[arrList.length]="마니아섹스";
    arrList[arrList.length]="만화보지";
    arrList[arrList.length]="망가";
    arrList[arrList.length]="망가짱";
    arrList[arrList.length]="모노섹스";
    arrList[arrList.length]="모두모아성인용품";
    arrList[arrList.length]="모모TV";
    arrList[arrList.length]="몰래보기";
    arrList[arrList.length]="몰래카메라";
    arrList[arrList.length]="몰래캠코더";
    arrList[arrList.length]="몰카";
    arrList[arrList.length]="몰카365";
    arrList[arrList.length]="몰카588";
    arrList[arrList.length]="몰카tv";
    arrList[arrList.length]="무료망가";
    arrList[arrList.length]="무료몰카";
    arrList[arrList.length]="무료성인";
    arrList[arrList.length]="무료성인동영상";
    arrList[arrList.length]="무료성인만화";
    arrList[arrList.length]="무료성인방송";
    arrList[arrList.length]="무료성인사이트";
    arrList[arrList.length]="무료성인싸이트";
    arrList[arrList.length]="무료성인엽기";
    arrList[arrList.length]="무료성인영화";
    arrList[arrList.length]="무료성인정보";
    arrList[arrList.length]="무료섹스";
    arrList[arrList.length]="무료섹스동영상";
    arrList[arrList.length]="무료섹스사이트";
    arrList[arrList.length]="무료야동";
    arrList[arrList.length]="무료야설";
    arrList[arrList.length]="무료포르노";
    arrList[arrList.length]="무료포르노동영상";
    arrList[arrList.length]="무료헨타이";
    arrList[arrList.length]="무전망가";
    arrList[arrList.length]="미국뽀르노";
    arrList[arrList.length]="미국포르노";
    arrList[arrList.length]="미니스커트";
    arrList[arrList.length]="미란다성인섹스샵";
    arrList[arrList.length]="미소걸성인용품";
    arrList[arrList.length]="미소녀";
    arrList[arrList.length]="미소녀게임";
    arrList[arrList.length]="미소녀섹스가이드";
    arrList[arrList.length]="미스누드";
    arrList[arrList.length]="미스터콘돔";
    arrList[arrList.length]="미스토픽닷컴";
    arrList[arrList.length]="미스토픽성인용품점";
    arrList[arrList.length]="미시촌";
    arrList[arrList.length]="미쎄스터";
    arrList[arrList.length]="미아리";
    arrList[arrList.length]="미아리2000";
    arrList[arrList.length]="미아리588";
    arrList[arrList.length]="미아리tv";
    arrList[arrList.length]="미아리섹스하리";
    arrList[arrList.length]="미아리쇼";
    arrList[arrList.length]="미아리텍사스";
    arrList[arrList.length]="바나나TV";
    arrList[arrList.length]="바나나티비";
    arrList[arrList.length]="바이엔에로";
    arrList[arrList.length]="밝은세상성인용품";
    arrList[arrList.length]="벗기는고스톱";
    arrList[arrList.length]="베스트성인용품";
    arrList[arrList.length]="베이비아웃성인용품";
    arrList[arrList.length]="변태";
    arrList[arrList.length]="보스성인클럽";
    arrList[arrList.length]="보조기구";
    arrList[arrList.length]="보지";
    arrList[arrList.length]="보지나라";
    arrList[arrList.length]="보지마TV";
    arrList[arrList.length]="보지보지";
    arrList[arrList.length]="보지털";
    arrList[arrList.length]="부부나라";
    arrList[arrList.length]="부부섹스";
    arrList[arrList.length]="비너스성인용품";
    arrList[arrList.length]="빈야";
    arrList[arrList.length]="빈야성인";
    arrList[arrList.length]="빈야성인와레즈";
    arrList[arrList.length]="빠구리";
    arrList[arrList.length]="빨간티브이섹스";
    arrList[arrList.length]="뽀르노";
    arrList[arrList.length]="삐리넷";
    arrList[arrList.length]="사까시";
    arrList[arrList.length]="사라성인용품점";
    arrList[arrList.length]="사랑의침실테크닉";
    arrList[arrList.length]="사이버섹스";
    arrList[arrList.length]="사창가";
    arrList[arrList.length]="색스";
    arrList[arrList.length]="색스코리아";
    arrList[arrList.length]="샴푸의성인정보";
    arrList[arrList.length]="서양동영상";
    arrList[arrList.length]="서양뽀르노";
    arrList[arrList.length]="성게시판";
    arrList[arrList.length]="성고민상담";
    arrList[arrList.length]="성과섹스";
//    arrList[arrList.length]="성기";
    arrList[arrList.length]="성보조기구";
    arrList[arrList.length]="성상담";
    arrList[arrList.length]="성인";
    arrList[arrList.length]="성인18번지";
    arrList[arrList.length]="성인25시";
    arrList[arrList.length]="성인CD";
    arrList[arrList.length]="성인IJ";
    arrList[arrList.length]="성인갤러리";
    arrList[arrList.length]="성인게시판";
    arrList[arrList.length]="성인게임";
    arrList[arrList.length]="성인그리고섹스";
    arrList[arrList.length]="성인극장";
    arrList[arrList.length]="성인나라";
    arrList[arrList.length]="성인놀이문화";
    arrList[arrList.length]="성인누드";
    arrList[arrList.length]="성인뉴스";
    arrList[arrList.length]="성인대화";
    arrList[arrList.length]="성인대화방";
    arrList[arrList.length]="성인동영상";
    arrList[arrList.length]="성인드라마";
    arrList[arrList.length]="성인만화";
    arrList[arrList.length]="성인만화나라";
    arrList[arrList.length]="성인만화천국";
    arrList[arrList.length]="성인망가";
    arrList[arrList.length]="성인무료";
    arrList[arrList.length]="성인무료동영상";
    arrList[arrList.length]="성인무료사이트";
    arrList[arrList.length]="성인무료영화";
    arrList[arrList.length]="성인무비";
    arrList[arrList.length]="성인물";
    arrList[arrList.length]="성인미스랭크";
    arrList[arrList.length]="성인미팅방";
    arrList[arrList.length]="성인방송";
    arrList[arrList.length]="성인방송국";
    arrList[arrList.length]="성인방송안내";
    arrList[arrList.length]="성인배우";
    arrList[arrList.length]="성인별곡";
    arrList[arrList.length]="성인비됴";
    arrList[arrList.length]="성인비디오";
    arrList[arrList.length]="성인사이트";
    arrList[arrList.length]="성인사이트소개";
    arrList[arrList.length]="성인사진";
    arrList[arrList.length]="성인상품";
    arrList[arrList.length]="성인생방송";
    arrList[arrList.length]="성인샵";
    arrList[arrList.length]="성인서적";
    arrList[arrList.length]="성인성교육스쿨";
    arrList[arrList.length]="성인섹스민국";
    arrList[arrList.length]="성인섹스코리아";
    arrList[arrList.length]="성인소녀경";
    arrList[arrList.length]="성인소라가이드";
    arrList[arrList.length]="성인소설";
    arrList[arrList.length]="성인쇼";
    arrList[arrList.length]="성인쇼핑";
    arrList[arrList.length]="성인쇼핑몰";
    arrList[arrList.length]="성인시트콤";
    arrList[arrList.length]="성인싸이트";
    arrList[arrList.length]="성인애니";
    arrList[arrList.length]="성인애니메이션";
    arrList[arrList.length]="성인야설";
    arrList[arrList.length]="성인야화";
    arrList[arrList.length]="성인에로무비";
    arrList[arrList.length]="성인에로영화";
    arrList[arrList.length]="성인엽기";
    arrList[arrList.length]="성인엽기damoim";
    arrList[arrList.length]="성인영상";
    arrList[arrList.length]="성인영화";
    arrList[arrList.length]="성인영화관";
    arrList[arrList.length]="성인영화나라";
    arrList[arrList.length]="성인영화방";
    arrList[arrList.length]="성인영화세상";
    arrList[arrList.length]="성인영화천국";
    arrList[arrList.length]="성인와레즈";
    arrList[arrList.length]="성인용CD";
    arrList[arrList.length]="성인용품";
    arrList[arrList.length]="성인용품도매센터";
    arrList[arrList.length]="성인용품에로존";
    arrList[arrList.length]="성인용품할인매장";
    arrList[arrList.length]="성인유머";
    arrList[arrList.length]="성인이미지";
    arrList[arrList.length]="성인인증&x=23";
    arrList[arrList.length]="성인인터넷방송";
    arrList[arrList.length]="성인일본";
    arrList[arrList.length]="성인자료";
    arrList[arrList.length]="성인자료실";
    arrList[arrList.length]="성인전용";
    arrList[arrList.length]="성인전용관";
    arrList[arrList.length]="성인전용정보";
    arrList[arrList.length]="성인정보";
    arrList[arrList.length]="성인채팅";
    arrList[arrList.length]="성인챗";
    arrList[arrList.length]="성인천국";
    arrList[arrList.length]="성인체위";
    arrList[arrList.length]="성인카툰";
    arrList[arrList.length]="성인컨텐츠";
    arrList[arrList.length]="성인클럽";
    arrList[arrList.length]="성인플래쉬";
    arrList[arrList.length]="성인플래시";
    arrList[arrList.length]="성인화상";
    arrList[arrList.length]="성인화상채팅";
    arrList[arrList.length]="성일플래쉬";
    arrList[arrList.length]="성잉영화";
    arrList[arrList.length]="성체위";
    arrList[arrList.length]="성클리닉";
    arrList[arrList.length]="성테크닉";
    arrList[arrList.length]="성폭행";
    arrList[arrList.length]="성행위";
    arrList[arrList.length]="세븐누드닷컴";
    arrList[arrList.length]="세븐섹시";
    arrList[arrList.length]="세희야동";
    arrList[arrList.length]="섹";
    arrList[arrList.length]="섹걸";
    arrList[arrList.length]="섹걸닷컴";
    arrList[arrList.length]="섹골닷컴";
    arrList[arrList.length]="섹마";
    arrList[arrList.length]="섹쉬";
    arrList[arrList.length]="섹쉬뱅크";
    arrList[arrList.length]="섹쉬썸머타임";
    arrList[arrList.length]="섹쉬엽기";
    arrList[arrList.length]="섹스";
    arrList[arrList.length]="섹스19";
    arrList[arrList.length]="섹스25시";
    arrList[arrList.length]="섹스2TV";
    arrList[arrList.length]="섹스588섹스";
    arrList[arrList.length]="섹스6mm";
    arrList[arrList.length]="섹스700";
    arrList[arrList.length]="섹스89";
    arrList[arrList.length]="섹스DC";
    arrList[arrList.length]="섹스Koreana";
    arrList[arrList.length]="섹스Molca섹스코리아";
    arrList[arrList.length]="섹스SHOW";
    arrList[arrList.length]="섹스TV";
    arrList[arrList.length]="섹스and포르노";
    arrList[arrList.length]="섹스damoim";
    arrList[arrList.length]="섹스daum";
    arrList[arrList.length]="섹스molca";
    arrList[arrList.length]="섹스molcaTV";
    arrList[arrList.length]="섹스molca코리아";
    arrList[arrList.length]="섹스molca코리아TV";
    arrList[arrList.length]="섹스molca포르노";
    arrList[arrList.length]="섹스molka";
    arrList[arrList.length]="섹스sayclub";
    arrList[arrList.length]="섹스warez";
    arrList[arrList.length]="섹스yadong";
    arrList[arrList.length]="섹스가이드";
    arrList[arrList.length]="섹스갤러리";
    arrList[arrList.length]="섹스게시판";
    arrList[arrList.length]="섹스고고";
    arrList[arrList.length]="섹스굿";
    arrList[arrList.length]="섹스나라";
    arrList[arrList.length]="섹스나라69";
    arrList[arrList.length]="섹스노예";
    arrList[arrList.length]="섹스다음";
    arrList[arrList.length]="섹스다크";
    arrList[arrList.length]="섹스닷컴";
    arrList[arrList.length]="섹스데이타100";
    arrList[arrList.length]="섹스도우미";
    arrList[arrList.length]="섹스동";
    arrList[arrList.length]="섹스동영상";
    arrList[arrList.length]="섹스드라마";
    arrList[arrList.length]="섹스라이브";
    arrList[arrList.length]="섹스라이브TV";
    arrList[arrList.length]="섹스로봇";
    arrList[arrList.length]="섹스리아";
    arrList[arrList.length]="섹스리얼";
    arrList[arrList.length]="섹스링크";
    arrList[arrList.length]="섹스마스터";
    arrList[arrList.length]="섹스만화";
    arrList[arrList.length]="섹스매거진";
    arrList[arrList.length]="섹스모델";
    arrList[arrList.length]="섹스모아TV";
    arrList[arrList.length]="섹스몰";
    arrList[arrList.length]="섹스몰카";
    arrList[arrList.length]="섹스무비";
    arrList[arrList.length]="섹스무사";
    arrList[arrList.length]="섹스뮤직";
    arrList[arrList.length]="섹스믹스";
    arrList[arrList.length]="섹스바부제펜";
    arrList[arrList.length]="섹스벨리";
    arrList[arrList.length]="섹스보드";
    arrList[arrList.length]="섹스보조기구";
    arrList[arrList.length]="섹스보조용품";
    arrList[arrList.length]="섹스부인";
    arrList[arrList.length]="섹스브라";
    arrList[arrList.length]="섹스비디오";
    arrList[arrList.length]="섹스비안";
    arrList[arrList.length]="섹스사랑";
    arrList[arrList.length]="섹스사이트";
    arrList[arrList.length]="섹스사진";
    arrList[arrList.length]="섹스살롱";
    arrList[arrList.length]="섹스샘플";
    arrList[arrList.length]="섹스샵";
    arrList[arrList.length]="섹스샵2080";
    arrList[arrList.length]="섹스샵21";
    arrList[arrList.length]="섹스서치";
    arrList[arrList.length]="섹스선데이";
    arrList[arrList.length]="섹스성인만화";
    arrList[arrList.length]="섹스셀카";
    arrList[arrList.length]="섹스소나타";
    arrList[arrList.length]="섹스소라가이드";
    arrList[arrList.length]="섹스소리";
    arrList[arrList.length]="섹스시네";
    arrList[arrList.length]="섹스심리";
    arrList[arrList.length]="섹스씬";
    arrList[arrList.length]="섹스아이디";
    arrList[arrList.length]="섹스알리바바";
    arrList[arrList.length]="섹스애니마나";
    arrList[arrList.length]="섹스앤샵";
    arrList[arrList.length]="섹스야다이즈";
    arrList[arrList.length]="섹스야동";
    arrList[arrList.length]="섹스야시네";
    arrList[arrList.length]="섹스야호";
    arrList[arrList.length]="섹스에니메이션";
    arrList[arrList.length]="섹스에로";
    arrList[arrList.length]="섹스에로스TV";
    arrList[arrList.length]="섹스에로시안";
    arrList[arrList.length]="섹스엔바이";
    arrList[arrList.length]="섹스엔샵";
    arrList[arrList.length]="섹스엠티비닷컴";
    arrList[arrList.length]="섹스영상";
    arrList[arrList.length]="섹스영화";
    arrList[arrList.length]="섹스와레즈";
    arrList[arrList.length]="섹스왈";
    arrList[arrList.length]="섹스용품";
    arrList[arrList.length]="섹스월";
    arrList[arrList.length]="섹스월드";
    arrList[arrList.length]="섹스웰";
    arrList[arrList.length]="섹스인형";
    arrList[arrList.length]="섹스일기";
    arrList[arrList.length]="섹스자료실";
    arrList[arrList.length]="섹스자세";
    arrList[arrList.length]="섹스자진";
    arrList[arrList.length]="섹스잡지";
    arrList[arrList.length]="섹스재팬만화";
    arrList[arrList.length]="섹스정보";
    arrList[arrList.length]="섹스제이제이";
    arrList[arrList.length]="섹스제팬";
    arrList[arrList.length]="섹스제펜";
    arrList[arrList.length]="섹스조선";
    arrList[arrList.length]="섹스조아";
    arrList[arrList.length]="섹스조인";
    arrList[arrList.length]="섹스지존";
    arrList[arrList.length]="섹스짱";
    arrList[arrList.length]="섹스찌찌닷콤";
    arrList[arrList.length]="섹스채널";
    arrList[arrList.length]="섹스챗79";
    arrList[arrList.length]="섹스천사";
    arrList[arrList.length]="섹스천하";
    arrList[arrList.length]="섹스체위";
    arrList[arrList.length]="섹스촌";
    arrList[arrList.length]="섹스캠프";
    arrList[arrList.length]="섹스코러스";
    arrList[arrList.length]="섹스코리아";
    arrList[arrList.length]="섹스코리아21";
    arrList[arrList.length]="섹스코리아79";
    arrList[arrList.length]="섹스코리아DAMOIM";
    arrList[arrList.length]="섹스코리아MOLCA";
    arrList[arrList.length]="섹스코리아OK";
    arrList[arrList.length]="섹스코리아TV";
    arrList[arrList.length]="섹스코리아jp";
    arrList[arrList.length]="섹스코리아warez";
    arrList[arrList.length]="섹스코리아걸";
    arrList[arrList.length]="섹스코리아넷";
    arrList[arrList.length]="섹스코리아라이브";
    arrList[arrList.length]="섹스코리아무비";
    arrList[arrList.length]="섹스코리아성인엽기";
    arrList[arrList.length]="섹스코리아섹스섹스코리아";
    arrList[arrList.length]="섹스코리아소라가이드";
    arrList[arrList.length]="섹스코리아소라의가이드";
    arrList[arrList.length]="섹스코리아앤드섹스코리아";
    arrList[arrList.length]="섹스코리아엑스섹스코리아";
    arrList[arrList.length]="섹스코리아조선";
    arrList[arrList.length]="섹스코리아포르노";
    arrList[arrList.length]="섹스코리아하우스";
    arrList[arrList.length]="섹스코치";
    arrList[arrList.length]="섹스크림";
    arrList[arrList.length]="섹스클럽";
    arrList[arrList.length]="섹스킴";
    arrList[arrList.length]="섹스타임";
    arrList[arrList.length]="섹스타임69";
    arrList[arrList.length]="섹스테잎";
    arrList[arrList.length]="섹스테크닉";
    arrList[arrList.length]="섹스토이샵";
    arrList[arrList.length]="섹스토이코리아";
    arrList[arrList.length]="섹스투데이";
    arrList[arrList.length]="섹스트립";
    arrList[arrList.length]="섹스파일";
    arrList[arrList.length]="섹스파크";
    arrList[arrList.length]="섹스포르노";
    arrList[arrList.length]="섹스포르노molca";
    arrList[arrList.length]="섹스포르노샵";
    arrList[arrList.length]="섹스포르노트위스트김";
    arrList[arrList.length]="섹스포섹스티비";
    arrList[arrList.length]="섹스포유";
    arrList[arrList.length]="섹스포인트";
    arrList[arrList.length]="섹스포탈";
    arrList[arrList.length]="섹스프리덤";
    arrList[arrList.length]="섹스피아";
    arrList[arrList.length]="섹스하까";
    arrList[arrList.length]="섹스하네";
    arrList[arrList.length]="섹스하리";
    arrList[arrList.length]="섹스한국";
    arrList[arrList.length]="섹스해죠";
    arrList[arrList.length]="섹스헨타이";
    arrList[arrList.length]="섹스호빠";
    arrList[arrList.length]="섹스홀";
    arrList[arrList.length]="섹시";
    arrList[arrList.length]="섹시TV";
    arrList[arrList.length]="섹시wave";
    arrList[arrList.length]="섹시갤러리";
    arrList[arrList.length]="섹시걸";
    arrList[arrList.length]="섹시게이트";
    arrList[arrList.length]="섹시나라";
    arrList[arrList.length]="섹시나이트";
    arrList[arrList.length]="섹시누드";
    arrList[arrList.length]="섹시뉴스";
    arrList[arrList.length]="섹시맵";
    arrList[arrList.length]="섹시무비";
    arrList[arrList.length]="섹시사진";
    arrList[arrList.length]="섹시샵";
    arrList[arrList.length]="섹시성인용품";
    arrList[arrList.length]="섹시섹스코리아";
    arrList[arrList.length]="섹시스타";
    arrList[arrList.length]="섹시신문";
    arrList[arrList.length]="섹시씨엔엔";
    arrList[arrList.length]="섹시아이제이";
    arrList[arrList.length]="섹시에로닷컴";
    arrList[arrList.length]="섹시엔TV";
    arrList[arrList.length]="섹시엔몰";
    arrList[arrList.length]="섹시연예인";
    arrList[arrList.length]="섹시재팬";
    arrList[arrList.length]="섹시제팬";
    arrList[arrList.length]="섹시제펜";
    arrList[arrList.length]="섹시조선";
    arrList[arrList.length]="섹시존";
    arrList[arrList.length]="섹시짱";
    arrList[arrList.length]="섹시촌";
    arrList[arrList.length]="섹시코디";
    arrList[arrList.length]="섹시코리아";
    arrList[arrList.length]="섹시클럽";
    arrList[arrList.length]="섹시클릭";
    arrList[arrList.length]="섹시팅하자";
    arrList[arrList.length]="섹시팬티";
    arrList[arrList.length]="셀카";
    arrList[arrList.length]="셀프카메라";
    arrList[arrList.length]="소라가이드";
    arrList[arrList.length]="소라가이드-에로천국";
    arrList[arrList.length]="소라가이드TO";
    arrList[arrList.length]="소라가이드앤소라가이드";
    arrList[arrList.length]="소라가이드천사";
    arrList[arrList.length]="소라스가이드";
    arrList[arrList.length]="소라의MissTopic";
    arrList[arrList.length]="소라의가이드";
    arrList[arrList.length]="소라의섹스코리아가이드";
    arrList[arrList.length]="소라의야설공작소";
    arrList[arrList.length]="소라의프로포즈";
    arrList[arrList.length]="소라즈가이드";
    arrList[arrList.length]="쇼걸클럽";
    arrList[arrList.length]="쇼우망가";
    arrList[arrList.length]="쇼킹동영상";
    arrList[arrList.length]="쇼킹섹스";
    arrList[arrList.length]="쇼킹섹스코리아";
    arrList[arrList.length]="쇼킹에로";
    arrList[arrList.length]="숙모보지";
    arrList[arrList.length]="스와핑";
    arrList[arrList.length]="스타킹포유";
    arrList[arrList.length]="신마담";
    arrList[arrList.length]="실리콘하우스성인용품";
    arrList[arrList.length]="심야TV";
    arrList[arrList.length]="심한포르노";
    arrList[arrList.length]="싸죠";
    arrList[arrList.length]="싹쓰리닷컴";
    arrList[arrList.length]="쌕쌕이티비";
    arrList[arrList.length]="쌩molca";
    arrList[arrList.length]="쌩몰카";
    arrList[arrList.length]="쌩보지";
    arrList[arrList.length]="쌩보지쑈";
    arrList[arrList.length]="쌩쇼";
    arrList[arrList.length]="쌩쑈";
    arrList[arrList.length]="쌩포르노";
    arrList[arrList.length]="씨누드21";
    arrList[arrList.length]="씹";
    arrList[arrList.length]="아마걸포르노";
    arrList[arrList.length]="아색기가";
    arrList[arrList.length]="아이러브섹스";
    arrList[arrList.length]="아이러브에로스쿨";
    arrList[arrList.length]="아이섹스스타";
    arrList[arrList.length]="아이제이섹스";
    arrList[arrList.length]="아일러브섹스티비";
    arrList[arrList.length]="알몸";
    arrList[arrList.length]="애로";
    arrList[arrList.length]="애로영화";
    arrList[arrList.length]="애마부인";
    arrList[arrList.length]="애자매";
    arrList[arrList.length]="야게임";
    arrList[arrList.length]="야게임즈닷넷";
    arrList[arrList.length]="야겜";
    arrList[arrList.length]="야동";
    arrList[arrList.length]="야동게시판";
    arrList[arrList.length]="야사";
    arrList[arrList.length]="야설";
    arrList[arrList.length]="야설공작소";
    arrList[arrList.length]="야설신화";
    arrList[arrList.length]="야설의문";
    arrList[arrList.length]="야시";
    arrList[arrList.length]="야시25TV";
    arrList[arrList.length]="야시MTV";
    arrList[arrList.length]="야시molca";
    arrList[arrList.length]="야시걸";
    arrList[arrList.length]="야시꾸리";
    arrList[arrList.length]="야시네";
    arrList[arrList.length]="야시녀";
    arrList[arrList.length]="야시랭크";
    arrList[arrList.length]="야시룸";
    arrList[arrList.length]="야시시";
    arrList[arrList.length]="야시캠";
    arrList[arrList.length]="야시코리아";
    arrList[arrList.length]="야애니";
    arrList[arrList.length]="야오이";
    arrList[arrList.length]="야하네";
    arrList[arrList.length]="야하다";
    arrList[arrList.length]="야한";
    arrList[arrList.length]="야한거";
    arrList[arrList.length]="야한걸";
    arrList[arrList.length]="야한것";
    arrList[arrList.length]="야한게임";
    arrList[arrList.length]="야한그림";
    arrList[arrList.length]="야한놈";
    arrList[arrList.length]="야한놈SEX";
    arrList[arrList.length]="야한누드";
    arrList[arrList.length]="야한동영상";
    arrList[arrList.length]="야한만화";
    arrList[arrList.length]="야한밤";
    arrList[arrList.length]="야한밤TV";
    arrList[arrList.length]="야한밤티브이";
    arrList[arrList.length]="야한사이트";
    arrList[arrList.length]="야한사진";
    arrList[arrList.length]="야한소설";
    arrList[arrList.length]="야한쇼닷컴";
    arrList[arrList.length]="야한영화";
    arrList[arrList.length]="야한이야기";
    arrList[arrList.length]="야한클럽";
    arrList[arrList.length]="야해";
    arrList[arrList.length]="야해요";
    arrList[arrList.length]="어덜트10000";
    arrList[arrList.length]="어덜트TV";
    arrList[arrList.length]="어덜트라이프";
    arrList[arrList.length]="어덜트랜드";
    arrList[arrList.length]="어덜트섹시성인영화관";
    arrList[arrList.length]="어덜트존";
    arrList[arrList.length]="어덜트천사TV";
    arrList[arrList.length]="어덜트코믹플러스";
    arrList[arrList.length]="어덜트탑10";
    arrList[arrList.length]="어덜트피아";
    arrList[arrList.length]="에로";
    arrList[arrList.length]="에로2002";
    arrList[arrList.length]="에로2030";
    arrList[arrList.length]="에로69";
    arrList[arrList.length]="에로69TV";
    arrList[arrList.length]="에로79";
    arrList[arrList.length]="에로걸즈";
    arrList[arrList.length]="에로게이트";
    arrList[arrList.length]="에로게임";
    arrList[arrList.length]="에로관";
    arrList[arrList.length]="에로극장";
    arrList[arrList.length]="에로니폰";
    arrList[arrList.length]="에로닷컴";
    arrList[arrList.length]="에로당";
    arrList[arrList.length]="에로데이";
    arrList[arrList.length]="에로동";
    arrList[arrList.length]="에로동영상";
    arrList[arrList.length]="에로디비";
    arrList[arrList.length]="에로무비";
    arrList[arrList.length]="에로물";
    arrList[arrList.length]="에로뮤직비디오";
    arrList[arrList.length]="에로바다";
    arrList[arrList.length]="에로방티브";
    arrList[arrList.length]="에로배우";
    arrList[arrList.length]="에로비";
    arrList[arrList.length]="에로비디오";
    arrList[arrList.length]="에로비안나이트";
    arrList[arrList.length]="에로샵";
    arrList[arrList.length]="에로세일";
    arrList[arrList.length]="에로섹스TV";
    arrList[arrList.length]="에로소라가이드";
    arrList[arrList.length]="에로쇼";
    arrList[arrList.length]="에로스";
    arrList[arrList.length]="에로스TV";
    arrList[arrList.length]="에로스데이";
    arrList[arrList.length]="에로스아시아";
    arrList[arrList.length]="에로스재팬";
    arrList[arrList.length]="에로스코리아";
    arrList[arrList.length]="에로스쿨";
    arrList[arrList.length]="에로스타";
    arrList[arrList.length]="에로스타일";
    arrList[arrList.length]="에로스페셜";
    arrList[arrList.length]="에로스포유";
    arrList[arrList.length]="에로시네마";
    arrList[arrList.length]="에로시안닷컴";
    arrList[arrList.length]="에로시티";
    arrList[arrList.length]="에로씨네";
    arrList[arrList.length]="에로아시아";
    arrList[arrList.length]="에로앤섹스";
    arrList[arrList.length]="에로야";
    arrList[arrList.length]="에로에스";
    arrList[arrList.length]="에로엔조이";
    arrList[arrList.length]="에로영화";
    arrList[arrList.length]="에로영화관";
    arrList[arrList.length]="에로올";
    arrList[arrList.length]="에로와이프";
    arrList[arrList.length]="에로이브";
    arrList[arrList.length]="에로존";
    arrList[arrList.length]="에로주";
    arrList[arrList.length]="에로촬영현장";
    arrList[arrList.length]="에로카";
    arrList[arrList.length]="에로클릭";
    arrList[arrList.length]="에로투유";
    arrList[arrList.length]="에로틱코리아";
    arrList[arrList.length]="에로파크";
    arrList[arrList.length]="에로패티시";
    arrList[arrList.length]="에로팬티";
    arrList[arrList.length]="에로플래쉬";
    arrList[arrList.length]="에로필름";
    arrList[arrList.length]="엑스노브라";
    arrList[arrList.length]="엑스모텔";
    arrList[arrList.length]="엑스투어덜트";
    arrList[arrList.length]="엔터채널";
    arrList[arrList.length]="여성성인용품";
    arrList[arrList.length]="여성자위기구";
    arrList[arrList.length]="여자보지";
    arrList[arrList.length]="연인사이성인샵";
    arrList[arrList.length]="엽기에로";
    arrList[arrList.length]="엽기적인섹스";
    arrList[arrList.length]="오나니";
    arrList[arrList.length]="오럴섹스";
    arrList[arrList.length]="오렌지야동";
    arrList[arrList.length]="오르가즘";
    arrList[arrList.length]="오마담";
    arrList[arrList.length]="오마이에로";
    arrList[arrList.length]="오마이포르노";
    arrList[arrList.length]="오빠아파";
    arrList[arrList.length]="오빠아파닷컴";
    arrList[arrList.length]="오빠아퍼";
    arrList[arrList.length]="오사카섹스";
    arrList[arrList.length]="오성인";
    arrList[arrList.length]="오섹스";
    arrList[arrList.length]="오섹스야";
    arrList[arrList.length]="오섹스테레비";
    arrList[arrList.length]="오예성인영화";
    arrList[arrList.length]="오이섹스";
    arrList[arrList.length]="오케이섹스";
    arrList[arrList.length]="오케이섹스TV";
    arrList[arrList.length]="오케이섹스molca포르노";
    arrList[arrList.length]="오케이섹스티비";
    arrList[arrList.length]="옷벗기기";
    arrList[arrList.length]="옷벗기기게임";
    arrList[arrList.length]="와우섹스";
    arrList[arrList.length]="왕가스";
    arrList[arrList.length]="용주골";
    arrList[arrList.length]="우라본섹스코리아";
    arrList[arrList.length]="울트라섹스제팬";
    arrList[arrList.length]="원조교제";
    arrList[arrList.length]="월드섹스";
    arrList[arrList.length]="웹섹스코리아";
    arrList[arrList.length]="유호필름";
    arrList[arrList.length]="은빛갤러리";
    arrList[arrList.length]="음모";
    arrList[arrList.length]="이반성인용품몰";
    arrList[arrList.length]="이섹스";
    arrList[arrList.length]="이승희";
    arrList[arrList.length]="인섹스69";
    arrList[arrList.length]="인터넷성인방송";
    arrList[arrList.length]="일본동영상";
    arrList[arrList.length]="일본망가";
    arrList[arrList.length]="일본미소녀";
    arrList[arrList.length]="일본뽀르노";
    arrList[arrList.length]="일본성인만화";
    arrList[arrList.length]="일본성인방송";
    arrList[arrList.length]="일본섹스";
    arrList[arrList.length]="일본포르노";
    arrList[arrList.length]="자위";
    arrList[arrList.length]="자위기구";
    arrList[arrList.length]="자위씬";
    arrList[arrList.length]="자위용품";
    arrList[arrList.length]="자위코리아";
    arrList[arrList.length]="자위행위";
    arrList[arrList.length]="자지";
    arrList[arrList.length]="재팬마나";
    arrList[arrList.length]="재팬만화";
    arrList[arrList.length]="재팬팬티";
    arrList[arrList.length]="정력강화용품";
    arrList[arrList.length]="정력팬티";
    arrList[arrList.length]="정력포탈";
    arrList[arrList.length]="정사씬모음";
    arrList[arrList.length]="정사채널";
    arrList[arrList.length]="정세희";
    arrList[arrList.length]="정양";
    arrList[arrList.length]="정우성인용품전문점";
    arrList[arrList.length]="제이제이일본성인만화";
    arrList[arrList.length]="졸라야한닷컴";
    arrList[arrList.length]="좆물";
    arrList[arrList.length]="좋은생활성인용품";
    arrList[arrList.length]="진도희";
    arrList[arrList.length]="진주희";
    arrList[arrList.length]="찌찌";
    arrList[arrList.length]="채널레드TV";
    arrList[arrList.length]="체위";
    arrList[arrList.length]="체위동영상";
    arrList[arrList.length]="카섹스";
    arrList[arrList.length]="칼라섹스";
    arrList[arrList.length]="코리아성인가이드";
    arrList[arrList.length]="코리아섹스";
    arrList[arrList.length]="코리아섹스샵";
    arrList[arrList.length]="코리아스트립닷컴";
    arrList[arrList.length]="코리아엑스파일";
    arrList[arrList.length]="코리아포로노";
    arrList[arrList.length]="코리안걸스";
    arrList[arrList.length]="코섹스";
    arrList[arrList.length]="콘돔";
    arrList[arrList.length]="콘돔나라";
    arrList[arrList.length]="콘돔닥터";
    arrList[arrList.length]="콘돔몰";
    arrList[arrList.length]="콘돔예스";
    arrList[arrList.length]="콘돔피아";
    arrList[arrList.length]="쿠키걸";
    arrList[arrList.length]="쿨에로";
    arrList[arrList.length]="크림걸";
    arrList[arrList.length]="클럽AV스타";
    arrList[arrList.length]="클럽에로";
    arrList[arrList.length]="킬링티비";
    arrList[arrList.length]="타부코리아";
    arrList[arrList.length]="토탈에로";
    arrList[arrList.length]="투앤투동거클럽";
    arrList[arrList.length]="트위스트김";
    arrList[arrList.length]="트위스트김섹스코리아";
    arrList[arrList.length]="트위스트김소라가이드";
    arrList[arrList.length]="트위스트킴";
    arrList[arrList.length]="특수콘돔";
    arrList[arrList.length]="패션앤섹스";
    arrList[arrList.length]="패티쉬";
    arrList[arrList.length]="패티쉬우먼";
    arrList[arrList.length]="팬티캔디";
    arrList[arrList.length]="페로몬";
    arrList[arrList.length]="페티걸";
    arrList[arrList.length]="페티쉬";
    arrList[arrList.length]="페티쉬러브";
    arrList[arrList.length]="페티쉬매거진";
    arrList[arrList.length]="페티쉬우먼";
    arrList[arrList.length]="페티쉬즘";
    arrList[arrList.length]="페티쉬코리아";
    arrList[arrList.length]="페티시";
    arrList[arrList.length]="펜트하우스";
    arrList[arrList.length]="포로노";
    arrList[arrList.length]="포르노";
    arrList[arrList.length]="포르노24시";
    arrList[arrList.length]="포르노CINEMA";
    arrList[arrList.length]="포르노CNN";
    arrList[arrList.length]="포르노TV";
    arrList[arrList.length]="포르노bozi";
    arrList[arrList.length]="포르노molca티비";
    arrList[arrList.length]="포르노porno섹스코리아";
    arrList[arrList.length]="포르노worldcup";
    arrList[arrList.length]="포르노yadong";
    arrList[arrList.length]="포르노yadong섹스코리아";
    arrList[arrList.length]="포르노그라피";
    arrList[arrList.length]="포르노로";
    arrList[arrList.length]="포르노마나";
    arrList[arrList.length]="포르노만화";
    arrList[arrList.length]="포르노바다";
    arrList[arrList.length]="포르노사진";
    arrList[arrList.length]="포르노세상";
    arrList[arrList.length]="포르노섹스";
    arrList[arrList.length]="포르노섹스TV";
    arrList[arrList.length]="포르노섹스마나";
    arrList[arrList.length]="포르노섹스소라가이드";
    arrList[arrList.length]="포르노섹스코리아";
    arrList[arrList.length]="포르노섹스코리아소라가이드";
    arrList[arrList.length]="포르노섹스트위스트김";
    arrList[arrList.length]="포르노업";
    arrList[arrList.length]="포르노오팔팔";
    arrList[arrList.length]="포르노월드";
    arrList[arrList.length]="포르노집닷컴";
    arrList[arrList.length]="포르노천국";
    arrList[arrList.length]="포르노천사";
    arrList[arrList.length]="포르노테이프";
    arrList[arrList.length]="포르노테입";
    arrList[arrList.length]="포르노테잎";
    arrList[arrList.length]="포르노티비2580";
    arrList[arrList.length]="포르노파티";
    arrList[arrList.length]="포르노플레이보이";
    arrList[arrList.length]="포르로";
    arrList[arrList.length]="포카리섹스";
    arrList[arrList.length]="프리섹스샵";
    arrList[arrList.length]="프리섹스성인용품";
    arrList[arrList.length]="프리섹스코리아";
    arrList[arrList.length]="플래이보이";
    arrList[arrList.length]="플레이보이";
    arrList[arrList.length]="플레이보이2030";
    arrList[arrList.length]="플레이보이소라가이드";
    arrList[arrList.length]="플레이보이온라인";
    arrList[arrList.length]="플레이보조개";
    arrList[arrList.length]="플레이보지";
    arrList[arrList.length]="플레이섹스";
    arrList[arrList.length]="피임기구";
    arrList[arrList.length]="피임용품";
    arrList[arrList.length]="핑크누드샵";
    arrList[arrList.length]="핑크섹스샵";
    arrList[arrList.length]="핑크코리아";
    arrList[arrList.length]="하드코어";
    arrList[arrList.length]="하리수누드";
    arrList[arrList.length]="하이텔성인CLUB+19";
    arrList[arrList.length]="한글섹스사이트";
    arrList[arrList.length]="한글섹스코리아";
    arrList[arrList.length]="한스테이성인";
    arrList[arrList.length]="핫도그TV";
    arrList[arrList.length]="핫성인용품";
    arrList[arrList.length]="핫섹스";
    arrList[arrList.length]="핫섹스재팬";
    arrList[arrList.length]="핫섹스코리아";
    arrList[arrList.length]="핫포르노";
    arrList[arrList.length]="핫포르노섹스";
    arrList[arrList.length]="항문섹스";
    arrList[arrList.length]="해적,야동";
    arrList[arrList.length]="해피데이21성인용품";
    arrList[arrList.length]="해피투섹스";
    arrList[arrList.length]="향기콘돔";
    arrList[arrList.length]="헤라러브샵";
    arrList[arrList.length]="헨타이";
    arrList[arrList.length]="헨타이망가";
    arrList[arrList.length]="헬로우콘돔";
    arrList[arrList.length]="헬프섹스";
    arrList[arrList.length]="홈섹스TV";
    arrList[arrList.length]="화이트섹스라인";
    arrList[arrList.length]="후이즈섹스";
    arrList[arrList.length]="후장";
    arrList[arrList.length]="훔쳐보기";
    arrList[arrList.length]="히든포르노";
    arrList[arrList.length]="세희네";
    arrList[arrList.length]="seheene";
    arrList[arrList.length]="세희네쩜넷";
    arrList[arrList.length]="무비왕";
    arrList[arrList.length]="av배우";
    arrList[arrList.length]="잠지만화";
    arrList[arrList.length]="야설소라의가이드";
    arrList[arrList.length]="섹시녀사진";
    arrList[arrList.length]="잠지";
    arrList[arrList.length]="uh-oh.com";
    arrList[arrList.length]="여성자위시대";
    arrList[arrList.length]="www.uh-oh.com";
    arrList[arrList.length]="bubunara";
    arrList[arrList.length]="공짜성인싸이트";
    arrList[arrList.length]="빈야와레즈";
    arrList[arrList.length]="코섹스넷";
    arrList[arrList.length]="강추야동";
    arrList[arrList.length]="케이섹스";
    arrList[arrList.length]="옷벗기기게임자신있는";
    arrList[arrList.length]="x386";
    arrList[arrList.length]="k양비디오";
    arrList[arrList.length]="tprtm";
    arrList[arrList.length]="옆기성인동영상";
    arrList[arrList.length]="잠지털게임";
    arrList[arrList.length]="성인사이트메로나";
    arrList[arrList.length]="K양";
    arrList[arrList.length]="메로나섹스";
    arrList[arrList.length]="live10tv.com";
    arrList[arrList.length]="옷벗기기게임잡지";
    arrList[arrList.length]="도쿄마나";
    arrList[arrList.length]="www.sex2848.com";
    arrList[arrList.length]="게시판섹스";
    arrList[arrList.length]="소라의";
    arrList[arrList.length]="섹스존";
    arrList[arrList.length]="산소와레즈";
    arrList[arrList.length]="야한거벌끈벌끈";
    arrList[arrList.length]="섹시포유";
    arrList[arrList.length]="한국야동";
    arrList[arrList.length]="두두와레즈";
    arrList[arrList.length]="섹스망가";
    arrList[arrList.length]="야겜usa";
    arrList[arrList.length]="포노";
    arrList[arrList.length]="야한게임만남의장소";
    arrList[arrList.length]="조쟁이.잠지.키스";
    arrList[arrList.length]="잠지hentai";
    arrList[arrList.length]="여자옷벗기기게임";
    arrList[arrList.length]="센스디스";
    arrList[arrList.length]="센스디스무비";
    arrList[arrList.length]="하소연";
    arrList[arrList.length]="성현아누드";
    arrList[arrList.length]="성현아누드집";
    arrList[arrList.length]="에로키위";
    arrList[arrList.length]="은주조갯살";
    arrList[arrList.length]="빨간궁뎅이";
    arrList[arrList.length]="바부와레즈";
    arrList[arrList.length]="당근와레즈";
    arrList[arrList.length]="게임와레즈";
    arrList[arrList.length]="누드모델가능해";
    arrList[arrList.length]="성인메로나";
    arrList[arrList.length]="우기와레즈";
    arrList[arrList.length]="성현아누드사진";
    arrList[arrList.length]="꽃게와레즈";
    arrList[arrList.length]="클릭와레즈";
    arrList[arrList.length]="메로나,성인";
    arrList[arrList.length]="야쿠르트와레즈";
    arrList[arrList.length]="오아시스걸";
    arrList[arrList.length]="스카이와레즈";
    arrList[arrList.length]="신마담와레즈";
    arrList[arrList.length]="럭키와레즈";
    arrList[arrList.length]="성인방송69채널";
    arrList[arrList.length]="야한놈엽기";
    arrList[arrList.length]="와레즈코리아";
    arrList[arrList.length]="우기의최강와레즈";
    arrList[arrList.length]="세희넷";
    arrList[arrList.length]="와레즈용";
    arrList[arrList.length]="와레즈79";
    arrList[arrList.length]="앙마와레즈";
    arrList[arrList.length]="스누피야동";
    arrList[arrList.length]="누드크로키";
    arrList[arrList.length]="59time";
    arrList[arrList.length]="공주야동";
    arrList[arrList.length]="와래즈$디아블로";
    arrList[arrList.length]="에로스토토";
    arrList[arrList.length]="블루와레즈";
    arrList[arrList.length]="www.live10tv.com";
    arrList[arrList.length]="키위와레즈";
    arrList[arrList.length]="영화와레즈";
    arrList[arrList.length]="www.sex012.com";
    arrList[arrList.length]="2040love";
    arrList[arrList.length]="성현아누두집";
    arrList[arrList.length]="심마담";
    arrList[arrList.length]="게시판아동";
    arrList[arrList.length]="광년이보지";
    arrList[arrList.length]="어두움와레즈";
    arrList[arrList.length]="샤먼와레즈";
    arrList[arrList.length]="섹시동영상성생활";
    arrList[arrList.length]="만두와레즈";
    arrList[arrList.length]="ya-han";
    arrList[arrList.length]="대박와레즈";
    arrList[arrList.length]="신마담pds";
    arrList[arrList.length]="69tv";
    arrList[arrList.length]="구멍야동";
    arrList[arrList.length]="worldsex.com";
    arrList[arrList.length]="최강와레즈";
    arrList[arrList.length]="게시판성현아";
    arrList[arrList.length]="sexy588";
    arrList[arrList.length]="몰카넷";
    arrList[arrList.length]="와레즈탑";
    arrList[arrList.length]="성현아누드갤러리";
    arrList[arrList.length]="야동자료실";
    arrList[arrList.length]="여고야동";
    arrList[arrList.length]="유민누드사진";
    arrList[arrList.length]="황수정야동";
    arrList[arrList.length]="게시판sex";
    arrList[arrList.length]="동거사이트";
    arrList[arrList.length]="게시판와레즈";
    arrList[arrList.length]="가시나와레즈";
    arrList[arrList.length]="어둠와레즈";
    arrList[arrList.length]="와레즈사이트";
    arrList[arrList.length]="와레즈게임";
    arrList[arrList.length]="해적와레즈";
    arrList[arrList.length]="퓨전와레즈";
    arrList[arrList.length]="유틸와레즈";
    arrList[arrList.length]="파랑새와레즈";
    arrList[arrList.length]="우기의와레즈";
    arrList[arrList.length]="꼬께와레즈";
    arrList[arrList.length]="미소녀사진";
    arrList[arrList.length]="확끈이와레즈";
    arrList[arrList.length]="쿨와레즈";
    arrList[arrList.length]="돼지와레즈";
    arrList[arrList.length]="브라운와레즈";
    arrList[arrList.length]="럭키월드와레즈";
    arrList[arrList.length]="무료와레즈";
    arrList[arrList.length]="참치와레즈";
    arrList[arrList.length]="와레즈타운";
    arrList[arrList.length]="메로나.co.kr";
    arrList[arrList.length]="메로나";
    arrList[arrList.length]="ㄴㄷㅌ";
    arrList[arrList.length]="원조tv";
    arrList[arrList.length]="빡촌";
    arrList[arrList.length]="울트라쏘세지";
    arrList[arrList.length]="울트라소세지";
    arrList[arrList.length]="목욕탕몰카";
    arrList[arrList.length]="은주조개살";
    arrList[arrList.length]="봉숙이";
    arrList[arrList.length]="live69tv";
    arrList[arrList.length]="오현경비디오";
    arrList[arrList.length]="꼬추";
    arrList[arrList.length]="애무";
    arrList[arrList.length]="와레즈";
    arrList[arrList.length]="warez";
    arrList[arrList.length]="오레즈";
    arrList[arrList.length]="오레즈와레즈";
    arrList[arrList.length]="졸라맨똥피하기게임다운로드";
    arrList[arrList.length]="짱와레즈";
    arrList[arrList.length]="짱와레즈닷컴";
    arrList[arrList.length]="h양비디오";
    arrList[arrList.length]="h양";
    arrList[arrList.length]="따무라";
    arrList[arrList.length]="무료포르노사이트";
    arrList[arrList.length]="야해요야동";
    arrList[arrList.length]="야시꾸리닷컴";
    arrList[arrList.length]="h양섹스비디오";
    arrList[arrList.length]="누드겔러리";
    arrList[arrList.length]="야덩";
    arrList[arrList.length]="미야리야동";
    arrList[arrList.length]="성인폰팅";
    arrList[arrList.length]="성인미팅";
    arrList[arrList.length]="성인번개";
    arrList[arrList.length]="성인만남";
    arrList[arrList.length]="성인전화";
    arrList[arrList.length]="성인벙개";
    arrList[arrList.length]="성인킹카";
    arrList[arrList.length]="성인퀸카";
    arrList[arrList.length]="졸라섹스";
    arrList[arrList.length]="재벌딸포르노";
    arrList[arrList.length]="재벌포르노";
    arrList[arrList.length]="딸포르노";
    arrList[arrList.length]="재벌딸동영상";
    arrList[arrList.length]="맛있는섹스";
    arrList[arrList.length]="맛있는섹스그리고사랑";
    arrList[arrList.length]="미씨촌";
    arrList[arrList.length]="권민중누드";
    arrList[arrList.length]="권민중누드집";
    arrList[arrList.length]="이브의욕망";
    arrList[arrList.length]="동거";
    arrList[arrList.length]="폰섹스";
    arrList[arrList.length]="전화방";
    arrList[arrList.length]="번개팅";
    arrList[arrList.length]="폰팅";
    arrList[arrList.length]="교제알선";
    arrList[arrList.length]="클리토리스";
    arrList[arrList.length]="포르노사이트";
    arrList[arrList.length]="성인정보검색";
    arrList[arrList.length]="성인전용서비스";
    arrList[arrList.length]="따무라야동";
    arrList[arrList.length]="노팬티";
    arrList[arrList.length]="딜도";
    arrList[arrList.length]="마스터베이션";
    arrList[arrList.length]="망까";
    arrList[arrList.length]="머니헌터";
    arrList[arrList.length]="멜섭";
    arrList[arrList.length]="몰래";
    arrList[arrList.length]="바구리";
    arrList[arrList.length]="밤일";
    arrList[arrList.length]="번색";
    arrList[arrList.length]="본디지";
    arrList[arrList.length]="부랄";
    arrList[arrList.length]="빙신";
    arrList[arrList.length]="빠걸";
    arrList[arrList.length]="빠꾸리";
    arrList[arrList.length]="빠라";
    arrList[arrList.length]="빠라줘";
    arrList[arrList.length]="빨간마후라";
    arrList[arrList.length]="빨강마후라";
    arrList[arrList.length]="빨기";
    arrList[arrList.length]="빨어";
    arrList[arrList.length]="빽보지";
    arrList[arrList.length]="뽀로노";
    arrList[arrList.length]="사까치";
    arrList[arrList.length]="색골";
    arrList[arrList.length]="성감대";
    arrList[arrList.length]="성생활";
    arrList[arrList.length]="세엑";
    arrList[arrList.length]="섹골";
    arrList[arrList.length]="섹녀";
    arrList[arrList.length]="섹도우즈";
    arrList[arrList.length]="섹무비";
    arrList[arrList.length]="섹보지";
    arrList[arrList.length]="섹소리";
    arrList[arrList.length]="섹티즌";
    arrList[arrList.length]="섹할";
    arrList[arrList.length]="쇼킹";
    arrList[arrList.length]="수타킹";
    arrList[arrList.length]="스트립쑈";
    arrList[arrList.length]="스쿨걸";
    arrList[arrList.length]="쌕스";
    arrList[arrList.length]="에니탑";
    arrList[arrList.length]="야껨";
    arrList[arrList.length]="야똥";
    arrList[arrList.length]="야섹";
    arrList[arrList.length]="영계";
    arrList[arrList.length]="오랄";
    arrList[arrList.length]="유흥";
    arrList[arrList.length]="육봉";
    arrList[arrList.length]="잡년";
    arrList[arrList.length]="잡놈";
    arrList[arrList.length]="재랄";
    arrList[arrList.length]="저년";
    arrList[arrList.length]="정사";
    arrList[arrList.length]="조루";
    arrList[arrList.length]="쫒";
    arrList[arrList.length]="크리토리스";
    arrList[arrList.length]="페니스";
    arrList[arrList.length]="패티시";
    arrList[arrList.length]="페팅";
    arrList[arrList.length]="펜티";
    arrList[arrList.length]="펨돔";
    arrList[arrList.length]="펨섭";
    arrList[arrList.length]="포르느";
    arrList[arrList.length]="핸타이";
    arrList[arrList.length]="헴타이";
    arrList[arrList.length]="호빠";
    arrList[arrList.length]="혼음";
    arrList[arrList.length]="bdsm";
    arrList[arrList.length]="bo지";
    arrList[arrList.length]="bozi";
    arrList[arrList.length]="c2joy";
    arrList[arrList.length]="dildo";
    arrList[arrList.length]="haduri";
    arrList[arrList.length]="hardcore";
    arrList[arrList.length]="hentai";
    arrList[arrList.length]="IJ";
    arrList[arrList.length]="jaji";
    arrList[arrList.length]="jgirls";
    arrList[arrList.length]="kgirls";
    arrList[arrList.length]="nallari";
    arrList[arrList.length]="playbog";
    arrList[arrList.length]="porn";
    arrList[arrList.length]="1090tv";
    arrList[arrList.length]="2c8";
    arrList[arrList.length]="99bb";
    arrList[arrList.length]="민셩야설";
    arrList[arrList.length]="보짓";
    arrList[arrList.length]="빠굴";
    arrList[arrList.length]="빠순이";
    arrList[arrList.length]="빨아";
    arrList[arrList.length]="뻐킹";
    arrList[arrList.length]="수간";
    arrList[arrList.length]="쌕쉬";
    arrList[arrList.length]="쎄끈";
    arrList[arrList.length]="와래즈";
    arrList[arrList.length]="애널";
    arrList[arrList.length]="오럴";
    arrList[arrList.length]="육갑";
    arrList[arrList.length]="윤간";
    arrList[arrList.length]="음욕";
    arrList[arrList.length]="음탕";
    arrList[arrList.length]="쪼가리";
    arrList[arrList.length]="컴섹";
    arrList[arrList.length]="폰색";
    arrList[arrList.length]="호스트빠";
    arrList[arrList.length]="fuck";
    arrList[arrList.length]="gangbang";
    arrList[arrList.length]="jfantasy";
    arrList[arrList.length]="jasal";
    arrList[arrList.length]="playbozi";
    arrList[arrList.length]="prno";
    arrList[arrList.length]="귀두";
    arrList[arrList.length]="구멍";
    arrList[arrList.length]="난교";
    arrList[arrList.length]="도촬";
    arrList[arrList.length]="바이브레이터";
    arrList[arrList.length]="불륜";
    arrList[arrList.length]="뻐르너";
    arrList[arrList.length]="뻐르노";
    arrList[arrList.length]="뻘노";
    arrList[arrList.length]="뽀르너";
    arrList[arrList.length]="뽀지";
    arrList[arrList.length]="뽈노";
    arrList[arrList.length]="삽입";
    arrList[arrList.length]="색쓰";
    arrList[arrList.length]="섹쑤";
    arrList[arrList.length]="섹쓰";
    arrList[arrList.length]="수음";
    arrList[arrList.length]="스너프";
    arrList[arrList.length]="스왑";
    arrList[arrList.length]="애액";
    arrList[arrList.length]="엑스터시";
    arrList[arrList.length]="야근병동";
    arrList[arrList.length]="음란";
    arrList[arrList.length]="음부";
    arrList[arrList.length]="이반";
    arrList[arrList.length]="젖";
    arrList[arrList.length]="최음제";
    arrList[arrList.length]="치마속";
    arrList[arrList.length]="패니스";
    arrList[arrList.length]="팬티";
    arrList[arrList.length]="퍼르노";
    arrList[arrList.length]="포경";
    arrList[arrList.length]="포르너";
    arrList[arrList.length]="폰쎅";
    arrList[arrList.length]="히로뽕";
    arrList[arrList.length]="asiangirl";
    arrList[arrList.length]="ecstasy";
    arrList[arrList.length]="hidden";
    arrList[arrList.length]="oral";
    arrList[arrList.length]="penis";
    arrList[arrList.length]="penthouse";
    arrList[arrList.length]="porner";
    arrList[arrList.length]="suck";
    arrList[arrList.length]="swaping";
//    arrList[arrList.length]="18";
    arrList[arrList.length]="넣어줘";
    arrList[arrList.length]="무삭제원판";
    arrList[arrList.length]="색녀";
    arrList[arrList.length]="좃물";
    arrList[arrList.length]="퇴폐이발소";
    arrList[arrList.length]="보짓물";
    arrList[arrList.length]="무료성인소설";
    arrList[arrList.length]="씹물";
    arrList[arrList.length]="스와핑모임";
    arrList[arrList.length]="부부교환섹스";
    arrList[arrList.length]="섹스나이트";
    arrList[arrList.length]="섹스경험담";
    arrList[arrList.length]="sora's";
    arrList[arrList.length]="봉숙이야동";
    arrList[arrList.length]="보지빨기";
    arrList[arrList.length]="여자옷벗기기";
    arrList[arrList.length]="폰섹";
    arrList[arrList.length]="캠빨";
    arrList[arrList.length]="김완선누드";
    arrList[arrList.length]="김완선누드집";
    arrList[arrList.length]="이혜영누드";
    arrList[arrList.length]="이혜영누드집";
    arrList[arrList.length]="이주현누드";
    arrList[arrList.length]="이주현누드집";
    arrList[arrList.length]="이주현nude";
    arrList[arrList.length]="김완선nude";
    arrList[arrList.length]="이혜영nude";
    arrList[arrList.length]="이지현누드";
    arrList[arrList.length]="이지현누드집";
    arrList[arrList.length]="지현누드집";
    arrList[arrList.length]="이지현누드사진";
    arrList[arrList.length]="이지현누드샘플";
    arrList[arrList.length]="화상폰팅";
    arrList[arrList.length]="티켓다방";
    arrList[arrList.length]="여자따먹기";
    arrList[arrList.length]="보지먹기";
    arrList[arrList.length]="보지따기";
    arrList[arrList.length]="포르노키위";
    arrList[arrList.length]="포르노배우";
    arrList[arrList.length]="성인포르노";
    arrList[arrList.length]="공짜포르노";
    arrList[arrList.length]="포르노스타";
    arrList[arrList.length]="한국포르노";
    arrList[arrList.length]="포르노비디오";
    arrList[arrList.length]="백지영포르노";
    arrList[arrList.length]="포르노자키";
    arrList[arrList.length]="슬림페티쉬";
    arrList[arrList.length]="무료페티쉬";
    arrList[arrList.length]="스타킹페티쉬";
    arrList[arrList.length]="무료성인게시판";
    arrList[arrList.length]="자위방법";
    arrList[arrList.length]="여자자위";
    arrList[arrList.length]="화장실몰카";
    arrList[arrList.length]="몰카동영상";
    arrList[arrList.length]="여고생몰카";
    arrList[arrList.length]="몰카리스트";
    arrList[arrList.length]="스와핑몰카";
    arrList[arrList.length]="이발소몰카";
    arrList[arrList.length]="몰카사진";
    arrList[arrList.length]="몰카사이트";
    arrList[arrList.length]="백지영몰카";
    arrList[arrList.length]="변태사진";
    arrList[arrList.length]="변태게임";
    arrList[arrList.length]="변태만화";
    arrList[arrList.length]="변태이야기";
    arrList[arrList.length]="변태섹스";
    arrList[arrList.length]="변태사이트";
    arrList[arrList.length]="아줌마보지";
    arrList[arrList.length]="아줌마섹스";
    arrList[arrList.length]="아줌마페티쉬";
    arrList[arrList.length]="아줌마야동";
    arrList[arrList.length]="자지털";
    arrList[arrList.length]="보지자지";
    arrList[arrList.length]="자지빨기";
    arrList[arrList.length]="헤어누드";
    arrList[arrList.length]="남자누드";
    arrList[arrList.length]="연예인누드";
    arrList[arrList.length]="세미누드";
    arrList[arrList.length]="고소영누드";
    arrList[arrList.length]="셀프누드";
    arrList[arrList.length]="무료누드";
    arrList[arrList.length]="이효리누드";
    arrList[arrList.length]="이혜영누드사진";
    arrList[arrList.length]="누드동영상";
    arrList[arrList.length]="이헤영누드";
    arrList[arrList.length]="여자누드";
    arrList[arrList.length]="남성누드";
    arrList[arrList.length]="야설게시판";
    arrList[arrList.length]="소라야설";
    arrList[arrList.length]="무료성인야설";
    arrList[arrList.length]="소라의야설";
    arrList[arrList.length]="야설록";
    arrList[arrList.length]="공짜야설";
    arrList[arrList.length]="소라가이드야설";
    arrList[arrList.length]="소라의야설가이드";
    arrList[arrList.length]="소라야설공작소";
    arrList[arrList.length]="야한미소녀";
    arrList[arrList.length]="미아리야동";
    arrList[arrList.length]="빠구리야동";
    arrList[arrList.length]="무료섹스페티쉬";
    arrList[arrList.length]="함소원누드";
    arrList[arrList.length]="함소원";
    arrList[arrList.length]="함소원누드집";
    arrList[arrList.length]="함소원nude";
    arrList[arrList.length]="소원누드";
    arrList[arrList.length]="소원누드집";
    arrList[arrList.length]="함소원헤어누드";
    arrList[arrList.length]="함소원누드사진";
    arrList[arrList.length]="함소원누두";
    arrList[arrList.length]="함소원누드보기";
    
    arrList[arrList.length]  = "자살";
	arrList[arrList.length]  = "음독";
	arrList[arrList.length]  = "청산가리";
	arrList[arrList.length]  = "청산가루";

	for(i=0; i < arrList.length; i++){
		var badWord = arrList[i];
   
      while(true){
		if(msg.indexOf(badWord) != -1){
			alert("부적절한 단어가 포함되어 있습니다. [ " + badWord  + " ]" );
			
			msg = msg.replace(badWord, "");
			return false;
		}
		else{
			break;
		}
	  }
	}

	return true;
}

function wordchk2(msg) {
	arrList = new Array();
/*
	arrList[arrList.length]  = "테마샵";
	arrList[arrList.length]  = "테마몰";
	arrList[arrList.length]  = "테마가게";
	arrList[arrList.length]  = "테마상점";
	arrList[arrList.length]  = "모바일몰";
	arrList[arrList.length]  = "모바일샵";
	arrList[arrList.length]  = "브랜드샵";
	arrList[arrList.length]  = "스타샵";
	arrList[arrList.length]  = "선물상점";
	arrList[arrList.length]  = "음반상점";
	arrList[arrList.length]  = "음악상점";
	arrList[arrList.length]  = "카페상점";
	arrList[arrList.length]  = "까페상점";
	arrList[arrList.length]  = "카패상점";
	arrList[arrList.length]  = "카페상점";
	arrList[arrList.length]  = "음반가게";
	arrList[arrList.length]  = "음반샵";
	arrList[arrList.length]  = "음반몰";
	arrList[arrList.length]  = "신고센터";
	arrList[arrList.length]  = "뮤직샵";
	arrList[arrList.length]  = "뮤직몰";
	arrList[arrList.length]  = "음악샵";
	arrList[arrList.length]  = "음악가게";
	arrList[arrList.length]  = "음악몰";
	arrList[arrList.length]  = "우수고객";
	arrList[arrList.length]  = "도우미";
	arrList[arrList.length]  = "고객센터";
	arrList[arrList.length]  = "고객센타";
	arrList[arrList.length]  = "고객샌터";
	arrList[arrList.length]  = "고객샌타";
	arrList[arrList.length]  = "할인쿠폰";
	arrList[arrList.length]  = "카페몰";
	arrList[arrList.length]  = "카페샵";
	arrList[arrList.length]  = "까페몰";
	arrList[arrList.length]  = "까페샵";
	arrList[arrList.length]  = "카패몰";
	arrList[arrList.length]  = "카페샵";
	arrList[arrList.length]  = "까패몰";
	arrList[arrList.length]  = "까패샵";
	arrList[arrList.length]  = "캐릭터몰";
	arrList[arrList.length]  = "캐릭터샵";
	arrList[arrList.length]  = "케릭터몰";
	arrList[arrList.length]  = "케릭터샵";
	arrList[arrList.length]  = "게임머니";
	arrList[arrList.length]  = "가게주인";
	arrList[arrList.length]  = "기프트샵";
	arrList[arrList.length]  = "기프트몰";
	arrList[arrList.length]  = "아이템샵";
	arrList[arrList.length]  = "아이템몰";
	arrList[arrList.length]  = "아바타샵";
	arrList[arrList.length]  = "아바타몰";
	arrList[arrList.length]  = "쾌스천맨";
	arrList[arrList.length]  = "쾌스천걸";
	arrList[arrList.length]  = "퀘스천맨";
	arrList[arrList.length]  = "퀘스천걸";
	arrList[arrList.length]  = "선물가게";
	arrList[arrList.length]  = "선물몰";
	arrList[arrList.length]  = "선물샵";
	arrList[arrList.length]  = "아바타";
	arrList[arrList.length]  = "주인장";
	arrList[arrList.length]  = "블로거";
	arrList[arrList.length]  = "블로깅";
	arrList[arrList.length]  = "불로거";
	arrList[arrList.length]  = "불로깅";
	arrList[arrList.length]  = "볼로거";
	arrList[arrList.length]  = "볼로깅";
	arrList[arrList.length]  = "볼르거";
	arrList[arrList.length]  = "볼르깅";
	arrList[arrList.length]  = "불르거";
	arrList[arrList.length]  = "불르깅";
	arrList[arrList.length]  = "블로그";
	arrList[arrList.length]  = "블르그";
	arrList[arrList.length]  = "불로그";
	arrList[arrList.length]  = "불르그";
	arrList[arrList.length]  = "볼로그";
	arrList[arrList.length]  = "볼르그";
	arrList[arrList.length]  = "네이버";
	arrList[arrList.length]  = "내이버";
	arrList[arrList.length]  = "운영자";
	arrList[arrList.length]  = "관리자";
	arrList[arrList.length]  = "어드민";
	arrList[arrList.length]  = "마스터";
	arrList[arrList.length]  = "엔에치엔";
	arrList[arrList.length]  = "엔에치앤";
	arrList[arrList.length]  = "엔애치앤";
	arrList[arrList.length]  = "엔애치엔";
	arrList[arrList.length]  = "앤에치엔";
	arrList[arrList.length]  = "앤에치앤";
	arrList[arrList.length]  = "앤애치앤";
	arrList[arrList.length]  = "앤애치엔";
	arrList[arrList.length]  = "엔에취엔";
	arrList[arrList.length]  = "엔에취앤";
	arrList[arrList.length]  = "엔애치앤";
	arrList[arrList.length]  = "엔애취엔";
	arrList[arrList.length]  = "앤에취엔";
	arrList[arrList.length]  = "앤에취앤";
	arrList[arrList.length]  = "앤애취앤";
	arrList[arrList.length]  = "앤애취엔";
	arrList[arrList.length]  = "페이퍼";
	arrList[arrList.length]  = "지식인";
	arrList[arrList.length]  = "쥬니버";
	arrList[arrList.length]  = "미즈클럽";
	arrList[arrList.length]  = "토크광장";
	arrList[arrList.length]  = "포토앨범";
	arrList[arrList.length]  = "마이홈";
	arrList[arrList.length]  = "엔메거진";
	arrList[arrList.length]  = "앤메거진";
	arrList[arrList.length]  = "엔매거진";
	arrList[arrList.length]  = "앤매거진";
	arrList[arrList.length]  = "미즈네";
	arrList[arrList.length]  = "키워드샵";
	arrList[arrList.length]  = "엔토이";
	arrList[arrList.length]  = "폴에버";
	arrList[arrList.length]  = "폴애버";
	arrList[arrList.length]  = "한게임";
	arrList[arrList.length]  = "한개임";
	arrList[arrList.length]  = "엔토이";
	arrList[arrList.length]  = "앤토이";
	arrList[arrList.length]  = "블로그씨";
	arrList[arrList.length]  = "블로그";
	arrList[arrList.length]  = "네이버";
	arrList[arrList.length]  = "내이버";
	arrList[arrList.length]  = "운영자";
	arrList[arrList.length]  = "관리자";
	arrList[arrList.length]  = "어드민";
	arrList[arrList.length]  = "마스터";
	arrList[arrList.length]  = "엔에치엔";
	arrList[arrList.length]  = "엔에치앤";
	arrList[arrList.length]  = "엔애치앤";
	arrList[arrList.length]  = "엔애치엔";
	arrList[arrList.length]  = "앤에치엔";
	arrList[arrList.length]  = "앤에치앤";
	arrList[arrList.length]  = "앤애치앤";
	arrList[arrList.length]  = "앤애치엔";
	arrList[arrList.length]  = "엔에취엔";
	arrList[arrList.length]  = "엔에취앤";
	arrList[arrList.length]  = "엔애치앤";
	arrList[arrList.length]  = "엔애취엔";
	arrList[arrList.length]  = "앤에취엔";
	arrList[arrList.length]  = "앤에취앤";
	arrList[arrList.length]  = "앤애취앤";
	arrList[arrList.length]  = "앤애취엔";
	arrList[arrList.length]  = "페이퍼";
	arrList[arrList.length]  = "지식인";
	arrList[arrList.length]  = "쥬니버";
	arrList[arrList.length]  = "미즈클럽";
	arrList[arrList.length]  = "토크광장";
	arrList[arrList.length]  = "포토앨범";
	arrList[arrList.length]  = "마이홈";
	arrList[arrList.length]  = "엔메거진";
	arrList[arrList.length]  = "앤메거진";
	arrList[arrList.length]  = "엔매거진";
	arrList[arrList.length]  = "앤매거진";
	arrList[arrList.length]  = "미즈네";
	arrList[arrList.length]  = "키워드샵";
	arrList[arrList.length]  = "엔토이";
	arrList[arrList.length]  = "폴에버";
	arrList[arrList.length]  = "폴애버";
	arrList[arrList.length]  = "한게임";
	arrList[arrList.length]  = "한개임";
	arrList[arrList.length]  = "엔토이";
	arrList[arrList.length]  = "앤토이";
	arrList[arrList.length]  = "매니저";
	arrList[arrList.length]  = "메니저";
	arrList[arrList.length]  = "매니져";
	arrList[arrList.length]  = "메니져";
	arrList[arrList.length]  = "스탭";
	arrList[arrList.length]  = "스텝";*/
	arrList[arrList.length]  = "갈보";
	arrList[arrList.length]  = "강간";
	arrList[arrList.length]  = "개년";
	arrList[arrList.length]  = "개놈";
	arrList[arrList.length]  = "개뇬";
	arrList[arrList.length]  = "개보지";
	arrList[arrList.length]  = "개삽년";
	arrList[arrList.length]  = "개새끼";
	arrList[arrList.length]  = "개세이";
	arrList[arrList.length]  = "개쉐이";
	arrList[arrList.length]  = "개자식";
	arrList[arrList.length]  = "개자지";
	arrList[arrList.length]  = "개지랄";
	arrList[arrList.length]  = "그룹섹스";
	arrList[arrList.length]  = "까자";
	arrList[arrList.length]  = "꼬추";
	arrList[arrList.length]  = "노브라";
	arrList[arrList.length]  = "니미";
	arrList[arrList.length]  = "니미랄";
	arrList[arrList.length]  = "니미럴";
	arrList[arrList.length]  = "니애미";
	arrList[arrList.length]  = "니에미";
	arrList[arrList.length]  = "등신";
	arrList[arrList.length]  = "딸딸이";
	arrList[arrList.length]  = "또라이";
	arrList[arrList.length]  = "레즈비언";
	arrList[arrList.length]  = "멜섹";
	arrList[arrList.length]  = "몰카";
	arrList[arrList.length]  = "문섹";
	arrList[arrList.length]  = "미친넘";
	arrList[arrList.length]  = "미친년";
	arrList[arrList.length]  = "미친놈";
	arrList[arrList.length]  = "미친뇬";
	arrList[arrList.length]  = "번색";
	arrList[arrList.length]  = "번색";
	arrList[arrList.length]  = "번섹";
	arrList[arrList.length]  = "번섹";
	arrList[arrList.length]  = "번쌕";
	arrList[arrList.length]  = "병신";
	arrList[arrList.length]  = "보지";
	arrList[arrList.length]  = "본디지";
	arrList[arrList.length]  = "부랄";
	arrList[arrList.length]  = "부부교환";
	arrList[arrList.length]  = "불알";
	arrList[arrList.length]  = "빙신";
	arrList[arrList.length]  = "빠구리";
	arrList[arrList.length]  = "빠굴";
	arrList[arrList.length]  = "빠꾸리";
	arrList[arrList.length]  = "빡우리";
	arrList[arrList.length]  = "빡울";
	arrList[arrList.length]  = "뽀르노";
	arrList[arrList.length]  = "새꺄";
	arrList[arrList.length]  = "새끈";
	arrList[arrList.length]  = "새끈남";
	arrList[arrList.length]  = "새끈녀";
	arrList[arrList.length]  = "새끼";
	arrList[arrList.length]  = "색남";
	arrList[arrList.length]  = "색녀";
	arrList[arrList.length]  = "색녀";
	arrList[arrList.length]  = "색스";
	arrList[arrList.length]  = "색폰";
	arrList[arrList.length]  = "성인만화";
	arrList[arrList.length]  = "성인물";
	arrList[arrList.length]  = "성인소설";
	arrList[arrList.length]  = "성인엽기";
	arrList[arrList.length]  = "성인영화";
	arrList[arrList.length]  = "성인용";
	arrList[arrList.length]  = "성인용품";
	arrList[arrList.length]  = "성인잡지";
	arrList[arrList.length]  = "세꺄";
	arrList[arrList.length]  = "섹";
	arrList[arrList.length]  = "섹  스";
	arrList[arrList.length]  = "섹 스";
	arrList[arrList.length]  = "섹녀";
	arrList[arrList.length]  = "섹스";
	arrList[arrList.length]  = "섹스";
	arrList[arrList.length]  = "쉬팔";
	arrList[arrList.length]  = "쉬펄";
	arrList[arrList.length]  = "스발";
	arrList[arrList.length]  = "스와핑";
	arrList[arrList.length]  = "스와핑";
	arrList[arrList.length]  = "시발";
	arrList[arrList.length]  = "시벌";
	arrList[arrList.length]  = "시파";
	arrList[arrList.length]  = "시펄";
	arrList[arrList.length]  = "십팔금";
	arrList[arrList.length]  = "쌍넘";
	arrList[arrList.length]  = "쌍년";
	arrList[arrList.length]  = "쌍놈";
	arrList[arrList.length]  = "쌔";
	arrList[arrList.length]  = "쌔깐";
	arrList[arrList.length]  = "쌔끈";
	arrList[arrList.length]  = "쌕쓰";
	arrList[arrList.length]  = "쌕폰";
	arrList[arrList.length]  = "썅";
	arrList[arrList.length]  = "썅넘";
	arrList[arrList.length]  = "썅년";
	arrList[arrList.length]  = "썅놈";
	arrList[arrList.length]  = "썅놈";
	arrList[arrList.length]  = "쒸팔";
	arrList[arrList.length]  = "쒸펄";
	arrList[arrList.length]  = "쓰바";
	arrList[arrList.length]  = "씌팍";
	arrList[arrList.length]  = "씨바";
	arrList[arrList.length]  = "씨발";
	arrList[arrList.length]  = "씨발";
	arrList[arrList.length]  = "씨발";
	arrList[arrList.length]  = "씨발넘";
	arrList[arrList.length]  = "씨발년";
	arrList[arrList.length]  = "씨발놈";
	arrList[arrList.length]  = "씨발뇬";
	arrList[arrList.length]  = "씨방";
	arrList[arrList.length]  = "씨방새";
	arrList[arrList.length]  = "씨버럴";
	arrList[arrList.length]  = "씨벌";
	arrList[arrList.length]  = "씨보랄";
	arrList[arrList.length]  = "씨보럴";
	arrList[arrList.length]  = "씨부랄";
	arrList[arrList.length]  = "씨부럴";
	arrList[arrList.length]  = "씨부리";
	arrList[arrList.length]  = "씨불";
	arrList[arrList.length]  = "씨불";
	arrList[arrList.length]  = "씨브랄";
	arrList[arrList.length]  = "씨파";
	arrList[arrList.length]  = "씨파";
	arrList[arrList.length]  = "씨팍";
	arrList[arrList.length]  = "씨팔";
	arrList[arrList.length]  = "씨팔";
	arrList[arrList.length]  = "씨펄";
	arrList[arrList.length]  = "씹";
	arrList[arrList.length]  = "씹물";
	arrList[arrList.length]  = "씹보지";
	arrList[arrList.length]  = "씹새";
	arrList[arrList.length]  = "씹새끼";
	arrList[arrList.length]  = "씹색";
	arrList[arrList.length]  = "씹세";
	arrList[arrList.length]  = "씹세이";
	arrList[arrList.length]  = "씹쉐";
	arrList[arrList.length]  = "씹쉐이";
	arrList[arrList.length]  = "씹쌔";
	arrList[arrList.length]  = "씹쌔기";
	arrList[arrList.length]  = "씹자지";
	arrList[arrList.length]  = "씹창";
	arrList[arrList.length]  = "씹탱";
	arrList[arrList.length]  = "씹탱구리";
	arrList[arrList.length]  = "씹팔";
	arrList[arrList.length]  = "씹펄";
	arrList[arrList.length]  = "씹할";
	arrList[arrList.length]  = "야동";
	arrList[arrList.length]  = "야동";
	arrList[arrList.length]  = "야사";
	arrList[arrList.length]  = "야사";
	arrList[arrList.length]  = "야설";
	arrList[arrList.length]  = "야설";
	arrList[arrList.length]  = "야캠";
	arrList[arrList.length]  = "야캠";
	arrList[arrList.length]  = "야한";
	arrList[arrList.length]  = "야한동영상";
	arrList[arrList.length]  = "야한사이트";
	arrList[arrList.length]  = "야한사진";
	arrList[arrList.length]  = "에로";
	arrList[arrList.length]  = "에로";
	arrList[arrList.length]  = "오랄";
	arrList[arrList.length]  = "와레즈";
	arrList[arrList.length]  = "와레즈";
	arrList[arrList.length]  = "원조";
	arrList[arrList.length]  = "원조교재";
	arrList[arrList.length]  = "원조교제";
	arrList[arrList.length]  = "원조교제";
	arrList[arrList.length]  = "음란";
	arrList[arrList.length]  = "자위";
	arrList[arrList.length]  = "자지";
	arrList[arrList.length]  = "자지";
	arrList[arrList.length]  = "정품게임";
	arrList[arrList.length]  = "젖꼭지";
	arrList[arrList.length]  = "젖탱";
	arrList[arrList.length]  = "젖탱";
	arrList[arrList.length]  = "젖탱이";
	arrList[arrList.length]  = "젼나";
	arrList[arrList.length]  = "조까";
	arrList[arrList.length]  = "조까";
	arrList[arrList.length]  = "졸라";
	arrList[arrList.length]  = "좃";
	arrList[arrList.length]  = "좃나";
	arrList[arrList.length]  = "좃나게";
	arrList[arrList.length]  = "좆";
	arrList[arrList.length]  = "좆같";
	arrList[arrList.length]  = "좆꼴리";
	arrList[arrList.length]  = "좆나";
	arrList[arrList.length]  = "좆나게";
	arrList[arrList.length]  = "좆빠";
	arrList[arrList.length]  = "좇";
	arrList[arrList.length]  = "좇";
	arrList[arrList.length]  = "좇같";
	arrList[arrList.length]  = "좇꼴려";
	arrList[arrList.length]  = "좇꼴리";
	arrList[arrList.length]  = "좇빠";
	arrList[arrList.length]  = "지랄";
	arrList[arrList.length]  = "지랄";
	arrList[arrList.length]  = "지미랄";
	arrList[arrList.length]  = "체모";
	arrList[arrList.length]  = "캠색";
	arrList[arrList.length]  = "캠섹";
	arrList[arrList.length]  = "캠섹";
	arrList[arrList.length]  = "컴색";
	arrList[arrList.length]  = "컴색";
	arrList[arrList.length]  = "컴섹";
	arrList[arrList.length]  = "컴섹";
	arrList[arrList.length]  = "팬티";
	arrList[arrList.length]  = "페니스";
	arrList[arrList.length]  = "페니스";
	arrList[arrList.length]  = "페티쉬";
	arrList[arrList.length]  = "포르노";
	arrList[arrList.length]  = "포르노";
	arrList[arrList.length]  = "폰색";
	arrList[arrList.length]  = "폰색";
	arrList[arrList.length]  = "폰섹";
	arrList[arrList.length]  = "폰섹";
	arrList[arrList.length]  = "폰섹";
	arrList[arrList.length]  = "폰쌕";
	arrList[arrList.length]  = "헨타이";
	arrList[arrList.length]  = "호로새끼";
	arrList[arrList.length]  = "호빠";
	arrList[arrList.length]  = "호스테스바";
	arrList[arrList.length]  = "호스트바";
	arrList[arrList.length]  = "화상색";
	arrList[arrList.length]  = "화상섹";
	
	arrList[arrList.length]  = "자살";
	arrList[arrList.length]  = "음독";
	arrList[arrList.length]  = "청산가리";
	arrList[arrList.length]  = "청산가루";

	for(i=0; i < arrList.length; i++){
		var badWord = arrList[i];
      
      while(true){
		if(msg.indexOf(badWord) != -1){
			alert("부적절한 단어가 포함되어 있습니다. [ " + badWord  + " ]" );
			msg = msg.replace(badWord, "");
			return false;
			
		}
		else{
			break;
		}
	  }
	}

	return true;
}
function ajaxPromise(url,jsonData,methodType) {
	return new Promise((resolve, reject) => {
		const token = $("meta[name='_csrf']").attr("content");
		const header = $("meta[name='_csrf_header']").attr("content");

		$.ajax({
			type: methodType,
			url: url,
			contentType: 'application/json',
			data: JSON.stringify(jsonData),
			dataType: "json",
			beforeSend: function (xhr) {
				xhr.setRequestHeader(header, token);
			},
			success: function (data) {
				if (data.newToken != undefined){
					if (data.newToken != '') {
						$("meta[name='_csrf']").attr("content", data.newToken);
						if ($("input[name='_csrf']").val() != undefined) {
							$("input[name='_csrf']").val(data.newToken);
						}
					}
				} else {
					alert(data.resultCode);
				}
				resolve(data);
			},
			error: function (xhr) {
				reject(xhr);
			}
		});
	});
}

function resultProcess(e) {
	loading.end();
	"OK" == getResultMsg(e.resultCode) ? location.reload() : alert(getResultMsg(e.resultCode))
}

function fn_headerKeywordSearch(keyword) {
	$("#header_searchKeyword").val(keyword);
	fn_headerSearch();
}

function fn_headerSearch() {
	loading.start();
	var text = $("#header_searchKeyword").val().trim();

	$("#header_searchKeyword").val(text);

	$("#headerSearchBox").attr("action", "/main/publicData/action/publicDataList").submit();
	/*if(wordchk1(text) && wordchk2(text) && text != "" ){


		headerKeywordList.unshift(escape(text));
		setCookieArray("headerKeywordList",headerKeywordList,1);


		const serializedValues = $("#headerSearchBox").serializeObject();
		fnMcenAjaxJson("POST", "/main/publicData/popularityInsertProcess", JSON.stringify(serializedValues), function () {
			$("#headerSearchBox").attr("action", "/main/publicData/action/publicDataList").submit();
		}, "");
	} else {
		loading.end();
	}*/




}

function fn_headerInput() {
	if (event.keyCode === 13) {
		fn_headerSearch();
		event.preventDefault();
	};
}

function fn_mobileHeaderInput() {
	if (event.keyCode === 13) {
		$("#header_searchKeyword").val($("#m_header_searchKeyword").val().trim());
		fn_headerSearch();
		event.preventDefault();
	};
}

function fn_mobileHeaderSearch() {
	$("#header_searchKeyword").val($("#m_header_searchKeyword").val().trim());
	fn_headerSearch();
}

var setCookie = function(name, value, exp) {
	var date = new Date();
	date.setTime(date.getTime() + exp*24*60*60*1000);
	document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
};

var getCookie = function(name) {
	var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
	return value? value[2] : null;
};

var setCookieArray=  function( cname, carray, exdays ) {
	var set = new Set(carray);
	carray = [...set];
	var str = "";
	for(var i = 0; i < carray.length; i++) {
		if (i < 9){
			if(str != "" ) str += ",";
			str += carray[i];
		}
	}
	setCookie( cname, str, exdays );
}

var getCookieArray= function( cname ) {
	var str = getCookie(cname);
	return str == null ? [] : str.split(",");
}

var deleteCookie = function(name) {
	document.cookie = name + '=; expires=Thu, 01 Jan 1999 00:00:10 GMT;path=/;';
}

var headerKeywordList = [];


function headerRecentKeywordGet(){
	headerKeywordList =  getCookieArray("headerKeywordList");
	var keywordTag;
	headerKeywordList.map(function (item,idx) {
		keywordTag = $(`<li class="main-search-li"><a href="javascript:;" onclick="fn_headerKeywordSearch('${unescape(item)}');"> <span class="num">${idx+1}</span> 
							<span class="keyword">${unescape(item)}</span>
						</a></li>`);
		$("#headerRecentKeyword").append(keywordTag);
	});
}


function popularityGet() {
	fnMcenAjaxJson("POST", "/main/publicData/popularitySelectProcess", "{}", function (data) {
		var keywordTag;
		data.resultMapList.map(function (item,idx) {
			keywordTag = $(`<li class="main-search-li"><a href="javascript:;" onclick="fn_headerKeywordSearch('${unescape(item.keywordName)}');"> <span class="num">${idx+1}</span> 
							<span class="keyword">${unescape(item.keywordName)}</span>
						</a></li>`);
			$("#popularityKeyword").append(keywordTag);
		});
	}, "");
}


function headerRecentKeywordAllDelete(){
	deleteCookie("headerKeywordList");
	$("#headerRecentKeyword").empty();
	headerKeywordList = [];
}

// $(function () {
// 	headerRecentKeywordGet();
// 	popularityGet();
// })


// jsp 호출
function fnMcenAjaxJsp(methodType, url, jsonData, callBackFunction, callBackErrorFunction) {

	const token = $("meta[name='_csrf']").attr("content");
	const header = $("meta[name='_csrf_header']").attr("content");
	const grecc = $("meta[name='_grecc']").attr("content");
	let action;
	if (url.lastIndexOf('/') == -1) {
		action = url;
	} else {
		action = url.substr(url.lastIndexOf('/') + 1);
	}
	$.ajax({
		type: methodType,
		url: url,
		contentType: 'application/json',
		data: jsonData,
		dataType: "html",
		beforeSend: function(xhr) {
			xhr.setRequestHeader(header, token);
		},
		success: function(data, status, xhr) {
			//console.log(data)
			if ( data.newToken != undefined){
				if (data.newToken != '') {
					$("meta[name='_csrf']").attr("content", data.newToken);
					if ($("input[name='_csrf']").val() != undefined) {
						$("input[name='_csrf']").val(data.newToken);
					}
				}
			} else {
				//alert(data.resultCode);
			}
			callBackFunction(data);
		},
		error: function(xhr, textStatus, errorThrown) {
			if (callBackErrorFunction != "") {
				callBackErrorFunction(xhr.responseText);
			} else {
				try {
					const data = JSON.parse(xhr.responseText);
					if (data.resultCode == 0 || data.resultCode == -8010) {
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						alert(getResultMsg(data.resultCode));
					} else if (data.resultCode == -2002) {
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						callBackFunction(data);
					} else {
						if (data.newToken != '') {
							$("meta[name='_csrf']").attr("content", data.newToken);
							if ($("input[name='_csrf']").val() != undefined) {
								$("input[name='_csrf']").val(data.newToken);
							}
						}
						callBackFunction(data);
					}
				} catch (e) {
					alert('잘못된 호출입니다!');
					if (e instanceof SyntaxError) {
						printError(e, true);
					} else {
						printError(e, false);
					}
					location.reload();
				}

			}
		}
	});
}
