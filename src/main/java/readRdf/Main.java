package readRdf;

import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.vocabulary.VCARD;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static <Property> void main(String[] args) throws IOException {
        String inputFileName = "src/main/java/readRdf/vc-db-1.rdf";
        Model model = ModelFactory.createDefaultModel();
        InputStream in = RDFDataMgr.open(inputFileName);
        if (in==null){
            throw new IllegalArgumentException(
                    "File: " + inputFileName + " not found");
        }
        //read the file
        model.read(in,null);


        //pick all names form file
        ResIterator iter = model.listSubjectsWithProperty(VCARD.FN);
        if (iter.hasNext()) {
            System.out.println("The database contains vcards for:");
            while (iter.hasNext()) {
                System.out.println("  " +
                        iter.nextResource()
                                .getProperty(VCARD.FN)
                                    .getString());
            }
        } else {
            System.out.println("No vcards were found in the database");
        }


        StmtIterator i = model.listStatements(
                new
                        SimpleSelector(null, VCARD.FN, (RDFNode) null) {
                            @Override
                            public boolean selects(Statement s) {
                                return s.getString().endsWith("Smith");
                            }
                        });
        String fileName = "src/main/java/readRdf/output.rdf";
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

        if (i.hasNext()){
            while (i.hasNext()){
                System.out.println(i.nextStatement().getString());

            }
        }else {
            System.out.println("Nothing");
        }


        //sout all
//        model.write(System.out);

    }
}
