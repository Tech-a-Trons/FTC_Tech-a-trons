package IntoTheDeep.SammyStuff.GameCode.auton;

import android.util.Size;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.SortOrder;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.opencv.ColorBlobLocatorProcessor;
import org.firstinspires.ftc.vision.opencv.ColorRange;
import org.firstinspires.ftc.vision.opencv.ColorSpace;
import org.firstinspires.ftc.vision.opencv.ImageRegion;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;

import java.util.List;
@TeleOp(name = "StrafeIfNotDetected")
public class StrafeIfNotDetected extends LinearOpMode {
    DcMotor fl, fr, bl, br;
    Servo claw;

    double y;
    double x;
    double m;
    double b;
    double mx;

    boolean clawclosed = false;


    @Override
    public void runOpMode() throws InterruptedException {
        ColorBlobLocatorProcessor processor = new ColorBlobLocatorProcessor.Builder()
                .setTargetColorRange(ColorRange.RED)
                .setContourMode(ColorBlobLocatorProcessor.ContourMode.EXTERNAL_ONLY)
                .setRoi(ImageRegion.asUnityCenterCoordinates(-0.5, 0.5, 0.5, -0.5))
                .setDrawContours(true)
                .setErodeSize(5)
                .setDilateSize(5)
                .setBlurSize(5)
                .build();

        VisionPortal portal = new VisionPortal.Builder()
                .addProcessor(processor)
                .setCameraResolution(new Size(1280, 720))
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .build();


        fl = hardwareMap.get(DcMotor.class, "fl");
        fr = hardwareMap.get(DcMotor.class, "fr");
        bl = hardwareMap.get(DcMotor.class, "bl");
        br = hardwareMap.get(DcMotor.class, "br");

        fl.setDirection(DcMotorSimple.Direction.REVERSE);
        bl.setDirection(DcMotorSimple.Direction.REVERSE);

        claw = hardwareMap.get(Servo.class, "specimenclaw");


        waitForStart();

        List<ColorBlobLocatorProcessor.Blob> blobs = processor.getBlobs();

        ColorBlobLocatorProcessor.Util.sortByArea(SortOrder.DESCENDING, blobs);
        ColorBlobLocatorProcessor.Util.filterByArea(50, 20000, blobs);
        ColorBlobLocatorProcessor.Blob largestContour;

        if (blobs.size() == 0) {
            largestContour = null;
        } else {
            largestContour = blobs.get(0);

        }

            while (largestContour == null) {


                fl.setPower(-0.4);
                fr.setPower(0.4);
                bl.setPower(0.4);
                br.setPower(-0.4);
                if (blobs.size() == 0) {
                    largestContour = null;
                } else {

                    largestContour = blobs.get(0);

                    break;

                }

            }
        if (blobs.size() == 0) {
            largestContour = null;
        } else {
            largestContour = blobs.get(0);

        }

            while (largestContour != null){
                RotatedRect boxFit = largestContour.getBoxFit();
                if (blobs.size() == 0) {
                    largestContour = null;
                    break;
                } else {
                    largestContour = blobs.get(0);
                    boxFit = largestContour.getBoxFit();
                }

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
                    b = 0;
                    mx = m * x;
                    y = mx + b;

                    if(y>5){
                        fl.setPower(0.4);
                        fr.setPower(0.4);
                        bl.setPower(0.4);
                        br.setPower(0.4);
                    }else{
                        fl.setPower(0.4);
                        fr.setPower(0.4);
                        bl.setPower(0.4);
                        br.setPower(0.4);
                        sleep(800);

                    }
            }
        }
    }

