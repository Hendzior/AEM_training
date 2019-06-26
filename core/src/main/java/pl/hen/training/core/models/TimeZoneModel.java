package pl.hen.training.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.Self;
import pl.hen.training.core.components.TimeZoneService;


import javax.inject.Inject;

/**
 * Created by hen on 2019-06-12.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TimeZoneModel {
    @Self
    private Resource resource;

    @OSGiService
    private TimeZoneService TimeZoneService;

    @Inject
    private String timezone;

    public String getCurrentTimeForTimezone() {

        return TimeZoneService.getCurrentTimeForTimezone(timezone);
    }
}

