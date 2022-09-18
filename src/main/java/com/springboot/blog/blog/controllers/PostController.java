package com.springboot.blog.blog.controllers;

import com.springboot.blog.blog.entities.Post;
import com.springboot.blog.blog.payloads.PostDto;
import com.springboot.blog.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/add")
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto,
            @PathVariable Integer userId,
            @PathVariable Integer categoryId
    ){
        PostDto createdPost = postService.createPost(postDto,userId,categoryId);
        return new ResponseEntity<PostDto>(createdPost, HttpStatus.CREATED);
    }

    //get by user
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
        List<PostDto> postDtos = postService.getPostsByUser(userId);

        return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
    }

    //get by user
    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtos = postService.getPostByCategory(categoryId);

        return new ResponseEntity<List<PostDto>>(postDtos,HttpStatus.OK);
    }

    //get all posts
    @GetMapping("/list")
    public ResponseEntity<List<PostDto>> getPosts(){
        List<PostDto> posts = postService.getAllPost();
        return new ResponseEntity<List<PostDto>>(posts,HttpStatus.OK);
    }

    //get post by id
    @GetMapping("/list/{postId}")
    public ResponseEntity<PostDto> getPosts(@PathVariable Integer postId){
        PostDto postDto = postService.getPostById(postId);
        return new ResponseEntity<PostDto>(postDto,HttpStatus.OK);
    }

}
