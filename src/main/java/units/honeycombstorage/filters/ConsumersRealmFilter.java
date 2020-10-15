package units.honeycombstorage.filters;

import java.io.IOException;
import java.util.regex.Pattern;
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
    all consumers resources are in path consumersrealm/* and in /api/consumerarea/*
    In case of the consumer click on the download link of an uploader email, instead of
    redirect to login page if the session is expired, the filter redirect to a simple form
    that validate the credentials. To check if the request is a valid url regular expression
    are used.
    In all other case the filter limits to send error.
*/

@WebFilter(filterName = "ConsumersRealmFilter",
        urlPatterns = {"/consumersrealm/*", "/api/consumerarea/*"})
public class ConsumersRealmFilter implements Filter {

    public ConsumersRealmFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String role = (String) request.getSession().getAttribute("role");
        if (role != null && role.equals("consumer")) {
            chain.doFilter(request, response);
        } else if (role != null) {
            response.sendError(401, "Only consumers can came here. You have the role of " + role);
        } else {
            //check if the url is a possible valid download link
            Pattern pattern = null;
            if (pattern.matches("/api/consumerarea/file/[0-9]+/\\w+\\.\\w+", request.getRequestURI())) {

                String[] parameters = request.getRequestURI().split("/");
                request.setAttribute("id", parameters[4]);
                request.setAttribute("filename", parameters[5]);
                request.getRequestDispatcher("/quicklogin.jsp").forward(request, response);
            } else {
                response.sendError(401, "Only consumers can came here.");
            }
        }

    }

    @Override
    public void destroy() {

    }

}
