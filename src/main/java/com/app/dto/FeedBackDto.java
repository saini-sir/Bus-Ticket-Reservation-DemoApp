package com.app.dto;

import com.app.model.Bus;
import com.app.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeedBackDto {
    private Integer feedbackId;
    private Integer driverRating;
    private Integer serviceRating;
    private Integer overallRating;
    private String comments;

    @JsonIgnore
    @OneToOne
    private User users;

    @JsonIgnore
    @OneToOne
    private Bus bus;
}
