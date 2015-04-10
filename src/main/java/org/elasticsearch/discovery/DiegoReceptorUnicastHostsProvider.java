package org.elasticsearch.discovery;

import io.pivotal.receptor.commands.ActualLRPResponse;
import io.pivotal.receptor.commands.DesiredLRPResponse;
import io.pivotal.receptor.support.Port;
import org.elasticsearch.Version;
import org.elasticsearch.cluster.node.DiscoveryNode;
import org.elasticsearch.common.component.AbstractComponent;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.network.NetworkService;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
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
    private final Version version;

    private int serverPort;

    @Inject
    public DiegoReceptorUnicastHostsProvider(Version version, Settings settings, TransportService transportService, NetworkService networkService){
        super(settings);
        this.transportService = transportService;
        this.client = new DiegoReceptorClient(settings);
        this.networkService = networkService;
        this.serverPort = settings.getAsInt("transport.tcp.port",9300);
        this.version = version;
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
        logger.debug("This node ip address{} ", ipAddress);

        for(ActualLRPResponse lrp : lrps){
            try {
                TransportAddress[] transportAddresses = transportService.addressesFromString(lrp.getAddress()+":"+findPort(lrp.getPorts()).getHostPort());
                nodes.add(new DiscoveryNode("elasticsearch-"+lrp.getInstanceGuid(),transportAddresses[0],version.minimumCompatibilityVersion()));
            } catch (Exception e) {
                logger.error("Could not create transport address for lrp: {}", lrp);
            }

        }

        logger.debug("Found nodes: {}", nodes);

        return nodes;
    }

    private Port findPort(Port[] ports){

        for(Port port : ports){
            if(port.getContainerPort() == this.serverPort)
                return port;
        }
        return null;
    }
}
