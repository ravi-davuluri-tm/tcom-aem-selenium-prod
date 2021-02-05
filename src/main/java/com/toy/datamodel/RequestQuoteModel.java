package com.toy.datamodel;

import java.util.ArrayList;

/**
 * This class is data model of request quote contains properties of all fields
 * 
 * @author Asif
 *
 */
public class RequestQuoteModel {

	private String seriesName;
	private String modelName;
	private String firstName;
	private String lastName;
	private String zipCode;
	private String email;
	private String phone;
	private String address;
	private String city;
	private String vendorName;
	private String dealerCode;
	private String commentText;
	private String exteriorColor;
	private String interiorColor;
	private String price;
	private String offerRibbon;
	private String offerDescription;
	private String trim;
	private boolean set = false;
	private String siteName = "website-toyota.com";
	private String campaignCode = "TWTT10570000-Toyota.com Request a Quote";
	
	private String language;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getOfferDescription() {
		return offerDescription;
	}

	public void setOfferDescription(String offerDescription) {
		this.offerDescription = offerDescription;
	}
	
	public boolean isSet() {
		return set;
	}

	public void setSet(boolean set) {
		this.set = set;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getOfferRibbon() {
		return offerRibbon;
	}

	public void setOfferRibbon(String offerRibbon) {
		this.offerRibbon = offerRibbon;
	}
	
	

	public String getPrice() {
		return price;
	}

	public void setPrice(String msrp) {
		this.price = msrp;
	}

	public String getExteriorColor() {
		return exteriorColor;
	}

	public void setExteriorColor(String exteriorColor) {
		this.exteriorColor = exteriorColor;
	}

	public String getInteriorColor() {
		return interiorColor;
	}

	public void setInteriorColor(String interiorColor) {
		this.interiorColor = interiorColor;
	}

	public String getCommentText() {
		return commentText;
	}

	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getSeriesName() {
		return seriesName;
	}

	//swaping Camry 2020 to 2020 Camry
	public String getSeriesNameEsp() {
		int spacePos = seriesName.indexOf(" ");
		String seriesNameEsp = seriesName.substring(spacePos+1, seriesName.length()) + " " + seriesName.substring(0, spacePos);
		return seriesNameEsp;
				
	}
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/* xml email response */

	public String getTrim() {
		if (trim == null)
			return modelName;
		else
			return trim;
	}
	
	public void setTrim(String trim) {
		this.trim = trim;
	}

	public String getCampaignCode() {
		return campaignCode;
	}

	public void setCampaignCode(String campaignCode) {
		this.campaignCode = campaignCode;
	}

	public String getYear() {
		return seriesName.substring(0, 4);
	}

	public String getMake() {
		return "toyota";
	}

	public String getModel() {
		return seriesName.substring(5);
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	

	@Override
	public String toString() {
		String trimName;
		if(trim ==null ) 
			trimName = modelName;		
		else 
			trimName = trim;
		
		return "RequestQuoteModel [seriesName=" + seriesName + ", trimName=" + trimName + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", zipCode=" + zipCode + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", city=" + city + ", vendorName=" + vendorName + ", dealerCode="
				+ dealerCode + ", commentText=" + commentText + ", exteriorColor=" + exteriorColor + ", interiorColor="
				+ interiorColor + ", price=" + price + ", offerRibbon=" + offerRibbon + ", offerDescription=" + offerDescription + ", siteName=" + siteName
				+ ", campaignCode=" + campaignCode + "]  language=" + language + "]";
	}
}







