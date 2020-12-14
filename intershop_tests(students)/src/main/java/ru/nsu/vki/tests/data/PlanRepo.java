package ru.nsu.vki.tests.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PlanRepo {
    @JsonProperty("id")
    public UUID id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("details")
    public String details;

    @JsonProperty("fee")
    public int fee;
}
