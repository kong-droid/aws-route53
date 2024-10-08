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
    @Override
    public void createARecord(SubDomainBuilder builder, String hostedZoneId, String subDomain, String ip) {
        builder.createARecord(hostedZoneId, subDomain, ip);
    }

    @Override
    public void createCnameRecord(SubDomainBuilder builder, String hostedZoneId, String cname, String ip) {
        builder.createCnameRecord(hostedZoneId, cname, ip);
    }

    @Override
    public void create(String hostedZoneId, String subDomain, String cname, String ip) {
        val builder = new SubDomainBuilder();
        this.createARecord(builder, hostedZoneId, subDomain, ip);
        this.createCnameRecord(builder, hostedZoneId, cname, ip);
    }
}
