package org.openpaas.ieda.deploy.web.deploy.cf.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.core.Application;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openpaas.ieda.common.exception.CommonException;
import org.openpaas.ieda.deploy.web.common.base.BaseDeployControllerUnitTest;
import org.openpaas.ieda.deploy.web.common.dao.CommonDeployDAO;
import org.openpaas.ieda.deploy.web.common.dao.ManifestTemplateVO;
import org.openpaas.ieda.deploy.web.common.dto.ReplaceItemDTO;
import org.openpaas.ieda.deploy.web.deploy.cf.dao.CfDAO;
import org.openpaas.ieda.deploy.web.deploy.cf.dao.CfVO;
import org.openpaas.ieda.deploy.web.deploy.cf.dto.CfListDTO;
import org.openpaas.ieda.deploy.web.deploy.cf.dto.CfParamDTO;
import org.openpaas.ieda.deploy.web.deploy.common.dao.network.NetworkDAO;
import org.openpaas.ieda.deploy.web.deploy.common.dao.network.NetworkVO;
import org.openpaas.ieda.deploy.web.deploy.common.dao.resource.ResourceDAO;
import org.openpaas.ieda.deploy.web.deploy.common.dao.resource.ResourceVO;
import org.openpaas.ieda.deploy.web.management.code.dao.CommonCodeDAO;
import org.openpaas.ieda.deploy.web.management.code.dao.CommonCodeVO;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.MessageSource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringApplicationConfiguration(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class CfServiceUnitTest extends BaseDeployControllerUnitTest {

    @InjectMocks
    CfService mockCfService;
    @Mock
    CfDAO mockCfDAO;
    @Mock
    CommonDeployDAO mockCommonDeployDAO;
    @Mock
    NetworkDAO mockNetworkDAO;
    @Mock
    ResourceDAO mockResourceDAO;
    @Mock
    CommonCodeDAO mockCommonCodeDAO;
    @Mock
    MessageSource mockMessageSource;



    /****************************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 하나의 메소드가 실행되기전 호출
     * @title : setUp
     * @return : void
     *****************************************************************/
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        getLoggined();
    }

    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : CF 정보 목록 전체 조회 테스트
     * @title : testGetCfList
     * @return : void
     ***************************************************/
    @Test
    public void testGetCfList() {
        List<CfVO> expectCfList = setCfInfoList();
        List<NetworkVO> expectNetowrks = setNetworkInfoList("default");
        ResourceVO expectResource = setResourceInfo();
        when(mockCfDAO.selectCfList(anyString(), anyString())).thenReturn(expectCfList);
        when(mockMessageSource.getMessage(anyString(), anyObject(), anyObject())).thenReturn("DEPLOY_TYPE_CF");
        when(mockNetworkDAO.selectNetworkList(anyInt(), anyString())).thenReturn(expectNetowrks);
        when(mockResourceDAO.selectResourceInfo(anyInt(), anyString())).thenReturn(expectResource);
        List<CfListDTO> resultList = mockCfService.getCfLIst("openstack", "cf");
        for (int i = 0; i < resultList.size(); i++) {
            assertEquals(expectCfList.get(i).getAppSshFingerprint(), resultList.get(i).getAppSshFingerprint());
            assertEquals(expectCfList.get(i).getDeploymentFile(), resultList.get(i).getDeploymentFile());
            assertEquals(expectCfList.get(i).getDeploymentName(), resultList.get(i).getDeploymentName());
            assertEquals(expectCfList.get(i).getDeployStatus(), resultList.get(i).getDeployStatus());
            assertEquals(expectCfList.get(i).getDiegoYn(), resultList.get(i).getDiegoYn());
            assertEquals(expectCfList.get(i).getIngestorIp(), resultList.get(i).getIngestorIp());
            assertEquals(expectCfList.get(i).getPaastaMonitoringUse(), resultList.get(i).getPaastaMonitoringUse());
            assertEquals(expectCfList.get(i).getTaskId(), resultList.get(i).getTaskId());
            assertEquals(expectResource.getStemcellName(), resultList.get(i).getStemcellName());
            assertEquals(expectResource.getStemcellVersion(), resultList.get(i).getStemcellVersion());
        }
    }

    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 네트워크 사이즈가 2개 이상일 경우 테스트
     * @title : testGetCfListNetworkSize2
     * @return : void
     ***************************************************/
    @Test
    public void testGetCfListNetworkSize2() {
        List<CfVO> expectCfList = setCfInfoList();
        List<NetworkVO> expectNetowrks = setNetworkInfoList("size");
        ResourceVO expectResource = setResourceInfo();
        when(mockCfDAO.selectCfList(anyString(), anyString())).thenReturn(expectCfList);
        when(mockMessageSource.getMessage(anyString(), anyObject(), anyObject())).thenReturn("DEPLOY_TYPE_CF");
        when(mockNetworkDAO.selectNetworkList(anyInt(), anyString())).thenReturn(expectNetowrks);
        when(mockResourceDAO.selectResourceInfo(anyInt(), anyString())).thenReturn(expectResource);
        List<CfListDTO> resultList = mockCfService.getCfLIst("openstack", "cf");
        for (int i = 0; i < resultList.size(); i++) {
            assertEquals(expectCfList.get(i).getAppSshFingerprint(), resultList.get(i).getAppSshFingerprint());
            assertEquals(expectCfList.get(i).getDeploymentFile(), resultList.get(i).getDeploymentFile());
            assertEquals(expectCfList.get(i).getDeploymentName(), resultList.get(i).getDeploymentName());
            assertEquals(expectCfList.get(i).getDeployStatus(), resultList.get(i).getDeployStatus());
            assertEquals(expectCfList.get(i).getDiegoYn(), resultList.get(i).getDiegoYn());
            assertEquals(expectCfList.get(i).getIngestorIp(), resultList.get(i).getIngestorIp());
            assertEquals(expectCfList.get(i).getPaastaMonitoringUse(), resultList.get(i).getPaastaMonitoringUse());
            assertEquals(expectCfList.get(i).getTaskId(), resultList.get(i).getTaskId());
            assertEquals(expectResource.getStemcellName(), resultList.get(i).getStemcellName());
            assertEquals(expectResource.getStemcellVersion(), resultList.get(i).getStemcellVersion());
        }
    }

    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : CF 상세 정보 조회 테스트
     * @title : testGetCfInfo
     * @return : void
     ***************************************************/
    @Test
    public void testGetCfInfo() {
        CfVO expectCfInfo = setCfInfo("default");
        when(mockMessageSource.getMessage(anyString(), anyObject(), anyObject())).thenReturn("DEPLOY_TYPE_CF");
        when(mockCfDAO.selectCfInfoById(anyInt())).thenReturn(expectCfInfo);
        CfVO resultCfInfo = mockCfService.getCfInfo(1);
        assertEquals(expectCfInfo.getAppSshFingerprint(), resultCfInfo.getAppSshFingerprint());
        assertEquals(expectCfInfo.getCountryCode(), resultCfInfo.getCountryCode());
        assertEquals(expectCfInfo.getCreateUserId(), resultCfInfo.getCreateUserId());
        assertEquals(expectCfInfo.getDeaDiskMB(), resultCfInfo.getDeaDiskMB());
        assertEquals(expectCfInfo.getDeaMemoryMB(), resultCfInfo.getDeaMemoryMB());
        assertEquals(expectCfInfo.getDeploymentFile(), resultCfInfo.getDeploymentFile());
        assertEquals(expectCfInfo.getDeployStatus(), resultCfInfo.getDeployStatus());
        assertEquals(expectCfInfo.getDescription(), resultCfInfo.getDescription());
        assertEquals(expectCfInfo.getDiegoYn(), resultCfInfo.getDiegoYn());
        assertEquals(expectCfInfo.getDirectorUuid(), resultCfInfo.getDirectorUuid());
    }
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 네트워크 정보 목록 조회
     * @title : testGetNetowrkListInfo
     * @return : void
    ***************************************************/
    @Test
    public void testGetNetowrkListInfo() {
        when(mockNetworkDAO.selectNetworkList(anyInt(), anyString())).thenReturn(new ArrayList<NetworkVO>());
        mockCfService.getNetowrkListInfo(1, "DEPLOY_TPE_CF");
    }
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 인프라 및 릴리즈 버전 별 job 목록 조회
     * @title : testGetJobTemplateList
     * @return : void
    ***************************************************/
    @Test
    public void testGetJobTemplateList() {
        when(mockCfDAO.selectCfJobTemplatesByReleaseVersion(any())).thenReturn(new ArrayList<HashMap<String, String>>());
        mockCfService.getJobTemplateList("AWS", "273");
    }

    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : CF 상세 정보 조회 값이 Null 값일 경우 테스트
     * @title : testGetCfInfoNullPointException
     * @return : void
     ***************************************************/
    @Test(expected = CommonException.class)
    public void testGetCfInfoNullPointException() {
        when(mockMessageSource.getMessage(anyString(), anyObject(), anyObject())).thenReturn("DEPLOY_TYPE_CF");
        when(mockCfDAO.selectCfInfoById(anyInt())).thenReturn(null);
        when(mockMessageSource.getMessage(anyString(), anyObject(), anyObject())).thenReturn("nullPoint");
        mockCfService.getCfInfo(1);
    }

    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : CF 배포 파일 생성 중 Manifest 결과 값이 NULL일 경우 테스트
     * @title :testCreateSettingFileNullPoint
     * @return :void
     ***************************************************/
    @Test(expected=CommonException.class)
    public void testCreateSettingFileNullPoint() {
        when(mockCommonDeployDAO.selectManifetTemplate(anyString(), anyString(), anyString(), anyString())).thenReturn(null);
        CfVO expectCfInfo = setCfInfo("default");
        mockCfService.createSettingFile(expectCfInfo);
    }
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : CF 배포 파일 생성 중 해당 Version의 Input 파일이 존재 하지 않을 경우 테스트
     * @title :testCreateSettingFileInputFileNullPoint
     * @return :void
     ***************************************************/
    @Test(expected=CommonException.class)
    public void testCreateSettingFileInputFileNullPoint() {
        ManifestTemplateVO manifestTemplateInfo = setManifestTemplateInfo("default");
        when(mockCommonDeployDAO.selectManifetTemplate(anyString(), anyString(), anyString(), anyString())).thenReturn(manifestTemplateInfo);
        CfVO expectCfInfo = setCfInfo("default");
        mockCfService.createSettingFile(expectCfInfo);
    }
    
    /***************************************************
    * @project : Paas 플랫폼 설치 자동화
    * @description : Option Manifest 템플릿 정보 설정 테스트
    * @title : testSetOptionManifestTemplateInfo
    * @return : void
    ***************************************************/
    @Test
    public void testSetOptionManifestTemplateInfo(){
        ManifestTemplateVO manifestTemplateInfo = setManifestTemplateInfo("default");
        CfVO expectCfInfo = setCfInfo("default");
        ManifestTemplateVO vo = new ManifestTemplateVO();
        mockCfService.setOptionManifestTemplateInfo(manifestTemplateInfo, vo, expectCfInfo);
    }
    
    /***************************************************
    * @project : Paas 플랫폼 설치 자동화
    * @description : Option Manifest 템플릿 정보가 존재 하지 않을 경우 테스트
    * @title : testSetOptionManifestTemplateInfoEmpty
    * @return : void
    ***************************************************/
    @Test
    public void testSetOptionManifestTemplateInfoEmpty(){
        ManifestTemplateVO manifestTemplateInfo = setManifestTemplateInfo("empty");
        CfVO expectCfInfo = setCfInfo("default");
        ManifestTemplateVO vo = new ManifestTemplateVO();
        mockCfService.setOptionManifestTemplateInfo(manifestTemplateInfo, vo, expectCfInfo);
    }
    
    /***************************************************
    * @project : Paas 플랫폼 설치 자동화
    * @description : Option Manifest 템플릿 정보가 Diego 사용여부 사용 인 경우 테스트
    * @title : testSetOptionManifestTemplateInfoDiegoY
    * @return : void
    ***************************************************/
    @Test
    public void testSetOptionManifestTemplateInfoDiegoY(){
        ManifestTemplateVO manifestTemplateInfo = setManifestTemplateInfo("default");
        CfVO expectCfInfo = setCfInfo("diego");
        ManifestTemplateVO vo = new ManifestTemplateVO();
        mockCfService.setOptionManifestTemplateInfo(manifestTemplateInfo, vo, expectCfInfo);
    }
    
    /***************************************************
    * @project : Paas 플랫폼 설치 자동화
    * @description : Option Manifest 템플릿 정보가 PaaS-ta 모니터링 사용여부 사용 인 경우 테스트
    * @title : testSetOptionManifestTemplateInfoPaasTaY
    * @return : void
    ***************************************************/
    @Test
    public void testSetOptionManifestTemplateInfoPaasTaY(){
        ManifestTemplateVO manifestTemplateInfo = setManifestTemplateInfo("default");
        CfVO expectCfInfo = setCfInfo("paas-ta");
        ManifestTemplateVO vo = new ManifestTemplateVO();
        mockCfService.setOptionManifestTemplateInfo(manifestTemplateInfo, vo, expectCfInfo);
    }
    
    /***************************************************
    * @project : Paas 플랫폼 설치 자동화
    * @description : CF 단순 레코드 삭제  테스트
    * @title : testDeleteCfInfoRecord
    * @return : void
    ***************************************************/
    @Test
    public void testDeleteCfInfoRecord(){
        CfParamDTO.Delete dto = new CfParamDTO.Delete();
        dto.setId("1");
        mockCfService.deleteCfInfoRecord(dto);
    }
    
    /***************************************************
    * @project : Paas 플랫폼 설치 자동화
    * @description : OPENSTACK CF 정보 Replace Test
    * @title : testSetReplaceItems
    * @return : void
    ***************************************************/
    @Test
    public void testSetReplaceItemsOpenstack(){
        CfVO expectCfInfo = setCfInfo("paas-ta");
        mockCfService.setReplaceItems(expectCfInfo, "openstack");
    }
    
    /***************************************************
    * @project : Paas 플랫폼 설치 자동화
    * @description : VSPHERE CF 정보 Replace Test
    * @title : testSetReplaceItems
    * @return : void
    ***************************************************/
    @Test
    public void testSetReplaceItemsVsphere(){
        CfVO expectCfInfo = setCfInfo("vsphere");
        mockCfService.setReplaceItems(expectCfInfo, "vsphere");
    }
    
    /***************************************************
    * @project : Paas 플랫폼 설치 자동화
    * @description : AWS CF 정보 Replace Test
    * @title : testSetReplaceItemsAws
    * @return : void
    ***************************************************/
    @Test
    public void testSetReplaceItemsAws(){
        CfVO expectCfInfo = setCfInfo("aws");
        mockCfService.setReplaceItems(expectCfInfo, "aws");
    }
    
    /***************************************************
    * @project : Paas 플랫폼 설치 자동화
    * @description : Google CF 정보 Replace Test
    * @title : testSetReplaceItemsGoogle
    * @return : void
    ***************************************************/
    @Test
    public void testSetReplaceItemsGoogle(){
        
        when(mockCommonCodeDAO.selectCommonCodeList(anyString())).thenReturn(setCommonCodeListInfo());
        CfVO expectCfInfo = setCfInfo("google");
        mockCfService.setReplaceItems(expectCfInfo, "google");
    }
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description :  CF 고급 설정 정보 ReplaceItemDTO에 설정
     * @title : testSetReplaceItmesFromJobsInfo
     * @return : void
    ***************************************************/
    @Test
    public void testSetReplaceItmesFromJobsInfo() {
        CfVO vo = setJobsReplaceItems();
        mockCfService.setReplaceItmesFromJobsInfo(vo, new ArrayList<ReplaceItemDTO>());
        
    }
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : Jobs 정보 설정
     * @title : setJobsReplaceItems
     * @return : CfVO
    ***************************************************/
    public CfVO setJobsReplaceItems(){
        CfVO vo = new CfVO();
        List<HashMap<String, Object>> jobs = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> job = new HashMap<String, Object>();
        job.put("id", 1);
        job.put("seq", 1);
        job.put("deploy_type", "DEPLOY_TYPE_CF");
        job.put("job_name", "consul");
        job.put("instances", 2);
        job.put("zone", "z1");
        jobs.add(job);
        vo.setJobs(jobs);
        return vo;
        
    }
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 공통 코드 목록 정보 설정
     * @title : setCommonCodeListInfo
     * @return : List<CommonCodeVO>
    ***************************************************/
    public List<CommonCodeVO> setCommonCodeListInfo(){
        List<CommonCodeVO> list = new ArrayList<CommonCodeVO>();
        CommonCodeVO vo = new CommonCodeVO();
        vo.setCodeIdx(1175);
        vo.setCodeName("nats");
        vo.setCodeValue("4001");
        vo.setCodeNameKR("nats");
        vo.setCodeDescription("defaultNetwork_nats");
        vo.setSortOrder(1);
        vo.setParentCode("4000");
        return list;
    }
    
    /***************************************************
     * @param type 
     * @project : Paas 플랫폼 설치 자동화
     * @description : CF 상세 정보 조회 테스트
     * @title : testGetCfInfo
     * @return : void
     ***************************************************/
    public ManifestTemplateVO setManifestTemplateInfo(String type) {
        ManifestTemplateVO vo = new ManifestTemplateVO();
        vo.setCfTempleate("cf.yml");
        vo.setCommonBaseTemplate("base.yml");
        vo.setCommonJobTemplate("job.yml");
        vo.setCommonOptionTemplate("option.yml");
        vo.setCreateUserId("admin");
        vo.setDeployType("cf");
        vo.setIaasPropertyTemplate("iaas.yml");
        vo.setId(1);
        vo.setIaasType("openstack");
        vo.setInputTemplate("cf_openstack_inputs");
        vo.setMetaTemplate("meta.yml");
        vo.setMinReleaseVersion("247");
        vo.setOptionEtc("etc.yml");
        vo.setOptionNetworkTemplate("network.yml");
        vo.setOptionResourceTemplate("resource.yml");
        vo.setReleaseType("cf");
        vo.setShellScript("shell");
        vo.setTemplateVersion("247");
        vo.setUpdateUserId("admin");
        if(type.equals("empty")){
            vo = new ManifestTemplateVO();
        }
        return vo;
    }

    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : CF 상세 정보 조회 테스트
     * @title : testGetCfInfo
     * @return : void
     ***************************************************/
    public CfVO setCfInfo(String type) {
        CfVO vo = new CfVO();
        vo.setAppSshFingerprint("fingerprint");
        vo.setNetworks(setNetworkInfoList("default"));
        vo.setNetwork(vo.getNetworks().get(0));
        vo.setCountryCode("seoul");
        vo.setCreateUserId("admin");
        vo.setDeaDiskMB(8888);
        vo.setDeaMemoryMB(41768);
        vo.setDeploymentFile("cf-yml");
        vo.setDeploymentName("cf");
        vo.setDeployStatus("deploy");
        vo.setDescription("cf");
        vo.setDiegoYn("N");
        vo.setDirectorUuid("uuid");
        vo.setDomain("domain");
        vo.setDomainOrganization("paas-ta");
        vo.setEmail("paas@com");
        vo.setIaasType("openstack");
        vo.setId(1);
        vo.setUnitName("testunit");
        vo.setUpdateUserId("admin");
        vo.setTaskId(900);
        vo.setStateName("test");
        vo.setReleaseVersion("248");
        vo.setReleaseName("cf");
        vo.setPaastaMonitoringUse("yes");
        vo.setOrganizationName("pass-ta");
        vo.setIngestorIp("172.16.100.100");
        vo.setKeyFile("cf-key.yml");
        vo.setLocalityName("mapo");
        vo.setLoginSecret("test");
        vo.setResource(setResourceInfo());
        if(type.equals("diego")) vo.setDiegoYn("true");
        if(type.equals("paas-ta")) {
            vo.setPaastaMonitoringUse("true");
            vo.setNetworks(setNetworkInfoList("size"));
            vo.setDiegoYn("true");
        }
        if(type.equals("vsphere")){
            vo.setIaasType("vsphere");
            vo.setNetworks(setNetworkInfoList("EXTERNAL"));
        }
        if(type.equals("google")){
            vo.setIaasType("google");
            vo.setNetworks(setNetworkInfoList("size"));
        }
        if(type.equals("aws")){
            vo.setNetworks(setNetworkInfoList("size"));
            vo.setIaasType("aws");
        }
        vo.setJobs(new ArrayList<HashMap<String, Object>>());
        return vo;
    }

    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : CF 리소스 정보 설정
     * @title : setResourceInfo
     * @return : ResourceVO
     ***************************************************/
    public ResourceVO setResourceInfo() {
        ResourceVO vo = new ResourceVO();
        vo.setBoshPassword("bosh");
        vo.setCreateUserId("admin");
        vo.setDeployType("openstack");
        vo.setId(1);
        vo.setLargeCpu(10);
        vo.setLargeDisk(1000);
        vo.setLargeFlavor("m1.large");
        vo.setLargeRam(1000);
        vo.setMediumCpu(5);
        vo.setMediumDisk(500);
        vo.setMediumFlavor("m1.medium");
        vo.setMediumRam(500);
        vo.setRunnerCpu(1500);
        vo.setRunnerDisk(1500);
        vo.setRunnerFlavor("m1.xlarge");
        vo.setRunnerRam(1500);
        vo.setSmallFlavor("m1.small");
        vo.setSmallCpu(1);
        vo.setSmallRam(1000);
        vo.setSmallDisk(100);
        vo.setStemcellName("stemcell");
        vo.setStemcellVersion("3417");
        vo.setUpdateUserId("adimn");
        vo.getId();
        vo.getDeployType();
        vo.getBoshPassword();
        vo.getStemcellName();
        vo.getStemcellVersion();
        vo.getSmallFlavor();
        vo.getMediumCpu();
        vo.getMediumFlavor();
        vo.getLargeFlavor();
        vo.getMediumRam();
        vo.getRunnerFlavor();
        vo.getSmallCpu();
        vo.getMediumDisk();
        vo.getSmallRam();
        vo.getSmallDisk();
        vo.getLargeCpu();
        vo.getLargeRam();
        vo.getLargeDisk();
        vo.getRunnerCpu();
        vo.getRunnerRam();
        vo.getRunnerDisk();
        vo.getCreateUserId();
        vo.getUpdateUserId();
        return vo;
    }

    /***************************************************
     * @param string
     * @project : Paas 플랫폼 설치 자동화
     * @description : CF 네트워크 정보 설정
     * @title : setNetworkInfoList
     * @return : List<NetworkVO>
     ***************************************************/
    public List<NetworkVO> setNetworkInfoList(String type) {
        List<NetworkVO> list = new ArrayList<NetworkVO>();
        NetworkVO vo = new NetworkVO();
        vo.setAvailabilityZone("testZone");
        vo.setCloudSecurityGroups("seg");
        vo.setCreateUserId("admin");
        vo.setDeployType("cf-deploy");
        vo.setId(1);
        vo.setNet("internal");
        vo.setNetworkName("netName");
        vo.setPublicStaticIP("172.168.100.100");
        vo.setSeq(1);
        vo.setSubnetDns("8.8.8.8");
        vo.setSubnetGateway("192.168.1.1");
        vo.setSubnetId("subId");
        vo.setSubnetRange("192.168.1.0/24");
        vo.setSubnetReservedFrom("192.168.1.1");
        vo.setSubnetReservedTo("192.168.1.20");
        vo.setSubnetStaticFrom("192.168.1.11");
        vo.setSubnetStaticTo("192.168.1.40");
        vo.setUpdateUserId("admin");
        vo.getAvailabilityZone();
        vo.getCloudSecurityGroups();
        vo.getCreateUserId();
        vo.getDeployType();
        vo.getId();
        vo.getNet();
        vo.getNetworkName();
        vo.getPublicStaticIP();
        vo.getSeq();
        vo.getSubnetDns();
        vo.getSubnetGateway();
        vo.getSubnetId();
        vo.getSubnetRange();
        vo.getSubnetReservedFrom();
        vo.getSubnetReservedTo();
        vo.getSubnetStaticFrom();
        vo.getSubnetStaticTo();
        vo.getUpdateUserId();
        list.add(vo);
        if (type.equals("size")) {
            vo = new NetworkVO();
            vo.setNet("internal");
            vo.setNetworkName("netName");
            vo.setPublicStaticIP("172.168.100.101");
            vo.setSeq(1);
            vo.setSubnetDns("8.8.8.8");
            vo.setSubnetGateway("192.168.2.1");
            vo.setSubnetId("subId");
            vo.setSubnetRange("192.168.1.2/24");
            vo.setSubnetReservedFrom("192.168.2.1");
            vo.setSubnetReservedTo("192.168.2.20");
            vo.setSubnetStaticFrom("192.168.2.11");
            vo.setSubnetStaticTo("192.168.2.40");
            list.add(vo);
            vo = new NetworkVO();
            vo.setNet("internal");
            vo.setNetworkName("netName2");
            vo.setPublicStaticIP("172.168.100.102");
            vo.setSeq(1);
            vo.setSubnetDns("8.8.8.8");
            vo.setSubnetGateway("192.168.3.1");
            vo.setSubnetId("subId");
            vo.setSubnetRange("192.168.1.3/24");
            vo.setSubnetReservedFrom("192.168.3.1");
            vo.setSubnetReservedTo("192.168.3.20");
            vo.setSubnetStaticFrom("192.168.3.11");
            vo.setSubnetStaticTo("192.168.3.40");
            list.add(vo);
        }
        if(type.equals("EXTERNAL")){
            vo = new NetworkVO();
            vo.setNet("EXTERNAL");
            vo.setNetworkName("netName3");
            vo.setPublicStaticIP("172.168.100.102");
            list.add(vo);
        }
        return list;
    }

    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : CF 목록 조회 기대 값 설정
     * @title : setCfInfoList
     * @return : List<CfVO>
     ***************************************************/
    public List<CfVO> setCfInfoList() {
        List<CfVO> list = new ArrayList<CfVO>();
        CfVO vo = new CfVO();
        vo.setAppSshFingerprint("fingerprint");
        vo.setNetworks(setNetworkInfoList("default"));
        vo.setNetwork(vo.getNetworks().get(0));
        vo.setCountryCode("seoul");
        vo.setCreateUserId("admin");
        vo.setDeaDiskMB(8888);
        vo.setDeaMemoryMB(41768);
        vo.setDeploymentFile("cf-yml");
        vo.setDeploymentName("cf");
        vo.setDeployStatus("deploy");
        vo.setDescription("cf");
        vo.setDiegoYn("N");
        vo.setDirectorUuid("uuid");
        vo.setDomain("domain");
        vo.setDomainOrganization("paas-ta");
        vo.setEmail("paas@com");
        vo.setIaasType("openstack");
        vo.setId(1);
        vo.setUnitName("testunit");
        vo.setUpdateUserId("admin");
        vo.setTaskId(900);
        vo.setStateName("test");
        vo.setReleaseVersion("248");
        vo.setReleaseName("cf");
        vo.setPaastaMonitoringUse("yes");
        vo.setOrganizationName("pass-ta");
        vo.setIngestorIp("172.16.100.100");
        vo.setKeyFile("cf-key.yml");
        vo.setLocalityName("mapo");
        vo.setLoginSecret("test");
        vo.getId();
        vo.getIaasType();
        vo.getDiegoYn();
        vo.getCreateUserId();
        vo.getUpdateUserId();
        vo.getDeploymentName();
        vo.getDirectorUuid();
        vo.getReleaseName();
        vo.getReleaseVersion();
        vo.getAppSshFingerprint();
        vo.getDeaMemoryMB();
        vo.getDeaDiskMB();
        vo.getDomain();
        vo.getDescription();
        vo.getDomainOrganization();
        vo.getCountryCode();
        vo.getStateName();
        vo.getLocalityName();
        vo.getOrganizationName();
        vo.getUnitName();
        vo.getEmail();
        vo.getDeploymentFile();
        vo.getDeployStatus();
        vo.getTaskId();
        vo.getKeyFile();
        vo.getLoginSecret();
        vo.getPaastaMonitoringUse();
        vo.getIngestorIp();
        vo.getNetwork();
        list.add(vo);
        return list;
    }
}
