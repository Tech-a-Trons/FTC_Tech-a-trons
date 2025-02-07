package IntoTheDeep.SammyStuff.S_Tests.TestAuton.VIsiontesting.colorDetection;

import android.util.Size;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.ImageRegion;
import org.firstinspires.ftc.vision.opencv.PredominantColorProcessor;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvWebcam;
@Autonomous(name = "ColorDetectionFTCSDK")
public class FTC_SDK_VisionTest extends LinearOpMode {



    MultipleTelemetry telemetry = new MultipleTelemetry(FtcDashboard.getInstance().getTelemetry());

    @Override
    public void runOpMode() throws InterruptedException {
        PredominantColorProcessor colorSensor = new PredominantColorProcessor.Builder()
                 .setRoi(ImageRegion.asUnityCenterCoordinates(-0.1, 0.1, 0.1, -0.1))
               // .setRoi(ImageRegion.entireFrame())
                .setSwatches(
                        PredominantColorProcessor.Swatch.RED,
                        PredominantColorProcessor.Swatch.BLUE,
                        PredominantColorProcessor.Swatch.YELLOW
                        // PredominantColorProcessor.Swatch.BLACK,
                        // PredominantColorProcessor.Swatch.WHITE
                )
                .build();


        VisionPortal portal = new VisionPortal.Builder()
                .addProcessor(colorSensor)
                .setCameraResolution(new Size(320, 240))
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .build();

        telemetry.setMsTransmissionInterval(50);

        while (opModeIsActive()||opModeInInit()){
            PredominantColorProcessor.Result Color = colorSensor.getAnalysis();
            telemetry.addData("Color Detected",Color.closestSwatch);




            telemetry.update();

        }
    }
}
