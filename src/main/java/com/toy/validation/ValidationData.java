package com.toy.validation;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.toy.datamodel.RequestQuoteModel;
import com.toy.selenium.core.Configuration;
import com.toy.utilities.Utilities;

public class ValidationData {

	/**
	 * Validate email content of request a quote
	 * 
	 * @param requestQuoteModel : request data hold
	 * @param mailContent       : mail content
	 */
	public static void validateRAQEmailData(RequestQuoteModel requestQuoteModel, String mailContent, String language)
			throws Exception {
		String raqExperience = Configuration.readApplicationFile("raqExperienceValue");
		Assert.assertTrue(
				mailContent.toLowerCase().contains("<b>make:</b>" + requestQuoteModel.getMake().toLowerCase() + "</p>"),
				requestQuoteModel.getMake() + " Make not found in Email");

		switch (language) {
		case "Esp":
			if(requestQuoteModel.getSeriesName() != null && requestQuoteModel.getSeriesName() != ""){
			Assert.assertTrue(
					mailContent.contains("<b>year:</b>" + Utilities.getSeriesYearEsp(requestQuoteModel) + "</p>"),
					Utilities.getSeriesYearEsp(requestQuoteModel) + " Year not found in Email");
			Assert.assertTrue(
					mailContent.toLowerCase().contains(
							"<b>model:</b>" + Utilities.getSeriesNameEsp(requestQuoteModel).toLowerCase() + "</p>"),
					Utilities.getSeriesNameEsp(requestQuoteModel).toLowerCase() + " Model not found in Email");
			}
			if (raqExperience.equalsIgnoreCase("control")  && requestQuoteModel.getTrim() != null && !requestQuoteModel.getTrim().equals("")) {
				String customTrim = Utilities.getCustomeTrim(requestQuoteModel.getTrim(), requestQuoteModel.getSeriesName());
				System.out.println("Custom Trim :"+customTrim);
				Assert.assertTrue(						
						mailContent.toLowerCase()
								.contains("<b>trim:</b>" + customTrim.toLowerCase().trim()), customTrim + "Trim not found in Email");
			}
			break;
		default:
			if(requestQuoteModel.getSeriesName() != null && requestQuoteModel.getSeriesName() != ""){
				Assert.assertTrue(mailContent.contains("<b>year:</b>" + requestQuoteModel.getYear() + "</p>"),
						requestQuoteModel.getYear() + " Year not found in Email");
				String model = Utilities.getCustomeModel(requestQuoteModel.getModel());
				Assert.assertTrue(
						mailContent.toLowerCase()
								.contains("<b>model:</b>" + model.toLowerCase().trim() + "</p>"),
								model + "Model not found in Email");
			}
			if (raqExperience.equalsIgnoreCase("control")  && requestQuoteModel.getTrim() != null && !requestQuoteModel.getTrim().equals("")) {
				String customTrim = Utilities.getCustomeTrim(requestQuoteModel.getTrim(), requestQuoteModel.getSeriesName());
				System.out.println("Custom Trim :"+customTrim);	
				Assert.assertTrue(
						mailContent.toLowerCase()
								.contains("<b>trim:</b>" + customTrim.toLowerCase().trim()), customTrim + "Trim not found in Email");
			}
			break;
		}

		Assert.assertTrue(
				mailContent.contains("<b>first name:</b>" + requestQuoteModel.getFirstName().toLowerCase() + "</p>"),
				requestQuoteModel.getFirstName() + " First Name not found in Email");
		Assert.assertTrue(
				mailContent.contains("<b>last name:</b>" + requestQuoteModel.getLastName().toLowerCase() + "</p>"),
				requestQuoteModel.getLastName() + " Last name not found in Email");		

		if (raqExperience.equalsIgnoreCase("control")) {
			Assert.assertTrue(
					mailContent
							.contains("<b>address line1:</b>" + requestQuoteModel.getAddress().toLowerCase() + "</p>"),
					requestQuoteModel.getAddress() + " Address not found in Email");
			Assert.assertTrue(
					mailContent.contains("<b>email:</b>" + requestQuoteModel.getEmail().toLowerCase() + "</p>"),
					requestQuoteModel.getEmail() + " Mail not found in Email");
			Assert.assertTrue(mailContent.contains("<b>city:</b>" + requestQuoteModel.getCity().toLowerCase() + "</p>"),
					requestQuoteModel.getCity() + " City not found in Email");
		}

		String phoneNumber = requestQuoteModel.getPhone().replaceAll("[^0-9]", "");
		Assert.assertTrue(mailContent.contains("<b>phone:</b>" + phoneNumber + "</p>"),
				requestQuoteModel.getPhone() + " Phone nuumber not found in Email");
		if (requestQuoteModel.getExteriorColor() != null && !requestQuoteModel.getExteriorColor().equals(""))
			Assert.assertTrue(
					mailContent.toLowerCase().contains(
							"<b>exterior color:</b>" + requestQuoteModel.getExteriorColor().toLowerCase() + "</p>"),
					requestQuoteModel.getExteriorColor() + " Exterior Color not found in Email");
		if (requestQuoteModel.getInteriorColor() != null && !requestQuoteModel.getInteriorColor().equals(""))
			Assert.assertTrue(
					mailContent.toLowerCase().contains(
							"<b>interior color:</b>" + requestQuoteModel.getInteriorColor().toLowerCase() + "</p>"),
					requestQuoteModel.getInteriorColor() + " Interior Color not found in Email");
		if (requestQuoteModel.getPrice() != null && !requestQuoteModel.getPrice().equals(""))
			Assert.assertTrue(
					mailContent.toLowerCase()
							.contains("<b>vehicle price:</b>" + requestQuoteModel.getPrice().toLowerCase() + "</p>"),
					requestQuoteModel.getPrice() + " price not found in Email");
		Assert.assertTrue(mailContent.contains(requestQuoteModel.getVendorName().toLowerCase()),
				requestQuoteModel.getVendorName() + " Vendor Name Not found in Email");
		Assert.assertTrue(mailContent.contains(requestQuoteModel.getDealerCode().toLowerCase() + "</p>"),
				requestQuoteModel.getDealerCode() + " Dealer code not found in Email");
		Assert.assertTrue(
				mailContent.toLowerCase().contains("<b>lead source:</b>" + requestQuoteModel.getSiteName() + "</p>"),
				" Web site for Website not found in Email");
		if (requestQuoteModel.getOfferRibbon() != null && !requestQuoteModel.getOfferRibbon().equals("")) {
			if (requestQuoteModel.getOfferRibbon().toLowerCase().equals("finance")
					|| requestQuoteModel.getOfferRibbon().toLowerCase().equals("financiamiento")) {
				Assert.assertTrue(
						mailContent.contains("zip:" + " " + requestQuoteModel.getZipCode() + "type: apr" + " "
								+ "year: " + requestQuoteModel.getYear()),
						"zip apr or year not found for finance in Email");
			} else {
				Assert.assertTrue(mailContent.toLowerCase().contains("type: lease"),
						requestQuoteModel.getOfferRibbon() + " not found in Email");
			}
			// zip: 32004 type: APR year: 2020
		}
		Assert.assertTrue(
				mailContent.toLowerCase().contains(
						"<b>lead sub source:</b>" + requestQuoteModel.getCampaignCode().toLowerCase() + "</p>"),
				"Campaign code not found in Email");
		Assert.assertTrue(mailContent.contains("<b>postal code:</b>" + requestQuoteModel.getZipCode() + "</p>"),
				requestQuoteModel.getZipCode() + " Zip not found in Email");
	}

