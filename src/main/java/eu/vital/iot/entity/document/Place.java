package eu.vital.iot.entity.document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Place implements Serializable {

	private static final long serialVersionUID = 3369724935999315959L;

	@SerializedName("place_id")
	@Expose
	private String placeId;
	@SerializedName("licence")
	@Expose
	private String licence;
	@SerializedName("osm_type")
	@Expose
	private String osmType;
	@SerializedName("osm_id")
	@Expose
	private String osmId;
	@SerializedName("lat")
	@Expose
	private String lat;
	@SerializedName("lon")
	@Expose
	private String lon;
	@SerializedName("display_name")
	@Expose
	private String displayName;
	@SerializedName("address")
	@Expose
	private Address address;
	@SerializedName("boundingbox")
	@Expose
	private List<String> boundingbox = new ArrayList<String>();

	/**
	 *
	 * @return The placeId
	 */
	public String getPlaceId() {
		return placeId;
	}

	/**
	 *
	 * @param placeId
	 *            The place_id
	 */
	public void setPlaceId(String placeId) {
		this.placeId = placeId;
	}

	/**
	 *
	 * @return The licence
	 */
	public String getLicence() {
		return licence;
	}

	/**
	 *
	 * @param licence
	 *            The licence
	 */
	public void setLicence(String licence) {
		this.licence = licence;
	}

	/**
	 *
	 * @return The osmType
	 */
	public String getOsmType() {
		return osmType;
	}

	/**
	 *
	 * @param osmType
	 *            The osm_type
	 */
	public void setOsmType(String osmType) {
		this.osmType = osmType;
	}

	/**
	 *
	 * @return The osmId
	 */
	public String getOsmId() {
		return osmId;
	}

	/**
	 *
	 * @param osmId
	 *            The osm_id
	 */
	public void setOsmId(String osmId) {
		this.osmId = osmId;
	}

	/**
	 *
	 * @return The lat
	 */
	public String getLat() {
		return lat;
	}

	/**
	 *
	 * @param lat
	 *            The lat
	 */
	public void setLat(String lat) {
		this.lat = lat;
	}

	/**
	 *
	 * @return The lon
	 */
	public String getLon() {
		return lon;
	}

	/**
	 *
	 * @param lon
	 *            The lon
	 */
	public void setLon(String lon) {
		this.lon = lon;
	}

	/**
	 *
	 * @return The displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 *
	 * @param displayName
	 *            The display_name
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 *
	 * @return The address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 *
	 * @param address
	 *            The address
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 *
	 * @return The boundingbox
	 */
	public List<String> getBoundingbox() {
		return boundingbox;
	}

	/**
	 *
	 * @param boundingbox
	 *            The boundingbox
	 */
	public void setBoundingbox(List<String> boundingbox) {
		this.boundingbox = boundingbox;
	}

}
