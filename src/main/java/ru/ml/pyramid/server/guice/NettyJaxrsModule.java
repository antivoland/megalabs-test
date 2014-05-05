package ru.ml.pyramid.server.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import org.jboss.resteasy.plugins.server.netty.NettyJaxrsServer;
import org.jboss.resteasy.spi.ResteasyDeployment;
import ru.ml.pyramid.server.App;
import ru.ml.pyramid.server.res.WeightRes;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NettyJaxrsModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("server-port")).toInstance(App.config.getInt("server.port"));
    }

    @Provides
    @Singleton
    NettyJaxrsServer provideServer(ResteasyDeployment deployment, @Named("server-port") int port) {
        NettyJaxrsServer server = new NettyJaxrsServer();
        server.setDeployment(deployment);
        server.setPort(port);
        return server;
    }

    @Provides
    @Singleton
    ResteasyDeployment provideDeployment() {
        ResteasyDeployment deployment = new ResteasyDeployment();
        deployment.getActualResourceClasses().add(WeightRes.class);

        deployment.getProviders().add(new ExceptionMapper<ClientErrorException>() {
            @Override
            public Response toResponse(ClientErrorException e) {
                return e.getResponse();
            }
        });
        return deployment;
    }
}
