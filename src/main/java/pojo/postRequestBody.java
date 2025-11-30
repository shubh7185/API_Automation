package pojo;

import java.util.List;
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class postRequestBody {

    private String name;
    private String job;
    private List<String> Languages;
    private String updatedAt;
    private String id;
    private String createdAt;
    private List<cityRequest> cityRequestsBody;

    public List<String> getLanguages() {
        return Languages;
    }

    public void setLanguages(List<String> languages) {
        Languages = languages;
    }

    public List<cityRequest> getCityRequestsBody() {
        return cityRequestsBody;
    }

    public void setCityRequestsBody(List<cityRequest> cityRequestsBody) {
        this.cityRequestsBody = cityRequestsBody;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }
    public void setJob(String job) {
        this.job = job;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }


    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
