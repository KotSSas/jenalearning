package bag;

import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.vocabulary.VCARD;

import java.io.InputStream;

public class Main {
    public static void main(String[] args) {
        String inputFileName = "src/main/java/readRdf/vc-db-1.rdf";
        Model model = ModelFactory.createDefaultModel();
        InputStream in = RDFDataMgr.open(inputFileName);
        if (in==null){
            throw new IllegalArgumentException(
                    "File: " + inputFileName + " not found");
        }
        //read the file
        model.read(in,null);


        Bag smiths = model.createBag();

// select all the resources with a VCARD.FN property
// whose value ends with "Smith"
        StmtIterator iter = model.listStatements(
                new SimpleSelector(null, VCARD.FN, (RDFNode) null) {
                    public boolean selects(Statement s) {
                        return s.getString().endsWith("Smith");
                    }
                });
// add the Smith's to the bag
        while (iter.hasNext()) {
            smiths.add(iter.nextStatement().getSubject());
        }

        NodeIterator iter2 = smiths.iterator();
        if (iter2.hasNext()) {
            System.out.println("The bag contains:");
            while (iter2.hasNext()) {
                System.out.println("  " +
                        ((Resource) iter2.next())
                                .getProperty(VCARD.FN)
                                .getString());
            }
        } else {
            System.out.println("The bag is empty");
        }
    }
}
