package me.gacl.domain;

public class StudentTest {
    private String id;

    private String name;

    private Integer prade;

    private String teacherid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPrade() {
        return prade;
    }

    public void setPrade(Integer prade) {
        this.prade = prade;
    }

    public String getTeacherid() {
        return teacherid;
    }

    public void setTeacherid(String teacherid) {
        this.teacherid = teacherid == null ? null : teacherid.trim();
    }
}