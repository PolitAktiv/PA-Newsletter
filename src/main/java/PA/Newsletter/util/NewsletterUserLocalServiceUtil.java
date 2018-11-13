package PA.Newsletter.util;

import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;

import com.liferay.dynamic.data.lists.model.DDLRecord;
import com.liferay.dynamic.data.lists.model.DDLRecordSet;
import com.liferay.dynamic.data.lists.service.DDLRecordLocalServiceUtil;
import com.liferay.dynamic.data.lists.service.DDLRecordSetLocalServiceUtil;
import com.liferay.dynamic.data.mapping.model.DDMForm;
import com.liferay.dynamic.data.mapping.model.DDMFormField;
import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.UnlocalizedValue;
import com.liferay.dynamic.data.mapping.model.Value;
import com.liferay.dynamic.data.mapping.storage.DDMFormFieldValue;
import com.liferay.dynamic.data.mapping.storage.DDMFormValues;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.theme.ThemeDisplay;




/**
 * 
 * @author EcM
 *
 */
public class NewsletterUserLocalServiceUtil {

	public void addUser(long recordId, String firstname, String lastname, String email, ActionRequest actionRequest) {
		//long recordId = 126428;
		ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		try {
			
			long groupId = themeDisplay.getLayout().getGroupId();
			long recordSetId = recordId;
			int displayIndex = DDLRecordLocalServiceUtil.getRecords(recordId).size();
			ServiceContext serviceContext=ServiceContextFactory.getInstance(DDLRecord.class.getName(),actionRequest);
			long userId = serviceContext.getUserId();
			
			DDLRecordSet ddlRecordSet = DDLRecordSetLocalServiceUtil.getDDLRecordSet(recordId);
			DDMStructure ddmStructure = ddlRecordSet.getDDMStructure();
            
            System.out.println("Wir sind jetzt bei den Fields");

            
            DDMForm ddmForm = ddmStructure.getDDMForm();
            ddmForm.getDDMFormFields().forEach(field -> System.out.println(field.getName()));
            DDMFormValues ddmFormValues = new DDMFormValues(ddmForm);
            ddmFormValues.setDefaultLocale(LocaleUtil.getDefault());
            
            System.out.println(ddmFormValues.getDDMFormFieldValuesMap().keySet());
            if (ddmFormValues.getDDMFormFieldValues().isEmpty()) {
            	System.out.println("test 42: empty eksde");
            }
            List<DDMFormField> ddmFormFields = ddmForm.getDDMFormFields();
            if (ddmFormFields.isEmpty()) {
            	System.out.println("ddmFormFields empty");
            }
            List<DDMFormFieldValue> formFieldValues = new ArrayList<DDMFormFieldValue>();
            
            for (DDMFormField ddmFormField : ddmFormFields) {
            	String fieldDataType = "text";
            	Value value;
            	DDMFormFieldValue ffv;
            	System.out.println(LocaleUtil.getDefault());
            	if (ddmFormField.getName() == null || ddmFormField.getName() == "") {
                	System.out.println("ddmFormField name empty");
                }
            	System.out.println("type: " + ddmFormField.getType());
            	System.out.println("datatype: " + ddmFormField.getDataType());
				switch (ddmFormField.getName()) {
				case "Vorname":
					ddmFormField.setName("Vorname");
					ffv = new DDMFormFieldValue();
					ffv.setName("Vorname");
					value = new UnlocalizedValue(firstname);
                    //value = (Value) FieldConstants.getSerializable(fieldDataType, firstname);
					//value = ffv.getValue();
					//if (value == null) { System.out.println("Value is null: " + firstname); }
					//value.addString(LocaleUtil.getDefault(), firstname);
                    System.out.println("Value for " + ffv.getName() + ": " + value.getString(LocaleUtil.getDefault()));
					ffv.setValue(value);
                    formFieldValues.add(ffv);
                    break;
				case "Nachname":
					ddmFormField.setName("Nachname");
					ffv = new DDMFormFieldValue();
					ffv.setName("Nachname");
					value = new UnlocalizedValue(lastname);
                    //value = (Value) FieldConstants.getSerializable(fieldDataType, lastname);
					//value = ffv.getValue();
					//if (value == null) { System.out.println("Value is null: " + firstname); }
					//value.addString(LocaleUtil.getDefault(), lastname);
                    System.out.println("Value for " + ffv.getName() + ": " + value.getString(LocaleUtil.getDefault()));
					ffv.setValue(value);
                    formFieldValues.add(ffv);
                    break;
				case "Email":
					ddmFormField.setName("Email");
					ffv = new DDMFormFieldValue();
					ffv.setName("Email");
					value = new UnlocalizedValue(email);
                    //value = (Value) FieldConstants.getSerializable(fieldDataType, email);
					//value = ffv.getValue();
					//if (value == null) { System.out.println("Value is null: " + firstname); }
					//value.addString(LocaleUtil.getDefault(), email);
                    System.out.println("Value for " + ffv.getName() + ": " + value.getString(LocaleUtil.getDefault()));
					ffv.setValue(value);
                    formFieldValues.add(ffv);
                    break;
				}
				
			}          
            
            ddmFormValues.setDDMFormFieldValues(formFieldValues);
            
            for (DDMFormFieldValue x : ddmFormValues.getDDMFormFieldValues()) {
				if(x.getName() == null || x.getName().equals("")) {
					System.out.println("name is ned da");
				} else {System.out.println("name: " + x.getName()); }
				/*if(x.getValue() == null || x.getValue().equals("")) {
					System.out.println("value is ned da");
				} else {System.out.println("value: " + x.getValue()); }*/
			}
            
			DDLRecord record = DDLRecordLocalServiceUtil.addRecord(userId, groupId, recordSetId, displayIndex, ddmFormValues, serviceContext);
			System.out.println("-------------------------------");
			if (record == null) {
				System.out.println("record is null");
			}
			for (DDMFormFieldValue x : record.getDDMFormFieldValues("Email")) {
				if(x.getName() == null || x.getName().equals("")) {
					System.out.println("name is ned da");
				} else {System.out.println("name: " + x.getName()); }
				/*if(x.getValue() == null || x.getValue().equals("")) {
					System.out.println("value is ned da");
				} else {System.out.println("value: " + x.getValue()); }*/
			}
		} catch (PortalException e1) {
			e1.printStackTrace();
		}
	}
	
