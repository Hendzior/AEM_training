package pl.hen.training.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.jcr.query.Query;
import java.util.*;

/**
 * Created by pmyslinski on 2019-07-01.
 */
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ArticleListModel {

    @Self
    private Resource resource;


    public List<Resource> getArticles() {
        ResourceResolver resourceResolver = resource.getResourceResolver();
        Iterator<Resource> resources = resourceResolver.findResources("SELECT * FROM [cq:Page] AS s " +
                "WHERE ISDESCENDANTNODE([/content/hen-training/fr/en_fr]) " +
                "and [jcr:content/cq:template]='/apps/training/templates/article' ORDER BY [jcr:created] desc", Query.JCR_SQL2);

        Set<String> resourceSet = new HashSet<>();
        List<Resource> resourceList = new ArrayList<>();
        while (resources.hasNext()) {

            Resource next = resources.next();
            if (resourceSet.add(next.getName())) {
                resourceList.add(next);
            }
        }
        return resourceList;
    }
}
