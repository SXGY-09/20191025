package com.sxgy.sp32.bean;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_article")
public class Article implements Serializable {
	private static final long serialVersionUID = 1L;

	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String supplier;
	private Double price;
	private String locality;
	private Date putawayDate;
	private Integer storage;
	private String image;
	private String description;
	private Date createDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public Date getPutawayDate() {
		return putawayDate;
	}

	public void setPutawayDate(Date putawayDate) {
		this.putawayDate = putawayDate;
	}

	public Integer getStorage() {
		return storage;
	}

	public void setStorage(Integer storage) {
		this.storage = storage;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", supplier=" + supplier + "]";
	}
}
