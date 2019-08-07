package com.epam.course.springboot.response;

import com.epam.course.springboot.entity.Agent;

public class PropertyResponse {

	private Long id;
	private String title;
	
	private String desc;
	
	private String address1;
	
	private String address2;
	
	
	private Double price;
	
	private Agent agent;
	
	private String status;
	
	private String type;
	
	private Long area;
	private Long beds;
	private Long baths;
	private Long garages;
	private String imgUrl;
	
	public PropertyResponse() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getArea() {
		return area;
	}

	public void setArea(Long area) {
		this.area = area;
	}

	public Long getBeds() {
		return beds;
	}

	public void setBeds(Long beds) {
		this.beds = beds;
	}

	public Long getBaths() {
		return baths;
	}

	public void setBaths(Long baths) {
		this.baths = baths;
	}

	public Long getGarages() {
		return garages;
	}

	public void setGarages(Long garages) {
		this.garages = garages;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	
}
