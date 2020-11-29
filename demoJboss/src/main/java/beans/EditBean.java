package beans;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class EditBean implements Serializable {
    @Inject private SendMsgBean smb;

    public SendMsgBean getSmb() {
        return smb;
    }
}
