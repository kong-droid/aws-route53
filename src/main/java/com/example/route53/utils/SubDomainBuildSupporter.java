package com.example.route53.utils;

import lombok.val;
import software.amazon.awssdk.services.route53.model.*;

public abstract class SubDomainBuildSupporter {

    public ResourceRecordSet aRecordSet(String subDomain, String ip) {
        val ipBuilder = ResourceRecord.builder().value(ip).build();
        return ResourceRecordSet.builder()
                .name(subDomain)
                .type(RRType.A)
                .ttl(600L)
                .resourceRecords(ipBuilder)
                .build();
    }

    public ResourceRecordSet cnameRecordSet(String subDomain, String cname) {
        val cnameBuilder = ResourceRecord.builder().value(cname).build();
        return ResourceRecordSet.builder()
                .name(subDomain)
                .type(RRType.CNAME)
                .ttl(600L)
                .resourceRecords(cnameBuilder)
                .build();
    }

    public ChangeBatch changeBatch(ResourceRecordSet recordSet, ChangeAction changeAction) {
        val changeBuilder = Change.builder()
                .action(changeAction)
                .resourceRecordSet(recordSet)
                .build();
        return ChangeBatch.builder()
                .changes(changeBuilder)
                .build();
    }

    public ChangeResourceRecordSetsRequest setRequest(String hostedZoneId, ChangeBatch changeBatch) {
        return ChangeResourceRecordSetsRequest.builder()
                .hostedZoneId(hostedZoneId)
                .changeBatch(changeBatch)
                .build();
    }

}
