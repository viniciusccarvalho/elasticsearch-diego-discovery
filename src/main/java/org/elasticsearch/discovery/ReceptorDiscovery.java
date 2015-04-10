package org.elasticsearch.discovery;

import org.elasticsearch.Version;
import org.elasticsearch.cluster.ClusterName;
import org.elasticsearch.cluster.ClusterService;
import org.elasticsearch.cluster.node.DiscoveryNodeService;
import org.elasticsearch.cluster.settings.DynamicSettings;
import org.elasticsearch.common.collect.ImmutableList;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.network.NetworkService;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.discovery.zen.ZenDiscovery;
import org.elasticsearch.discovery.zen.elect.ElectMasterService;
import org.elasticsearch.discovery.zen.ping.ZenPing;
import org.elasticsearch.discovery.zen.ping.ZenPingService;
import org.elasticsearch.discovery.zen.ping.unicast.UnicastZenPing;
import org.elasticsearch.node.settings.NodeSettingsService;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;

/**
 * Created by vcarvalho on 4/9/15.
 */
public class ReceptorDiscovery extends ZenDiscovery {

    @Inject
    public ReceptorDiscovery(Settings settings, ClusterName clusterName, ThreadPool threadPool, TransportService transportService,
                        ClusterService clusterService, NodeSettingsService nodeSettingsService, ZenPingService pingService,
                        DiscoverySettings discoverySettings,DiscoveryNodeService discoveryNodeService,
                        ElectMasterService electMasterService, DynamicSettings dynamicSettings) {
        super(settings,clusterName,threadPool,transportService,clusterService,nodeSettingsService,discoveryNodeService,pingService,electMasterService,discoverySettings);
    }

}
