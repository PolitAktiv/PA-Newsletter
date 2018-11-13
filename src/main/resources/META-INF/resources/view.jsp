<%@ include file="/init.jsp" %>

<% 
PermissionChecker permCheck = themeDisplay.getPermissionChecker();
if(permCheck.isOmniadmin()) {
%>
<%@ include file="/portlet/adminBar.jsp" %>
<%}%>

<%@ include file="/portlet/signup.jsp" %>

<% 
if(permCheck.isOmniadmin()) {
%>
<%@ include file="/portlet/newsletterCreation.jsp" %>
<script>
	//functions to switch between views
	function showSignup() {
		$("#newsletter-signup").show();
		$("#newsletter-creation").hide();
	}
	function showNewsletterCreation() {
		$("#newsletter-signup").hide();
		$("#newsletter-creation").show();
	}
</script>

<%}%>


