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
@WebFilter(filterName = "ConsumersRealmFilter",
        urlPatterns = {"/consumersrealm/*", "/consumerarea/*"})
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
        String username = (String) request.getSession().getAttribute("username");
        if (role != null && role.equals("consumer")) {
            chain.doFilter(request, response);
        } else if (role != null) {
            response.sendError(401, "Only consumers can came here. You have the role of " + role);
        } else {
            response.sendError(401, "Only consumers can came here.");
        }

    }

    @Override
    public void destroy() {

    }

}
