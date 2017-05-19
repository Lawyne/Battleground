package greycat.samples.Battleground;




import greycat.*;
import static greycat.Tasks.*;
import static greycat.internal.task.CoreActions.*;


public class BattlegroundOntology {

    public static void main(String[] args) {

        //Create a minimal graph with the default configuration
        Graph g = new GraphBuilder().build();

        //Connect the graph
        g.connect((Boolean isConnected) -> {
            //Display that the graph database is connected!
            System.out.println("Connected : " + isConnected);

            //TRYING HERE !!!
            newTask()
                    .loop("1","10",
                            newTask()
                                .then(createNode())
                                .then(setAttribute("name",Type.STRING,"node_{{i}}"))
                                .then(setAttribute("type",Type.STRING,"Tank"))
                                    ///Trying to math i
                                .then(setAttribute("power",Type.DOUBLE,"{{i}}"))
                                .then(travelInTime("0"))
                                .then(println("{{result}}")))

                    .execute(g,null);

            newTask()
                    .loop("1","14",
                            newTask()
                                    .then(createNode())
                                    .then(setAttribute("name",Type.STRING,"node_{{i}}"))
                                    .then(setAttribute("type",Type.STRING,"Target"))
                                    .then(setAttribute("resilience",Type.DOUBLE,"{{i}}"))
                                    .then(travelInTime("0"))
                                    .then(println("{{result}}")))

                    .execute(g,null);

            /*
            loop("0", "3",
                    .newNode()
                            .setProperty("name", Type.STRING, "node_{{i}}")
                            .print("{{result}}")
            ).execute(g,null);
            /////


            Node sensor0 = g.newNode(0, 0); //create a new node for world 0 and time 0
            sensor0.set("sensorId", Type.INT, 12); //set the id attribute as an integer
            sensor0.set("name",Type.STRING, "sensor0"); //set the name attribute as a string

            //Display the first node we created
            System.out.println(sensor0); //print {"world":0,"time":0,"id":1,"sensorId":12,"name":"sensor0"}

            Node room0 = g.newNode(0, 0); //create new node for world 0 and time 0
            room0.set("name",Type.STRING, "room0"); //set the name attribute
            room0.addToRelation("sensors", sensor0); //add the sensor0 to the relation sensors of room0

            //Let's display the room0 node to see what's inside
            System.out.println(room0); //print {"world":0,"time":0,"id":2,"name":"room0","sensors":[1]}

            //iterate over the saved sensors relation from room0
            room0.relation("sensors", (Node[] sensors) -> {
                System.out.println("Relationship Sensors:");
                for (Node sensor : sensors) {
                    System.out.println("\t" + sensor.toString());
                }

                //Disconnect the database
                g.disconnect(result -> {
                    System.out.println("GoodBye!");
                });

            }); */


            g.disconnect(result -> {
                System.out.println("GoodBye!");
            });
        });
    }

}
