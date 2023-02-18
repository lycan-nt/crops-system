package com.owl.systems.crops.builder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventSearchCriterios {
    private Integer typeEvent;
    private Date fromDate;
    private Date toDate;
    private Pageable pageable;
}
