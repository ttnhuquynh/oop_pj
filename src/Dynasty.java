public class Dynasty {
    private long id;
    private String name;
    private String yearStart;
    private String yearEnd;

    private String description;

    private String countryName;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    static int currentId = 0;
    private int related;

    public int getRelated() {
        return related;
    }

    public void setRelated(int related) {
        this.related = related;
    }
    //    public Dynasty(String name){
//        this.name = name;
//        this.id = ++currentId;
//        this.yearEnd = "Không rõ";
//        this.yearStart = "Không rõ";
//        this.countryName = "Không rõ";
//
//    }



    public Dynasty(String name, String yearStart, String yearEnd ){
        this.id = ++ currentId;
        this.name = name;
        this.yearStart = yearStart == null ? "Không rõ" : yearStart;
        this.yearEnd = yearEnd == null ? "Không rõ" : yearEnd;
        this.countryName = "Không rõ";
        this.related = 0;
    }

    public Dynasty(String name, String yearStart, String yearEnd, String description, int related){
        this.id = ++ currentId;
        this.name = name;
        this.yearStart = yearStart == null ? "Không rõ" : yearStart;
        this.yearEnd = yearEnd == null ? "Không rõ" : yearEnd;
        this.description = description == null ? "Không có" : description;
        this.countryName = "Không rõ";
        this.related = related;
    }

    public Dynasty(String name, String yearStart ){
        this.id = ++ currentId;
        this.name = name;
        this.yearStart = yearStart;
        this.yearEnd = "Không rõ";
        this.countryName = "Không rõ";
        this.related = 0;
    }

    public Dynasty(String countryName ){
        this.id = ++ currentId;
        this.name = "Không rõ";
        this.yearStart = "Không rõ";
        this.yearEnd = "Không rõ";
        this.countryName = countryName;
        this.related = 0;
    }
    public long getId() {
//        System.out.println(id);
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getYearStart() {
//        System.out.println(yearStart);
        return yearStart;
    }

    public void setYearStart(String yearStart) {
        this.yearStart = yearStart;
    }

    public String getYearEnd() {
//        System.out.println(yearEnd);
        return yearEnd;
    }

    public void setYearEnd(String yearEnd) {
        this.yearEnd = yearEnd;
    }

    public String getName() {
//        System.out.println(name);
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
