package server.endpoints;


import com.google.gson.Gson;
import server.controllers.StudentController;
import server.models.Student;
import server.providers.StudentTable;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/students")
public class StudentEndpoint {

    //Opretter arraylist med students.
    StudentTable studentTable = new StudentTable();

    @GET
    public Response getStudents() {

        //Returnerer Gson til Json.
        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson(new String[]{"student1", "student2"})) //skal ændres til connection med databasen når config filen er lavet.
                .build();
        //debugger kan stoppe program og eksekvere det og se hvad der sker.
    }

    @GET
    @Path("{idStudent}")
    public Response getStudentById(@PathParam("idStudent") String idStudent) {

        StudentTable studentTable = new StudentTable();
        Student foundStudent = null;

        // If else statement tjekker om parametren er tom.
        if (idStudent.isEmpty()) {

            return Response
                    .status(400)
                    .entity("{\"Missing Student ID\":\"true\"}")
                    .build();
        }

        try {
            foundStudent = studentTable.getStudentById(idStudent);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        // If student not found:
        if (!true) {

            return Response
                    .status(400)
                    .entity("{\"Student not found\":\"true\"}")
                    .build();
        }

        //Returnerer Gson til Json.
        return Response
                .status(200)
                .type("application/json")
                .entity(new Gson().toJson(foundStudent)) //".toJson(foundStudent) skal indsættes når config filen kører.
                .build();
    }

     /*@POST
    public Response createStudent(String jsonStudent) {

        //String jsonStudent gemmer alle students.

        Student newStudent = new Gson().fromJson(jsonStudent, Student.class);
        StudentTable.createStudent(newStudent);

        //Returnerer Gson til Json.
        return Response
                .status(200)
                .type("application/json")
                .entity("{\"studentCreated\":\"true\"}")
                .build();
    }
     */
}
