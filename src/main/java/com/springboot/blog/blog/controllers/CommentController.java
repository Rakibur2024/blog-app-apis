package com.springboot.blog.blog.controllers;

import com.springboot.blog.blog.entities.Comment;
import com.springboot.blog.blog.payloads.ApiResponse;
import com.springboot.blog.blog.payloads.CommentDto;
import com.springboot.blog.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto comment, @PathVariable Integer postId){
        CommentDto createdComment = commentService.createComment(comment,postId);
        return new  ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);
    }

    @DeleteMapping("delete/{commentId}")
    public ApiResponse deleteCommentById(@PathVariable Integer commentId){
        commentService.deleteComment(commentId);
        return new ApiResponse("Comment Deleted Successfully", true);
    }
}
