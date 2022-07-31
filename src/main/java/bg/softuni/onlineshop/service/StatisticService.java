package bg.softuni.onlineshop.service;

import bg.softuni.onlineshop.model.entity.StatisticEntity;
import bg.softuni.onlineshop.model.view.StatisticViewModel;

import java.util.List;

public interface StatisticService {
    StatisticEntity saveVisitationInDataBase(String ip);
    List<StatisticViewModel> getStatistic();

    void dropTable();
}
