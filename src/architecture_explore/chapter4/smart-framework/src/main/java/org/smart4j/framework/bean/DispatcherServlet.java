package org.smart4j.framework.bean;

import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.helper.HelperLoader;
import org.smart4j.framework.util.JsonUtil;
import org.smart4j.framework.util.ReflectionUtil;
import org.smart4j.framework.util.StringUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();

        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerClass);

            Map<String, Object> paramMap = this.getParamMap(req);
            Param param = new Param(paramMap);
            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            if (result instanceof View) {
                handleView(req, res, (View)result);
            } else if (result instanceof Data) {
                handleData(req, res, (Data)result);
            } else {
                throw new RuntimeException("unsupported action result type");
            }
        }
    }

    private void handleView(HttpServletRequest req, HttpServletResponse res, View v) throws IOException, ServletException {
        String path = v.getPath();
        if (StringUtil.isNotEmpty(path)){
            if (path.startsWith("/")){
                res.sendRedirect(req.getContextPath() + path);
            }else {
                Map<String, Object> model = v.getModel();
                model.forEach((key, value) ->  req.setAttribute(key, value));
                req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, res);
            }
        }
    }

    private void handleData(HttpServletRequest req, HttpServletResponse res, Data data) throws IOException {
        Object model = data.getModel();
        if (model != null){
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            PrintWriter writer = res.getWriter();
            String json = JsonUtil.toJson(model);
            writer.write(json);
            writer.flush();
            writer.close();
        }
    }


    private Map<String, Object> getParamMap(HttpServletRequest req) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        Enumeration<String> paramNames = req.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = paramNames.nextElement();
            String paramValue = req.getParameter(paramName);
            paramMap.put(paramName, paramValue);
        }

        return paramMap;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        HelperLoader.init();
        ServletContext servletContext = config.getServletContext();

        //tomcat 内置的jsp servlet处理
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");

        //tomcat 内置的default servlet处理
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }
}
