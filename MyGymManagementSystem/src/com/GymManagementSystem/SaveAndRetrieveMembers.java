package com.GymManagementSystem;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class SaveAndRetrieveMembers {

    // Establishing the connection
    private static MongoClient mongoClient = new MongoClient("localhost", 27017);

    // Connecting to the mongo database
    private static MongoDatabase mongoDatabase = mongoClient.getDatabase("GymManagementSystem");


    public static void save(List<DefaultMember> memberList){

        String collection;

        MongoCollection existingCollection = mongoDatabase.getCollection("DefaultMember");
        existingCollection.drop();

        existingCollection = mongoDatabase.getCollection("StudentMember");
        existingCollection.drop();

        existingCollection = mongoDatabase.getCollection("Over60Member");
        existingCollection.drop();

        for (DefaultMember member: memberList) {
            if (member instanceof Over60Member){
                collection = "Over60Member";
            }else if(member instanceof StudentMember){
                collection = "StudentMember";
            }else{
                collection = "DefaultMember";
            }

            // Connecting to the collection in the database
            MongoCollection mongoCollection = mongoDatabase.getCollection(collection);

            Document document = new Document();

            if (collection.equals("DefaultMember")){
               document = insertDefaultDocument(member, document);
            }else if(collection.equals("StudentMember")){
                document = insertDefaultDocument(member, document);
                document.append("School",((StudentMember) member).getSchoolName());
                document.append("Extra physical activities",((StudentMember) member).getIsEngagingPhysicalActivitiesInSchool());

            }else{
                document = insertDefaultDocument(member, document);
                document.append("Age",((Over60Member) member).getAge());
                document.append("Using Medications",((Over60Member) member).getMedications());
            }

            mongoCollection.insertOne(document);
        }
    }

    // As these fields are common to all classes it has been taken separately and append to collections separately by calling the method.
    private static Document insertDefaultDocument(DefaultMember member, Document document){
        document.append("Membership No",member.getMembershipNumber());
        document.append("Member name",member.getMemberName());
        document.append("Membership start date",member.getMembershipStartDate());
        document.append("Contact No",member.getContactNo());
        document.append("Height",member.getHeight());
        document.append("Weight",member.getWeight());

        return  document;
    }

    //retrieve all saved data from the database and assign to memberList whenever the program starts.
    public static ArrayList<DefaultMember> retrieve(){
        ArrayList<DefaultMember> retrievedMemberList = new ArrayList<DefaultMember>();

        MongoCollection existingCollection = mongoDatabase.getCollection("DefaultMember");

        MongoCursor<Document> cursor = existingCollection.find().iterator();

        while (cursor.hasNext()){
            Document doc = cursor.next();

            List values = new ArrayList(doc.values());

            DefaultMember defaultMember = new DefaultMember(values.get(1).toString(), values.get(2).toString(), values.get(3).toString(), values.get(4).toString(), Double.parseDouble(values.get(5).toString()), Double.parseDouble(values.get(6).toString()));
            retrievedMemberList.add(defaultMember);
        }

        existingCollection = mongoDatabase.getCollection("StudentMember");

        cursor = existingCollection.find().iterator();

        while (cursor.hasNext()){
            Document doc = cursor.next();

            List values = new ArrayList(doc.values());

            DefaultMember studentMember = new StudentMember(values.get(1).toString(), values.get(2).toString(), values.get(3).toString(), values.get(4).toString(), Double.parseDouble(values.get(5).toString()), Double.parseDouble(values.get(6).toString()), values.get(7).toString(), values.get(8).toString());
            retrievedMemberList.add(studentMember);
        }

        existingCollection = mongoDatabase.getCollection("Over60Member");

        cursor = existingCollection.find().iterator();

        while (cursor.hasNext()){
            Document doc = cursor.next();

            List values = new ArrayList(doc.values());

            DefaultMember over60MemberMember = new Over60Member(values.get(1).toString(), values.get(2).toString(), values.get(3).toString(), values.get(4).toString(), Double.parseDouble(values.get(5).toString()), Double.parseDouble(values.get(6).toString()), Integer.parseInt(values.get(7).toString()), values.get(8).toString());
            retrievedMemberList.add(over60MemberMember);
        }

        return retrievedMemberList;
    }
}
