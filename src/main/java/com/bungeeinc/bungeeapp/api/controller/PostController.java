package com.bungeeinc.bungeeapp.api.controller;

import com.bungeeinc.bungeeapp.api.annotation.activeuser.ActiveUser;
import com.bungeeinc.bungeeapp.api.service.PostService;
import com.bungeeinc.bungeeapp.api.service.model.endpoint.post.share.request.ShareRequest;
import com.bungeeinc.bungeeapp.api.service.model.endpoint.post.share.response.ShareResponse;
import com.bungeeinc.bungeeapp.database.models.account.BungeeUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/share")
    public ShareResponse shareResponse(@RequestBody @Valid ShareRequest request,
                                       @ActiveUser BungeeUserDetails user) {
        return postService.share(user, request);
    }

}
