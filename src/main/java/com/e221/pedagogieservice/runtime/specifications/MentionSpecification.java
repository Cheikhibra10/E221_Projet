package com.e221.pedagogieservice.runtime.specifications;

import com.e221.pedagogieservice.domain.models.Mention;
import org.springframework.data.jpa.domain.Specification;

public class MentionSpecification {
    public static Specification<Mention> hasClient(Long domaineId) {
        return (root, query, criteriaBuilder) -> {
            if (domaineId == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("domaine").get("id"), domaineId);
        };
    }
}
