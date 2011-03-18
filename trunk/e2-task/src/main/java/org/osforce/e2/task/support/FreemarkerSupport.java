package org.osforce.e2.task.support;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public abstract class FreemarkerSupport {

	protected Configuration configuration;
	
	public FreemarkerSupport() {
	}
	
	public Configuration getConfiguration() {
		return configuration;
	}
	
	@Autowired
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	protected String render(String name, Map<Object, Object> model) throws IOException, TemplateException {
		Template template = configuration.getTemplate(name);
		return render(template, model);	
	}
	
	protected String render(Template template, Map<Object, Object> model) throws TemplateException, IOException {
		Writer out = new StringWriter();
		template.process(model, out);
		return out.toString();
	}
}
