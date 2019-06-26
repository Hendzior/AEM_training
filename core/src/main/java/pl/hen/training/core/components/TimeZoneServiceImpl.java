package pl.hen.training.core.components;


import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by pmyslinski on 2019-06-24.
 */

@Component
@Service
public class TimeZoneServiceImpl implements TimeZoneService {

    public String getCurrentTimeForTimezone(String timezone) {

        if(timezone==null){
            timezone="Europe/Berlin";
        }
        ZoneId id = ZoneId.of(timezone);
        ZonedDateTime today = ZonedDateTime.now(id);

        return DateTimeFormatter
                .ofPattern("HH:mm")
                .format(today);
    }

}
