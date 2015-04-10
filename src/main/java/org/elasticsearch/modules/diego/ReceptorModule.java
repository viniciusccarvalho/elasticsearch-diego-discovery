package org.elasticsearch.modules.diego;

import org.elasticsearch.common.inject.AbstractModule;
import org.elasticsearch.service.DiegoReceptorClient;

/**
 * Created by vcarvalho on 4/9/15.
 */
public class ReceptorModule  extends AbstractModule{

    @Override
    protected void configure() {
        bind(DiegoReceptorClient.class).to(DiegoReceptorClient.class).asEagerSingleton();
    }
}
