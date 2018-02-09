package org.openpaas.ieda.controller.deploy.web.management.auth;

import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.openpaas.ieda.controller.common.BaseController;
import org.openpaas.ieda.deploy.web.management.auth.dao.AuthManagementVO;
import org.openpaas.ieda.deploy.web.management.auth.dto.AuthManagementDTO;
import org.openpaas.ieda.deploy.web.management.auth.service.AuthManagementService;
import org.openpaas.ieda.deploy.web.management.code.dao.CommonCodeVO;
import org.openpaas.ieda.deploy.web.management.code.service.CommonCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthManagementController extends BaseController {

    @Autowired private AuthManagementService service;
    @Autowired private CommonCodeService commonservice;
    
    private final static Logger LOGGER = LoggerFactory.getLogger(AuthManagementController.class);
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 권한 관리 화면 이동
     * @title goAuthManagement
     * @return : String
    ***************************************************/
    @RequestMapping(value = "/admin/role", method = RequestMethod.GET)
    public String goAuthManagement() {
        if (LOGGER.isInfoEnabled()) { LOGGER.info("================================================> 권한 관리 화면 이동"); }
        return "/deploy/management/auth/authManagement";
    }
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 권한 그룹 리스트 조회
     * @title getRoleGroupList
     * @return : ResponseEntity<HashMap<String,Object>>
    ***************************************************/
    @RequestMapping(value = "/admin/role/group/list", method = RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getRoleGroupList() {
        if (LOGGER.isInfoEnabled()) { LOGGER.info("================================================> 권한 그룹 리스트 요청"); }
        List<AuthManagementVO> roleGroupList = service.getRoleGroupList();
        HashMap<String, Object> list = new HashMap<String, Object>();
        int size =0;
        if( roleGroupList.size() > 0  ) {
            size = roleGroupList.size();
        }
        list.put("total", size);
        list.put("records", roleGroupList);
        return new ResponseEntity<HashMap<String, Object>>(list, HttpStatus.OK);
    }
        
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 권한 그룹 하위 코드 목록 조회
     * @title getRoleDetailList
     * @return : ResponseEntity<HashMap<String,Object>>
    ***************************************************/
    @RequestMapping(value="/admin/role/group/{roleId}", method=RequestMethod.GET)
    public ResponseEntity<HashMap<String, Object>> getRoleDetailList(@PathVariable int roleId) {
        if(LOGGER.isInfoEnabled()){ LOGGER.info("================================================> 권한 코드 조회 요청");  }
        List<HashMap<String,Object>> roleDetailList = service.getRoleDetailList(roleId);        
        HashMap<String, Object> list = new HashMap<String, Object>();    
        int count = 0;
        if (roleDetailList.size() > 0) {
            count = roleDetailList.size();
        }
        list.put("total", count);
        list.put("records", roleDetailList);
        return new ResponseEntity<HashMap<String, Object>>(list, HttpStatus.OK);
    }
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 권한 그룹 등록 요청 시 공통 코드 요청
     * @title getCommonCodeList
     * @return : ResponseEntity<List<CommonCodeVO>>
    ***************************************************/
    @RequestMapping(value = "/admin/role/commonCodeList", method = RequestMethod.GET)
    public ResponseEntity<List<CommonCodeVO>> getCommonCodeList() {
        if (LOGGER.isInfoEnabled()) { LOGGER.info("================================================> 등록 클릭 시 코드 그룹 조회 요청"); }
        List<CommonCodeVO> list = commonservice.getCommonCodeList();
        return new ResponseEntity<List<CommonCodeVO>>(list, HttpStatus.OK);
    }
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 권한 그룹 저장
     * @title saveRoleInfo
     * @return : ResponseEntity<?>
    ***************************************************/
    @RequestMapping(value = "/admin/role/group/add", method = RequestMethod.POST)
    public ResponseEntity<?> saveRoleInfo(@RequestBody @Valid AuthManagementDTO.Regist dto) {
        if (LOGGER.isInfoEnabled()) { LOGGER.info("================================================> 권한 그룹 추가 요청"); }
        service.saveRoleInfo(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 권한 그룹 삭제 요청
     * @title deleteRole
     * @return : ResponseEntity<?>
    ***************************************************/
    @RequestMapping(value = "/admin/role/group/delete/{roleId}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRole(@PathVariable Integer roleId) {
        if (LOGGER.isInfoEnabled()) {LOGGER.info("================================================> 권한 그룹 삭제 요청");}
        service.deleteRole(roleId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 권한 그룹 수정 요청
     * @title updateRole
     * @return : ResponseEntity<?>
    ***************************************************/
    @RequestMapping(value ="/admin/role/group/update/{roleId}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateRole(@PathVariable int roleId, @RequestBody @Valid AuthManagementDTO.Regist updateAuthDto) {
        if (LOGGER.isInfoEnabled()) {LOGGER.info("================================================> 권한 그룹 수정 요청");}
        service.updateRole(roleId,updateAuthDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 권한 상세 수정(등록) 요청
     * @title : saveRoleDetail
     * @return : ResponseEntity<?>
    ***************************************************/
    @RequestMapping(value="/admin/role/detail/update/{roleId}" , method = RequestMethod.POST)
    public ResponseEntity<?> saveRoleDetail(@PathVariable int roleId,@RequestBody @Valid AuthManagementDTO.Regist dto){
        if (LOGGER.isInfoEnabled()) {LOGGER.info("================================================> 권한 상세 등록 요청");}
        service.saveRoleDetail(roleId,dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    
}