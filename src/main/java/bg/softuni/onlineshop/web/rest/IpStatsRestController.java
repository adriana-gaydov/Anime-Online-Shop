package bg.softuni.onlineshop.web.rest;

import bg.softuni.onlineshop.model.view.IpStatsViewModel;
import bg.softuni.onlineshop.service.IpStatsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api/statistic")
public class IpStatsRestController {

    private final IpStatsService ipStatsService;

    public IpStatsRestController(IpStatsService ipStatsService) {
        this.ipStatsService = ipStatsService;
    }

    @GetMapping("")
    @Transactional
    public ResponseEntity<List<IpStatsViewModel>> getStatistic() {
        List<IpStatsViewModel> ipStatsViewModels = this.ipStatsService.getStatistic();
        return new ResponseEntity<>(ipStatsViewModels, HttpStatus.OK);
    }

}
