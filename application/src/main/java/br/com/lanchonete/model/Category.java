package br.com.lanchonete.model;

import java.util.UUID;

public class Category {

    private UUID id;
    private UUID catalogCategoryId;
    private String name;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getCatalogCategoryId() {
        return catalogCategoryId;
    }

    public void setCatalogCategoryId(UUID catalogCategoryId) {
        this.catalogCategoryId = catalogCategoryId;
    }
}
