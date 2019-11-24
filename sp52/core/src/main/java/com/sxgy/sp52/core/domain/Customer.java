package com.sxgy.sp52.core.domain;

import com.sxgy.sp52.core.pojo.Sp52Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author SXGY_09
 * @description 客户
 * @date 2019-11-23 15:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {
    private String token;

    //ID
    private long customerId;

    //Name
    private String customerName;


    private String password;
    //roles
    private List<Sp52Role> roles;
    private String client;
}
