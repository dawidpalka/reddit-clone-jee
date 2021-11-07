package pl.dawidpalka.readstack.domain.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class DiscoveryBasicInfo {
    private String title;
    private String url;
    private String description;
    private LocalDateTime dateAdded;
}
