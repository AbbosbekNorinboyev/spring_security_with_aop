package uz.brb.spring_security_with_aop.service;

import uz.brb.spring_security_with_aop.dto.request.CardRequest;
import uz.brb.spring_security_with_aop.dto.response.Response;

public interface CardService {
    Response<?> createCard(CardRequest request);
}
