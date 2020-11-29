package scopus;

public final class Person {
    private final String name;
    private final String university;
    private final String sc;
    private final long docsCount;
    private final int hIndex;

    public Person(String name, String university, String sc, long docsCount, int hIndex) {
        this.name = name;
        this.university = university;
        this.sc = sc;
        this.docsCount = docsCount;
        this.hIndex = hIndex;
    }

    public String getName() {
        return name;
    }

    public String getUniversity() {
        return university;
    }

    public String getSc() {
        return sc;
    }

    public long getDocsCount() {
        return docsCount;
    }

    public int gethIndex() {
        return hIndex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
