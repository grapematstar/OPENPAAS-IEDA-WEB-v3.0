package org.openpaas.ieda.deploy.web.deploy.cf.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

public class CfParamDTO {
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Default{
        private String id; //id
        @NotNull
        private String iaas; //IaaS
        @NotNull
        private String diegoYn;//diego 사용 유무
        
        // 1.1 Deployment 정보
        @NotNull
        private String deploymentName; //배포명
        @NotNull
        private String directorUuid; //설치관리자 UUID
        @NotNull
        private String releaseName; //릴리즈명
        @NotNull
        private String releaseVersion; //릴리즈 버전
        
        private String loggregatorReleaseName;
        private String loggregatorReleaseVersion;
        
        private String appSshFingerprint; //SSH 핑거프린트
        @NotNull
        private String deaMemoryMB; //deaMemoryMB
        @NotNull
        private String deaDiskMB; //deaDiskMB
        
        // 1.2 기본정보
        @NotNull
        private String domain; //도메인
        @NotNull
        private String description; //도메인 설명
        @NotNull
        private String domainOrganization; //도메인 그룹
        @NotNull
        private String loginSecret; //로그인 비밀번호
        @NotNull
        private String paastaMonitoringUse;//PaaS-TA 모니터링 사용 유무
        private String ingestorIp;//PaaS-TA 모니터링 DB 서버 IP
        
        
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getIaas() {
            return iaas;
        }
        public void setIaas(String iaas) {
            this.iaas = iaas;
        }
        public String getDiegoYn() {
            return diegoYn;
        }
        public void setDiegoYn(String diegoYn) {
            this.diegoYn = diegoYn;
        }
        public String getDeploymentName() {
            return deploymentName;
        }
        public void setDeploymentName(String deploymentName) {
            this.deploymentName = deploymentName;
        }
        public String getDirectorUuid() {
            return directorUuid;
        }
        public void setDirectorUuid(String directorUuid) {
            this.directorUuid = directorUuid;
        }
        public String getReleaseName() {
            return releaseName;
        }
        public void setReleaseName(String releaseName) {
            this.releaseName = releaseName;
        }
        public String getReleaseVersion() {
            return releaseVersion;
        }
        public void setReleaseVersion(String releaseVersion) {
            this.releaseVersion = releaseVersion;
        }
        public String getLoggregatorReleaseName() {
            return loggregatorReleaseName;
        }
        public void setLoggregatorReleaseName(String loggregatorReleaseName) {
            this.loggregatorReleaseName = loggregatorReleaseName;
        }
        public String getLoggregatorReleaseVersion() {
            return loggregatorReleaseVersion;
        }
        public void setLoggregatorReleaseVersion(String loggregatorReleaseVersion) {
            this.loggregatorReleaseVersion = loggregatorReleaseVersion;
        }
        public String getAppSshFingerprint() {
            return appSshFingerprint;
        }
        public void setAppSshFingerprint(String appSshFingerprint) {
            this.appSshFingerprint = appSshFingerprint;
        }
        public String getDeaMemoryMB() {
            return deaMemoryMB;
        }
        public void setDeaMemoryMB(String deaMemoryMB) {
            this.deaMemoryMB = deaMemoryMB;
        }
        public String getDeaDiskMB() {
            return deaDiskMB;
        }
        public void setDeaDiskMB(String deaDiskMB) {
            this.deaDiskMB = deaDiskMB;
        }
        public String getDomain() {
            return domain;
        }
        public void setDomain(String domain) {
            this.domain = domain;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }
        public String getDomainOrganization() {
            return domainOrganization;
        }
        public void setDomainOrganization(String domainOrganization) {
            this.domainOrganization = domainOrganization;
        }
        public String getLoginSecret() {
            return loginSecret;
        }
        public void setLoginSecret(String loginSecret) {
            this.loginSecret = loginSecret;
        }
        public String getPaastaMonitoringUse() {
            return paastaMonitoringUse;
        }
        public void setPaastaMonitoringUse(String paastaMonitoringUse) {
            this.paastaMonitoringUse = paastaMonitoringUse;
        }
        public String getIngestorIp() {
            return ingestorIp;
        }
        public void setIngestorIp(String ingestorIp) {
            this.ingestorIp = ingestorIp;
        }
    }
    
    public static class Delete{
        @NotNull
        private String iaas; //IaaS
        @NotNull
        private String id; //id
        @NotNull
        private String platform;//플랫폼 유형
        
        public String getIaas() {
            return iaas;
        }
        public void setIaas(String iaas) {
            this.iaas = iaas;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getPlatform() {
            return platform;
        }
        public void setPlatform(String platform) {
            this.platform = platform;
        }
    }
    
    public static class Install{
        @NotNull
        private String iaas;//IaaS
        @NotNull
        private String id; //id
        @NotNull
        private String platform;//플랫폼 유형

        public String getIaas() {
            return iaas;
        }
        public void setIaas(String iaas) {
            this.iaas = iaas;
        }
        public String getId() {
            return id;
        }
        public void setId(String id) {
            this.id = id;
        }
        public String getPlatform() {
            return platform;
        }
        public void setPlatform(String platform) {
            this.platform = platform;
        }
        
    }
    
}