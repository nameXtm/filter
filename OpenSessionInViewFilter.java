package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.sql.SQLException;
@WebFilter("*.do")
public class OpenSessionInViewFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    try {
        //开启事务（取消事务的提交 ）
        TransactionManger.beninTrans();
        filterChain.doFilter(servletRequest,servletResponse);
        //提交事务
        TransactionManger.commit();
    }catch (Exception e){
        e.printStackTrace();
        try {
            //回滚事务
            TransactionManger.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
