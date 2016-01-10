package t01;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

@WebFilter(urlPatterns = "*")
public class MainFilter implements Filter {

    private static final String TYPE = "text/html";
    private static final String ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("filter start");
        System.out.println(((HttpServletRequest)servletRequest).getRequestURL());
        servletResponse.setContentType(TYPE);
        servletRequest.setCharacterEncoding(ENCODING);
        servletResponse.setCharacterEncoding(ENCODING);
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("filter end");
    }

}
