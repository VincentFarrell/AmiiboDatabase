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

    public Amiibo(String amiiboName, String amiiboSeries, String gameSeries, String headID,  String tailID, String image, String releaseAU, String releaseEU, String releaseJP, String releaseNA, String type, String character) {
        this.amiiboSeries = amiiboSeries;
        this.character = character;
        this.gameSeries = gameSeries;
        this.headID = headID;
        this.image = image;
        this.amiiboName = amiiboName;
        this.releaseEU = releaseEU;
        this.releaseAU = releaseAU;
        this.releaseJP = releaseJP;
        this.releaseNA = releaseNA;
        this.tailID = tailID;
        this.type = type;
    }


    public String getAmiiboSeries() {
        return amiiboSeries;
    }

    public void setAmiiboSeries(String amiiboSeries) {
        this.amiiboSeries = amiiboSeries;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
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

    public String getReleaseEU() {
        return releaseEU;
    }

    public void setReleaseEU(String releaseEU) {
        this.releaseEU = releaseEU;
    }

    public String getReleaseAU() {
        return releaseAU;
    }

    public void setReleaseAU(String releaseAU) {
        this.releaseAU = releaseAU;
    }

    public String getReleaseJP() {
        return releaseJP;
    }

    public void setReleaseJP(String releaseJP) {
        this.releaseJP = releaseJP;
    }

    public String getReleaseNA() {
        return releaseNA;
    }

    public void setReleaseNA(String releaseNA) {
        this.releaseNA = releaseNA;
    }

    public String getTailID() {
        return tailID;
    }

    public void setTailID(String tailID) {
        this.tailID = tailID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
