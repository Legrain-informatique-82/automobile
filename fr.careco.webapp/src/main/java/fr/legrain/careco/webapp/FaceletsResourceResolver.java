package fr.legrain.careco.webapp;

import java.net.URL;

import javax.faces.view.facelets.ResourceResolver;

/**
 * http://stackoverflow.com/questions/6199458/how-to-create-a-modular-jsf-2-0-application/6201044#6201044
 * 
 * Note: since Servlet 3.0 and newer JBoss/JSF 2.0 versions, the whole ResourceResolver approach is not necessary 
 * if you keep the files in /META-INF/resources folder. The above ResourceResolver is only mandatory in Servlet 2.5 or 
 * older JBoss/JSF versions because they've bugs in META-INF resource resolving.
 * 
 * +++ Dans web.xml
 * <context-param>
 *   <param-name>javax.faces.FACELETS_RESOURCE_RESOLVER</param-name>
 *   <param-value>fr.legrain.careco.webapp.FaceletsResourceResolver</param-value>
 * </context-param>
 *
 */
public class FaceletsResourceResolver extends ResourceResolver {

    private ResourceResolver parent;
    private String basePath;

    public FaceletsResourceResolver(ResourceResolver parent) {
        this.parent = parent;
        this.basePath = "/META-INF/resources"; // TODO: Make configureable?
    }

    @Override
    public URL resolveUrl(String path) {
        URL url = parent.resolveUrl(path); // Resolves from WAR.

        if (url == null) {
            url = getClass().getResource(basePath + path); // Resolves from JAR.
        }

        return url;
    }

}