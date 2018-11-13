package PA.Newsletter.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.mail.kernel.model.MailMessage;
import com.liferay.mail.kernel.service.MailServiceUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import javax.mail.internet.InternetAddress;

/**
 * @author EcM
 */
public class NewsletterMailUtil {
	
	private Log _log;
	
	public NewsletterMailUtil(Log _log) {
		this._log = _log;
	}
	
    public void sendVerification(ActionRequest actionRequest, ActionResponse actionResponse) {
    	
    	_log.info("<<< sendVerification >>>");
    	
    	ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
    	String email = ParamUtil.getString(actionRequest, "newsletterEmail").trim();
    	_log.info("<<< Email: " + email + " >>>");
    	String firstname = ParamUtil.getString(actionRequest, "newsletterFirstname").trim();
    	_log.info("<<< Firstname: " + firstname + " >>>");
    	String lastname = ParamUtil.getString(actionRequest, "newsletterLastname").trim();
    	_log.info("<<< Lastname: " + lastname + " >>>");
    	Long groupId = themeDisplay.getCompanyGroupId();
    	_log.info("<<< Group ID: " + groupId + " >>>");
    	String verifyUrl = ParamUtil.getString(actionRequest, "verifyURL").trim();
    	_log.info("<<< Verify URL: " + verifyUrl + " >>>");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        Long verifyId = themeDisplay.getCompanyId();
        String encodedToken = Base64.encode((timeStamp
        									+"email"+email+"email_"
        									+"fn"+firstname+"fn_"
        									+"ln"+lastname+"ln_"
        									+"group"+groupId+"group_"
        									+verifyId).getBytes());
        
        _log.info("<<< sendVerification >>>");
        

        if (sendEmail(email,encodedToken, verifyUrl, themeDisplay)) {
            SessionMessages.add(actionRequest, "request_processed",
                    "You have sent verification to \"" + email
                            + "\" successfully.");
        } else {
            SessionErrors.add(actionRequest, "problem-occurred");
        }
    }
    
    public boolean sendEmail(String email,String encodedToken,String verifyUrl,ThemeDisplay themeDisplay) {
        
    	if(email == null || encodedToken == null || verifyUrl == null || themeDisplay == null) {
    		_log.info("<<< sendEmail parameter null >>> ");
    	}

    	try {
        	_log.info("<<< sendEmail >>> " + email);
            System.out.println(email+"   "+encodedToken+"     "+verifyUrl);
            InternetAddress toAddress = new InternetAddress(email);
            InternetAddress fromAddress = new InternetAddress(PrefsPropsUtil.getString(themeDisplay.getCompanyId(),PropsKeys.ADMIN_EMAIL_FROM_ADDRESS));
            String body = "Hi<br><br> Please Verify your email address<br> Please click on below link : <br><a href='"
                    + verifyUrl
                    + "&token="+encodedToken+"'>VERIFY</a><br><br><b>Thanks</b><br>";
            MailMessage mailMessage = new MailMessage();
            mailMessage.setTo(toAddress);
            mailMessage.setFrom(fromAddress);
            mailMessage.setSubject("Email Verification "
                    + email);
            mailMessage.setBody(body);
            mailMessage.setHTMLFormat(true);
            MailServiceUtil.sendEmail(mailMessage); // Sending message
        } catch (Exception e) {
        	System.out.println("Mail send failed");
            return false;
        }
        return true;
    }
}
