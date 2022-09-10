package com.presidents.controller;

import com.presidents.model.dto.PresidentDto;
import com.presidents.service.president.PresidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

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

    @GetMapping("/all-paginated")
    public List<PresidentDto> getPresidentPaginated(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        return presidentService.getAllPresidentsPaginated(pageNumber, pageSize).getContent();
    }

    @GetMapping("find/{name}")
    public Set<PresidentDto> findPresidentsByName(@PathVariable String name) {
        return presidentService.findPresidentsByName(name);
    }

    @GetMapping("find-by-party/{party}")
    public Set<PresidentDto> findPresidentsByPoliticalParty(@PathVariable String party) {
        return presidentService.findPresidentsByPoliticalParty(party);
    }

    @PostMapping("save")
    @ResponseStatus(HttpStatus.CREATED)
    public PresidentDto save(@Valid @RequestBody PresidentDto presidentDto) {
        return presidentService.savePresident(presidentDto);
    }

    @PutMapping("update")
    public PresidentDto update(@Valid @RequestBody PresidentDto presidentDto) {
        return presidentService.updatePresident(presidentDto);
    }

//    Wyłącznie w celach dydaktycznych
//    @ExceptionHandler({RuntimeException.class, IllegalAccessError.class})
//    public final ResponseEntity<Object> handleExceptions(Exception ex) {
//        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    @PatchMapping("update")
    public PresidentDto updatePartial(@RequestBody PresidentDto presidentDto) {
        return presidentService.updatePresidentPartial(presidentDto);
    }

    @DeleteMapping("delete/{id}")
    public void deleteById(@PathVariable Long id) {
        presidentService.deletePresident(id);
    }

}
