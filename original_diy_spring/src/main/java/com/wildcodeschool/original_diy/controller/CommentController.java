package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyComment;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.repository.CommentRepository;
import com.wildcodeschool.original_diy.repository.UserRepository;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import com.wildcodeschool.original_diy.request.CommentRequest;
import com.wildcodeschool.original_diy.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test/comment")
public class CommentController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkshopRepository workshopRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;


    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/confirm/{id}")
    public ResponseEntity<Object> changeConfirmation(@PathVariable("id") Long id) {
        try {
            DiyComment comment = commentRepository.getById(id);
            boolean status = comment.isConfirmed();
            if (status) {
                comment.setConfirmed(false);
            } else {
                comment.setConfirmed(true);
            }
            commentRepository.save(comment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @RequestMapping("/new")
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentRequest commentRequest, Authentication authentication) {

        try {
            DiyUser user = userRepository.getUserByUsername(authentication.getName());
            DiyComment comment = commentService.commentControl(commentRequest, user);
            return new ResponseEntity<>(comment, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("permitAll()")
    @GetMapping("/comment-by-workshop/{id}")
    public ResponseEntity<List<DiyComment>> getCommentByWorkshop(@PathVariable("id") Long id) {
        try {

            List<DiyComment> comments = new ArrayList<>();
            DiyWorkshop workshop = workshopRepository.getById(id);

            comments.addAll(commentRepository.getCommentByWorkshop(workshop));

            if (comments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(comments, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
        System.out.println("id:::::" + id);
        try {
            DiyComment comment = commentRepository.getById(id);
            commentRepository.delete(comment);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
