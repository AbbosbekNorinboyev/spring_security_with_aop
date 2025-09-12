package uz.brb.spring_security_with_aop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.brb.spring_security_with_aop.entity.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
}