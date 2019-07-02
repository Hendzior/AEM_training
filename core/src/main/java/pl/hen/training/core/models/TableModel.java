package pl.hen.training.core.models;

import lombok.Data;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pmyslinski on 2019-06-21.
 */
@Data
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TableModel {

    @Inject
    private String tableRows;

    @Inject
    private String tableColumns;

    public List<String> getRowsList() {
        List<String> rowList = new ArrayList<String>();
        for (int i = 0; i < Integer.parseInt(tableRows); i++) {

            rowList.add(Integer.toString(i + 1));
        }
        return rowList;
    }

    public List<String> getColumnsList() {
        List<String> columsList = new ArrayList<String>();
        for (int i = 0; i < Integer.parseInt(tableColumns); i++) {

            columsList.add(Integer.toString(i + 1));
        }
        return columsList;
    }

}
