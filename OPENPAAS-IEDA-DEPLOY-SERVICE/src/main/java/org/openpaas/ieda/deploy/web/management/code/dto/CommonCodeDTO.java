package org.openpaas.ieda.deploy.web.management.code.dto;

import javax.validation.constraints.NotNull;

public class CommonCodeDTO {
        
public static class Regist {
        private Integer recid;
        private Integer codeIdx; //codeIdx
        @NotNull
        private String codeName; //code명
        @NotNull
        private String codeValue; //code값
        private String codeNameKR; //code명(한글)
        private String codeDescription; //code 설명
        private String subGroupCode; //하위 그룹코드
        private String parentCode; //상위 codeIdx
        private String createUserId; //생성한 사용자
        private String updateUserId; //수정한 사용자
        private Integer sortOrder; //정렬
        
        public Integer getRecid() {
            return recid;
        }

        public void setRecid(Integer recid) {
            this.recid = recid;
        }

        public Integer getCodeIdx() {
            return codeIdx;
        }

        public void setCodeIdx(Integer codeIdx) {
            this.codeIdx = codeIdx;
        }

        public String getCodeName() {
            return codeName;
        }

        public void setCodeName(String codeName) {
            this.codeName = codeName;
        }

        public String getCodeValue() {
            return codeValue;
        }

        public void setCodeValue(String codeValue) {
            this.codeValue = codeValue;
        }

        public String getCodeDescription() {
            return codeDescription;
        }

        public void setCodeDescription(String codeDescription) {
            this.codeDescription = codeDescription;
        }

        public String getSubGroupCode() {
            return subGroupCode;
        }

        public void setSubGroupCode(String subGroupCode) {
            this.subGroupCode = subGroupCode;
        }

        public Integer getSortOrder() {
            return sortOrder;
        }

        public void setSortOrder(Integer sortOrder) {
            this.sortOrder = sortOrder;
        }

        public String getParentCode() {
            return parentCode;
        }

        public void setParentCode(String parentCode) {
            this.parentCode = parentCode;
        }

        public String getCreateUserId() {
            return createUserId;
        }

        public void setCreateUserId(String createUserId) {
            this.createUserId = createUserId;
        }

        public String getUpdateUserId() {
            return updateUserId;
        }

        public void setUpdateUserId(String updateUserId) {
            this.updateUserId = updateUserId;
        }

        public String getCodeNameKR() {
            return codeNameKR;
        }

        public void setCodeNameKR(String codeNameKR) {
            this.codeNameKR = codeNameKR;
        }

    }
    
}