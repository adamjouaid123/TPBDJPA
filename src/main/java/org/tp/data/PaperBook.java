package org.tp.data;

import jakarta.persistence.*;

@Entity
// ToDo: instancier la valeur du discriminant pour la sous-classe PaperBook

public class PaperBook extends Book {
    private int pageCount;

    // Getters and Setters
    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }
}