package uz.ages.fb_cms_b.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ages.fb_cms_b.entity.Table;
import uz.ages.fb_cms_b.payload.TableDto;
import uz.ages.fb_cms_b.payload.TagsDto;
import uz.ages.fb_cms_b.repository.TableRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/table")
public class TableController {

    @Autowired
    TableRepository tableRepository;

    @GetMapping
    public List<Table> getAllTables() {
        return tableRepository.findAll();
    }

    @GetMapping("/{id}")
    public Table getOneTable(@PathVariable Integer id) {
        Optional<Table> byId = tableRepository.findById(id);
        return byId.orElseGet(Table::new);
    }

    @PostMapping
    public ResponseEntity<?> addTables(@RequestBody TableDto tableDto) {
        Table table = new Table();
        table.setTitle(tableDto.getTitle());
        table.setContent(tableDto.getContent());
        tableRepository.save(table);
        return ResponseEntity.ok("Saqlandi.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editTables(@PathVariable Integer id, @RequestBody TableDto tableDto) {
        Optional<Table> byId = tableRepository.findById(id);
        if (byId.isPresent()) {
            Table table = byId.get();
            table.setTitle(tableDto.getTitle());
            table.setContent(tableDto.getContent());
            Table save = tableRepository.save(table);
            return ResponseEntity.status(200).body("Table Changed");
        } else {
            Table table = new Table();
            table.setTitle(tableDto.getTitle());
            table.setContent(tableDto.getContent());
            return ResponseEntity.status(201).body("Bunday idli table yo'q ekan. Yangi qushildi.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTags(@PathVariable Integer id) {
        Optional<Table> byId = tableRepository.findById(id);
        if (byId.isPresent()) {
            tableRepository.deleteById(id);
        }
        return ResponseEntity.status(409).body("Deleted");
    }
}
