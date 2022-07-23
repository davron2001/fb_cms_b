package uz.ages.fb_cms_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.ages.fb_cms_b.entity.Tags;
import uz.ages.fb_cms_b.payload.TagsDto;
import uz.ages.fb_cms_b.repository.TagsRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TagsController {

    @Autowired
    TagsRepository tagsRepository;

    @GetMapping
    public List<Tags> getAllTags() {
        return tagsRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tags getOneTag(@PathVariable Integer id) {
        Optional<Tags> byId = tagsRepository.findById(id);
        return byId.orElseGet(Tags::new);
    }

    @PostMapping
    public ResponseEntity<?> addTags(@RequestBody TagsDto tagsDto) {
        Tags tags = new Tags();
        tags.setTitle(tagsDto.getTitle());
        tags.setContent(tagsDto.getContent());
        tagsRepository.save(tags);
        return ResponseEntity.ok("Saqlandi.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editTags(@PathVariable Integer id, @RequestBody TagsDto tagsDto) {
        Optional<Tags> byId = tagsRepository.findById(id);
        if (byId.isPresent()) {
            Tags tags = byId.get();
            tags.setTitle(tagsDto.getTitle());
            tags.setContent(tagsDto.getContent());
            Tags save = tagsRepository.save(tags);
            return ResponseEntity.status(200).body("Tags Changed");
        } else {
            Tags tags = new Tags();
            tags.setTitle(tagsDto.getTitle());
            tags.setContent(tagsDto.getContent());
            return ResponseEntity.status(201).body("Bunday idli tag yuq ekan. Yangi qushildi.");
        }
    }
}
