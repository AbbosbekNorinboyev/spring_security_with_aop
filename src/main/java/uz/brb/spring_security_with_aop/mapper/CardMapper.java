package uz.brb.spring_security_with_aop.mapper;

import org.springframework.stereotype.Component;
import uz.brb.spring_security_with_aop.dto.request.CardRequest;
import uz.brb.spring_security_with_aop.dto.response.CardResponse;
import uz.brb.spring_security_with_aop.entity.Card;

import java.util.ArrayList;
import java.util.List;

@Component
public class CardMapper {
    public Card toEntity(CardRequest request) {
        return Card.builder()
                .cardName(request.getCardName())
                .expiry(request.getExpiry())
                .build();
    }

    public CardResponse toResponse(Card card) {
        return CardResponse.builder()
                .id(card.getId())
                .cardName(card.getCardName())
                .expiry(card.getExpiry())
                .build();
    }

    public List<CardResponse> responseList(List<Card> cards) {
        if (cards != null && !cards.isEmpty()) {
            return cards.stream().map(this::toResponse).toList();
        }
        return new ArrayList<>();
    }
}
