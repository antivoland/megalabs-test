package ru.ml.pyramid.server.guice;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import ru.ml.pyramid.server.App;
import ru.ml.pyramid.server.model.Pyramid;
import ru.ml.pyramid.server.model.PyramidImpl;

public class PyramidModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Integer.class).annotatedWith(Names.named("pyramid-height")).toInstance(App.config.getInt("pyramid.height"));
        bind(Float.class).annotatedWith(Names.named("thing-weight")).toInstance(App.config.getFloat("thing.weight"));
        bind(Pyramid.class).to(PyramidImpl.class);
    }
}
