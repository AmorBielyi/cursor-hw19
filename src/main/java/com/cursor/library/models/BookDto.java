package com.cursor.library.models;

import lombok.*;

import java.util.List;

/**
 * Used for book creation in some particular method.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BookDto {

    private String name;
    private String description;
    private List<String > authors;
    private int yearOfPublication;
    private int numberOfWords;
    private int rating;
}
