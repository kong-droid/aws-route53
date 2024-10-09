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

        route53Config.route53Client().changeResourceRecordSets(request);
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

        route53Config.route53Client().changeResourceRecordSets(request);
    }

}
