package com.indusnet.ums.service;


import com.indusnet.ums.common.ResponseModel;
import com.indusnet.ums.model.UserModel;

import jakarta.validation.Valid;

public interface IUserService {

	ResponseModel add(@Valid UserModel model);
	String get(String id);

 String getAll();
	ResponseModel update(UserModel model);

	ResponseModel delete(String id);
}

