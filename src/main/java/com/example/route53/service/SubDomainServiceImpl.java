package com.example.route53.service;

import com.example.route53.utils.SubDomainBuilder;
import lombok.val;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubDomainServiceImpl implements SubDomainService {

    private final SubDomainBuilder subDomainBuilder;

    @Override
    public void createARecord(String hostedZoneId, String subDomain, String ip) {
        subDomainBuilder.createARecord(hostedZoneId, subDomain, ip);
    }

    @Override
    public void createCnameRecord(String hostedZoneId, String subDomain, String cname) {
        subDomainBuilder.createCnameRecord(hostedZoneId, subDomain, cname);
    }
}
