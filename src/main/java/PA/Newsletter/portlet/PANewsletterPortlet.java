package PA.Newsletter.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import PA.Newsletter.constants.PANewsletterPortletKeys;
import PA.Newsletter.util.NewsletterMailUtil;
import PA.Newsletter.util.NewsletterUserLocalServiceUtil;

import java.io.IOException;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
//import aQute.bnd.annotation.metatype.Configurable;
import com.liferay.portal.kernel.util.ParamUtil;

/**
 * (1)configurationPid represents the configuration for this portlet class
 */
@Component(
        configurationPid = "PA.Newsletter.configuration.NewsletterConfiguration",
        immediate = true, 
        property = {
        		"com.liferay.portlet.display-category=Politaktiv",
        		"com.liferay.portlet.instanceable=true",
        		"javax.portlet.display-name=PA-Newsletter Portlet",
        		"javax.portlet.init-param.template-path=/",
        		"javax.portlet.init-param.view-template=/view.jsp",
        		"javax.portlet.name=" + PANewsletterPortletKeys.PANewsletter,
        		"javax.portlet.resource-bundle=content.Language",
        		"javax.portlet.security-role-ref=power-user,user"
        }, 
        service = Portlet.class
        )
public class PANewsletterPortlet extends MVCPortlet {
	    
    @Override
    public void doView(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {
      
        super.doView(renderRequest, renderResponse);
    }
    
    /**
     * 
     * (1)If a method is annoted with @Activate then the method will be called at the time of activation of the component
     *  so that we can perform initialization task
     *  
     * (2) This class is annoted with @Component where we have used configurationPid with id com.proliferay.configuration.DemoConfiguration
     * So if we modify any configuration then this method will be called. 
     */
    
    @Activate
    @Modified
    protected void activate(Map<Object, Object> properties) {
        _log.info("#####Calling activate() method######");
        
        //_demoConfiguration = ConfigurableUtil.createConfigurable(DemoConfiguration.class, properties);
        
    }
    
    public void newsletterSignup(ActionRequest request, ActionResponse response) {
    	_log.info("#####Calling activate() method######");
    	String firstname = ParamUtil.getString(request, "newsletterFirstname");
    	String lastname = ParamUtil.getString(request, "newsletterLastname");
    	String email = ParamUtil.getString(request, "newsletterEmail");
    	System.out.println("param util: " + firstname + " " + lastname + " " + email);
    	
    	new NewsletterUserLocalServiceUtil().addUser(126428, firstname, lastname, email, request);
    	//NewsletterMailUtil mailUtil = new NewsletterMailUtil(_log);
    	//mailUtil.sendVerification(request, response);
    }
    
    public void newsletterSend(ActionRequest request, ActionResponse response) {

    }

    private static final Log _log = LogFactoryUtil.getLog(PANewsletterPortlet.class);

   

}