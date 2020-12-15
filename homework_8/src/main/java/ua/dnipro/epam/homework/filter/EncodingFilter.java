package ua.dnipro.epam.homework.filter;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class EncodingFilter implements Filter {
    public static final Logger LOG = Logger.getLogger(EncodingFilter.class);

    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOG.debug("Filter initialization starts");
        encoding = "UTF-8";
        LOG.debug("Filter initialization finished");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {


        HttpServletRequest httpRequest = (HttpServletRequest) req;
        LOG.trace("Request uri --> " + httpRequest.getRequestURI());

        String requestEncoding = req.getCharacterEncoding();

        if (requestEncoding == null) {
            LOG.trace("Request encoding = null, set encoding --> " + encoding);
            req.setCharacterEncoding(encoding);
        }

        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        LOG.debug("Filter destruction starts");

        LOG.debug("Filter destruction finished");
    }
}
