package models;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;
import org.testng.collections.CollectionUtils;

import java.util.*;

@Getter
@Setter
public class Company {

    private String name;
    private String type;
    private List<String> users;
    private String emailOwned;

    public Company(String email) {
        String random = RandomStringUtils.randomAlphabetic(7);
        this.name = random;
        this.type = "ООО";
        List<String> users = new ArrayList<>();
        users.add(email);
        this.users = users;
        this.emailOwned = email;
    }

    public Company(Map<String, Object> map) {
        this.name = map.get("name").toString();
        this.type = map.get("type").toString();
        this.users = (List<String>) map.get("users");
    }

    public JSONObject createCompanyJson() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("company_name", this.name).put("company_type", this.type).put("email_owner", this.emailOwned)
                .put("company_users", this.users);
        return requestBody;
    }

    public Company getCompanyFromResponse(Response response) {
        Map<String, Object> map = response.path("company");
        return new Company(map);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Company)) return false;
        Company company = (Company) o;
        return Objects.equals(name, company.name) &&
                Objects.equals(type, company.type) &&
                Objects.equals(users, company.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type, users);
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", users=" + users +
                '}';
    }
}
