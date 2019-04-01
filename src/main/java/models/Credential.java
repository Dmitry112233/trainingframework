package models;

import io.restassured.response.Response;
import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

import java.util.Objects;

@Getter
public class Credential {

    private String email;
    private String name;
    private String password;

    public Credential() {
        String randomStr = RandomStringUtils.randomAlphabetic(7);
        this.email = randomStr + "@mail.com";
        this.name = randomStr;
        this.password = randomStr;
    }

    public Credential(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    public JSONObject createCredentialJson() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", this.name)
                .put("email", this.email)
                .put("password", this.password);
        return requestBody;
    }

    public Credential getCredentialFromResponse(Response response) {
        return new Credential(response.path("email"),
                response.path("name"), response.path("password"));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Credential)) return false;
        Credential that = (Credential) o;
        return email.equals(that.email) &&
                name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, name, password);
    }

    @Override
    public String toString() {
        return "Credential{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
