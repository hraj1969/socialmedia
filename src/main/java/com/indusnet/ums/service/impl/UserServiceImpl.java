package com.indusnet.ums.service.impl;

import java.time.Instant;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.indusnet.ums.common.ResponseModel;
import com.indusnet.ums.exception.CustomNotFoundException;
import com.indusnet.ums.exception.UnprocessableException;
import com.indusnet.ums.model.UserModel;
import com.indusnet.ums.repository.IUserServiceRepository;
import com.indusnet.ums.service.IUserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import io.jsonwebtoken.SignatureAlgorithm;


@Service
@Slf4j
public class UserServiceImpl implements IUserService {
		
	@Autowired
	Gson gson = new Gson();
	
	@Autowired
	IUserServiceRepository repository;
	@Override
	public ResponseModel add(UserModel model) {
	    
	    ResponseModel responseObj = null;      
	    
	    
	    Long currentTimeInMilli = Instant.now().toEpochMilli();     
	    UserModel saveModel=UserModel.builder()
	    		.email(model.getEmail())
				.id(model.getId())
				.phone(model.getPhone())
	    		.name(model.getName())
				.address(model.getAddress())
	    		.password(model.getName())
	    		.dateCreated(currentTimeInMilli)
	    		.build();
	
	    repository.save(saveModel);
	
	    log.info(gson.toJson(saveModel));
	
	    responseObj=ResponseModel.builder()
	            .messageEn("User Added Successfully")
	            .messageFr("User Added Successfully")
	            .messageTypeId(1)
	            .statusCode(HttpStatus.OK)
	            .build();
	
	    return responseObj;
	
	}
	@Override
	public String get(String id) {

		ResponseModel responseObj = null;
		Optional<UserModel> user = repository.findById(id);
		return repository.findById(id).toString();

	}


	public String getAll() {


		List<UserModel> user = repository.findAll();
		Object users = null;
		return gson.toJson(users);

	}

	@Override
	public ResponseModel update(UserModel model) {
		Optional<UserModel> userOptional = repository.findById(model.getId());
		if (userOptional.isPresent()) {
			UserModel existingUser = userOptional.get();
			existingUser.setEmail(model.getEmail());
			existingUser.setName(model.getName());
			existingUser.setPassword(model.getPassword());
			existingUser.setPhone(model.getPhone());
			repository.save(existingUser);

			log.info(gson.toJson(existingUser));

			ResponseModel responseObj = ResponseModel.builder()
					.messageEn("User Updated Successfully")
					.messageTypeId(1)
					.statusCode(HttpStatus.OK)
					.build();

			return responseObj;
		} else {
			return ResponseModel.builder()
					.messageEn("User Not Found")
					.messageTypeId(0)
					.statusCode(HttpStatus.NOT_FOUND)
					.build();
		}
	}

	@Override
	public ResponseModel delete(String id) {
		Optional<UserModel> userOptional = repository.findById(id);
		if (userOptional.isPresent()) {
			repository.deleteById(id);

			ResponseModel responseObj = ResponseModel.builder()
					.messageEn("User Deleted Successfully")
					.messageTypeId(1)
					.statusCode(HttpStatus.OK)
					.build();

			return responseObj;
		} else {
			return ResponseModel.builder()
					.messageEn("User Not Found")
					.messageTypeId(0)
					.statusCode(HttpStatus.NOT_FOUND)
					.build();
		}
	}






}

