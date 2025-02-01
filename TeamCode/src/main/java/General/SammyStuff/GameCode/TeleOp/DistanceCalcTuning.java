package General.SammyStuff.GameCode.TeleOp;

import android.util.Size;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.SortOrder;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ImageRegion;
import org.opencv.core.RotatedRect;

import java.util.List;

@TeleOp(name = "DistanceCalcTests")
public class DistanceCalcTuning  extends LinearOpMode {


    double y;
    double x;
    double m;
    double b;
    double mx;


    @Override
    public void runOpMode() throws InterruptedException {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        ColorBlobLocatorProcessor colorLocator = new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(ColorRange.RED)         // use a predefined color match
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)    // exclude blobs inside blobs
                //.setRoi(ImageRegion.asUnityCenterCoordinates(-0.5, 0.5, 0.5, -0.5))  // search central 1/4 of camera view
                .setRoi(ImageRegion.entireFrame())
                .setDrawContours(true)                        // Show contours on the Stream Preview
                .setErodeSize(5)
                .setDilateSize(5)
                .setBlurSize(5)                               // Smooth the transitions between different colors in image
                .build();


        VisionPortal portal = new VisionPortal.Builder()
                .addProcessor(colorLocator)
                .setCameraResolution(new Size(1280, 720))
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .build();

        telemetry.setMsTransmissionInterval(50);   // Speed up telemetry updates, Just use for debugging.
        telemetry.setDisplayFormat(Telemetry.DisplayFormat.MONOSPACE);




        while (opModeIsActive() || opModeInInit()) {


            List<ColorBlobLocatorProcessor.Blob> blobs = colorLocator.getBlobs();

            ColorBlobLocatorProcessor.Util.sortByArea(SortOrder.DESCENDING, blobs);
            ColorBlobLocatorProcessor.Util.filterByArea(50, 20000, blobs);
            ColorBlobLocatorProcessor.Blob largestBlob;
            if (blobs.size() == 0) {
                largestBlob = null;
            } else {
                largestBlob = blobs.get(0);
                RotatedRect boxFit = largestBlob.getBoxFit();

                org.opencv.core.Size myBoxFitSize;
                myBoxFitSize = boxFit.size;

                double width;
                double height;
                if (boxFit.angle == 90) {
                    height = myBoxFitSize.width;
                    width = myBoxFitSize.height;
                } else {
                    width = myBoxFitSize.width;
                    height = myBoxFitSize.height;
                }
                x = height;
                m = 0.19;
                b= 0;
                mx = m*x;
                y = mx+b;
                telemetry.addData("distance",y);
                telemetry.addData("width", width);
                telemetry.addData("height", height);
                telemetry.addData("angle", boxFit.angle);


                telemetry.update();


            }
        }
    }
}
