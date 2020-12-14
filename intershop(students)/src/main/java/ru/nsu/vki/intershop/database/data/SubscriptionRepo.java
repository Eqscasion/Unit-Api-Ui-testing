package ru.nsu.vki.intershop.database.data;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubscriptionRepo {
    @JsonProperty("id")
    private UUID id;

    @JsonProperty("customerId")
    private UUID customerId;

    @JsonProperty("planId")
    private UUID planId;

    public UUID getId() {
        return id;
    }

    public SubscriptionRepo setId(UUID id) {
        this.id = id;
        return this;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public SubscriptionRepo setCustomerId(UUID customerId) {
        this.customerId = customerId;
        return this;
    }

    public UUID getPlanId() {
        return planId;
    }

    public SubscriptionRepo setPlanId(UUID planId) {
        this.planId = planId;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SubscriptionRepo that = (SubscriptionRepo) obj;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public SubscriptionRepo clone() {
        return new SubscriptionRepo()
                .setId(id)
                .setCustomerId(customerId)
                .setPlanId(planId);
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", planId=" + planId +
                '}';
    }
}
