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
public class SubDomainBuilder extends SubDomainBuildSupporter {

    private final Route53Config route53Config;

    public boolean operationARecord(String hostedZoneId, String subDomain, String ip, ChangeAction changeAction) {
        val recordSet = aRecordSet(subDomain, ip);
        val changeBatch = changeBatch(recordSet, changeAction);
        val request = setRequest(hostedZoneId, changeBatch);
        return actionRoute53(request);
    }

    public boolean operationCnameRecord(String hostedZoneId, String subDomain, String cname, ChangeAction changeAction) {
        val recordSet = cnameRecordSet(subDomain, cname);
        val changeBatch = changeBatch(recordSet, changeAction);
        val request = setRequest(hostedZoneId, changeBatch);
        return actionRoute53(request);
    }

    private boolean actionRoute53(ChangeResourceRecordSetsRequest request) {
        try {
            return route53Config
                    .route53Client()
                    .changeResourceRecordSets(request)
                    .sdkHttpResponse()
                    .isSuccessful();
        } catch (Exception e) {
            log.info("route53 error. message: {}", e.getMessage());
        }
        return false;
    }
}
