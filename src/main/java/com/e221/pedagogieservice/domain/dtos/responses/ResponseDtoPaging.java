package com.e221.pedagogieservice.domain.dtos.responses;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDtoPaging <T> {
    private List<T> result;
    private int page;
    private long totalElements;
    private int totalPages;
}
