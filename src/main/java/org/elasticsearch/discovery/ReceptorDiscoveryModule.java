package org.elasticsearch.discovery;

import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.discovery.zen.ZenDiscoveryModule;

/**
 * Created by vcarvalho on 4/9/15.
 */
public class ReceptorDiscoveryModule extends ZenDiscoveryModule{

    @Inject
    public ReceptorDiscoveryModule(Settings settings){
        if(settings.getAsBoolean("diego.enabled",true)){
            addUnicastHostProvider(DiegoReceptorUnicastHostsProvider.class);
        }
    }

    @Override
    protected void bindDiscovery() {
        bind(Discovery.class).to(ReceptorDiscovery.class).asEagerSingleton();
    }
}
