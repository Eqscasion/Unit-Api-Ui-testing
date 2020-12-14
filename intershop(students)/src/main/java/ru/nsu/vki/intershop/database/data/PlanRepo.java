package ru.nsu.vki.intershop.database.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanRepo {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("details")
    private String details;

    @JsonProperty("fee")
    private int fee;

    public UUID getId() {
        return id;
    }

    public PlanRepo setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PlanRepo setName(String name) {
        this.name = name;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public PlanRepo setDetails(String details) {
        this.details = details;
        return this;
    }

    public int getFee() {
        return fee;
    }

    public PlanRepo setFee(int fee) {
        this.fee = fee;
        return this;
    }

    @Override
    public boolean equals(Object obyekt) {
        if (this == obyekt) return true;
        if (obyekt == null || getClass() != obyekt.getClass()) return false;
        PlanRepo plan = (PlanRepo) obyekt;
        return id != null ? id.equals(plan.id) : plan.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public PlanRepo clone() {
        return new PlanRepo()
                .setId(id)
                .setName(name)
                .setFee(fee)
                .setDetails(details);
    }

    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", details='" + details + '\'' +
                ", fee=" + fee +
                '}';
    }
}
