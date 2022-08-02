package bg.softuni.onlineshop.service.impl;

import bg.softuni.onlineshop.config.mapping.StatisticMapper;
import bg.softuni.onlineshop.model.entity.IpStatsEntity;
import bg.softuni.onlineshop.model.view.IpStatsViewModel;
import bg.softuni.onlineshop.repository.IpStatsRepository;
import bg.softuni.onlineshop.service.IpStatsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IpStatsServiceImpl implements IpStatsService {

    private final IpStatsRepository ipStatsRepository;

    public IpStatsServiceImpl(IpStatsRepository ipStatsRepository) {
        this.ipStatsRepository = ipStatsRepository;
    }

    @Override
    public IpStatsEntity saveVisitationInDataBase(String ip) {
        IpStatsEntity statistic = new IpStatsEntity();
        statistic.setIpAddress(ip);
        statistic.setLocalDateTime(LocalDateTime.now());
        return this.ipStatsRepository.saveAndFlush(statistic);
    }

    @Override
    public List<IpStatsViewModel> getStatistic() {
        List<IpStatsEntity> statisticList = this.ipStatsRepository.findAll();
        return statisticList
                .stream()
                .map(StatisticMapper.INSTANCE::entityToViewModel)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void dropTable() {
        this.ipStatsRepository.deleteAll();
    }

}
