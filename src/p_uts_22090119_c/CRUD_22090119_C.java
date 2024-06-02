/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package p_uts_22090119_c;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.types.ObjectId;
/**
 *
 * @author bass
 */
public class CRUD_22090119_C {
    public static void main(String[] args){
        try {
            MongoDatabase db = koneksi.konekMongoDB();
            
            System.out.println("---------------------------------");
            System.out.println("Daftar Tabel dalam Database");
            MongoIterable<String> tables = db.listCollectionNames();
            
            for (String name : tables){
                System.out.println(name);
            }
            
            System.out.println("---------------------------------");
            MongoCollection<Document> col = db.getCollection("col_22090119_C");
            System.out.println("Menambahkan Data 1 ");
            Document data1 = new Document();
            data1.put("nama", "M Bais Yufan Mardiansah");
            data1.put("nim", "22090119");
            col.insertOne(data1);
            System.out.println("Data 1 Berhasil di tambahkan");
            
            System.out.println("Menambahkan Data 2 ");
            Document data2 = new Document();
            data2.put("nama", "M Bais Yufan Mardiansah");
            data2.put("nim", "22090119");
            data2.put("kelas", "C");
            col.insertOne(data2);
            System.out.println("Data 2 Berhasil di tambahkan");
            
            System.out.println("Menambahkan Data 3");
            Document data3 = new Document();
            data3.put("nama", "M Bais Yufan Mardiansah");
            data3.put("nim", "22090119");
            data3.put("kelas", "C");
            data3.put("alamat", "JL bawal gg 7 Kota Tegal");
            col.insertOne(data3);
            System.out.println("Data 3 Berhasil di tambahkan");
            
            System.out.println("---------------------------------");
            System.out.println("Data yang telah Disimpan");
            MongoCursor<Document> cursor = col.find().iterator();
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
            
            System.out.println("---------------------------------");
            System.out.println("Edit Data 2");
            Document data = new Document();
            data.put("nim", "22090130");
            data.put("kelas", "B");
            Document dataEdit = new Document ("$set", data);
            UpdateResult hasilEdit = col.updateOne(eq("_id",new ObjectId(data2.get("_id").toString())), dataEdit);
            System.out.println("---------------------------------");
            System.out.println("Data 2 Berhasil di edit");
            
            System.out.println("---------------------------------");
            System.out.println("Data yang telah Diupdate");
            cursor = col.find().iterator();
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
            
            col.deleteOne(eq("_id", new ObjectId(data3.get("_id").toString())));
            System.out.println("---------------------------------");
            System.out.println("Data 3 Berhasil dihapus");
            System.out.println("==========================");
            System.out.println("Data setelah Dihapus");
            cursor = col.find().iterator();
            while (cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
