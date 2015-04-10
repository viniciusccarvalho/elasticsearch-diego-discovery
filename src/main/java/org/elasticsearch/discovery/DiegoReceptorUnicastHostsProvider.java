package org.elasticsearch.discovery;

import io.pivotal.receptor.commands.ActualLRPResponse;
import io.pivotal.receptor.commands.DesiredLRPResponse;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.network.NetworkService;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.discovery.zen.ping.unicast.UnicastHostsProvider;
import org.elasticsearch.service.DiegoReceptorClient;
import org.elasticsearch.transport.TransportService;

import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vcarvalho on 4/9/15.
 */
public class DiegoReceptorUnicastHostsProvider extends AbstractComponent implements UnicastHostsProvider {

    private final DiegoReceptorClient client;
    private final TransportService transportService;
    private final NetworkService networkService;

    @Inject
    public DiegoReceptorUnicastHostsProvider(DiegoReceptorClient client, Settings settings, TransportService transportService, NetworkService networkService){
        super(settings);
        this.transportService = transportService;
        this.client = client;
        this.networkService = networkService;

    }


    @Override
    public List<DiscoveryNode> buildDynamicNodes() {

        String processGuid = System.getenv("PROCESS_GUID");


        List<ActualLRPResponse> lrps = client.getActualLRPsByProcessGuid(processGuid);
        List<DiscoveryNode> nodes = new ArrayList<DiscoveryNode>(lrps.size());


        String ipAddress = "";
        try {
            InetAddress inetAddress = networkService.resolvePublishHostAddress(null);
            if (inetAddress != null) {
                ipAddress = inetAddress.getHostAddress();
            }
        } catch (IOException e) {
        }


        for(ActualLRPResponse lrp : lrps){
            
        }


        return nodes;
    }
}
