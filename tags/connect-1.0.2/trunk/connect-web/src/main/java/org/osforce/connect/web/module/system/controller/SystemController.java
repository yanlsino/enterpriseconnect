package org.osforce.connect.web.module.system.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Jan 29, 2011 - 11:18:51 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
@Controller
@RequestMapping("/system")
public class SystemController {

	private EntityManager entityManager;
	
	public SystemController() {
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
