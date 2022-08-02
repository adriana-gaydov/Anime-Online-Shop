package bg.softuni.onlineshop.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "ip_stats")
public class IpStatsEntity extends BaseEntity {

    private LocalDateTime localDateTime;
    private String ipAddress;

    public IpStatsEntity() {
    }

    @Column(nullable = false, name = "local_date_time")
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Column(nullable = false)
    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

}
