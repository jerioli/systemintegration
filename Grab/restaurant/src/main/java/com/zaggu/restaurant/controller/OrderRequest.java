package com.zaggu.restaurant.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class OrderRequest {
    @JsonProperty("customerId")
    private Long customerId;
    @JsonProperty("restaurantId")
    private Long restaurant_id;
    @JsonProperty("menuItemIds")
    private List<Long> menuItemIds;
}