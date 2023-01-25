package ru.jankbyte.trafficpolice.controller.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;

import ru.jankbyte.trafficpolice.service.UserService;
import ru.jankbyte.trafficpolice.model.entity.auth.User;
import ru.jankbyte.trafficpolice.model.entity.auth.Roles;
import ru.jankbyte.trafficpolice.model.EntityCount;
import ru.jankbyte.trafficpolice.model.ResultPage;

import java.util.List;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/user",
    consumes = {MediaType.APPLICATION_JSON_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserRestController {
    private final UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }

    @PutMapping({"", "/"})
    public User save(@Valid @RequestBody User user) {
        return service.save(user);
    }

    @PostMapping("/{id}")
    public User update(@Valid @RequestBody User user,
            @PathVariable("id") Long id) {
        return service.update(id, user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removeById(
            @PathVariable("id") Long id) {
        service.removeById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public User getByName(
            @RequestParam("name") String name) {
        return service.getByName(name);
    }

    @GetMapping("/{id}")
    public User getById(@PathVariable("id") Long id) {
        return service.getById(id);
    }

    @GetMapping("/")
    public ResultPage<User> getAllByPagable(
            @RequestParam(defaultValue = "1") int page) {
        return service.getAll(page);
    }
}
