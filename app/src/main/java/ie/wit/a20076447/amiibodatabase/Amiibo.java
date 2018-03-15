package ie.wit.a20076447.amiibodatabase;


public class Amiibo {

    private String amiiboSeries;
    private String character;
    private String gameSeries;
    private String headID;
    private String image;
    private String amiiboName;
    private String releaseEU;
    private String releaseAU;
    private String releaseJP;
    private String releaseNA;
    private String tailID;
    private String type;

    public Amiibo()
    {

    }

    public Amiibo(String amiiboName, String amiiboSeries, String gameSeries, String headID, String tailID, String image)
    {
        this.amiiboSeries = amiiboSeries;
        this.gameSeries = gameSeries;
        this.headID = headID;
        this.image = image;
        this.amiiboName = amiiboName;
        this.tailID = tailID;
    }


    public String getAmiiboSeries() {
        return amiiboSeries;
    }

    public void setAmiiboSeries(String amiiboSeries) {
        this.amiiboSeries = amiiboSeries;
    }

    public String getGameSeries() {
        return gameSeries;
    }

    public void setGameSeries(String gameSeries) {
        this.gameSeries = gameSeries;
    }

    public String getHeadID() {
        return headID;
    }

    public void setHeadID(String headID) {
        this.headID = headID;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAmiiboName() {
        return amiiboName;
    }

    public void setAmiiboName(String amiiboName) {
        this.amiiboName = amiiboName;
    }

    public String getTailID() {
        return tailID;
    }

    public void setTailID(String tailID) {
        this.tailID = tailID;
    }
}
