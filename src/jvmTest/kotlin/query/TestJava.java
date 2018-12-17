package query;

import client.query.helper.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class TestJava {

    @Test
    public void test() {
        FilterBuilder builder = new FilterBuilder();
        Attribute category = new Attribute("category");
        FilterFacet categoryBlue = new FilterFacet(category, "blue");
        Group groupOr = new GroupOr("groupOr");

        builder.add(groupOr, categoryBlue);
        builder.remove(groupOr, categoryBlue);
    }
}
