package com.bagas.repositories.interfaces;

import com.bagas.entities.Session;

import java.util.List;

public interface SessionRepository extends CRUDRepository<Session> {

    List<Session> findByUserName(String name);
}
