package com.jrgs.mongodbexample;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.mongodb.client.model.Sorts.ascending;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
        mongoLogger.setLevel(Level.SEVERE);
        MongoCredential credenciales = MongoCredential.createCredential(
                "pruebaDBAdmin", "Prueba", "prueba".toCharArray()
        );

        try (MongoClient myClient = new MongoClient(
                    new ServerAddress("192.168.224.125"),
                    credenciales,
                    MongoClientOptions.builder().build())) {

//            agregarEmpleado(myClient);
            buscarEmpleados(myClient);
//            actualizarDepartamentos2(myClient);
//            borrarEmpleados(myClient);

        } catch (MongoCommandException e) {
            System.err.println(e.getErrorMessage());
        }
    }

    public static void borrarEmpleados(MongoClient myClient) {
//         MongoCollection<Document> empleados = myClient.getDatabase("Prueba").getCollection("Empleados");
        MongoCollection<Document> departamentos = myClient.getDatabase("Prueba").getCollection("Departamentos");
        List<Integer> codigos = new ArrayList<>();
        codigos.add(7007);
        codigos.add(7887);
        departamentos.updateMany(new BasicDBObject(), Updates.unset("listaEmpleados2"));

 //       empleados.deleteMany(Filters.in("empno", codigos));
    }

    public static void actualizarDepartamentos(MongoClient myClient) throws MongoCommandException {
        MongoCollection<Document> departamentos = myClient.getDatabase("Prueba").getCollection("Departamentos");
        MongoCollection<Document> empleados = myClient.getDatabase("Prueba").getCollection("Empleados");

        for ( Document empleado : empleados.find() ) {
                DBObject dbObjectEmpleado = BasicDBObject.parse(empleado.toJson());
                departamentos.updateOne(Filters.eq("deptno", Integer.parseInt(dbObjectEmpleado.get("departamento").toString())),
                                        Updates.addToSet("listaEmpleados", Integer.parseInt(dbObjectEmpleado.get("empno").toString())));
        }
     }

     public static void actualizarDepartamentos2(MongoClient myClient) throws MongoCommandException {
        MongoCollection<Document> departamentos = myClient.getDatabase("Prueba").getCollection("Departamentos");
        //departamentos.updateMany( new BasicDBObject(), Updates.set("País", "España"));
         List<String> provincias = new ArrayList<>();
         provincias.add("ALICANTE");
         provincias.add("VALENCIA");
         departamentos.updateMany( Filters.in("provincia", provincias ),
                                   Updates.set("Comunidad", "COMUNIDAD VALENCIANA"));
     }

    public static void agregarEmpleado(MongoClient myClient) throws MongoCommandException {
        MongoCollection<Document> empleados = myClient.getDatabase("Prueba").getCollection("Empleados");
//        Document empleado = new Document();
//        empleado.append("empno", 7007);
//        empleado.append("nombre", "DÍEZ");
//        empleado.append("puesto","PROGRAMADOR");
//        empleado.append("departamento", 20);
//
//        empleados.insertOne(empleado);
        empleados.insertOne( new Document().append("empno", 7887).
                                            append("nombre", "DÍEZ").
                                            append("puesto","PROGRAMADOR").
                                            append("departamento", 80) );
    }

    public static void buscarEmpleados(MongoClient myClient) throws MongoCommandException {
      MongoCollection<Document> empleados = myClient.getDatabase("Prueba").getCollection("Empleados");

//                    System.out.println("Número de empleados en la colección \'"
//                    + empleados.getNamespace() + "\': "
//                    + empleados.countDocuments());
//
        MongoCursor<Document> myCursor = empleados.find().iterator();
        while ( myCursor.hasNext() ) {
            DBObject empleado = BasicDBObject.parse(myCursor.next().toJson());
            System.out.print("Número: " + empleado.get("empno").toString());
            System.out.println(" Nombre: " + empleado.get("nombre").toString());
        }

                //            BasicDBObject filtro = BasicDBObject.parse("{departamento:20}");
//        BasicDBObject filtro = BasicDBObject.parse("{$and:[{departamento:{$gt:20}},{puesto:'VENDEDOR'}]}");
//        for (Document miDocumento : empleados.find(filtro).sort(ascending("nombre"))) {
//            DBObject empleado = BasicDBObject.parse(miDocumento.toJson());
//            System.out.print("Número: " + empleado.get("empno").toString());
//            System.out.println(" Nombre: " + empleado.get("nombre").toString());

        }
    }
