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
//
//    @PatchMapping("update")
//    public String updatePartial(@RequestBody President president) {
//        President p = PresidentsDB.presidentRepository.get(president.getId().intValue());
//        if (president.getName() != null) {
//            p.setName(president.getName());
//            log.info("President with id: {} - Name updated!", president.getId());
//        }
//        if (president.getSurname() != null) {
//            p.setSurname(president.getSurname());
//            log.info("President with id: {} - Surname updated!", president.getId());
//        }
//        if (president.getTermFrom() != null) {
//            p.setTermFrom(president.getTermFrom());
//            log.info("President with id: {} - TermFrom updated!", president.getId());
//        }
//        if (president.getTermTo() != null) {
//            p.setTermTo(president.getTermTo());
//            log.info("President with id: {} - TermTo updated!", president.getId());
//        }
//        if (president.getPoliticalParty() != null) {
//            p.setPoliticalParty(president.getPoliticalParty());
//            log.info("President with id: {} - Political party updated!", president.getId());
//        }
//        return "President updated!";
//    }
//
//    @DeleteMapping("delete/{id}")
//    public String deleteById(@PathVariable int id) {
//        PresidentsDB.presidentRepository.remove(id);
//        log.info("President deleted!");
//        return "President deleted!";
//    }
}