	/**
	 * Validate email content of request a quote
	 * 
	 * @param requestQuoteModel : request data hold
	 * @param mailContent       : mail content
	 */
	public static void validateOtherRAQTextEmailData(RequestQuoteModel requestQuoteModel, String mailContent,
			String language) throws Exception {
		System.out.println("Verify Trim:" + requestQuoteModel.getTrim());
		System.out.println("zip:" + " " + requestQuoteModel.getZipCode() + " " + "type: Cash" + " " + "year: "
				+ requestQuoteModel.getYear());
		Assert.assertTrue(
				mailContent.toLowerCase().contains("<b>make:</b>" + requestQuoteModel.getMake().toLowerCase() + "</p>"),
				requestQuoteModel.getMake() + " Make not found in Email");

		switch (language) {
		case "Esp":
			Assert.assertTrue(
					mailContent.contains("<b>year:</b>" + Utilities.getSeriesYearEsp(requestQuoteModel) + "</p>"),
					Utilities.getSeriesYearEsp(requestQuoteModel) + " Year not found in Email");
			Assert.assertTrue(
					mailContent.toLowerCase().contains(
							"<b>model:</b>" + Utilities.getSeriesNameEsp(requestQuoteModel).toLowerCase() + "</p>"),
					Utilities.getSeriesNameEsp(requestQuoteModel).toLowerCase() + " Model not found in Email");
			if (requestQuoteModel.getTrim() != null && !requestQuoteModel.getTrim().equals("")) {
				String customTrim = Utilities.getCustomeTrim(requestQuoteModel.getTrim(), requestQuoteModel.getSeriesName());
				System.out.println(customTrim);	
				Assert.assertTrue(mailContent.toLowerCase().contains(customTrim.toLowerCase().trim()), customTrim+ " Trim not found in Email");
			}
			break;
		default:
			Assert.assertTrue(mailContent.contains("<b>year:</b>" + requestQuoteModel.getYear() + "</p>"),
					requestQuoteModel.getYear() + " Year not found in Email");
			String customModel = Utilities.getCustomeModel(requestQuoteModel.getModel());
			Assert.assertTrue(
					mailContent.toLowerCase()
							.contains("<b>model:</b>" + customModel.toLowerCase().trim() + "</p>"),
							customModel + " Model not found in Email");

			if (requestQuoteModel.getTrim() != null && !requestQuoteModel.getTrim().equals("")) {
				String customTrim = Utilities.getCustomeTrim(requestQuoteModel.getTrim(), requestQuoteModel.getSeriesName());
				System.out.println(customTrim);					
				Assert.assertTrue(mailContent.toLowerCase().contains(customTrim.toLowerCase().trim()), customTrim + " Trim not found in Email");
			}
			break;
		}
		Assert.assertTrue(
				mailContent.contains("<b>first name:</b>" + requestQuoteModel.getFirstName().toLowerCase() + "</p>"),
				requestQuoteModel.getFirstName() + " First Name not found in Email");
		Assert.assertTrue(
				mailContent.contains("<b>last name:</b>" + requestQuoteModel.getLastName().toLowerCase() + "</p>"),
				requestQuoteModel.getLastName() + " Last name not found in Email");
		Assert.assertTrue(mailContent.contains("<b>email:</b>" + requestQuoteModel.getEmail().toLowerCase() + "</p>"),
				requestQuoteModel.getEmail() + " Mail not found in Email");

		String raqExperience = Configuration.readApplicationFile("raqExperienceValue");

		if (raqExperience.equalsIgnoreCase("control")) {
			Assert.assertTrue(
					mailContent
							.contains("<b>address line1:</b>" + requestQuoteModel.getAddress().toLowerCase() + "</p>"),
					requestQuoteModel.getAddress() + " Address not found in Email");

			Assert.assertTrue(mailContent.contains("<b>city:</b>" + requestQuoteModel.getCity().toLowerCase() + "</p>"),
					requestQuoteModel.getCity() + " City not found in Email");
		}

		String phoneNumber = requestQuoteModel.getPhone().replaceAll("[^0-9]", "");
		Assert.assertTrue(mailContent.contains("<b>phone:</b>" + phoneNumber + "</p>"),
				requestQuoteModel.getPhone() + " Phone nuumber not found in Email");
		if (requestQuoteModel.getExteriorColor() != null && !requestQuoteModel.getExteriorColor().equals(""))
			Assert.assertTrue(
					mailContent.toLowerCase().contains(
							"<b>exterior color:</b>" + requestQuoteModel.getExteriorColor().toLowerCase() + "</p>"),
					requestQuoteModel.getExteriorColor() + " Exterior Color not found in Email");
		if (requestQuoteModel.getInteriorColor() != null && !requestQuoteModel.getInteriorColor().equals(""))
			Assert.assertTrue(
					mailContent.toLowerCase().contains(
							"<b>interior color:</b>" + requestQuoteModel.getInteriorColor().toLowerCase() + "</p>"),
					requestQuoteModel.getInteriorColor() + " Interior Color not found in Email");
		if (requestQuoteModel.getPrice() != null && !requestQuoteModel.getPrice().equals(""))
			Assert.assertTrue(
					mailContent.toLowerCase()
							.contains("<b>vehicle price:</b>" + requestQuoteModel.getPrice().toLowerCase() + "</p>"),
					requestQuoteModel.getPrice() + " price not found in Email");
		Assert.assertTrue(mailContent.contains(requestQuoteModel.getVendorName().toLowerCase()),
				requestQuoteModel.getVendorName() + " Vendor Name Not found in Email");
		Assert.assertTrue(mailContent.contains(requestQuoteModel.getDealerCode().toLowerCase() + "</p>"),
				requestQuoteModel.getDealerCode() + " Dealer code not found in Email");
		Assert.assertTrue(
				mailContent.toLowerCase().contains("<b>lead source:</b>" + requestQuoteModel.getSiteName() + "</p>"),
				" Web site for Website not found in Email");
		if (requestQuoteModel.getOfferDescription() != null && !requestQuoteModel.getOfferDescription().equals("")) {
			Assert.assertTrue(mailContent.toLowerCase().contains(requestQuoteModel.getOfferDescription().toLowerCase()),
					requestQuoteModel.getOfferDescription().toLowerCase() + " offer description not found in Email");
		}
		if (requestQuoteModel.getOfferRibbon() != null && !requestQuoteModel.getOfferRibbon().equals("")) {
			if (requestQuoteModel.getOfferRibbon().toLowerCase().equals("finance")
					|| requestQuoteModel.getOfferRibbon().toLowerCase().equals("financiamiento")) {
				Assert.assertTrue(mailContent.contains("zip:" + " " + requestQuoteModel.getZipCode()),
						"zip not found in comments section in Email");
				Assert.assertTrue(mailContent.contains("type: apr"),
						"type: APR not found in comments section in Email");
				Assert.assertTrue(mailContent.contains("year:" + " " + requestQuoteModel.getYear()),
						"Year not found in comments section in Email");
			} else if (requestQuoteModel.getOfferRibbon().toLowerCase().equals("lease")
					|| requestQuoteModel.getOfferRibbon().toLowerCase().equals("arrendar")) {
				Assert.assertTrue(mailContent.contains("zip:" + " " + requestQuoteModel.getZipCode()),
						"zip not found in comments section in Email");
				Assert.assertTrue(mailContent.contains("type: lease"),
						"type: lease not found in comments section in Email");
				Assert.assertTrue(mailContent.contains("year:" + " " + requestQuoteModel.getYear()),
						"Year not found in comments section in Email");

			} else if (requestQuoteModel.getOfferRibbon().toLowerCase().equals("cash back")
					|| requestQuoteModel.getOfferRibbon().toLowerCase().equals("reembolso")) {

				Assert.assertTrue(mailContent.contains("zip:" + " " + requestQuoteModel.getZipCode()),
						"zip not found in comments section in Email");
				Assert.assertTrue(mailContent.contains("type: cash"),
						"type: cash not found in comments section in Email");
				Assert.assertTrue(mailContent.contains("year:" + " " + requestQuoteModel.getYear()),
						"Year not found in comments section in Email");
			}

		}
		Assert.assertTrue(
				mailContent.toLowerCase().contains(
						"<b>lead sub source:</b>" + requestQuoteModel.getCampaignCode().toLowerCase() + "</p>"),
				requestQuoteModel.getCampaignCode().toLowerCase() + " Campaign code not found in Email");
		Assert.assertTrue(mailContent.contains("<b>postal code:</b>" + requestQuoteModel.getZipCode() + "</p>"),
				requestQuoteModel.getZipCode() + " Zip not found in Email");
	}

	public static void verifyMailContainsTrim(String mailContent) {
		if (mailContent.contains("<b>trim:</b>")) {
			String str[] = mailContent.split("<b>trim:</b>");
			String trimValue = str[1].split("</p>")[0];
			Assert.assertNotNull(trimValue, "Trim value not found");
			Assert.assertTrue(trimValue.length() != 0, "Trim value length is equal to zero");

		} else {
			Assert.assertTrue(false, "Mail not contains trim");
		}
	}
	

}