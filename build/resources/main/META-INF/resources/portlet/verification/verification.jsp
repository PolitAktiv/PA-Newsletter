<%@include file="/init.jsp" %>

<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="java.util.List"%>
<%@page import="com.liferay.portal.kernel.util.Base64"%>
<%@page import="com.liferay.portal.kernel.oauth.Token"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@taglib uri="http://java.sun.com/portlet_2_0" prefix="portlet" %>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@taglib uri="http://liferay.com/tld/aui" prefix="aui"%>
<%@taglib uri="http://liferay.com/tld/theme" prefix="liferay-theme"%>
<%@taglib prefix="liferay-portlet" uri="http://liferay.com/tld/portlet" %>
<%@page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@page import="com.liferay.portal.kernel.util.PortalUtil" %>
<%@page import="javax.servlet.http.HttpServletRequest" %>
<%@page import="PA.Newsletter.util.NewsletterUserLocalServiceUtil" %>

Hello<br>
<%
	String emailToken = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(renderRequest)).getParameter("token");
	NewsletterUserLocalServiceUtil userUtil = new NewsletterUserLocalServiceUtil();
	System.out.println("--- Token from verification: " + emailToken + " ---");
    if (emailToken == null || emailToken.equalsIgnoreCase("null")
            || emailToken.length() == 0) {
%>
SORRY Your email is not verified.
<%
    } else {
        String decodetoken = new String(Base64.decode(emailToken),
                StringPool.UTF8);
        String email = decodetoken.substring(
                decodetoken.indexOf("email") + "email".length(),
                decodetoken.indexOf("email_"));
        System.out.println(email + "  decodetoken   " + decodetoken);
        String firstname = decodetoken.substring(
                decodetoken.indexOf("fn") + "fn".length(),
                decodetoken.indexOf("fn_"));
        String lastname = decodetoken.substring(
                decodetoken.indexOf("ln") + "ln".length(),
                decodetoken.indexOf("ln_"));
        long groupId = Long.parseLong(decodetoken.substring(
                decodetoken.indexOf("group") + "group".length(),
                decodetoken.indexOf("group_")));
        long verifyId= Long.parseLong(decodetoken.substring(decodetoken
                .lastIndexOf("_") + 1));
        System.out.println("  verifyId   " + verifyId);
        long verifyIdCorrect = themeDisplay.getCompanyId();
        
        if (userUtil.checkIfEmailIsNotInNewsletter(email)) {
        	if (userUtil.checkIfEmailIsNotInNewsletter(email) && verifyIdCorrect == verifyId) {
                System.out.println("Verified address " + email +
                		" from " + firstname + " " + lastname +
                		" in group " + groupId);
            } else {
    		%>
    			SORRY Your email is not verified.
    		<%
        	}
        } else {
        %>
			Your email is already verified.
		<%
        }
        
    }
%>