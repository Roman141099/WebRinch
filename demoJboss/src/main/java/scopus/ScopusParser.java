package scopus;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScopusParser {
    public static void main(String[] args) throws IOException {
        ScopusParser sp = new ScopusParser("C:\\Users\\User\\Documents\\demoJboss\\src\\main\\resources\\urls.properties");
        sp.formURL("humanListUrl", "Kamaz", "2016", "2020", "cp-f").forEach(System.out::println);
    }

    private final Path path;
    private static final Properties urls = new Properties();

    public Path getPath() {
        return path;
    }

    public ScopusParser(String path) {
        this.path = Paths.get(path);
        try {
            urls.load(Files.newBufferedReader(this.path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Person> formURL(String property, String theme,
                                String firstYear, String lastYear,
                                String sortMode) {
        theme = String.join("+", theme.split(" "));
        String[] packagedURL = urls.getProperty(property).split("\\|");
        String curURL = packagedURL[0].concat(sortMode).concat(packagedURL[1]).
                concat(theme).concat(packagedURL[2]).concat(firstYear).
                concat(packagedURL[3]).concat(lastYear).concat(packagedURL[4]).
                concat(theme).concat(packagedURL[5]);
        Document doc = null;
        try {
            doc = Jsoup.connect(curURL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert doc != null;
        Elements searched = doc.select("tr.searchArea");
        return searched.parallelStream().map(searchingPersons).collect(Collectors.toList());
    }
    private final Function<Element, Person> searchingPersons = o -> {
        String name = "";
        String university = "";
        String sc = "";
        long docsCount = 0;
        int hIndex = 0;
        name = o.selectFirst("span.ddmAuthorList").selectFirst("a").text();
        return new Person(name, university, sc, docsCount, hIndex);
    };
}
