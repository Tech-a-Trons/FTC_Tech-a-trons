package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@Autonomous(name = "ETAT_Autonomous_V2", group = "ETAT")
//@Disabled
public class ETAT_Autonomous extends LinearOpMode {
    // get an instance of the "ETAT_functions" class.
    private ETAT_Functions ETAT_Robot_functions = new ETAT_Functions(this);

    //Hardware Map Names for drive motors and odometry wheels.
    String frName = "fr", brName = "br", flName = "fl", blName = "bl";
    String FrontLeftEncoderName = frName, FrontRightEncoderName = flName, BackMiddleEncoderName = blName;
    String IMUname = "imu";

    @Override
    public void runOpMode() throws InterruptedException {
        //Initialize Telemetry.
        ETAT_Robot_functions.TelemetryInit(250); // Default refresh rate is 250ms

        //Robot Hardware Mapping.
        ETAT_Robot_functions.myRobot_HardwareMap(frName, brName, flName, blName,
                FrontLeftEncoderName, FrontRightEncoderName, BackMiddleEncoderName,
                IMUname);
        // Robot Hardware Configuration
        ETAT_Robot_functions.myRobot_HardwareConfig();

        ETAT_Robot_functions.TelemetryShowHeading("Initialization & Configuration : DONE");
        ETAT_Robot_functions.TelemetryShowHeading("> Wait for Start ......");

        waitForStart();


    }
}