package com.example.route53.service;

public interface SubDomainService {
    boolean createARecord(String hostedZoneId, String subDomain, String ip);
    boolean createCnameRecord(String hostedZoneId, String subDomain, String cname);
    boolean deleteARecord(String hostedZoneId, String subDomain, String ip);
    boolean deleteCnameRecord(String hostedZoneId, String subDomain, String cname);
}
