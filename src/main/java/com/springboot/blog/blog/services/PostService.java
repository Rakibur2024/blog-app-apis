package com.springboot.blog.blog.services;

import com.springboot.blog.blog.entities.Post;
import com.springboot.blog.blog.payloads.PostDto;

import java.util.List;

public interface PostService {
    //create
    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //update
    Post updatePost(PostDto postDto, Integer postId);

    //delete
    void deletePost(Integer postId);

    //get all posts
    List<PostDto> getAllPost();

    //get post by id
    PostDto getPostById(Integer postId);

    //get all post by category
    List<PostDto> getPostByCategory(Integer categoryId);

    //get all posts by user
    List<PostDto> getPostsByUser(Integer userId);

    //search post
    List<Post> searchPosts(String keyword);
}
