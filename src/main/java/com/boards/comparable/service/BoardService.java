package com.boards.comparable.service;

import com.boards.comparable.domain.Board;
import com.boards.comparable.repository.BoardRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class BoardService {

    @Inject
    BoardRepository boardRepository;

    @Transactional
    public List<Board> getAllBoards() {
        return boardRepository.listAll();
    }

    @Transactional
    public Board getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    @Transactional
    public Board createBoard(Board board) {
        boardRepository.persist(board);
        return board;
    }

    @Transactional
    public Board updateBoard(Board updatedBoard) {

        Board eBoard = boardRepository.findById(updatedBoard.getId());
        if (eBoard != null) {
            //

            return eBoard;
        }
        return null;
    }

    @Transactional
    public boolean deleteBoard(Long id) {
        boardRepository.deleteById(id);
        return false;
    }
}
