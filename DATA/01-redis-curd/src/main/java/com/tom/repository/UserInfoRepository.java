package com.tom.repository;

import org.springframework.data.repository.CrudRepository;

import com.tom.bean.UserInfo;

public interface UserInfoRepository extends CrudRepository<UserInfo, String> {

}
