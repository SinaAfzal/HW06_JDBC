package entity;

public class Books {
    private Integer id;
    private String title;
    private int pubYear;
    private String[] authorsFullNames;
    private int[] authorsIDs;


    public Books(Integer id, String title, int pubYear, String[] authorsFullNames, int[] authorsIDs) {
        this.id = id;
        this.title = title;
        this.pubYear = pubYear;
        this.authorsFullNames = authorsFullNames;
        this.authorsIDs = authorsIDs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPubYear() {
        return pubYear;
    }

    public void setPubYear(int pubYear) {
        this.pubYear = pubYear;
    }

    public String[] getAuthorsFullNames() {
        return authorsFullNames;
    }

    public void setAuthorsFullNames(String[] authorsFullNames) {
        this.authorsFullNames = authorsFullNames;
    }

    public int[] getAuthorsIDs() {
        return authorsIDs;
    }

    public void setAuthorsIDs(int[] authorsIDs) {
        this.authorsIDs = authorsIDs;
    }

}
//**********************************************Developed by:***********************************************************
//******************************************* Sina Afzalsoltani ********************************************************
//===============================================Maktab 101=============================================================