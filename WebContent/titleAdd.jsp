<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>직책 추가</title>
<script type="text/javascript" 
src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script>
$(function(){
    $('#add').on("click", function(){
        var newTitle ={
                titleNo:$('#titleNo').val(),
                titleName:$('#titleName').val()
                };
       $.ajax({
           type:"post",
           url:"TitleAddHandler",
           cache: false,
           data: JSON.stringify(newTitle),
           complete:function(data){
               alert("추가되었습니다." + data);
               window.location.href="TitleListHandler";
           }
       }); 
    });
});
</script>
</head>
<body>
	<fieldset>
		<legend>직책 추가</legend>
		<ul>
			<li>
				<label for="titleNo">직책 번호</label>
				<input id="titleNo" type="number" name="titleNo" value="${param.nextNo }" readonly>
			</li>
			<li>
				<label for="titleName">직책</label>
				<input id="titleName" type="text" name="titleName" >
			</li>
			<li>
				<button id="add">추가</button>
				<button id="cancel">취소</button>
			</li>
		</ul>
	</fieldset>
</body>
</html>