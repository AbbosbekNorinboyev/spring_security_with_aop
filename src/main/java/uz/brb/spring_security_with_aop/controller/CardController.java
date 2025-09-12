package uz.brb.spring_security_with_aop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
