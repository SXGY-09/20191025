package com.sxgy.sp33.bean;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity
@Table(name="tb_clz")
public class Clz implements Serializable {
	private static final long serialVersionUID = 1L;
	public Clz() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int code;
	private String name;
	@OneToMany(fetch=FetchType.LAZY,targetEntity=Stu.class,mappedBy="clz")
	private Set<Stu> stu=new HashSet<>();
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Stu> getStu() {
		return stu;
	}
	public void setStu(Set<Stu> student) {
		this.stu = student;
	}
	@Override
	public String toString() {
		return "Clz [code=" + code + ", name=" + name + "]";
	}
	public Clz(String name) {
		super();
		this.name = name;
	}
}
