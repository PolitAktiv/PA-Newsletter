<%@ include file="/init.jsp" %>

<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>


<portlet:actionURL name="newsletterSignup" var="newsletterSignup" />

<portlet:renderURL var="verifyURL">
    <portlet:param name="mvcPath" value="/portlet/verification/verification.jsp" />
</portlet:renderURL>


<%-- ---------------------------------------------- --%>

<style>
.termscond-popup {
	display: none;
	background: white;
	border-radius: 7px;
	bottom: 0;
	left: 0;
	margin: auto;
	overflow: scroll;
	position: fixed;
	right: 0;
	top: 0;
	max-width: 75%;
	max-height: 80%;
	z-index: 11;
	padding: 0 14px;
}

.overlay1 {
	width: 100%;
	background: #000;
	opacity: 0.4;
	height: 100%;
	position: fixed;
	top: 0;
	left: 0;
	display: none;
	z-index: 10;
}

div.formContainer {
	overflow: hidden;
}

.aui .control-group.error .checkbox {
	color: #b50303;
}
</style>

<%-- ---------------------------------------------- --%>

<div id="newsletter-signup">
	<form action="<%=newsletterSignup%>" method="post" id="newsletterSignup"
		name="newsletterSignup" enctype="multipart/form-data">

		<div class="formContainer">

			<label for="newsletterFirstname">Vorname</label> 
			<aui:input id="newsletterFirstname" name="newsletterFirstname"
				placeholder="Vorname" style="max-width: 100%;" type="text" />

			<label for="newsletterLastname">Nachname</label> 
			<aui:input id="newsletterLastname" name="newsletterLastname"
				placeholder="Nachname" style="max-width: 100%;" type="text" />

			<label for="newsletterEmail">E-Mail</label> 
			<aui:input id="newsletterEmail" name="newsletterEmail" placeholder="E-Mail"
				style="max-width: 100%;" type="email" />
			

			<div>
				<aui:input name="acceptTermsChkbox" class="acceptTerms"
					id="acceptTermsChkbox" type="checkbox" /> Ich habe
				die <a onclick='showPopup();' href='javascript:void(0)'>Nutzungsbedingungen</a>
				gelesen und bin damit einverstanden.

			</div>
			
			<aui:input name="verifyURL" type="hidden" value="<%=verifyURL.toString()%>" id="verifyURL" />
			<div>
				<button class="btn btn-primary acceptTermsButton" id="buttonSubmit"
					name="buttonSubmit" type="submit"
					value="Für den Newsletter Registrieren">Für den Newsletter Registrieren</button>
			</div>
		</div>
	</form>
</div>

<script>
	//functions to show or hide the terms and conditions
	function showPopup() {
		$(".termscond-popup").show();
		$(".overlay1").show();
	}
	function closePopup() {
		$(".termscond-popup").hide();
		$(".overlay1").hide();
	}
</script>

<%
	long groupID = themeDisplay.getScopeGroupId();
%>

<div class="termscond-popup">
<%-- 
	<liferay-ui:journal-article showTitle="true" groupId="<%=groupID%>"
		articleId="123456789" />
	<div align="center">
		<button class="btn btn-primary" onClick="closePopup();">Schließen</button>
	</div>
--%>
	Hallo
	<div align="center">
		<button class="btn btn-primary" onClick="closePopup();">Schließen</button>
	</div>
</div>
<div class="overlay1"></div>

<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/additional-methods.min.js"></script>
<%-- 
<script>
	var form = $("#newsletterForm");
	jQuery.validator.setDefaults({
		debug : true,
		success : "valid"
	});
	form
			.validate({
				rules : {
					newsletterFirstname : {
						required : true
					},
					newsletterLastname : {
						required : true
					},
					newsletterEmail : {
						required : true,
						email : true
					},
					acceptTermsChkbox : {
						required : true
					}
				},
				messages : {
					newsletterFirstname : {
						required : "<div style='color: #b50303;'>Dieses Feld ist erforderlich</div>"
					},
					newsletterLastname : {
						required : "<div style='color: #b50303;'>Dieses Feld ist erforderlich</div>"
					},
					newsletterEmail : {
						required : "<div style='color: #b50303;'>Dieses Feld ist erforderlich</div>",
						email : "<div style='color: #b50303;'>Bitte geben Sie eine gültige E-Mail Adresse an</div>"
					},
					acceptTermsChkbox : {
						required : "<div style='color: #b50303;'>Bitte stimmen Sie den Nutzungsbedingungen zu</div>"
					}
				}
			});
</script>
--%>