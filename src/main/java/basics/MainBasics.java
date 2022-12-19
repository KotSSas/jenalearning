package basics;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.vocabulary.VCARD;

import java.io.FileWriter;
import java.io.IOException;

public class MainBasics {
    private String personURI = "http://somewhere/JohnSmith";
    private String fullName = "John Smith";
    private String givenName = "John";
    private String familyName = "Smith";
    Model model;


    public Model getModel() {
        return this.model;
    }

    public MainBasics() {
        this.model = ModelFactory.createDefaultModel();

        Resource johnSmith = model.getResource(personURI)
                .addProperty(VCARD.FN,fullName)
                .addProperty(VCARD.N, model.createResource())
                .addProperty(VCARD.Given,givenName)
                .addProperty(VCARD.Family,familyName);
    }

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
    }
}
