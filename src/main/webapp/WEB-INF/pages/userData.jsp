<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Upload Multi File</title>
<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" href="css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
<script src="js/bootstrap.min.js"></script>

<!-- <script src="js/myjs.js"></script> -->
</head>
<style type="text/css">
div.scroll {
    background-color: #00FFFF;
    max-height: 150px;
    overflow: auto;
}
</style>
<body>

	<!-- MyUploadForm -->
	<div class="panel panel-primary col-lg-8 col-lg-offset-2">
		<form:form modelAttribute="myUploadForm" method="POST" action=""
			enctype="multipart/form-data" cssClass="form-horizontal" role="form">
			<br />
			<script type="text/javascript">
				$(document).ready(function()
				        {
				            // addClass
				            $('#addBtn').click(function(){
				                // Thêm class active vào thẻ h1
				                var text1='<div><div class="col-md-8">'
					                +'<form:input path="fileDatas" type="file" cssClass="form-control" />'
								    +'</div><div class="col-md-4">'
									+'<button type="button" id="remove" class=" btn btn-link form-control">Remove</button>'
									+'</div></div>';
							
				                $('.row').append(text1);
				            });
				             
				            // removeClass
				            $('#remove').click(function(){
				                $(this).parent().parent().remove();
				            });
				        });
				</script>
				
			<div class="row scroll" >
			<div>
				<div class="col-md-8">
					<form:input path="fileDatas" type="file" cssClass="form-control" />
				</div>
				<!-- <div class="col-md-4">
					<button type="button" id="remove" class=" btn btn-link form-control">Remove</button>
				</div> -->
				</div>
			</div>
			<br>
			<div style="background-color: pink;">
			<form:button type="button" class="btn btn-link" id="addBtn">Add</form:button>
			<form:button type="submit" class="btn btn-link">Import</form:button>
			</div>

		</form:form>
		<table border="1">
			<tr>
				<th>Username</th>
				<th>Group</th>
				<th>First name</th>
				<th>Last name</th>
			</tr>
			<c:forEach var="user" items="${list}">
				<tr>
					<td>${user.id}</td>
					<td>${user.groupId}</td>
					<td>${user.firstName}</td>
					<td>${user.lastName}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

</body>
</html>