package pl.dawidpalka.readstack.domain.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryFullInfo {
    private Integer id;
    private String name;
    private String description;
}
