package com.gentics.cailun.core.data.service.content;

import io.vertx.ext.apex.RoutingContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.graphdb.GraphDatabaseService;
import org.springframework.data.neo4j.support.Neo4jTemplate;

import com.gentics.cailun.core.data.service.ContentService;
import com.gentics.cailun.core.data.service.I18NService;
import com.gentics.cailun.core.data.service.LanguageService;
import com.gentics.cailun.core.data.service.TagService;
import com.gentics.cailun.core.data.service.UserService;
import com.gentics.cailun.core.rest.common.response.AbstractRestModel;
import com.gentics.cailun.etc.CaiLunSpringConfiguration;

public class TransformationInfo {

	private RoutingContext routingContext;

	private UserService userService;
	private LanguageService languageService;
	private GraphDatabaseService graphDb;
	private TagService tagService;
	private Neo4jTemplate neo4jTemplate;
	private CaiLunSpringConfiguration springConfiguration;
	private ContentService contentService;
	private I18NService i18nService;

	private int maxDepth;

	private List<String> languageTags = new ArrayList<>();

	private Map<String, AbstractRestModel> objectReferences = new HashMap<>();

	public TransformationInfo(RoutingContext rc, int maxDepth, List<String> languageTags) {
		this.routingContext = rc;
		this.maxDepth = maxDepth;
		this.languageTags = languageTags;
	}

	public Map<String, AbstractRestModel> getObjectReferences() {
		return objectReferences;
	}

	public int getMaxDepth() {
		return maxDepth;
	}

	public AbstractRestModel getObject(String uuid) {
		return objectReferences.get(uuid);
	}

	public void addObject(String uuid, AbstractRestModel object) {
		objectReferences.put(uuid, object);
	}

	public List<String> getLanguageTags() {
		return languageTags;
	}

	public LanguageService getLanguageService() {
		return languageService;
	}

	public void setLanguageService(LanguageService languageService) {
		this.languageService = languageService;
	}

	public CaiLunSpringConfiguration getSpringConfiguration() {
		return springConfiguration;
	}

	public void setSpringConfiguration(CaiLunSpringConfiguration springConfiguration) {
		this.springConfiguration = springConfiguration;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public GraphDatabaseService getGraphDb() {
		return graphDb;
	}

	public void setGraphDb(GraphDatabaseService graphDb) {
		this.graphDb = graphDb;
	}

	public Neo4jTemplate getNeo4jTemplate() {
		return neo4jTemplate;
	}

	public void setNeo4jTemplate(Neo4jTemplate neo4jTemplate) {
		this.neo4jTemplate = neo4jTemplate;
	}

	public ContentService getContentService() {
		return contentService;
	}

	public void setContentService(ContentService contentService) {
		this.contentService = contentService;
	}

	public RoutingContext getRoutingContext() {
		return routingContext;
	}

	public void setRoutingContext(RoutingContext routingContext) {
		this.routingContext = routingContext;
	}

	public void setTagService(TagService tagService) {
		this.tagService = tagService;
	}

	public TagService getTagService() {
		return tagService;
	}

	public I18NService getI18n() {
		return i18nService;
	}

	public void setI18nService(I18NService i18nService) {
		this.i18nService = i18nService;
	}

}
