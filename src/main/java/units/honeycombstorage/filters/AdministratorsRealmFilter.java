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

// all administrator resources are or in path administratorsrealm/* or in /api/administratorarea/*
// this filter check if any request can get this resource, verifying if role in session are equals to 'administrator'


@WebFilter(filterName = "AdministatorsRealmFilter",
        urlPatterns = {"/administratorsrealm/*", "/api/administratorarea/*"})
public class AdministratorsRealmFilter implements Filter {

    public AdministratorsRealmFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String role = (String) request.getSession().getAttribute("role");
        if (role != null && role.equals("administrator")) {
            chain.doFilter(request, response);
        } else if (role != null) {
            response.sendError(401, "Only administrators can come here. You have the role of " + role);
        } else {
            response.sendError(401, "You are not a user");
        }

    }

    @Override
    public void destroy() {

    }

}