	private static void removeUser(String email, ThemeDisplay themeDisplay) {
		
	}
	
	public boolean checkIfEmailIsNotInNewsletter(String email) {
		long recordID = 126428;
		boolean result = true;
		for (String newsletterEmail : getAllUserEmails(recordID)) {
			if (newsletterEmail.equalsIgnoreCase(email)) {
				result = false;
				break;
			}
		}
		return result;
	}
	
	private List<String> getAllUserEmails(long recordID) {
		//long recordID = 126428;
		List<String> result = new ArrayList<String>();
		String emailField = "Email";
		try {
			if(DDLRecordLocalServiceUtil.getRecords(recordID).isEmpty()) {
				System.out.println("Records sind leer");
			} else {
				System.out.println("Records sind nicht leer");
				System.out.println("Records in total: " + DDLRecordLocalServiceUtil.getRecords(recordID).size());
				System.out.println(DDLRecordLocalServiceUtil.getRecords(recordID).get(0).getRecordSet().getName());
				for (DDLRecord ddlRecord : DDLRecordLocalServiceUtil.getRecords(recordID)) {
					if (ddlRecord.getDDMFormFieldValues(emailField) == null) {
						System.out.println("Felder sind leer");
						System.out.println(ddlRecord.getDDMFormValues().getDDMFormFieldValuesMap());
					} else {
						
						System.out.println("Felder sind nicht leer");
						System.out.println("Felder in total: " + ddlRecord.getDDMFormFieldValues(emailField).size());
						for (DDMFormFieldValue ffv : ddlRecord.getDDMFormFieldValues(emailField)) {
							String value = ffv.getValue().getString(ffv.getValue().getDefaultLocale());
							System.out.println(value);
							result.add(value);
						}
					}
				}
			}
		} catch (PortalException e1) {
			e1.printStackTrace();
		}
		return result;
	}
}
