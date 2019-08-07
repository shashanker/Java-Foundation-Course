package com.epam.course.springboot.request;

import com.epam.course.springboot.enums.Status;

public class PropertyRequest {
	
	private Long id;
	private String title;
	private String desc;
	private String addr1;//for address line 1
	private String addr2;//for address line 2
	private String type;//for property type;
	private Double price;
	private Long agentId;
	private Status status;
	private Long area;
	private Long beds;
	private Long baths;
	private Long garages;
	private String imgUrl;
	
	public PropertyRequest() {
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
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
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
	public Long getAgentId() {
		return agentId;
	}
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	
	
}
