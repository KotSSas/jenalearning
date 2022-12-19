package basics;

import org.apache.commons.compress.harmony.pack200.Pack200ClassReader;
import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;

import java.io.FileWriter;
import java.io.IOException;

public class Iterator {
    public static void main(String[] args) throws IOException {
        Model model = new MainBasics().getModel();
        String fileName = "src/main/java/basics/output.rdf";

        FileWriter out = new FileWriter( fileName );
        try {
            model.write( out, "RDF/XML-ABBREV" );
        }
        finally {
            try {
                out.close();
            }
            catch (IOException closeException) {
                // ignore
            }
        }

        StmtIterator iter = model.listStatements();

        while (iter.hasNext()) {
            Statement statement = iter.nextStatement();
            Resource subject = statement.getSubject();
            Resource predicate = statement.getPredicate();
            RDFNode object = statement.getObject();


            System.out.println(subject.toString());
            System.out.println(predicate.toString());
            if (object instanceof Resource){
                System.out.println(object.toString());
            }else{
                System.out.println("\""+object.toString()+"\"");
            }
            System.out.println("----------------");
        }
    }
}
