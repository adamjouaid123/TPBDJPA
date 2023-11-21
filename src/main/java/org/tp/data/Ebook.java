package org.tp.data;

import jakarta.persistence.*;

@Entity
// ToDo: instancier la valeur du discriminant pour la sous-classe Ebook

public class Ebook extends Book {
    private String format;

    // Getters and Setters
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}