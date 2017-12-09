/**
 * @author ${Administrator}
 * @description ${DESCRIPTION}
 * @create 2017-12-09 11:05
 **/
import java.util.List;

import com.jsedom.mongodb.MongoDb;
import org.bson.Document;

import com.mongodb.client.result.UpdateResult;
import org.junit.Before;
import org.junit.Test;

public class MongoTest {

    @Before
    public void before(){
        MongoDb.connect("test", "darren", "120.26.61.237", 27017);
    }

    @Test
    public void testInsert(){
        Document document = new Document();
        document.append("name", "wang").append("gender", "female");
        MongoDb.insert(document);
    }

    @Test
    public void testFindAll(){
        List<Document> results = MongoDb.findAll();
        for(Document doc : results){
            System.out.println(doc.toJson());
        }
    }
}
