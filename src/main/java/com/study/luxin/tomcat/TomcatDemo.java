package com.study.luxin.tomcat;

import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.ContextConfig;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class TomcatDemo {

    public static void main(String[] args) throws Exception {
        File fileDir = new File("/Users/luxin/IdeaProjects/my-study/src/main/webapp");

        Tomcat tomcat = getTomcatInstance();

        StandardContext context = (StandardContext) tomcat.addWebapp("", fileDir.getAbsolutePath());

        tomcat.setPort(8080);
        tomcat.start();
        tomcat.getServer().await();
    }

    private static Tomcat getTomcatInstance() {
        return new Tomcat() {
            public Context addWebapp(Host host, String url, String name, String path) {
                Context ctx = new StandardContext();
                ctx.setName(name);
                ctx.setPath(url);
                ctx.setDocBase(path);

                ContextConfig ctxCfg = new ContextConfig();
                ctx.addLifecycleListener(ctxCfg);

                ctxCfg.setDefaultWebXml("org/apache/catalin/startup/NO_DEFAULT_XML");

                if (host == null) {
                    getHost().addChild(ctx);
                } else {
                    host.addChild(ctx);
                }
                return ctx;
            }
        };
    }

}
