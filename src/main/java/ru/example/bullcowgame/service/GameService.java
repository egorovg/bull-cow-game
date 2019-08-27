package ru.example.bullcowgame.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.example.bullcowgame.entity.User;
import ru.example.bullcowgame.model.NumberInputDTO;
import ru.example.bullcowgame.model.NumberOutputDTO;
import ru.example.bullcowgame.model.UsersResultsDTO;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

@Service
public class GameService {

    private static final String errorMessage = "Enter a 4-digit number!";
    private static final String winGame = "(You win!)";
    private static final String format = "#0.00";

    @Autowired
    private UserService userService;

    private ArrayList<Integer> number;

    private ArrayList<Integer> getArrayDigits() {
        return number;
    }

    public void generateNumber() {
        number = new ArrayList<>();
        Random r = new Random();
        Set<Integer> s = new HashSet<>();
        while (s.size() <= 4) {
            s.add(r.nextInt(9));
        }
        number.addAll(s);
        Collections.shuffle(number);
    }

    public String checkNumber(ArrayList<Integer> userNumber, User user) {
        String result;
        user.setCountAttempt(user.getCountAttempt() + 1);
        int countBulls = 0;
        int countCows = 0;
        for (int i = 0; i < 4; i++) {
            for (int o = 3; o >= 0; o--) {
                if (number.get(o).intValue() == userNumber.get(i).intValue()) countCows++;
            }
        }
        for (int k = 0; k < 4; k++) {
            if (userNumber.get(k).intValue() == number.get(k).intValue()) {
                countBulls++;
                countCows--;
            }
        }
        if (countBulls == 4) {
            result = countBulls + "B" + countCows + "C" + winGame;
        } else result = countBulls + "B" + countCows + "C";
        userService.save(user);
        return result;
    }

    public ResponseEntity addNumber(String payload, Authentication authentication) throws JsonProcessingException {
        String result = "";
        NumberInputDTO numberInputDTO;
        try {
            numberInputDTO = new ObjectMapper().readValue(payload, NumberInputDTO.class);
        } catch (IOException e) {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(new NumberOutputDTO(errorMessage)));
        }
        StringBuilder s = new StringBuilder(Integer.toString(numberInputDTO.getNumber()));
        if (s.length() > 4 || s.length() < 3) {
            return ResponseEntity.ok(new ObjectMapper().writeValueAsString(new NumberOutputDTO(errorMessage)));
        }
        if (s.length() == 3) s.insert(0, '0');
        char[] charArray = s.toString().toCharArray();
        ArrayList<Integer> cia = new ArrayList<>();
        for (int i = 0; i < charArray.length; i++) {
            int c = Character.getNumericValue(charArray[i]);
            cia.add(c);
        }
        if (getArrayDigits() != null) {
            result = s + "--" + checkNumber(cia, userService.findByUsername(authentication.getName()));
        }
        NumberOutputDTO numberOutputDTO = new NumberOutputDTO(result);
        return ResponseEntity.ok(new ObjectMapper().writeValueAsString(numberOutputDTO));
    }

    public List<UsersResultsDTO> results() {
        List<UsersResultsDTO> results = new ArrayList<>();
        if (!userService.findAll().isEmpty()) {
            for (User user : userService.findAll()) {
                if (user.getCountAttempt() != 0 && user.getCountGames() != 0) {
                    String avg = new DecimalFormat(format).format(user.getCountAttempt() * 1.0 / user.getCountGames());
                    results.add(new UsersResultsDTO(user.getUsername(), avg));
                }
            }
        }
        return results;
    }

    public String getNumber() {
        return getArrayDigits().get(0).toString() + getArrayDigits().get(1).toString() + getArrayDigits().get(2).toString() + getArrayDigits().get(3).toString();
    }

    public void newGameInitialize(Authentication authentication) {
        generateNumber();
        User user = userService.findByUsername(authentication.getName());
        user.setCountGames(user.getCountGames() + 1);
        userService.save(user);
    }
}
