package multipleModels;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.RDFDataMgr;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        String inputFileName = "src/main/java/multipleModels/vc-db-2.rdf";
        String inputFileName2 = "src/main/java/multipleModels/vc-db-3.rdf";

        Model model1 = ModelFactory.createDefaultModel();
        Model model2   = ModelFactory.createDefaultModel();

        InputStream in = RDFDataMgr.open(inputFileName);
        InputStream in2 = RDFDataMgr.open(inputFileName2);

        model1.read(in,"");
        model2.read(in2,"");
        // merge the Models
        Model model = model1.union(model2);
        model.write(System.out, "RDF/XML-ABBREV");

    }
}
