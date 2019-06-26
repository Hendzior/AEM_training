package pl.hen.training.core.models;

import com.day.cq.tagging.TagManager;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import javax.inject.Inject;
import javax.jcr.query.Query;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by pmyslinski on 2019-06-24.
 */
@Slf4j
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ResourceFindModel {

    @Inject
    private String tag;

    @Self
    private Resource resource;

    private String query;

    public List<Resource> getResourcesByType() {
        ResourceResolver resourceResolver = resource.getResourceResolver();
        query = "SELECT * FROM [nt:base] AS s WHERE CONTAINS" +
                "([cq:tags],'" + tag + "')";

        Set<Resource> resources =
                Sets.newHashSet(resourceResolver.findResources(query, Query.JCR_SQL2));
        ArrayList<Resource> resources1 = Lists.newArrayList(resources);

        Set<String> set = new LinkedHashSet<>();
        List<Resource> resources2 = resources1.stream()
                .filter(res -> {
                    set.contains(res.getPath());
                    if (set.contains(res.getPath())) {
                        return false;
                    } else {
                        set.add(res.getPath());
                        return true;
                    }
                })
                .collect(Collectors.toList());

        return resources2;
    }


    public List<Resource> getAllResourcesByTagName() {
        ResourceResolver resourceResolver = resource.getResourceResolver();
        log.info("tag name is {}", tag);
        if (tag == null) {
            return new ArrayList<Resource>();
        }
        TagManager tagManager = resourceResolver.adaptTo(TagManager.class);
        return Lists.newArrayList(tagManager.find(tag));
    }

    public String getLogging() {
        return "tag name is " + tag + "querry is : " + query;
    }

}
