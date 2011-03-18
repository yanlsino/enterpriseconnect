package org.osforce.e2.task.support;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 
 * @author gavin
 * @since 1.0.0
 * @create Mar 2, 2011 - 10:10:41 AM
 *  <a href="http://www.opensourceforce.org">开源力量</a>
 */
public abstract class FreemarkerUtil {

	public static String render(Configuration cfg, String name, Map<Object, Object> model) throws TemplateException, IOException {
		Template template = cfg.getTemplate(name);
		return render(template, model);
	}
	
	public static String render(Template template, Map<Object, Object> model) throws TemplateException, IOException {
		Writer out = new StringWriter();
		template.process(model, out);
		return out.toString();
	}
}
