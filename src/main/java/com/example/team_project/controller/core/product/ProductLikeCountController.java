package com.example.team_project.controller.core.product;

import com.example.team_project.domain.domain.product.countduplication.service.LikeCountCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("update/like")
public class ProductLikeCountController {

    private final LikeCountCheckService likeCountCheckService;

    @GetMapping()
    public String likeCount(@SessionAttribute("userId")Long userId, @RequestParam("productId")Long productId){
        likeCountCheckService.countCheck(userId,productId);

        return "redirect:/product/detail/"+productId;

    }

}


