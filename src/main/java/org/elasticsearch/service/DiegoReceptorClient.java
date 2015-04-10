package org.elasticsearch.service;

import io.pivotal.receptor.client.ReceptorClient;
import io.pivotal.receptor.commands.ActualLRPResponse;
import io.pivotal.receptor.commands.DesiredLRPResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;
import org.elasticsearch.common.settings.Settings;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by vcarvalho on 4/9/15.
 */
public class DiegoReceptorClient {

    private final ReceptorClient client;

    public DiegoReceptorClient(Settings settings){
        String receptorHost = System.getenv("RECEPTOR_HOST") == null ? settings.get("diego.receptor","http://receptor.192.168.1.11.xip.io") : System.getenv("RECEPTOR_HOST");

        if(settings.get("diego.credentials.user") != null){
            DefaultHttpClient httpClient = new DefaultHttpClient();
            BasicCredentialsProvider credentialsProvider =  new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(settings.get("diego.credentials.user"), settings.get("diego.credentials.pass")));
            httpClient.setCredentialsProvider(credentialsProvider);
            ClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory(httpClient);
            this.client = new ReceptorClient(receptorHost,rf);
        }else {
            this.client = new ReceptorClient(receptorHost);
        }

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
