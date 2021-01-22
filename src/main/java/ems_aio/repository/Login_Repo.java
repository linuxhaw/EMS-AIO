package ems_aio.repository;

import org.springframework.data.repository.CrudRepository;

import ems_aio.dto.User;

public interface Login_Repo extends CrudRepository<User, String> {

}
