package com.example.route53.service;

import com.example.route53.utils.SubDomainBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubDomainServiceImpl implements SubDomainService {

    private final SubDomainBuilder subDomainBuilder;

    @Override
    public boolean createARecord(String hostedZoneId, String subDomain, String ip) {
        return subDomainBuilder.createARecord(hostedZoneId, subDomain, ip);
    }

    @Override
    public boolean createCnameRecord(String hostedZoneId, String subDomain, String cname) {
        return subDomainBuilder.createCnameRecord(hostedZoneId, subDomain, cname);
    }

    @Override
    public boolean deleteARecord(String hostedZoneId, String subDomain, String ip) {
        return subDomainBuilder.deleteARecord(hostedZoneId, subDomain, ip);
    }

    @Override
    public boolean deleteCnameRecord(String hostedZoneId, String subDomain, String cname) {
        return subDomainBuilder.deleteCnameRecord(hostedZoneId, subDomain, cname);
    }
}
