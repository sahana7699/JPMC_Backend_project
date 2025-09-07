package com.jpmc.midascore.api;

import com.jpmc.midascore.entity.UserRecord;
import com.jpmc.midascore.repository.UserRepository;
import com.jpmc.midascore.foundation.Balance; // <-- uses the provided Balance class
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BalanceController {

    private final UserRepository userRepository;

    public BalanceController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/balance")
    public Balance getBalance(@RequestParam("userId") long userId) {
        // Your UserRepository (as given) returns entity or null:
        UserRecord user = userRepository.findById(userId);

        float amount = (user == null) ? 0f : user.getBalance();

        // The provided Balance class should be returned as-is.
        // If Balance has a constructor Balance(float amount), this line is correct:
        return new Balance(amount);

        // If (and only if) your Balance class has a no-args constructor + setter,
        // use this instead:
        // Balance b = new Balance();
        // b.setAmount(amount);
        // return b;
    }
}
