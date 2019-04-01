package models;

import io.restassured.response.Response;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class User {

    private String email;
    private String name;
    private List<String> tasks;
    private List<String> companies;
    private String hobby;
    private String adres;
    private String name1;
    private String surname1;
    private String fathername1;
    private String cat;
    private String dog;
    private String parrot;
    private String cavy;
    private String hamster;
    private String squirrel;
    private String phone;
    private String gender;
    private String birthday;

    private String date = "11.11.2000 12.00.00";

    public User() {
        String randomStr = RandomStringUtils.randomAlphabetic(7);
        this.email = randomStr.toLowerCase() + "@mail.ru";
        this.name = randomStr;
        List<String> tasks = new ArrayList<>();
        tasks.add("12");
        this.tasks = tasks;
        List<String> companies = new ArrayList<>();
        companies.add("36");
        this.companies = companies;
        this.hobby = randomStr;
        this.adres = randomStr;
        this.name1 = randomStr;
        this.surname1 = randomStr;
        this.fathername1 = randomStr;
        this.cat = randomStr;
        this.dog = randomStr;
        this.parrot = randomStr;
        this.cavy = randomStr;
        this.hamster = randomStr;
        this.squirrel = randomStr;
        this.phone = randomStr;
        this.gender = randomStr;
        this.birthday = date;
    }

    public User(String email, String name, List<Map<String, Integer>> tasks, List<Map<String, Integer>> companies, String hobby, String adres,
                String name1, String surname1, String fathername1, String cat, String dog, String parrot, String cavy,
                String hamster, String squirrel, String phone, String gender, HashMap<String, Integer> birthday) {
        this.email = email;
        this.name = name;
        this.tasks = new ArrayList<>();
        this.tasks.add(tasks.get(0).get("id").toString());
        this.companies = new ArrayList<>();
        this.companies.add(companies.get(0).get("id").toString());
        this.hobby = hobby;
        this.adres = adres;
        this.name1 = name1;
        this.surname1 = surname1;
        this.fathername1 = fathername1;
        this.cat = cat;
        this.dog = dog;
        this.parrot = parrot;
        this.cavy = cavy;
        this.hamster = hamster;
        this.squirrel = squirrel;
        this.phone = phone;
        this.gender = gender;
        long date = 3600000 + 1000L * birthday.get("sec");
        this.birthday = new java.text.SimpleDateFormat("dd.MM.yyyy HH.mm.ss").format(date);
    }

    public JSONObject createUserJson() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", this.getName()).put("email", this.getEmail()).put("hobby", this.getHobby())
                .put("adres", this.getAdres()).put("name1", this.getName1()).put("surname1", this.getSurname1())
                .put("fathername1", this.getFathername1()).put("cat", this.getCat()).put("dog", this.getDog())
                .put("parrot", this.getParrot()).put("cavy", this.getCavy()).put("hamster", this.getHamster())
                .put("squirrel", this.getSquirrel()).put("phone", this.getPhone())
                .put("gender", this.getGender()).put("birthday", this.getBirthday())
                .put("tasks", this.getTasks()).put("companies", this.getCompanies());
        return requestBody;
    }

    public User getUserFromResponse(Response response) {
        return new User(response.path("email"), response.path("name"), response.path("tasks"), response.path("companies")
                , response.path("hobby"), response.path("adres")
                , response.path("name1"), response.path("surname1"), response.path("fathername1"), response.path("cat"), response.path("dog")
                , response.path("parrot"), response.path("cavy"), response.path("hamster")
                , response.path("squirrel"), response.path("phone"), response.path("gender")
                , response.path("birthday"));
    }

}

