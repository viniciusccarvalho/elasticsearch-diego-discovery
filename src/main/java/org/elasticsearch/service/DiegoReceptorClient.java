package org.elasticsearch.service;

import io.pivotal.receptor.client.ReceptorClient;
import io.pivotal.receptor.commands.ActualLRPResponse;
import io.pivotal.receptor.commands.DesiredLRPResponse;

import java.util.List;

/**
 * Created by vcarvalho on 4/9/15.
 */
public class DiegoReceptorClient {

    private final ReceptorClient client;

    public DiegoReceptorClient(){
        this.client = new ReceptorClient("");
    }

    public List<ActualLRPResponse> getActualLRPs() {
        return client.getActualLRPs();
    }

    public List<ActualLRPResponse> getActualLRPsByDomain(String domain) {
        return client.getActualLRPsByDomain(domain);
    }

    public List<ActualLRPResponse> getActualLRPsByProcessGuid(String processGuid) {
        return client.getActualLRPsByProcessGuid(processGuid);
    }

    public ActualLRPResponse getActualLRPByProcessGuidAndIndex(String processGuid, int index) {
        return client.getActualLRPByProcessGuidAndIndex(processGuid, index);
    }

    public DesiredLRPResponse getDesiredLRP(String processGuid) {
        return client.getDesiredLRP(processGuid);
    }

    public List<DesiredLRPResponse> getDesiredLRPs() {
        return client.getDesiredLRPs();
    }

    public List<DesiredLRPResponse> getDesiredLRPsByDomain(String domain) {
        return client.getDesiredLRPsByDomain(domain);
    }

    public String[] getDomains() {
        return client.getDomains();
    }


}
