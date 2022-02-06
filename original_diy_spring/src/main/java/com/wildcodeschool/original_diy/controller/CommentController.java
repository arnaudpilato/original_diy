/*package com.wildcodeschool.original_diy.controller;

import com.wildcodeschool.original_diy.entity.DiyComment;
import com.wildcodeschool.original_diy.entity.DiyUser;
import com.wildcodeschool.original_diy.entity.DiyWorkshop;
import com.wildcodeschool.original_diy.repository.CommentRepository;
import com.wildcodeschool.original_diy.repository.UserRepository;
import com.wildcodeschool.original_diy.repository.WorkshopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/admin/commentaire")
    public String getWorkshops(Model model) {
        model.addAttribute("commentary", commentRepository.findAll());
        model.addAttribute("workshops", workshopRepository.getAllWorkshops());

        return "admin/workshop/commentary";
    }

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

    @GetMapping("/admin/comment/delete/{id}")
    public String deleteComment(@PathVariable("id") Long id) {
        DiyComment comment = commentRepository.getById(id);
        commentRepository.delete(comment);

        return "redirect:/admin/commentaire";
    }

    @GetMapping("/admin/comment/confirm/{id}")
    public String confirmComment(@PathVariable("id") Long id) {
        DiyComment comment = commentRepository.getById(id);
        if (comment.isConfirmed()){
            comment.setConfirmed(false);
        }else{
            comment.setConfirmed(true);
        }
        commentRepository.save(comment);

        return "redirect:/admin/commentaire";
    }
}
*/