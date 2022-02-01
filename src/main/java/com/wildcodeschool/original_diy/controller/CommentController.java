package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyComment;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.repository.CommentRepository;
import com.wildcodeschool.original_diy.repository.UserRepository;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;


@Controller
public class CommentController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WorkshopRepository workshopRepository;
    @Autowired
    private CommentRepository commentRepository;

    @RequestMapping("/saveComment")
    public String saveComment(@RequestParam(value = "workshopid") Long workshopid, @ModelAttribute DiyComment diyComment, Principal principal) {

        if (diyComment.getComment().equals("")) {
            return "redirect:/workshop/" + workshopid;
        }
        DiyUser user = userRepository.getByUsername(principal.getName());
        DiyWorkshop diyWorkshop = workshopRepository.getById(workshopid);
        diyComment.setDiyWorkshop(diyWorkshop);
        diyComment.setDiyUser(user);
        diyComment.setCreatedAt(new Date());
        commentRepository.save(diyComment);

        return "redirect:/workshop/" + workshopid;
    }
}
