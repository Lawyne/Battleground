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

            Node linkedNode = g.newNode(0,0);
            linkedNode.set("type", Type.STRING, "none");
            
            //TRYING HERE !!!
            newTask()
                    .loop("1","10",
                            newTask()
                                    ///Trying to math i
                                   /*.thenDo(new ActionFunction() {
                                        @Override
                                        public void eval(TaskContext taskContext) {
                                            MathExpressionEngine engine = CoreMathExpressionEngine.parse("5*4");
                                            double res = engine.eval(null,null,null);
                                            taskContext.continueTask();
                                        }
                                    })*/
                                    //.then(inject(10))
                                    //.then(defineAsVar("it"))
                                    //.then(print("{{it}}"))
                                    //.inject("{{=4*i}}")
                                    .thenDo(new ActionFunction() {
                                        @Override
                                        public void eval(TaskContext ctx) {
                                            ctx.continueWith(ctx.wrap(ctx.template("{{=10*i}}")).clone());
                                        }
                                    })
                                    //ICI !!!!!!
                                   // .inject(58)
                                   // .setAsVar("integer")
                                   // .executeExpression("10+i[0]")
                                   // .log("{{result}}")
                                    //.then(inject(CoreMathExpressionEngine.parse("4*{{i}}").eval(null,null,null)))
                                    .defineAsVar("res")
                                    //.println("{{res}}")
                                    .inject(linkedNode)
                                    .defineAsVar("x")
                                    .travelInTime("0")
                                    .createNode()
                                    .setAttribute("name",Type.STRING,"linked_node_{{i}}")
                                    .setAttribute("type",Type.STRING,"Weapon")
                                    .setAttribute("power",Type.DOUBLE,"{{res}}")
                                    //.println("{{result}}")
                                    .defineAsVar("y").createNode()
                                    .setAttribute("name",Type.STRING,"linked_node_{{i}}")
                                    .setAttribute("type",Type.STRING,"Weapon")
                                    .setAttribute("power",Type.DOUBLE,"{{res}}")
                                    //.println("{{result}}")
                                    .defineAsVar("y")
                                    .createNode()
                                    .setAttribute("name",Type.STRING,"linked_z_node_{{i}}")
                                    .setAttribute("type",Type.STRING,"Weapon")
                                    .setAttribute("power",Type.DOUBLE,"{{res}}")
                                    //.println("{{result}}")
                                    .defineAsVar("z")
                                    .createNode()
                                    .setAttribute("name",Type.STRING,"node_{{i}}")
                                    .setAttribute("type",Type.STRING,"Tank")
                                    .setAttribute("power",Type.DOUBLE,"{{res}}")
                                    .addVarToRelation("linked_to","x","type")
                                    .addVarToRelation("linked_to","y","type")
                                    .addVarToRelation("linked_to","z","type")
                                    //.println("{{result}}")
                                    .traverse("linked_to","type","Weapon")
                                    .travelInTime("0")
                                    //.println("{{result}}")
                                    .addToGlobalIndex("nodes","y")
                                    .addToGlobalIndex("nodes0","z")
                                    .indexNames()
                                    .println("{{result}}")
                                    .readGlobalIndex("{{result[0]}}")
                                    .println("{{result}}")


                    )
                    .indexNames()
                    .println("")
                    .println("")
                    .println("===================================================")
                    .println("From outside the loop")
                    .println("{{result}}")
                    .travelInTime("0")
                    .readGlobalIndex("{{result[0]}}")
                    .println("{{result}}")
                    .execute(g,null);

            newTask()
                    .indexNames()
                    .println("")
                    .println("")
                    .println("===================================================")
                    .println("From outside the task")
                    .travelInTime("0")
                    .println("{{result}}")
                    .readGlobalIndex("{{result[1]}}")
                    .println("{{result}}")


                    .execute(g,null);

            newTask()
                    .loop("1","14",
                            newTask()
                                    .createNode()
                                    .setAttribute("name",Type.STRING,"node_{{i}}")
                                    .setAttribute("type",Type.STRING,"Target")
                                    .setAttribute("resilience",Type.DOUBLE,"{{i}}")
                                    .addVarToRelation("can_destroy", "1", "1")
                                    .travelInTime("0")
                                    //.println("{{result}}")

                    )

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
