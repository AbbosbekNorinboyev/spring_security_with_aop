package uz.brb.spring_security_with_aop.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "card")
public class Card extends BaseEntity {
    private String cardName;
    private String expiry;
}
