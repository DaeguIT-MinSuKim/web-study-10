<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원 정보</title>
<style type="text/css">
	ul {
	    list-style: none;
	}
	li {
	    margin: 20px;
	}
	
	li label {
	    width: 120px;
	    float: left;
	}
	
	li select, input {
		width: 150px;
		height: 21px;
		box-sizing:border-box;
	}
	
	fieldset {
	    margin: 15px;
	}
</style>
<script type="text/javascript" 
src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script>

$(function(){
	$.post("TitleListHandler", function(json){
	    var titleSelected = ${emp.title.titleNo};
	     var dataLength = json.length;
	     if ( dataLength >=1 ){
	         var sCont = "";
	         sCont += "<option value='' disabled selected hidden>직책을 선택하세요...</option>";
	         for ( i=0 ; i < dataLength ; i++){
	             sCont += "<option value=" + json[i].titleNo;
	             if (titleSelected == json[i].titleNo){
	                 sCont += " selected ";
	             }
	             sCont += " >" + json[i].titleName + "</>";
	         }
	         $("#title").append(sCont);   
	     }
	});
	 
	$.post("DeptListHandler", function(json){
	    var deptSelected = ${emp.dept.deptNo};
	    
	  	var dataLength = json.length;
		if (dataLength >= 1) {
		    var sCont = "<option value='' disabled selected hidden>부서를 선택하세요...</option>";
		    for (i = 0; i < dataLength; i++) {
		        sCont += "<option value=" + json[i].deptNo ; 
		        if (deptSelected == json[i].deptNo ){
		            sCont += " selected ";
		        }
		        sCont += " >" + json[i].deptName + "</>";
		    }
		    $("#dept").append(sCont);
		}
	});

	var isEmpNoCheck = false;
	$('#empNoDupCheck').on("click", function(){
	    alert($('#empNo').val());
	    var emp = {empNo : $('#empNo').val()};
	    $.post("DuplicateEmpNoCheckHandler", emp, function(data){
	        if (data == 1){
	            alert("사용가능한 사원번호");
	            isEmpNoCheck = true;
	        }else{
	            alert("사원번호 중복");
	            $('#empNo').val('');
	            $('#empNo').focus();
	        }
	    });
	});
	
	$('#dept').on("change", function(){
		/* alert($('#dept').val()); */ 
		var selectedManagerd = ${emp.manager.empNo};
		
		$("#manager").empty();
		var dept = {deptNo : $('#dept').val()};
		$.get("EmpManagerListHandler", dept, function(json){
		    var dataLength = json.length;
	        if ( dataLength >=1 ){
	            var sCont = "";
	            for ( i=0 ; i < dataLength ; i++){
	                sCont += "<option value=" + json[i].empNo;
	                if (selectedManagerd == json[i].empNo ){
			            sCont += " selected ";
			        }
	                sCont += ">" + json[i].empName + "</>";
	            }
	            $("#manager").append(sCont);   
	        }
		});
	});
	
	$('#cancel').on("click", function(){
	    window.location.href = "EmpListHandler";
	});
	
    $('#add').on("click", function() {
        if ($('#passwd').val() != $('#repasswd').val()){
            alert("비밀번호가 틀립니다.");
            $('#passwd').val('');
            $('#repasswd').val('');
            $('#passwd').focus();
            return;
        }
        if (!isEmpNoCheck){
            alert("중복체크 하세요");
            return;
        }
        
        //validCheck() 추가하기
        
        var newEmp = {
            empNo : $('#empNo').val(),
            empName : $('#empName').val(),
            title:{titleNo:$('#title').val()},
            manager:{empNo:$('#manager').val()},
            salary:$('#salary').val(),
            dept:{deptNo:$('#dept').val()},
            regDate:$('#regDate').val(),
            email:$('#email').val(),
            tel:$('#tel').val(),
            passwd:$('#passwd').val()
        };

        $.ajax({
            type : "post",
            url : "EmpAddHandler",
            cache : false,
            data : JSON.stringify(newEmp),
            complete : function(data) {
                alert("추가되었습니다." + data);
                window.location.href = "EmpListHandler";
            }
        });
    });

});
</script>
</head>
<body>
	<fieldset>
		<legend>사원 추가</legend>
		<ul>
			<li>
				<label for="empNo">사원 번호</label>
				<input id="empNo" type="number" name="empNo" value="${emp.empNo}">
				<button id="empNoDupCheck" >중복체크</button>
			</li>
			<li>
				<label for="empName">사원명</label>
				<input id="empName" type="text" name="empName" value="${emp.empName}">
			</li>
			<li>
				<label for="dept">부서</label>
				<select id="dept"></select>
			</li>
			<li>
				<label for="manager">직속상사</label>
				<select id="manager"></select>
			</li>
			<li>
				<label for="title">직책</label>
				<select id="title"></select>
			</li>
			<li>
				<label for="salary">급여</label>
				<input id="salary" type="number" name="salary" value="${emp.salary}">
			</li>
			<li>
				<label for="regDate">입사일</label>
				<input id="regDate" type="date" name="regDate" value="<fmt:formatDate value='${emp.regDate}' pattern='yyyy-MM-dd' />">
			</li>
			<li>
				<label for="email">이메일</label>
				<input id="email" type="email" name="email" value="${emp.email}">
			</li>
			<li>
				<label for="tel">연락처</label>
				<input id="tel" type="tel" name="tel" value="${emp.tel}">
			</li>
			<li>
				<label for="passwd">비밀번호</label>
				<input id="passwd" type="password" name="passwd" value="${emp.passwd}">
			</li>
			<li>
				<label for="repasswd">비밀번호 확인</label>
				<input id="repasswd" type="password" name="repasswd" >
			</li>
			<li>
				<button id="modify">수정</button>
				<button id="delete">삭제</button>
				<button id="list">목록</button>
			</li>
		</ul>
	</fieldset>
</body>
</html>