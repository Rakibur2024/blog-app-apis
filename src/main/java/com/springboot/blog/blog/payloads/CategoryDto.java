package com.springboot.blog.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CategoryDto {
    private Integer categoryId;

    @NotEmpty
    @Size(min = 1,max = 100, message = "Minimum 4 characters")
    private String categoryTitle;

    @Size(min = 10,max = 200, message = "10-200 characters")
    private String categoryDescription;
}
