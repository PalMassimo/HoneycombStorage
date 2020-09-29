package units.honeycombstorage.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author massi
 */

/*
    all uploaders resources are in paths /uploadersrealm/* or in /api/uploaderarea/*
    the filter check if the request has the right to get there, check the session role attribute
*/

@WebFilter(filterName = "UploadersRealmFilter",
        urlPatterns = {"/uploadersrealm/*", "/api/uploaderarea/*"})
public class UploadersRealmFilter implements Filter {

    public UploadersRealmFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String role = (String) request.getSession().getAttribute("role");
        String username = (String) request.getSession().getAttribute("username");
        if (role != null && role.equals("uploader")) {
            chain.doFilter(request, response);
        } else if (role != null) {
            response.sendError(401, "Only uploaders can come here. You have the role of " + role);
        } else {
            response.sendError(401, "Only uploaders can come here.");
        }

    }

    @Override
    public void destroy() {

    }

}
