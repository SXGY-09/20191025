package com.sxgy.sp33.bean;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "tb_stu")
@NamedQuery(name="Stu.customFind",query="select s from Stu s where s.clz.name=?1")
public class Stu implements Serializable {
	private static final long serialVersionUID = 1L;

	public Stu() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String address;
	private Integer age;
	private Character sex;

	// 学生与班级是多对一的关系，这里配置的时双向关联
	@ManyToOne(fetch = FetchType.LAZY, targetEntity = Clz.class)
	@JoinColumn(name = "clzId", referencedColumnName = "code")
	private Clz clz;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Character getSex() {
		return sex;
	}

	public void setSex(Character sex) {
		this.sex = sex;
	}

	public Clz getClz() {
		return clz;
	}

	public void setClz(Clz clz) {
		this.clz = clz;
	}

	public Stu(String name, String address, Integer age, Character sex, Clz clz) {
		super();
		this.name = name;
		this.address = address;
		this.age = age;
		this.sex = sex;
		this.clz = clz;
	}

	@Override
	public String toString() {
		return "Stu [id=" + id + ", name=" + name + "]";
	}
}
