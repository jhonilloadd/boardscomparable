package com.boards.comparable;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

import com.boards.comparable.domain.Board;
import com.boards.comparable.service.BoardService;

@Path("/boards")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BoardsResource {

    @Inject
    private BoardService boardService;

    @GET
    public Response getAllBoards() {
        List<Board> boards = boardService.getAllBoards();
        return Response.ok(boards).build();
    }

    @GET
    @Path("/{id}")
    public Response getBoardById(@PathParam("id") Long id) {
        Board board = boardService.getBoardById(id);
        if (board != null) {
            return Response.ok(board).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response createBoard(Board board) {
        Board createdBoard = boardService.createBoard(board);
        return Response.status(Response.Status.CREATED).entity(createdBoard).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateBoard(@PathParam("id") Long id, Board updatedBoard) {
        updatedBoard.setId(id);
        Board board = boardService.updateBoard(updatedBoard);
        if (board != null) {
            return Response.ok(board).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBoard(@PathParam("id") Long id) {
        boolean deleted = boardService.deleteBoard(id);
        if (deleted) {
            return Response.noContent().build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
