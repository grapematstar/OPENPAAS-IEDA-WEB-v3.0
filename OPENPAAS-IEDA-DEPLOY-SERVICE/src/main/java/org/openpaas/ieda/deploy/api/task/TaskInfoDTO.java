package org.openpaas.ieda.deploy.api.task;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskInfoDTO {
    
    private String id;//TaskID
    private String state;//상태
    private String description;//상세내용
    private String timestamp;//시간
    private String result;//결과
    private String user;//사용자
    @JsonProperty("started_at")
    private String startedAt;
    private String deployment;
    
    public String getId() {
        return id;
    }
    public String getState() {
        return state;
    }
    public String getDescription() {
        return description;
    }
    public String getTimestamp() {
        return timestamp;
    }
    public String getResult() {
        return result;
    }
    public String getUser() {
        return user;
    }
    public void setId(String id) {
        this.id = id;
    }
    public void setState(String state) {
        this.state = state;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getStartedAt() {
        return startedAt;
    }
    public void setStartedAt(String startedAt) {
        this.startedAt = startedAt;
    }
    public String getDeployment() {
        return deployment;
    }
    public void setDeployment(String deployment) {
        this.deployment = deployment;
    }
    
}
