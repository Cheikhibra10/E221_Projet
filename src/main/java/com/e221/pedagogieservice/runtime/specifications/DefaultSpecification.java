package com.e221.pedagogieservice.runtime.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public class DefaultSpecification<T> {
    public Specification<T> isArchiveFalse() {
        return (root, query, cb) -> {
            try {
                root.get("archive"); // check if field exists
                return cb.isFalse(root.get("archive"));
            } catch (IllegalArgumentException e) {
                return cb.conjunction(); // no filter if archive doesn't exist
            }
        };
    }

}
