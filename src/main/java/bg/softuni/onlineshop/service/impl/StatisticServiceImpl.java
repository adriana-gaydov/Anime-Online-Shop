package bg.softuni.onlineshop.service.impl;

import bg.softuni.onlineshop.config.mapping.StatisticMapper;
import bg.softuni.onlineshop.model.entity.StatisticEntity;
import bg.softuni.onlineshop.model.view.StatisticViewModel;
import bg.softuni.onlineshop.repository.StatisticRepository;
import bg.softuni.onlineshop.service.StatisticService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticServiceImpl implements StatisticService {

    private final StatisticRepository statisticRepository;

    public StatisticServiceImpl(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    @Override
    public StatisticEntity saveVisitationInDataBase(String ip) {
        StatisticEntity statistic = new StatisticEntity();
        statistic.setIpAddress(ip);
        statistic.setLocalDateTime(LocalDateTime.now());
        return this.statisticRepository.saveAndFlush(statistic);
    }

    @Override
    public List<StatisticViewModel> getStatistic() {
        List<StatisticEntity> statisticList = this.statisticRepository.findAll();
        return statisticList
                .stream()
                .map(StatisticMapper.INSTANCE::entityToViewModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void dropTable() {
        this.statisticRepository.deleteAll();
    }

}
