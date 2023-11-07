package com.SalesFlowSA.SalesFlow.model.DTO;

import jakarta.persistence.*;

public class SupplierDTO {

    private String name;
    private String countryOfOrigin;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }
}
