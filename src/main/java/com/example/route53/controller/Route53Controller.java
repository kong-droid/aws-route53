package com.example.route53.controller;


import com.example.route53.dto.SubDomainDto;
import com.example.route53.service.SubDomainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.Callable;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/aws/route53")
@Tag(name = "Route53 Test", description = "서브 도메인 생성 테스트")
public class Route53Controller {

    private final SubDomainService subDomainService;

    @PostMapping("/a")
    @Operation(summary = "ip로 서브 도메인 생성")
    public Callable<Boolean> create(@Valid @RequestBody SubDomainDto.ARecordDto recordDto) {
        return () -> subDomainService.createARecord(recordDto.getHostedZoneId(), recordDto.getSubDomain(),
                    recordDto.getIp());
    }

    @PostMapping("/cname")
    @Operation(summary = "cname으로 서브도메인 등록")
    public Callable<Boolean> create(@Valid @RequestBody SubDomainDto.CnameRecordDto cnameRecordDto) {
        return () -> subDomainService.createCnameRecord(cnameRecordDto.getHostedZoneId(), cnameRecordDto.getSubDomain(),
                cnameRecordDto.getCname());
    }

    @DeleteMapping("/a")
    @Operation(summary = "ip로 서브 도메인 삭제")
    public Callable<Boolean> delete(@Valid @RequestBody SubDomainDto.ARecordDto recordDto) {
        return () -> subDomainService.deleteARecord(recordDto.getHostedZoneId(), recordDto.getSubDomain(),
                recordDto.getIp());
    }

    @DeleteMapping("/cname")
    @Operation(summary = "cname으로 서브도메인 삭제")
    public Callable<Boolean> delete(@Valid @RequestBody SubDomainDto.CnameRecordDto cnameRecordDto){
        return () -> subDomainService.deleteCnameRecord(cnameRecordDto.getHostedZoneId(), cnameRecordDto.getSubDomain(),
                cnameRecordDto.getCname());
    }
}

