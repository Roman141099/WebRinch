package beans;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.util.List;
@Named("test")
@SessionScoped
public class TestBean implements Serializable {
    private int num;
    private List<Name> go;
//    private @Inject Conversation conversation;
    private SelectItem[] item = {new SelectItem("Hi", "yee"), new SelectItem("Bye", "Noo")};
    private List<String> selected;
    public SelectItem[] getItem() {
        return item;
    }
    List<Name> table = List.of(new Name("ewqw", "asdas"), new Name("dsddd", "sss"));

    public List<Name> getTable() {
        return table;
    }

    public void setTable(List<Name> table) {
        this.table = table;
    }

    public List<String> getSelected() {
        return selected;
    }

    public void setSelected(List<String> selected) {
        this.selected = selected;
        System.out.println("GETTER " + selected);
    }

    public void setItem(SelectItem[] item) {
        this.item = item;

    }

    public List<Name> getGo() {
        return go;
    }

    public void setGo(List<Name> go) {
        this.go = go;
    }

//    public int getNum() {
//        if(num == 0)conversation.begin();
//        if(num == 10)conversation.end();
//        return num++;
//    }
}