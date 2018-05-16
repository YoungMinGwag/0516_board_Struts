<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>비밀번호오류</title>
<link rel="stylesheet" href="/0516_Board_Struts/board/common/css/css.css"
	type="text/css">
	<script type="text/javascript">
		function ErrorMessage() {
			alert("비밀번호가틀립니다");
			history.back(-1);
		}
	</script>
</head>

<body>
	<script>
		ErrorMessage()
	</script>
</body>
</html>