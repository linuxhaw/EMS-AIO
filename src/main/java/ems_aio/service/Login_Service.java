package ems_aio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ems_aio.dto.User;
import ems_aio.repository.Login_Repo;

@Service
public class Login_Service {
@Autowired
Login_Repo repo;
public List<User> getAllUser(){
	List<User> list=(List<User>) repo.findAll();
	return list;
}
}
