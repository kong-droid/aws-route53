package com.example.route53.utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import software.amazon.awssdk.services.route53.Route53Client;
import software.amazon.awssdk.services.route53.model.*;

@Slf4j
@RequiredArgsConstructor
public class SubDomainBuilder {

    private final Route53Client route53Client;

    public SubDomainBuilder() {
        this.route53Client = Route53Client.builder().build();
    }
    public void createARecord(String hostedZoneId, String subDomain, String ip) {
        val recordSet = ResourceRecordSet.builder()
                .name(subDomain)
                .type(RRType.A)
                .ttl(600L)
                .resourceRecords(
                        ResourceRecord.builder()
                                .value(ip)
                                .build()
                )
                .build();

        val changeBatch = ChangeBatch.builder()
                .changes(Change.builder()
                        .action(ChangeAction.CREATE)
                        .resourceRecordSet(recordSet)
                        .build())
                .build();


        val request = ChangeResourceRecordSetsRequest.builder()
                .hostedZoneId(hostedZoneId)
                .changeBatch(changeBatch)
                .build();

        route53Client.changeResourceRecordSets(request);
    }


    public void createCnameRecord(String hostedZoneId, String subDomain, String cname) {
        val recordSet = ResourceRecordSet.builder()
                .name(subDomain)
                .type(RRType.CNAME)
                .ttl(600L)
                .resourceRecords(
                        ResourceRecord.builder()
                                .value(cname)
                                .build()
                )
                .build();

        val changeBatch = ChangeBatch.builder()
                .changes(Change.builder()
                        .action(ChangeAction.CREATE)
                        .resourceRecordSet(recordSet)
                        .build())
                .build();


        val request = ChangeResourceRecordSetsRequest.builder()
                .hostedZoneId(hostedZoneId)
                .changeBatch(changeBatch)
                .build();

        route53Client.changeResourceRecordSets(request);
    }

}
