package com.e221.pedagogieservice.runtime.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DefaultSpecification<T> {
    public Specification<T> isArchiveFalse() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.isFalse(root.get("archive"));
    }
}
