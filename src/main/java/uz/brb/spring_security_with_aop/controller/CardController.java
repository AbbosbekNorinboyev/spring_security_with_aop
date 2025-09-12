package uz.brb.spring_security_with_aop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.brb.spring_security_with_aop.dto.request.CardRequest;
import uz.brb.spring_security_with_aop.dto.response.Response;
import uz.brb.spring_security_with_aop.service.CardService;

@RestController
@RequestMapping("/api/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public Response<?> createCard(@RequestBody CardRequest request) {
        return cardService.createCard(request);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/get")
    public Response<?> getCard(@RequestParam Long id) {
        return cardService.getCard(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    @GetMapping("/getAll")
    public Response<?> getAllCard() {
        return cardService.getAllCard();
    }
}
