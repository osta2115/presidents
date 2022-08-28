package com.presidents.controller;

import com.presidents.model.dto.PresidentDto;
import com.presidents.model.entity.President;
import com.presidents.service.president.PresidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("presidents")
public class PresidentsController {

    private final PresidentService presidentService;
    @GetMapping("all")
    public List<PresidentDto> getAll() {
        return presidentService.getAllPresidents();
    }

    @PostMapping("save")
    public PresidentDto save(@RequestBody PresidentDto presidentDto) {
        return presidentService.savePresident(presidentDto);
    }

    @PutMapping("update")
    public PresidentDto update(@RequestBody PresidentDto presidentDto) {
        return presidentService.updatePresident(presidentDto);
    }

    @PatchMapping("update")
    public PresidentDto updatePartial(@RequestBody PresidentDto presidentDto) {
        return presidentService.updatePresidentPartial(presidentDto);
    }
//
//    @DeleteMapping("delete/{id}")
//    public String deleteById(@PathVariable int id) {
//        PresidentsDB.presidentRepository.remove(id);
//        log.info("President deleted!");
//        return "President deleted!";
//    }
}
