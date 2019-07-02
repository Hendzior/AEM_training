package pl.hen.training.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pmyslinski on 2019-06-26.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultiCompModel {

    @Inject
    private String repeat;

    public List<Integer> getRepeatList(){
        List<Integer> repeatList = new ArrayList<>();
        for (int i = 0; i < Integer.parseInt(repeat) ; i++) {
            repeatList.add(i+1);
        }
        return repeatList;
    }
    public String getLogging() {
        return "Repeat number is:  " +repeat;
    }
}
