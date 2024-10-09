package com.example.route53.service;

import com.example.route53.utils.SubDomainBuilder;

public interface SubDomainService {
    void createARecord(String hostedZoneId, String subDomain, String ip);
    void createCnameRecord(String hostedZoneId, String subDomain, String cname);
}
