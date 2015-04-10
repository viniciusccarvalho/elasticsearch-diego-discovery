package org.elasticsearch.plugins.diego;

import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.discovery.ReceptorDiscoveryModule;
import org.elasticsearch.plugins.AbstractPlugin;

import java.util.Collection;

/**
 * Created by vcarvalho on 4/10/15.
 */
public class DiegoDiscoveryPlugin extends AbstractPlugin {


    private final Settings settings;

    public DiegoDiscoveryPlugin(Settings settings){
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



}
