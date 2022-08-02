package bg.softuni.onlineshop.service;

import bg.softuni.onlineshop.model.entity.IpStatsEntity;
import bg.softuni.onlineshop.model.view.IpStatsViewModel;

import java.util.List;

public interface IpStatsService {
    IpStatsEntity saveVisitationInDataBase(String ip);
    List<IpStatsViewModel> getStatistic();

    void dropTable();
}
