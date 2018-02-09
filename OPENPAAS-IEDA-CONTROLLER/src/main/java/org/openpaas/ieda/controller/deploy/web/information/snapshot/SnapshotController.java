package org.openpaas.ieda.controller.deploy.web.information.snapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openpaas.ieda.controller.common.BaseController;
import org.openpaas.ieda.deploy.web.information.snapshot.dto.SnapshotListDTO;
import org.openpaas.ieda.deploy.web.information.snapshot.service.SnapshotService;
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
public class SnapshotController extends BaseController{

    @Autowired SnapshotService snapshotService;
    final private static Logger LOGGER = LoggerFactory.getLogger(SnapshotController.class);
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 스냅샷 화면 호출
     * @title : goListSnapshot
     * @return : String
    ***************************************************/
    @RequestMapping(value="/info/snapshot", method=RequestMethod.GET)
    public String goListSnapshot() {
        if(LOGGER.isInfoEnabled()){ LOGGER.info("================================> 스냅샷 조회 화면 요청"); }
        return "/deploy/information/listSnapshot";
    }
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 스냅샷 정보 목록 조회
     * @title : getSnapshotList
     * @return : ResponseEntity<Map<String,Object>>
    ***************************************************/
    @RequestMapping(value="/info/snapshot/list/{deploymentName}", method=RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getSnapshotList(@PathVariable String deploymentName){
        if(LOGGER.isInfoEnabled()){ LOGGER.info("================================> 스냅샷 조회 요청");  }
        
        List<SnapshotListDTO> contents = snapshotService.getSnapshotList(deploymentName);
        HashMap<String, Object> result = new HashMap<String, Object>();
        int size =0;
        if( contents.size() != 0 ){
            size = contents.size();
        }
        result.put("records", contents);
        result.put("total", size);
        return new ResponseEntity<Map<String, Object>>( result, HttpStatus.OK);
    }
    
    
    /***************************************************
     * @project : Paas 플랫폼 설치 자동화
     * @description : 스냅샷 삭제
     * @title : deleteSnapshot
     * @return : ResponseEntity<?>
    ***************************************************/
    @RequestMapping(value="/info/snapshot/delete/{type}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deleteSnapshot(@PathVariable String type, @RequestBody SnapshotListDTO dto){
        if(LOGGER.isInfoEnabled()){ LOGGER.info("================================> 스냅샷 삭제 요청");  }
        snapshotService.deleteSnapshots(type, dto);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }
}
