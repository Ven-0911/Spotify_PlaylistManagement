package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreatePlaylist {
	
	private String name;
	private String description;
	@JsonProperty("public")
	private String publicValue;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPublicValue() {
		return publicValue;
	}
	public void setPublicValue(String publicValue) {
		this.publicValue = publicValue;
	}

}
