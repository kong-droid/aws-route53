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

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/aws/route53")
@Tag(name = "Route53 Test", description = "서브 도메인 생성 테스트")
public class Route53Controller {

    private final SubDomainService subDomainService;

    @PostMapping
    @Operation(summary = "서브 도메인 등록")
    public void create(@Valid @RequestBody SubDomainDto subDomainDto){
        subDomainService.create(subDomainDto.getHostedZoneId(), subDomainDto.getSubDomain(),
                subDomainDto.getCname(), subDomainDto.getIp());
    }

}

