import io.kubeless.Query;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

public class QueryTest {

    @Test
    public void testQryAllTypes(){
        Query query = new Query();
        List<Map> list = query.qryAllTypes();
        System.out.println(list.get(0));
        Assert.assertNotNull(list.get(0));
    }
}
