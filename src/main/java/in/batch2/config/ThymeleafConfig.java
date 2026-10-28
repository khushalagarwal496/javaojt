package in.batch2.config;

import in.batch2.utility.HibernateUtil;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.WebApplicationTemplateResolver;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.Map;

@WebListener
public class ThymeleafConfig implements ServletContextListener {

    private static JakartaServletWebApplication application;
    private static TemplateEngine templateEngine;
    private static JakartaServletWebApplication jakartaServletWebApplication;

    @Override
    public void contextInitialized(ServletContextEvent sce){
        application = JakartaServletWebApplication
                .buildApplication(sce.getServletContext());
        WebApplicationTemplateResolver resolver = new WebApplicationTemplateResolver(application);
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setSuffix(".html");
        resolver.setPrefix("/WEB-INF/templates/");
        resolver.setCharacterEncoding("UTF-8");

        templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(resolver);

        HibernateUtil.getSessionFactory();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce){
        HibernateUtil.getSessionFactory().close();
    }

    public static void render(HttpServletRequest req,
                              HttpServletResponse res,
                              Map<String,Object> variables,
                              String templateName)
        throws ServletException, IOException {
        IWebExchange exchange =
                application.buildExchange(req, res);
        WebContext context = new WebContext(exchange,res.getLocale());
        context.setVariables(variables);
        templateEngine.process(templateName , context , res.getWriter());
    }

}
