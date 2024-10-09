package com.example.route53.utils;

import com.example.route53.config.Route53Config;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.route53.model.*;


@Slf4j
@Service
@RequiredArgsConstructor
public class SubDomainBuilder {

    private final Route53Config route53Config;

    public void createARecord(String hostedZoneId, String subDomain, String ip) {
        val recordSet = aRecordSet(subDomain, ip);
        val changeBatch = changeBatch(recordSet, ChangeAction.CREATE);
        val request = setRequest(hostedZoneId, changeBatch);
        actionRoute53(request);
    }

    public void createCnameRecord(String hostedZoneId, String subDomain, String cname) {
        val recordSet = cnameRecordSet(subDomain, cname);
        val changeBatch = changeBatch(recordSet, ChangeAction.CREATE);
        val request = setRequest(hostedZoneId, changeBatch);
        actionRoute53(request);
    }

    private ResourceRecordSet aRecordSet(String subDomain, String ip) {
        val ipBuilder = ResourceRecord.builder().value(ip).build();
        return ResourceRecordSet.builder()
                .name(subDomain)
                .type(RRType.A)
                .ttl(600L)
                .resourceRecords(ipBuilder)
                .build();
    }

    private ResourceRecordSet cnameRecordSet(String subDomain, String cname) {
        val cnameBuilder = ResourceRecord.builder().value(cname).build();
        return ResourceRecordSet.builder()
                .name(subDomain)
                .type(RRType.CNAME)
                .ttl(600L)
                .resourceRecords(cnameBuilder)
                .build();
    }

    private ChangeBatch changeBatch(ResourceRecordSet recordSet, ChangeAction changeAction) {
        val changeBuilder = Change.builder()
                .action(changeAction)
                .resourceRecordSet(recordSet)
                .build();
        return ChangeBatch.builder()
                .changes(changeBuilder)
                .build();
    }

    private ChangeResourceRecordSetsRequest setRequest(String hostedZoneId, ChangeBatch changeBatch) {
        return ChangeResourceRecordSetsRequest.builder()
                .hostedZoneId(hostedZoneId)
                .changeBatch(changeBatch)
                .build();
    }

    private void actionRoute53(ChangeResourceRecordSetsRequest request) {
        route53Config.route53Client().changeResourceRecordSets(request);
    }
}
