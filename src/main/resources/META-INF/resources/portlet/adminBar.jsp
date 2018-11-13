<style>
.newsletterAdminBar {
  overflow:hidden;
  transition:transform 0.3s ease-out; 
  height:auto;
  transform:scaleY(0); 
  transform-origin:top; 
}
.newsletterAdminBar.expanded {
  transform:scaleY(1); 
}
</style>

<script>
document.querySelector('#newsletter-config-button').addEventListener('click', function() {
	  document.querySelector('#adminBarBottomPart').classList.toggle('expanded');
	});
</script>

<div id="adminBarTopPart">
<button id="newsletter-config-button">Ich öffne die Leiste</button>
</div>
<div id="adminBarBottomPart" class="newsletterAdminBar expanded">
<button id="newsletter-new-button" onclick="showNewsletterCreation()">Neuen Newsletter erstellen</button>
<button id="newsletter-signup-button" onclick="showSignup()">Zum Formular</button>
</div>