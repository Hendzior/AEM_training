package pl.hen.training.core.models;

import com.day.cq.commons.LanguageUtil;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by pmyslinski on 2019-06-18.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BreadCrumbModel {

    @Self
    private Resource resource;

    public Page getCurrentPage() {
        ResourceResolver resourceResolver = resource.getResourceResolver();
        PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
        return pageManager.getContainingPage(resource);
    }

    public List<Page> getAllParents() {

        return getParentPages(getCurrentPage());
    }

    private List<Page> getParentPages(Page page) {

        List<Page> parentList = new ArrayList<Page>();
        while (!page.getParent().getPath().equals(LanguageUtil.getLanguageRoot(resource.getPath()))) {
            parentList.add(page.getParent());
            page = page.getParent();

        }
        Collections.reverse(parentList);
        return parentList;
    }

}
