public class Site implements Comparable {
    private String id;
    private String name;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString(){
        return "[" + id + "-" + name + "]";
    }

    @Override
    public boolean equals(Object obj) {
        Site site = (Site) obj;
        return (name.equals(site.name));
    }

    public int compareTo(Object o) {
        Site a = (Site) o;
        return this.name.compareTo(a.name);
    }
}