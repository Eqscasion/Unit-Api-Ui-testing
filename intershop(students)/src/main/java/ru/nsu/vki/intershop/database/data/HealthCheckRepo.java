package ru.nsu.vki.intershop.database.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HealthCheckRepo {
    public HealthCheckRepo() {
        status = "Everything works";
    }

    @JsonProperty("status")
    public String status;
}