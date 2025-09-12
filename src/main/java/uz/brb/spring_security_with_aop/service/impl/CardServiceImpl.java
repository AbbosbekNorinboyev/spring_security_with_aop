package uz.brb.spring_security_with_aop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.brb.spring_security_with_aop.dto.request.CardRequest;
import uz.brb.spring_security_with_aop.dto.response.Response;
import uz.brb.spring_security_with_aop.entity.Card;
import uz.brb.spring_security_with_aop.mapper.CardMapper;
import uz.brb.spring_security_with_aop.repository.CardRepository;
import uz.brb.spring_security_with_aop.service.CardService;

import java.time.LocalDateTime;

import static uz.brb.spring_security_with_aop.util.Util.localDateTimeFormatter;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;

    @Override
    public Response<?> createCard(CardRequest request) {
        Card card = cardMapper.toEntity(request);
        cardRepository.save(card);
        return Response.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK)
                .message("Card successfully created")
                .timestamp(localDateTimeFormatter(LocalDateTime.now()))
                .build();
    }
}
