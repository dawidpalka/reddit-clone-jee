package pl.dawidpalka.readstack.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Category {
    private Integer id;
    private String name;
    private String description;
}
