package com.sgk.domain;

import lombok.Data;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by gebczyks on 2016-10-11.
 */
@Entity
@Data
public class Employee {
    private String firstName;
    private String secondName;
    private String department;
    private BigDecimal amount;
}
