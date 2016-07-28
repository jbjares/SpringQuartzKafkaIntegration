package eu.vital.iot.entity.document;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Address implements Serializable{

	private static final long serialVersionUID = 3369724935999315959L;
	@SerializedName("house_number")
	@Expose
	private String houseNumber;
	@SerializedName("road")
	@Expose
	private String road;
	@SerializedName("village")
	@Expose
	private String village;
	@SerializedName("county")
	@Expose
	private String county;
	@SerializedName("state")
	@Expose
	private String state;
	@SerializedName("postcode")
	@Expose
	private String postcode;
	@SerializedName("country")
	@Expose
	private String country;
	@SerializedName("country_code")
	@Expose
	private String countryCode;

	/**
	 *
	 * @return The houseNumber
	 */
	public String getHouseNumber() {
		return houseNumber;
	}

	/**
	 *
	 * @param houseNumber
	 *            The house_number
	 */
	public void setHouseNumber(String houseNumber) {
		this.houseNumber = houseNumber;
	}

	/**
	 *
	 * @return The road
	 */
	public String getRoad() {
		return road;
	}

	/**
	 *
	 * @param road
	 *            The road
	 */
	public void setRoad(String road) {
		this.road = road;
	}

	/**
	 *
	 * @return The village
	 */
	public String getVillage() {
		return village;
	}

	/**
	 *
	 * @param village
	 *            The village
	 */
	public void setVillage(String village) {
		this.village = village;
	}

	/**
	 *
	 * @return The county
	 */
	public String getCounty() {
		return county;
	}

	/**
	 *
	 * @param county
	 *            The county
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 *
	 * @return The state
	 */
	public String getState() {
		return state;
	}

	/**
	 *
	 * @param state
	 *            The state
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 *
	 * @return The postcode
	 */
	public String getPostcode() {
		return postcode;
	}

	/**
	 *
	 * @param postcode
	 *            The postcode
	 */
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	/**
	 *
	 * @return The country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 *
	 * @param country
	 *            The country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 *
	 * @return The countryCode
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 *
	 * @param countryCode
	 *            The country_code
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}