package org.vanduong.online_food_ordering_system.dto;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Data
@Embeddable
@Document(indexName = "restaurantdto")
public class RestaurantDto {

    private String title;

    @Column(length = 1000)
    private List<String> imgs;

    private String description;

    private Long id;



}
