package pl.hen.training.core.models;


import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

import com.google.common.collect.Iterators;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pmyslinski on 2019-06-18.
 */
@Slf4j
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NavigationModel {

    @Self
    private Resource resource;

    private List pageList = new ArrayList();
//    @Inject
//    @Via("resourceResolver")
//    private PageManager pageManager;

    public Page getCurrentPage() {
        ResourceResolver resourceResolver = resource.getResourceResolver();
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        return pageManager.getContainingPage(resource);
    }

    public List<Page> getAllChildren() {
         return getChildPages(getCurrentPage(), pageList);
    }

    public List<Page> getChildPages(Page page, List<Page> pageList) {

        Iterators.addAll(pageList, page.listChildren());
        Iterator<Page> pageIterator = page.listChildren();

        while (pageIterator.hasNext()) {
            getChildPages(pageIterator.next(), pageList);
        }
        return pageList;
    }


}
