package com.wildcodeschool.original_diy.service;

import com.wildcodeschool.original_diy.entity.DiyComment;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.repository.CommentRepository;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import com.wildcodeschool.original_diy.request.CommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    @Autowired
    WorkshopRepository workshopRepository;

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
}

