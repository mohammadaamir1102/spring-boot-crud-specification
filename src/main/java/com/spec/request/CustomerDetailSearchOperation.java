package com.spec.request;

import lombok.Data;

@Data
public class CustomerDetailSearchOperation {
    private String firstName;
    private String searchOperation;
    private Long offset;
    private Long pageNumber;
}
