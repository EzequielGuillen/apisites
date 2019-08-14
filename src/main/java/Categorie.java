public class Categorie implements Comparable {

    private String id;
    private  String name;

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

    public String toString() {
        return "[" + id + "-" + name + "]";
    }

    public int compareTo(Object o) {
        Categorie a = (Categorie) o;
        return this.name.compareTo(a.name);
    }

}