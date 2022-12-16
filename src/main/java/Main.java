import org.apache.jena.base.Sys;
import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.vocabulary.RDFS;
import org.apache.jena.vocabulary.VCARD;

import static org.apache.jena.vocabulary.OWLResults.system;

public class Main   {
    public static void main(String[] args) {
        String personURI = "http://somewhere/JohnSmith";
        String given = "John";
        String family = "Smith";
        String fullName = given+" "+family;


                Model model = ModelFactory.createDefaultModel();
        Resource johnSmith =

                model.createResource(personURI)
                        .addProperty(VCARD.FN, fullName)
                        .addProperty(VCARD.N,

                                model.createResource()
                                        .addProperty(VCARD.Given,given)
                                        .addProperty(VCARD.Family,family));



        Resource r = model.createResource();
        r.addProperty(RDFS.label, model.createLiteral("chat", "en"))
                .addProperty(RDFS.label, model.createLiteral("chat", "fr"))
                .addProperty(RDFS.label, model.createLiteral("<em>chat</em>", true));

        model.write(System.out);


//        StmtIterator iter  = model.listStatements();
//        while (iter.hasNext()) {
//            Statement stmt      = iter.nextStatement();  // get next statement
//            Resource  subject   = stmt.getSubject();     // get the subject*
//            Property  predicate = stmt.getPredicate();   // get the predicate*
//            RDFNode   object    = stmt.getObject();      // get the object*
//
//            System.out.print(subject.toString());
//            System.out.print(" " + predicate.toString() + " ");
//            if (object instanceof Resource) {
//                System.out.print(object.toString());
//            } else {
//                // object is a literal
//                System.out.print(" \"" + object.toString() + "\"");
//            }
//
//            System.out.println(" .");
//        }


//        RDFDataMgr.write(System.out, model, Lang.RDFXML);


//        RDFDataMgr.write(System.out, model, Lang.NTRIPLES);

    }
}
