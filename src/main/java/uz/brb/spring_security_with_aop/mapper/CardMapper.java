package uz.brb.spring_security_with_aop.mapper;

import org.springframework.stereotype.Component;
import uz.brb.spring_security_with_aop.dto.request.CardRequest;
import uz.brb.spring_security_with_aop.entity.Card;

@Component
public class CardMapper {
    public Card toEntity(CardRequest request) {
        return Card.builder()
                .cardName(request.getCardName())
                .expiry(request.getExpiry())
                .build();
    }
}
