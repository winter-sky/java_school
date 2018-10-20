package mainpackage.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

        @Override
        protected Class<?>[] getRootConfigClasses() {
            return null;
        }

        @Override
        protected Class<?>[] getServletConfigClasses() {

            return new Class[] {PersistenceJPAConfig.class};
        }

        @Override
        protected String[] getServletMappings() {
            return new String[] {"/"};
        }
    }

