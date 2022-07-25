package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.model.Topic;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import com.survivingcodingbootcamp.blog.repository.TopicRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/topics")
public class TopicController {

    private TopicRepository topicRepo;

    private PostRepository postRepo;


    public TopicController(TopicRepository topicRepo, PostRepository postRepo) {
        this.postRepo = postRepo;
        this.topicRepo = topicRepo;
    }
    @GetMapping("/{id}")
    public String displaySingleTopic(@PathVariable long id, Model model) {
        model.addAttribute("topic", topicRepo.findById(id).get());
        return "single-topic-template";
    }
    @PostMapping("/{id}/addPost")
    public String addPost(Model model, @PathVariable long id, @RequestParam String content, @RequestParam String author, @RequestParam String title) {
        Topic topic = topicRepo.findById(id).get();
        Post post = new Post(title,topic,content,author);
        postRepo.save(post);
        return "redirect:/topics/" +id;

    }
}
