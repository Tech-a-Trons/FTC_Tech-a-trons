package IntoTheDeep.SammyStuff.S_Tests.TestAuton.VIsiontesting;

import android.util.Size;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
@Autonomous(name = "CVScratchTest")
public class Apriltag2ndtut extends LinearOpMode {
    double cx = 640;
    double cy = 360;
    double fx =959.646;
    double fy = 959.646;
    MultipleTelemetry telemetry = new MultipleTelemetry( FtcDashboard.getInstance().getTelemetry());
    @Override
    public void runOpMode() throws InterruptedException {


        AprilTagProcessor tagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setLensIntrinsics(fx,fy,cx,cy)
                .build();
        VisionPortal Vportal = new VisionPortal.Builder()
                .addProcessor(tagProcessor)
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(1280, 720))
                .build();


        waitForStart();
        telemetry.addLine("Started");
        while (!isStopRequested() && opModeIsActive()) {
            if (tagProcessor.getDetections().size() > 0) {
                AprilTagDetection tag = tagProcessor.getDetections().get(0);

                double myTagPoseX = tag.ftcPose.x;
                double myTagPoseY = tag.ftcPose.y;
                double myTagPoseZ = tag.ftcPose.z;
                double myTagPosePitch = tag.ftcPose.pitch;
                double myTagPoseRoll = tag.ftcPose.roll;
                double myTagPoseYaw = tag.ftcPose.yaw;
                telemetry.addData("Apriltag detected:", tag.id);
                telemetry.addData("x", myTagPoseX);
                telemetry.addData("y", myTagPoseY);
                telemetry.addData("z", myTagPoseZ);
                telemetry.addData("yaw", myTagPoseYaw);
                telemetry.addData("roll", myTagPoseRoll);
                telemetry.addData("pitch", myTagPosePitch);


                telemetry.update();
            }
        }
    }
}

