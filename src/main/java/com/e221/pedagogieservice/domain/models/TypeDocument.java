package com.e221.pedagogieservice.domain.models;

import lombok.Getter;

@Getter
public enum TypeDocument {
    A_ramener("À ramener"),
    A_retirer("À retirer");

    private final String label;

    TypeDocument(String label) {
        this.label = label;
    }

}
