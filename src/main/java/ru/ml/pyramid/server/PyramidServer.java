package ru.ml.pyramid.server;

import com.google.inject.Guice;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import ru.ml.pyramid.server.guice.NettyJaxrsModule;
import ru.ml.pyramid.server.guice.PyramidModule;

public class PyramidServer {
    public static void main(String[] args) throws Exception {
        App.config = new PropertiesConfiguration("conf/server.properties");
        App.injector = Guice.createInjector(new NettyJaxrsModule(), new PyramidModule());
        App.injector.getInstance(NettyJaxrsServer.class).start();
    }
}
