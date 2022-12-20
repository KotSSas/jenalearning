package basics;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class Read {
    private static String file = "src/main/java/basics/data/example.rdf";
    private Model model;

    public Model getModel() {
        return  this.model;
    }
    public Read(){
        this.model = ModelFactory.createDefaultModel();
        InputStream in = FileManager.get().open(file);
        if (in == null){
            throw new IllegalArgumentException("Error. File NOT found!");
        }
        model.read(in,"");

    }
    public static void main(String[] args) throws IOException {
        Model model = new Read().getModel();

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
