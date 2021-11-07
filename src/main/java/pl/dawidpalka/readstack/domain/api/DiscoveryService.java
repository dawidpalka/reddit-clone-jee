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

    private DiscoveryBasicInfo map(Discovery d) {
        return new DiscoveryBasicInfo(
                d.getTitle(),
                d.getUrl(),
                d.getDescription(),
                d.getDateAdded()
        );
    }
}
