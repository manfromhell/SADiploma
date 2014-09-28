<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

<form:form commandName="inputData" action="setData" method="POST">
<form:label path="parentCode">Parent code</form:label>
<form:input path="parentCode"/>
<br><br>
<form:label path="numberOfMarks">Number Of Marks</form:label>
<form:input path="numberOfMarks"/>
<br><br>
<form:label path="marks">Marks</form:label>
<form:input path="marks"/>
<br><br>
<form:label path="initTemp">Initial temperature</form:label>
<form:input path="initTemp"/>
<br><br>
<form:label path="finalTemp">Final temperature</form:label>
<form:input path="finalTemp"/>
<br><br>
<form:label path="alpha">Alpha</form:label>
<form:input path="alpha"/>
<br><br>
<form:label path="iterationsPerTemperature">Iterations Per Temperature</form:label>
<form:input path="iterationsPerTemperature"/>
<br><br>
<form:label path="gapsCoef">Gaps Coefficient</form:label>
<form:input path="gapsCoef"/>
<br><br>
<form:label path="repCoef">Repeat Coefficient</form:label>
<form:input path="repCoef"/>
<br><br>
<form:button type="submit">OK</form:button>
</form:form>

</body>
</html>
