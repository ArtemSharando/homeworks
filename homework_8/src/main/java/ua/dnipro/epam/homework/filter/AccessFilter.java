package ua.dnipro.epam.homework.filter;

import ua.dnipro.epam.homework.entity.RoleName;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/testAdmin", "/createTest", "/deleteTest", "/userAdmin"})
public class AccessFilter implements Filter {
    private Logger log = Logger.getLogger(AccessFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Filter initialization starts");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String role = (String) httpRequest.getSession().getAttribute("userRole");
        if (role != null && role.equals(RoleName.ADMIN.getName())) {
            filterChain.doFilter(request, response);
        } else {
            httpResponse.sendRedirect("accessDenied.jsp");
            log.trace("access denied");
        }

    }

    @Override
    public void destroy() {
        log.debug("Filter destruction starts");


        log.debug("Filter destruction finished");
    }
}
