package com.example.route53.service;

import com.example.route53.utils.SubDomainBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.route53.model.ChangeAction;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubDomainServiceImpl implements SubDomainService {

    private final SubDomainBuilder subDomainBuilder;

    @Override
    public boolean createARecord(String hostedZoneId, String subDomain, String ip) {
        return subDomainBuilder.operationARecord(hostedZoneId, subDomain, ip, ChangeAction.CREATE);
    }

    @Override
    public boolean createCnameRecord(String hostedZoneId, String subDomain, String cname) {
        return subDomainBuilder.operationCnameRecord(hostedZoneId, subDomain, cname, ChangeAction.CREATE);
    }

    @Override
    public boolean deleteARecord(String hostedZoneId, String subDomain, String ip) {
        return subDomainBuilder.operationARecord(hostedZoneId, subDomain, ip, ChangeAction.DELETE);
    }

    @Override
    public boolean deleteCnameRecord(String hostedZoneId, String subDomain, String cname) {
        return subDomainBuilder.operationCnameRecord(hostedZoneId, subDomain, cname, ChangeAction.DELETE);
    }
}
