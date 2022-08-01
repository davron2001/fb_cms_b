package uz.ages.fb_cms_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ages.fb_cms_b.entity.Form;
import uz.ages.fb_cms_b.payload.FormDto;
import uz.ages.fb_cms_b.repository.FormRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/form")
public class FormController {

    @Autowired
    FormRepository formRepository;

    @GetMapping
    public List<Form> getAllForm() {
        return formRepository.findAll();
    }

    @GetMapping("/{id}")
    public Form getOneForm(@PathVariable Integer id) {
        Optional<Form> byId = formRepository.findById(id);
        return byId.orElseGet(Form::new);
    }

    @PostMapping
    public ResponseEntity<?> addForm(@RequestBody FormDto formDto) {
        Form form = new Form();
        form.setTitle(formDto.getTitle());
        form.setContent(formDto.getContent());
        formRepository.save(form);
        return ResponseEntity.ok("Form saqlandi.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editForm(@PathVariable Integer id, @RequestBody FormDto formDto) {
        Optional<Form> byId = formRepository.findById(id);
        if (byId.isPresent()) {
            Form form = byId.get();
            form.setTitle(formDto.getTitle());
            form.setContent(formDto.getContent());
            Form save = formRepository.save(form);
            return ResponseEntity.status(200).body("Form Changed");
        } else {
            Form form = new Form();
            form.setTitle(formDto.getTitle());
            form.setContent(formDto.getContent());
            return ResponseEntity.status(201).body("Bunday idli form yo'q ekan. Yangi qo'shildi.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteForm(@PathVariable Integer id) {
        Optional<Form> byId = formRepository.findById(id);
        if (byId.isPresent()) {
            formRepository.deleteById(id);
        }
        return ResponseEntity.status(409).body("Deleted");
    }
}
