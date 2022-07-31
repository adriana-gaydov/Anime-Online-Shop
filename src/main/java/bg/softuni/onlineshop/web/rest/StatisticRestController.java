package bg.softuni.onlineshop.web.rest;

import bg.softuni.onlineshop.model.view.StatisticViewModel;
import bg.softuni.onlineshop.service.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/statistic")
public class StatisticRestController {

    private final StatisticService statisticService;

    public StatisticRestController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("")
    @Transactional
    public ResponseEntity<List<StatisticViewModel>> getStatistic() {
        List<StatisticViewModel> statisticViewModels = this.statisticService.getStatistic();
        return new ResponseEntity<>(statisticViewModels, HttpStatus.OK);
    }

}
