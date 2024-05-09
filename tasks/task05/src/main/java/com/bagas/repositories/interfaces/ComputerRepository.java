package com.bagas.repositories.interfaces;

import com.bagas.entities.Computer;

import java.util.List;

public interface ComputerRepository extends CRUDRepository<Computer> {

    List<Computer> findByRoom(Integer room);
}
