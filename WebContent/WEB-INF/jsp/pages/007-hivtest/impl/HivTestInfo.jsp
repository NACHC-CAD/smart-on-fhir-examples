<%@ include file="/WEB-INF/jsp/header/ComponentHeader.jsp"%>

<html>

	<h2>Results of check for HIV test</h2>

	<h3>Has HIV Test:</h3>
	<table border="1">
		<tr>
			<td>Has HIV Test:</td>
			<td>${hivTestCheckResults.hasTest}</td>
		</tr>
	</table>

	<h3>Got ${diagnosticReportList.size()} DiagnosticReport Resources</h3>
	<table border="1">
		<th>System</th>
		<th>Code</th>
		<th>Display</th>
		<c:forEach items="${diagnosticReportList}" var="report">
			<tr>
				<td>${report.firstCodingSystem}</td>
				<td>${report.firstCodingCode}</td>
				<td>${report.firstCodingDisplay}</td>
			</tr>		
		</c:forEach>
	</table>

	<h3>Raw Data for Patient Resource</h3>
	<p>
		<textarea class="scrollable" id="patientJson">${patientJson}</textarea>
	</p>
	<h3>Patient Summary</h3>
	<table border="2">
		<tr>
			<td>Patient&nbsp;ID:&nbsp;</td>
			<td id="patientId">${patientId}</td>
		</tr>
		<tr>
			<td>First&nbsp;Name:&nbsp;</td>
			<td id="fname">${fname}</td>
		</tr>
		<tr>
			<td>Last&nbsp;Name:&nbsp;</td>
			<td id="lname">${lname}</td>
		</tr>
		<tr>
			<td>Gender:&nbsp;</td>
			<td id="gender">${gender}</td>
		</tr>
		<tr>
			<td>Date&nbsp;Of&nbsp;Birth:&nbsp;</td>
			<td id="dob">${dob}</td>
		</tr>
	</table>
	

</html>