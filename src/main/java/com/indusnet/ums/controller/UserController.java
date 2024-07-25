package com.indusnet.ums.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.indusnet.ums.util.ObjectIdTypeAdapter;
import com.indusnet.ums.common.MessageTypeConst;
import com.indusnet.ums.common.LoggingResponseModel;
import com.indusnet.ums.common.ResponseModel;
import com.indusnet.ums.model.UserModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.indusnet.ums.service.IUserService;

import lombok.extern.slf4j.Slf4j;

import java.sql.ClientInfoStatus;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ums/")
@Slf4j
public class UserController {


    @Autowired
    Gson gson;

    @Autowired
    IUserService service;

    Gson customGson = new GsonBuilder().registerTypeHierarchyAdapter(ObjectId.class, new ObjectIdTypeAdapter()).create();
    private String usersJson;


    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> register(@RequestBody String request) {

        LoggingResponseModel msgStart = LoggingResponseModel.builder()
                .message("Start add register")
                .messageTypeId(MessageTypeConst.SUCCESS)
                .build();

        log.info(gson.toJson(msgStart));
        ResponseModel response = service.add(customGson.fromJson(request, UserModel.class));
        HttpStatus status = response.getStatusCode() != null ? response.getStatusCode() : HttpStatus.NOT_FOUND;

        LoggingResponseModel msgEnd = LoggingResponseModel.builder()
                .message("End Add register")
                .messageTypeId(MessageTypeConst.SUCCESS)
                .build();

        log.info(gson.toJson(msgEnd));

        return new ResponseEntity<ResponseModel>(response, status);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getById(@PathVariable("id") String id) {
        return new ResponseEntity<String>(service.get(id), HttpStatus.OK);
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAll() {
        String userJson =service.getAll();

        return new ResponseEntity<>(usersJson, HttpStatus.OK);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> update(@RequestBody String request) {
        LoggingResponseModel msgStart = LoggingResponseModel.builder()
                .message("Start update user")
                .messageTypeId(MessageTypeConst.SUCCESS)
                .build();

        log.info(gson.toJson(msgStart));
        ResponseModel response = service.update(customGson.fromJson(request, UserModel.class));
        HttpStatus status = response.getStatusCode() != null ? response.getStatusCode() : HttpStatus.NOT_FOUND;

        LoggingResponseModel msgEnd = LoggingResponseModel.builder()
                .message("End update user")
                .messageTypeId(MessageTypeConst.SUCCESS)
                .build();

        log.info(gson.toJson(msgEnd));
        return new ResponseEntity<>(response, status);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseModel> delete(@PathVariable("id") String id) {
        LoggingResponseModel msgStart = LoggingResponseModel.builder()
                .message("Start delete user")
                .messageTypeId(MessageTypeConst.SUCCESS)
                .build();

        log.info(gson.toJson(msgStart));
        ResponseModel response = service.delete(id);
        HttpStatus status = response.getStatusCode() != null ? response.getStatusCode() : HttpStatus.NOT_FOUND;

        LoggingResponseModel msgEnd = LoggingResponseModel.builder()
                .message("End delete user")
                .messageTypeId(MessageTypeConst.SUCCESS)
                .build();

        log.info(gson.toJson(msgEnd));
        return new ResponseEntity<>(response, status);
    }




}
