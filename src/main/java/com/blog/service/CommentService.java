package com.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Service;

import com.blog.repository.CommentJpaRepository;
import com.blog.vo.Comment;


@Service
public class CommentService {
		
	@Autowired
	CommentJpaRepository commentJpaRepository;
	
	public boolean saveComment(Comment comment) {
		Comment result = commentJpaRepository.save(comment);
		boolean isSuccess = true;
		
		if (result == null) {
					return false;
		}
		
		return isSuccess;
	}
	
	public List<Comment> getCommentList(Long postId){
		List<Comment> comList = commentJpaRepository.findByPostIdOrderByRegDateDesc(postId);
		
		return comList;
	}
	
	public Comment getComment(Long id) {
		
		Comment com = commentJpaRepository.findById(id);
        return com;
    }
	
	 public boolean deleteComment(Long id) {
	        if (commentJpaRepository.existsById(id)) {
	            commentJpaRepository.deleteById(id);
	            return true;
	        }
	        return false;
	    }
	 
	 public List<Comment> searchComments(Long postId, String query) {
	        return commentJpaRepository.findByPostIdAndContentContainingOrderByRegDateDesc(postId, query);
	    }
	 
}
