package app.cta4j.service;

import app.cta4j.client.cta4j.api.TrainApi;
import app.cta4j.client.cta4j.invoker.ApiException;
import app.cta4j.client.cta4j.model.Train;
import com.rollbar.notifier.Rollbar;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
public final class StatusService {
    private final TrainApi trainApi;

    private final int run;

    private final Rollbar rollbar;

    private static final Logger LOGGER;

    static {
        LOGGER = LoggerFactory.getLogger(StatusService.class);
    }

    @Autowired
    public StatusService(TrainApi trainApi, @Value("${cta.run}") int run, Rollbar rollbar) {
        this.trainApi = Objects.requireNonNull(trainApi);

        this.run = run;

        this.rollbar = Objects.requireNonNull(rollbar);
    }

    private Train getNextTrain() {
        List<Train> trains;

        try {
            trains = this.trainApi.getUpcomingStations(this.run);
        } catch (ApiException e) {
            int code = e.getCode();

            if (code == HttpStatus.SC_NOT_FOUND) {
                return null;
            }

            this.rollbar.error(e);

            String message = e.getMessage();

            StatusService.LOGGER.error(message, e);

            return null;
        } catch (Exception e) {
            this.rollbar.error(e);

            String message = e.getMessage();

            StatusService.LOGGER.error(message, e);

            return null;
        }

        if ((trains == null) || trains.isEmpty()) {
            return null;
        }

        Comparator<Train> comparator = Comparator.comparing(Train::getArrivalTime);

        List<Train> sortedTrains = new ArrayList<>(trains);

        sortedTrains.sort(comparator);

        return sortedTrains.getFirst();
    }

    public String getStatus() {
        Train train = this.getNextTrain();

        if (train == null) {
            return null;
        }

        String destination = train.getDestination();

        Train.LineEnum line = train.getLine();

        String lineString = switch (line) {
            case BLUE -> "Blue";
            case BROWN -> "Brown";
            case GREEN -> "Green";
            case ORANGE -> "Orange";
            case PINK -> "Pink";
            case PURPLE -> "Purple";
            case RED -> "Red";
            case YELLOW -> "Yellow";
            default -> {
                String message = "Unexpected line: %s".formatted(line);

                this.rollbar.error(message);

                StatusService.LOGGER.error(message);

                yield null;
            }
        };

        if (lineString == null) {
            return null;
        }

        lineString += " Line";

        int run = train.getRun();

        String station = train.getStation();

        ZoneId zoneId = ZoneId.of("America/Chicago");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");

        String arrivalTime = train.getArrivalTime()
                                  .toInstant()
                                  .atZone(zoneId)
                                  .toLocalDateTime()
                                  .format(formatter);

        return "%s-bound %s Run %d will be arriving at %s at %s".formatted(destination, lineString, run, station,
            arrivalTime);
    }
}
