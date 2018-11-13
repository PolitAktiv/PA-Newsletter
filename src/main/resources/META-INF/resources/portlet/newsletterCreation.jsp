<style>
#newsletter-creation {
	
}
</style>

<%@page import="java.util.List"%>

<portlet:actionURL name="newsletterSend" var="newsletterSend" />

<div id="newsletter-creation">
	<form action="<%=newsletterSend%>" method="post" id="newsletterCreationForm"
		name="newsletterCreationForm" enctype="multipart/form-data">

		<div class="formContainer">
		
			<%-- <liferay-ui:input-editor name="newsletterHTMLContent"></liferay-ui:input-editor>
			--%>
			<select>
  				<option value="Gruppe1">Gruppe 1</option>
  				<option value="Gruppe2">Gruppe 2</option>
  			</select>
  			
  			<fieldset> 
  				<input type="radio" id="mc" name="Zahlmethode" value="Mastercard"> 
  				<label for="mc"> Mastercard</label> 
  				<input type="radio" id="vi" name="Zahlmethode" value="Visa"> 
  				<label for="vi"> Visa</label> 
  				<input type="radio" id="ae" name="Zahlmethode" value="AmericanExpress"> 
  				<label for="ae"> American Express</label> 
  			</fieldset>

			<div>
				<button class="btn btn-primary acceptTermsButton" id="buttonSubmit"
					name="buttonSubmit" type="submit"
					value="Newsletter versenden">Newsletter versenden</button>
			</div>
		</div>
	</form>
</div>

<%-- TODO: select webcontent here --%>

