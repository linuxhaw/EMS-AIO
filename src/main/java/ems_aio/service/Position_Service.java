package ems_aio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ems_aio.dto.Position;
import ems_aio.repository.Position_repo;

@Service
public class Position_Service {
	@Autowired
	Position_repo repo;
public void insert(Position position) {
	repo.save(position);
}
public List<Position> getAll(){
	List<Position> list=(List<Position>) repo.findAll();
	return list;
}
}
