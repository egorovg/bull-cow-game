package ru.example.bullcowgame.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.example.bullcowgame.service.GameService;
import ru.example.bullcowgame.service.UserService;

@Controller
public class MainController {

    @Autowired
    private GameService gameService;
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String root(Model model) {
        gameService.generateNumber();
        model.addAttribute("numberValue", gameService.getNumber());
        model.addAttribute("results", gameService.results());
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public @ResponseBody
    ResponseEntity addNumber(@RequestBody String inputNumber, Authentication authentication) throws JsonProcessingException {
        return gameService.addNumber(inputNumber, authentication);
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/newGame")
    public String newGame(Authentication authentication, Model model) {
        gameService.newGameInitializer(authentication);
        model.addAttribute("results", gameService.results());
        model.addAttribute("numberValue", gameService.getNumber());
        return "index";
    }
}


