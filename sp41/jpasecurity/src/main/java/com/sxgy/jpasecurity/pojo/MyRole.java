package com.sxgy.jpasecurity.pojo;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author SXGY_09
 * @description
 * @timestamp 2019-11-02 16:44
 */
@Entity
@Table(name = "my_role")
public class MyRole implements Serializable {
    public MyRole() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "authority")
    private String authority;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String toString() {
        return "MyRole{" +
                "id=" + id +
                ", authority='" + authority + '\'' +
                '}';
    }
}
