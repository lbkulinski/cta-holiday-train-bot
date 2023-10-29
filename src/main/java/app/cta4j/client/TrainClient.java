package app.cta4j.client;

import app.cta4j.model.FollowResponse;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface TrainClient {
    @GetExchange("/ttfollow.aspx")
    FollowResponse followTrain(@RequestParam("runnumber") int run);
}
