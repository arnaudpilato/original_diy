package com.wildcodeschool.original_diy.service;

import com.wildcodeschool.original_diy.entity.DiyComment;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.repository.CommentRepository;
import com.wildcodeschool.original_diy.repository.UserRepository;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import com.wildcodeschool.original_diy.request.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    WorkshopRepository workshopRepository;

    @Autowired
    UserRepository userRepository;

    public void changeConfirmation(Long id) {
        DiyComment comment = commentRepository.getById(id);
        boolean status = comment.isConfirmed();

        if (status) {
            comment.setConfirmed(false);
        } else {
            comment.setConfirmed(true);
        }

        commentRepository.save(comment);
    }

    public DiyComment createComment(@Valid @RequestBody CommentRequest commentRequest, Authentication authentication) {
        DiyUser user = userRepository.getUserByUsername(authentication.getName());
        DiyComment comment = commentControl(commentRequest, user);

        return comment;
    }

    public DiyComment commentControl(CommentRequest commentRequest, DiyUser user) {
        DiyComment comment = new DiyComment();
        comment.setComment(commentRequest.getComment());
        comment.setDiyUser(user);
        DiyWorkshop workshop = workshopRepository.getById(commentRequest.getDiyWorkshopId());
        comment.setDiyWorkshop(workshop);
        comment.setConfirmed(false);
        comment.setCreatedAt(new Date());
        commentRepository.save(comment);

        return comment;
    }

    public List<DiyComment> getCommentByWorkshop(Long id) {
        List<DiyComment> comments = new ArrayList<>();
        DiyWorkshop workshop = workshopRepository.getById(id);

        comments.addAll(commentRepository.getCommentByWorkshop(workshop));

        return comments;
    }

    public void delete(Long id) {
        DiyComment comment = commentRepository.getById(id);

        commentRepository.delete(comment);
    }
}

