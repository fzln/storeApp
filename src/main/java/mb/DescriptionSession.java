package mb;

import entity.Description;
import org.springframework.web.context.annotation.SessionScope;

import javax.inject.Named;

@Named
@SessionScope
public class DescriptionSession {

    private Description description;

    public Description getDescription() {
        return description;
    }

    public void setDescription(Description description) {
        this.description = description;
    }

    public void resetDescription() { description = null; }

    public void newDescription() {
        description = new Description();
    }
}
