package pl.dawidpalka.readstack.domain.api;

import pl.dawidpalka.readstack.domain.discovery.Discovery;
import pl.dawidpalka.readstack.domain.discovery.DiscoveryDao;

import java.util.List;
import java.util.stream.Collectors;

public class DiscoveryService {
    private final DiscoveryDao discoveryDao = new DiscoveryDao();

    public List<DiscoveryBasicInfo> findAll() {
        return discoveryDao.findAll()
                .stream().map(this::map)
                .collect(Collectors.toList());
    }

    public List<DiscoveryBasicInfo> findByCategory(int categoryId) {
        return discoveryDao.findByCategory(categoryId)
                .stream().map(this::map)
                .collect(Collectors.toList());
    }

    private DiscoveryBasicInfo map(Discovery discovery) {
        return new DiscoveryBasicInfo(
                discovery.getTitle(),
                discovery.getUrl(),
                discovery.getDescription(),
                discovery.getDateAdded()
        );
    }
}
