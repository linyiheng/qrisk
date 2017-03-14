<%@ page isELIgnored="false" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%--@ taglib uri="http://www.springframework.org/tags" prefix="spring" --%>
<%--@ taglib uri="http://www.springframework.org/tags/form" prefix="form" --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%--@ taglib uri="http://www.appfuse.org/tags/spring" prefix="appfuse" --%>
<%--@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" --%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="datePattern"><fmt:message key="date.format"/></c:set>
