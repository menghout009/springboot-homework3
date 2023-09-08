package com.example.spring_homework003.model.entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Books {

    private Integer book_id;
    private String title;
    private Timestamp published_date;
    private Author author;
    List<Category>  category = new ArrayList<>();
}
