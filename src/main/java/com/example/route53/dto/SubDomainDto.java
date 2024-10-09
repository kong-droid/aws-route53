package com.example.route53.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

public class SubDomainDto {

    @Getter
    @Setter
    public static class ARecordDto {

        @NotNull
        @Schema(description = "zoneId")
        private String hostedZoneId;

        @NotNull
        @Schema(description = "서브 도메인", example = "sub.example.com")
        private String subDomain;

        @NotNull
        @Schema(description = "ip", example = "127.0.0.1")
        private String ip;

    }

    @Getter
    @Setter
    public static class CnameRecordDto {

        @NotNull
        @Schema(description = "zoneId")
        private String hostedZoneId;

        @NotNull
        @Schema(description = "서브 도메인", example = "sub.example.com")
        private String subDomain;

        @NotNull
        @Schema(description = "도메인", example = "example.com")
        private String cname;

    }
}


