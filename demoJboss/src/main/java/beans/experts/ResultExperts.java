package beans.experts;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named("commands")
@SessionScoped
public class ResultExperts implements Serializable {
    private String keyWord;
    private List<Expert> scopus;
    private List<Expert> university;
    private String competition;

    public List<Expert> getScopus() {
        downloadScopus(keyWord);
        return scopus;
    }

    public String getCompetition() {
        return competition;
    }

    public String addComp(String par){
        System.out.println("Cookie : " + par);
        competition = par;
        return "null?faces-redirect=true";
    }

    public void setCompetition(String competition) {
        System.out.println("Setting " + competition);
        this.competition = competition;
    }

    public List<Expert> getUniversity() {
        downloadUniversity(keyWord);
        return university;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
    private void downloadScopus(String keyWord){
        //select from db
        scopus = Arrays.asList(new Expert("sss", "sdsd"), new Expert("wewe", "eee"));
    }
    private void downloadUniversity(String keyWord){
        university = Arrays.asList(new Expert(keyWord, keyWord), new Expert("222", "222"));
    }
}
