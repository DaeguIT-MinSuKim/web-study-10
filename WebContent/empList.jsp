<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" 
src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/locale/ko.js"></script>

<script>
$(function(){
    var delay = 5000;
    $.post("TitleListHandler", function(json){
        setTimeout(function() {
	        var dataLength = json.length;
	        if ( dataLength >=1 ){
	            var sCont = "";
	            for ( i=0 ; i < dataLength ; i++){
	                sCont += "<option value=" + json[i].titleNo + ">" + json[i].titleName + "</>";
	            }
	            $("#title").append(sCont);   
	        }
        }, delay);
    });
    
    $.post("DeptListHandler", function(json){
        setTimeout(function() {
	        var dataLength = json.length;
	        if ( dataLength >=1 ){
	            var sCont = "";
	            for ( i=0 ; i < dataLength ; i++){
	                sCont += "<option value=" + json[i].deptNo + ">" + json[i].deptName + "</>";
	            }
	            $("#department").append(sCont);   
	        }
        }, delay);
    });
    
    $('#addEmp').on("click", function(){
        self.location = "empAdd.jsp";
    });
     
     $.post("EmpListHandler", function(json){
         var dataLength = json.length;
         if ( dataLength >=1 ){
             var sCont = "";
             for ( i=0 ; i < dataLength ; i++){
                sCont += "<tr>";
                sCont += "<td>" + json[i].empNo + "</td>";
                sCont += "<td><a href='EmpGetHandler?empNo="+json[i].empNo+"'>" + json[i].empName + "</a></td>";
                sCont += "<td>" + json[i].title.titleName + "("+ json[i].title.titleNo + ")</td>";
                if (json[i].manager.empNo != 0){
                    sCont += "<td>" + json[i].manager.empName + "("+ json[i].manager.empNo + ")</td>";
                }else{
                    sCont += "<td></td>"; 
                }
                sCont += "<td>" + json[i].salary.toLocaleString("ko") + "</td>";
                sCont += "<td>" + json[i].dept.deptName + "("+ json[i].dept.deptNo + ")</td>";
                sCont += "<td>" + moment(json[i].regDate).format('LL') + "</td>";
                sCont += "<td>" + json[i].email + "</td>";
                sCont += "<td>" + json[i].tel + "</td>";
                sCont += "</tr>";
            }
            /* $("table > tbody:last-child").append(sCont);    */
            $("#load:last-child").append(sCont);   
        } 
     });
});
</script>
<style type="text/css">
h2 {
	text-align: center;
	text-decoration: underline;
}

.split {
	height: 1em;
}

table {
	width: 95%;
	max-width: 850px;
	margin: 0 auto;
	border-collapse: collapse;
	border-top: 2px solid #ff3030;
	text-align: center;
}

table thead td {
	font-weight: bold;
	border: 1px solid #ddd;
	border-top: 0;
	background: #f3f3f3;
	padding: 15px 5px;
}

table tbody td {
	padding: 13px 5px;
	border: 1px solid #ddd;
	border-top: 0;
}

a {
	text-decoration: none;
}
</style>
</head>
<body>
	<label for="title">직책</label>
	<select id="title">
	</select>
	<label for="department">부서</label>
	<select id="department">
	</select>
	
	<h2>사원목록</h2>
    <table>
        <thead>
            <td>사원번호</td><td>사원명</td><td>직책</td><td>직속상사</td><td>급여</td>
            <td>부서</td> <td>입사일</td> <td>이메일</td><td>연락처</td>
        </thead>
        <tbody id="load">
        </tbody>
    </table>
    <hr>
    <h2>사원목록2</h2>
    <table>
        <tr>
            <td>사원번호</td><td>사원명</td><td>직책</td><td>직속상사</td><td>급여</td><td>부서</td> <td>입사일</td> <td>이메일</td><td>연락처</td>
        </tr>
        <c:forEach var="emp" items="${list}">
        	<tr>
	        	<td>${emp.empNo}</td>
	        	<td><a href="EmpGetHandler?empNo=${emp.empNo}"> ${emp.empName} </a></td>
	        	<td>${emp.title.titleName}(${emp.title.titleNo})</td>
	        	<c:if test="${emp.manager.empNo ne 0}">
	        		<td>${emp.manager.empName}(${emp.manager.empNo})</td>
	        	</c:if>
	        	<c:if test="${emp.manager.empNo eq 0}">
	        		<td></td>
	        	</c:if>
	        	<td><fmt:formatNumber value="${fn:trim(emp.salary)}"></fmt:formatNumber></td>
	        	<td>${emp.dept.deptName}(${emp.dept.deptNo})</td> 
	        	<td><fmt:formatDate value="${emp.regDate}" pattern="yyyy년 MM월 dd일"/> </td> 
	        	<td>${emp.email}</td>
	        	<td>${emp.tel}</td>
	        	
	        </tr>
        </c:forEach>
    </table>

	<button id="addEmp">사원 추가</button>
</body>
</html>