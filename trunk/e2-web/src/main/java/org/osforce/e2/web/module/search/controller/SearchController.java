package org.osforce.e2.web.module.search.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Feb 18, 2011 - 10:30:07 PM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/search")
public class SearchController {

	private EntityManager entityManager;
	
	public SearchController() {
		
	}
	
	@PersistenceContext
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	@RequestMapping(value="index-sync")
	public String sync() {
		Search.getFullTextEntityManager(entityManager).createIndexer().start();
		return "redirect:/system";
	}
}
