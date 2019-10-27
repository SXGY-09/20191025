package com.sxgy.sp33.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.sxgy.sp33.bean.Clz;

public interface ClzRepositorySpecification extends JpaRepository<Clz, Integer>, JpaSpecificationExecutor<Clz> {

}
