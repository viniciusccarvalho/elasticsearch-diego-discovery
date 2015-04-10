package org.elasticsearch.plugins.discovery.diego;

import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.common.logging.ESLogger;
import org.elasticsearch.common.logging.Loggers;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.modules.diego.ReceptorModule;
import org.elasticsearch.plugins.AbstractPlugin;

import java.util.Collection;

/**
 * Created by vcarvalho on 4/9/15.
 */
public class ReceptorDiscoveryPlugin extends AbstractPlugin{

    private final Settings settings;
    private final ESLogger logger = Loggers.getLogger(ReceptorDiscoveryPlugin.class);

    public ReceptorDiscoveryPlugin(Settings settings){
        this.settings = settings;
    }

    @Override
    public String name() {
        return "diego-discovery";
    }

    @Override
    public String description() {
        return "Diego receptor discovery plugin";
    }

    @Override
    public Collection<Class<? extends Module>> modules() {
        Collection<Class<? extends Module>> modules = Lists.newArrayList();
        if(settings.getAsBoolean("diego",true)){
            modules.add(ReceptorModule.class);
        }
        return super.modules();
    }
}
