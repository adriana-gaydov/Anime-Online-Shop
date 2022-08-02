package bg.softuni.onlineshop.model.view;

import java.time.LocalDateTime;

public class IpStatsViewModel {
    private LocalDateTime localDateTime;
    private String ipAddress;
    private Long id;

    public IpStatsViewModel() {
    }

    public IpStatsViewModel(LocalDateTime localDateTime, String ipAddress, Long id) {
        this.localDateTime = localDateTime;
        this.ipAddress = ipAddress;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
