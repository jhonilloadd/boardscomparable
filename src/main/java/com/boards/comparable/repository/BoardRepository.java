package com.boards.comparable.repository;

import com.boards.comparable.domain.Board;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BoardRepository implements PanacheRepository<Board> {

}
