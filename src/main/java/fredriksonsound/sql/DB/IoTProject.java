package IV1351.DB;

import sun.jvm.hotspot.types.CIntegerField;

import java.util.ArrayList;
import java.util.Arrays;

public class iotProject {

    /**
     * All the tables complete with attributes and PK, FK constraints
     * @return list of tables
     */
    public static ArrayList<Table> tables() {
        Table location = new Table("location")
                .addColumn(new Column("name","varchar(128)"))
                .addColumn(new Column("id", true, true, "integer").setAutoNumber());

        Table tracker = new Table("rfid_tracker")
                .addColumn(new Column("id", true, true, "integer"))
                .addColumn(new Column("location", false, false,
                        new ForeignKey(location, location.getColumn("id")), "integer"));

        Table receiver = new Table("rfid_receiver")
                .addColumn(new Column("id", true, true, "integer"))
                .addColumn(new Column("location", false, false,
                new ForeignKey(location, location.getColumn("id")), "integer"));

        Table interest = new Table("interest")
                .addColumn(new Column("name", true, "varchar(128)"))
                .addColumn(new Column("id", true, true, "integer").setAutoNumber());

        Table trackerInterest = new Table("tracker_interest")
                .addColumn(new Column("interest", false, true,
                        new ForeignKey(interest, interest.getColumn("id")),"integer"))
                .addColumn(new Column("tracker", false, true,
                        new ForeignKey(tracker, interest.getColumn("id")),"integer"))
                .addColumn(new Column("weight", "float"));

        Table display = new Table("display")
                .addColumn(new Column("id", true, true, "integer").setAutoNumber())
                .addColumn(new Column("location", false, true,
                        new ForeignKey(location, location.getColumn("id")), "integer"));

        Table advertisementVideo = new Table("advertisement_video")
                .addColumn(new Column("id", true, true, "integer").setAutoNumber())
                .addColumn(new Column("interest", false, false,
                        new ForeignKey(interest, interest.getColumn("id")), "integer"))
                .addColumn(new Column("length_sec", false, false, "integer"))
                .addColumn(new Column("url", false, false, "varchar(255)"));

        Table playedAdvert = new Table("played_video")
            .addColumn(new Column("id", true, true, "integer").setAutoNumber())
            .addColumn(new Column("video", false, false,
                new ForeignKey(advertisementVideo, advertisementVideo.getColumn("id")), "integer"))
            .addColumn(new Column("time_epoch", false, false, "integer"));

        Table agency = new Table("agency")
                .addColumn(new Column("orgnr", true, true, "string"))
                .addColumn(new Column("name", true, false, "string"));


        Table users = new Table("users")
                .addColumn(new Column("username", true, true, "string"))
                .addColumn(new Column("email", true, false, "string"))
                .addColumn(new Column("agency", false, false, new ForeignKey(agency, agency.getColumn("orgnr")), "string"))
                .addColumn(new Column("pass_hash", false, false, "string"));

        Table orders = new Table("orders")
                .addColumn(new Column("id", true, true, "integer").setAutoNumber())
                .addColumn(new Column("credits", false, false, "integer"))
                .addColumn(new Column("user", false, false, new ForeignKey(users, users.getColumn("username")), "string"));

        Table advertisementOrder = new Table("advertisement_order")
                .addColumn(new Column("video", false, true,
                        new ForeignKey(advertisementVideo, advertisementVideo.getColumn("id")), "integer"))
                .addColumn(new Column("orders", false, true, new ForeignKey(orders, orders.getColumn("id")), "integer"))
                .addColumn(new Column("start_time_epoch", false, false, null, "integer"))
                .addColumn(new Column("end_time_epoch", false, false, null, "integer"));



        ArrayList<Table> allTables = new ArrayList<>(Arrays.asList(location, tracker, receiver, interest, trackerInterest, display,
                advertisementVideo, playedAdvert, agency, users, orders, advertisementOrder));
        //System.out.println(hissBestiktningsFöretag.getPopulationStatement());
        //System.exit(0);
        return allTables;
    }
}